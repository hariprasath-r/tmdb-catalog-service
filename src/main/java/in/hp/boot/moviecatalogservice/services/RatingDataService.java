package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.exceptions.RestTemplateResponseException;
import in.hp.boot.moviecatalogservice.models.RatingsResponse;
import in.hp.boot.moviecatalogservice.models.WatchlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class RatingDataService {

    @Autowired
    private RestTemplate restTemplate;

    public RatingsResponse getUserRating(String email) {
        try {
            URI uri = UriComponentsBuilder.fromUriString("http://localhost:8300/v1/ratings/{param}")
                    .build(email);
            ResponseEntity<RatingsResponse> response = restTemplate.getForEntity(uri, RatingsResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RestTemplateResponseException(e.getStatusCode(), e.getMessage(),
                    e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }

    public WatchlistResponse getUserWatchlist(String email) {
        try {
            URI uri = UriComponentsBuilder.fromUriString("http://localhost:8300/v1/watchlist/{param}")
                    .build(email);
            ResponseEntity<WatchlistResponse> response = restTemplate.getForEntity(uri, WatchlistResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RestTemplateResponseException(e.getStatusCode(), e.getMessage(),
                    e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }
}
