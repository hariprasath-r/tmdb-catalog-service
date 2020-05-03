package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.configs.MovieInfoServiceResources;
import in.hp.boot.moviecatalogservice.exceptions.RestTemplateResponseException;
import in.hp.boot.moviecatalogservice.models.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieInfoServiceResources movieInfoServiceResources;

    public MovieInfo getMovieInfo(String movieId) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(movieInfoServiceResources.getMovieInfoResource())
                    .pathSegment(movieId)
                    .build().toUri();
            ResponseEntity<MovieInfo> response = restTemplate.getForEntity(uri, MovieInfo.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RestTemplateResponseException(e.getStatusCode(), e.getMessage(),
                    e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }
}
