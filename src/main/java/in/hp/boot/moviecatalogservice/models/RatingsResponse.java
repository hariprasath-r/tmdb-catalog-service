package in.hp.boot.moviecatalogservice.models;

import lombok.Data;

import java.util.List;

@Data
public class RatingsResponse {
    private List<Ratings> ratings;
}
