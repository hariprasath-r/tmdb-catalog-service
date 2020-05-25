package in.hp.boot.moviecatalogservice.services;

import feign.FeignException;
import in.hp.boot.moviecatalogservice.configs.RatingDataServiceResources;
import in.hp.boot.moviecatalogservice.delegateproxies.RatingDataServiceProxy;
import in.hp.boot.moviecatalogservice.exceptions.RestTemplateResponseException;
import in.hp.boot.moviecatalogservice.models.RatingsResponse;
import in.hp.boot.moviecatalogservice.models.WatchlistResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@Slf4j
public class RatingDataService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingDataServiceResources ratingDataServiceResources;

    @Autowired
    private RatingDataServiceProxy ratingDataServiceProxy;

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

    public RatingsResponse getUserRatingFeign(String email) {
        try {
            return ratingDataServiceProxy.getRatingsForUser(email);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception: getUserRatingFeign Email: [{}], Status: [{}]", email, status);
            throw new RestTemplateResponseException(status, e.getMessage(),
                    e.contentUTF8());
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

    public WatchlistResponse getUserWatchlistFeign(String email) {
        try {
            return ratingDataServiceProxy.getWatchlistForUser(email);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception: getUserWatchlistFeign Email: [{}], Status: [{}]", email, status);
            throw new RestTemplateResponseException(status, e.getMessage(),
                    e.contentUTF8());
        }
    }
}
