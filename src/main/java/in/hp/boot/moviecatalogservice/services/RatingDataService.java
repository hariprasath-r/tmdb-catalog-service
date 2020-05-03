package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.configs.RatingDataServiceResources;
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

    @Autowired
    private RatingDataServiceResources ratingDataServiceResources;

    public RatingsResponse getUserRating(String email) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(ratingDataServiceResources.getRatingResource())
                    .pathSegment(email)
                    .build().toUri();
            ResponseEntity<RatingsResponse> response = restTemplate.getForEntity(uri, RatingsResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RestTemplateResponseException(e.getStatusCode(), e.getMessage(),
                    e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }

    public WatchlistResponse getUserWatchlist(String email) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(ratingDataServiceResources.getWatchlistResource())
                    .pathSegment(email)
                    .build().toUri();
            ResponseEntity<WatchlistResponse> response = restTemplate.getForEntity(uri, WatchlistResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RestTemplateResponseException(e.getStatusCode(), e.getMessage(),
                    e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }
}
