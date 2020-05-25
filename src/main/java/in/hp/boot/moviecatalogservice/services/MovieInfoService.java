package in.hp.boot.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.FeignException;
import in.hp.boot.moviecatalogservice.configs.MovieInfoServiceResources;
import in.hp.boot.moviecatalogservice.delegateproxies.MovieInfoServiceProxy;
import in.hp.boot.moviecatalogservice.exceptions.RestTemplateResponseException;
import in.hp.boot.moviecatalogservice.models.MovieInfo;
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
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieInfoServiceResources movieInfoServiceResources;

    @Autowired
    private MovieInfoServiceProxy movieInfoServiceProxy;

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

    @HystrixCommand(fallbackMethod = "getMovieInfoFeignFallback")
    public MovieInfo getMovieInfoFeign(String movieId) {
        try {
            log.debug("getMovieInfoFeign: fetching movie info for [{}]", movieId);
            return movieInfoServiceProxy.getInfo(movieId);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception: getMovieInfoFeign MovieId: [{}], Status: [{}]", movieId, status);
            throw new RestTemplateResponseException(status, e.getMessage(),
                    e.contentUTF8());
        }
    }

    public MovieInfo getMovieInfoFeignFallback(String movieId) {
        return new MovieInfo();
    }
}
