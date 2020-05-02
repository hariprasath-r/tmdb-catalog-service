package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.models.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    public MovieInfo getMovieInfo(String movieId) {
        return restTemplate.getForObject("http://localhost:8200/v1/movie-info/" + movieId, MovieInfo.class);
    }
}
