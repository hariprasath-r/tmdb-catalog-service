package in.hp.boot.moviecatalogservice.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Movie {
    List<String> genres;
    private String movieId;
    private String title;
    private String desciption;
    private Integer length;
    private LocalDate release_date;
    private String global_rating;
}