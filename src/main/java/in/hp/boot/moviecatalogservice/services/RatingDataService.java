package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.models.RatingsResponse;
import in.hp.boot.moviecatalogservice.models.WatchlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingDataService {

    @Autowired
    private RestTemplate restTemplate;

    public RatingsResponse getUserRating(String email) {
        return restTemplate.getForObject("http://localhost:8300/v1/ratings/" + email, RatingsResponse.class);
    }

    public WatchlistResponse getUserWatchlist(String email) {
        return restTemplate.getForObject("http://localhost:8300/v1/watchlist/" + email, WatchlistResponse.class);
    }
}
