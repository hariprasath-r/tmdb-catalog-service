package in.hp.boot.moviecatalogservice.services;

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

    public MovieInfo getMovieInfo(String movieId) {
        try {
            URI uri = UriComponentsBuilder.fromUriString("http://localhost:8200/v1/movie-info/{param}")
                    .build(movieId);
            ResponseEntity<MovieInfo> response = restTemplate.getForEntity(uri, MovieInfo.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RestTemplateResponseException(e.getStatusCode(), e.getMessage(),
                    e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }
}
