package in.hp.boot.moviecatalogservice.models;

import lombok.Data;

import java.util.List;

@Data
public class WatchlistResponse {
    private String email;
    private List<String> movies;
}
