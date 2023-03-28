## Cinephill



     Cinephill is a backend application built using Spring Boot 6 and Spring Security 3 
     that provides REST services for movie/tv show search, user authentication, and 
     notifications. The application interacts with external services and APIs, including AWS  SSM, AWS KMS, SES/SNS, and The Movie Database(TMDB) API.
     Cinephill is designed to provide a personalized movie and TV show search experience for its users. 
     With Cinephill, users can easily create a profile and set their preferred criteria based on rating, genre, and time frame. The application offers flexible features that allow users to easily narrow or widen these criteria, ensuring they receive accurate recommendations that align with their preferences. 
     It also includes an automated task that leverages AWS SES to update users whenever a new TV show is released that matches their criteria. This feature ensures that users are always up-to-date with the latest content that meets their preferences.
      
## Appendix

The Movie Database (TMDB) API

https://developers.themoviedb.org/3
## Authors

- [@byusluer](https://github.com/usluerL/)


## Table of Contents
    • Dependencies
    • Installation
    • Endpoints
    • Deployment
    • Contributing
    • License
    
    

## Dependencies
    • Spring Boot 6
    • Spring Security 3
    • AWS SSM
    • AWS KMS
    • AWS SES
    • The Movie Database (TMDb) API
    • Docker
    • MySQL
    

## Installation    
    1. Clone the repository to your local machine.
    2. Navigate to the root directory of the application.
    3. Configure the application.properties file with your TMDb API key and AWS credentials.
    4. Build the Docker image using the mvn spring-boot:build-image command.
    5. Start the application using Docker Compose.
    
## Endpoints  

Public Endpoints
 
    • GET ${..}/discover/tv - Returns a list of TV shows
    • GET ${..}/movie/popular - Returns a list of popular movies
    • GET ${..}/movie/{movieId} - Returns the details of a specific movie
    • GET ${..}/movie/{movieId}/credits - Returns the credits for a specific movie
    • GET ${..}/search/movie?query={query} - Returns a list of movies based on a search query
    • GET ${..}/public/movies/character - Returns the character name by entering a movie name and an actor/actress's name
    • GET ${..}/public/movies/popular - Returns a list of popular movies
    • GET ${..}/public/movies/search - Returns a list of movies based on a search query
    • GET ${..}/public/movies/{movieId} - Returns the details of a specific movie
    • GET ${..}/public/movies/{movieId}/cast - Returns the cast for a specific movie

Private Endpoints




    • GET ${..}/private - Returns private data for authenticated users
    • GET ${..}/private/tv/search - Returns a list of TV shows based on a search query
    • POST ${..}/private/users/{userId}/category - Adds a category setting to a user's profile


Authentication Endpoints


    • POST ${..}/api/auth/login - Authenticates a user and returns a JWT token
    • POST ${..}/api/auth/register - Registers a new user and returns a JWT token
## Deployment

Cinephill can be deployed using Docker. Docker images can be built using  The Paketo Buildpack for Maven automatically. Once the image is built, the application can be run using Docker Compose.


```bash
mvn spring-boot:build-image
```

```bash
docker compose up
```
## Contributing

    1. Fork the repository.
    2. Create a new branch for your feature.
    3. Make your changes and commit them to your branch.
    4. Push your branch to your forked repository.
    5. Submit a pull request.
## License

This project is licensed under the MIT License - see the LICENSE file for details.



