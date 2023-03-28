package com.byusluer.movierest.model.dto.request;

import com.byusluer.movierest.model.CategoryDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TVShowRequestDto {

    private List<CategoryDto> categories;
    private Double minRating;
    private LocalDate startDate;
    private LocalDate endDate;

}
