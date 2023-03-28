package com.byusluer.movierest.service.movie;

import com.byusluer.movierest.entity.User;
import com.byusluer.movierest.entity.UserProfile;
import com.byusluer.movierest.exception.MovieApiException;
import com.byusluer.movierest.exception.ResourceNotFoundException;
import com.byusluer.movierest.model.CategoryDto;
import com.byusluer.movierest.model.dto.request.TVShowRequestDto;
import com.byusluer.movierest.model.dto.response.TvShowResult;
import com.byusluer.movierest.repository.UserRepository;
import com.byusluer.movierest.scheduled.ScheduledTask;
import com.byusluer.movierest.service.apiclient.TmdbClient;
import com.byusluer.movierest.service.auth.JwtTokenService;
import com.byusluer.movierest.service.notification.NotificationService;
import com.byusluer.movierest.service.tv.TVShowService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TmdbTvShowService implements TVShowService {

    private final TmdbClient tmdbClient;
    private final NotificationService notificationService;
    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final ScheduledTask scheduledTask;

    @Override
    public TvShowResult searchTVShows(TVShowRequestDto tvShowRequest) {

        Map<String, Object> queryParams = setRequestParams(tvShowRequest);
        TvShowResult tvShowResult = tmdbClient.discoverTvShows(queryParams);
        if (tvShowResult.getResults().size() == 0) {
            throw new MovieApiException("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //todo since i am using aws free-tier sending all mails to me so, getUserProfileFromToken
        UserProfile userProfile = new UserProfile();
        notificationService.sendNotification(userProfile, "New TV Show Releases Test");


        scheduledTask.checkForNewReleases();
        return tvShowResult;
    }


    private Map<String, Object> setRequestParams(TVShowRequestDto tvShowRequest) {
        Map<String, Object> queryParams = new HashMap<>();
        List<Integer> genreIds = tvShowRequest.getCategories().stream().map(CategoryDto::getGenreId).toList();
        String genreIdsAsString = genreIds.stream().map(Object::toString).collect(Collectors.joining(","));

        queryParams.put("with_genres", String.join(",", genreIdsAsString));
        queryParams.put("vote_average.gte", tvShowRequest.getMinRating());
        queryParams.put("first_air_date.gte", tvShowRequest.getStartDate().toString());
        queryParams.put("first_air_date.lte", tvShowRequest.getEndDate().toString());
        queryParams.put("vote_count.gte", 1000);
        queryParams.put("sort_by", "first_air_date.desc");
        return queryParams;
    }

    public User getUserProfileFromJwt(HttpServletRequest request) {
        String token = jwtTokenService.getTokenFromRequest(request);
        String username = jwtTokenService.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String email = userDetails.getUsername();

        //userProfile.findByEmail(email);
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user", "email", "email"));
    }


}
