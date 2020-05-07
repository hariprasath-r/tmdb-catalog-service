package in.hp.boot.moviecatalogservice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieInfoServiceResources {
    public final String MOVIE_INFO = "/movie-info";
    private final String movieInfoService;
    private final String version;

    @Autowired
    public MovieInfoServiceResources(EndpointsConfig endpointsConfig) {
        this.movieInfoService = endpointsConfig.getMovieInfoService();
        this.version = endpointsConfig.getVersion();
    }

    public String getMovieInfoResource() {
        return movieInfoService + version + MOVIE_INFO;
    }
}
