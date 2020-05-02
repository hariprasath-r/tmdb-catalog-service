package in.hp.boot.moviecatalogservice.models;

import lombok.Data;

@Data
public class MovieInfo extends Movie {
    private Double user_rating;
}
