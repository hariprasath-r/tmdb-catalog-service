package in.hp.boot.moviecatalogservice.models;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("Movies")
public class MovieInfo extends Movie {
    private Double user_rating;
}
