package in.hp.boot.moviecatalogservice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RatingDataServiceResources {
    public final static String RATINGS = "/ratings";
    public final static String WATCHLIST = "/watchlist";

    private String ratingDataService;
    private String version;

    @Autowired
    public RatingDataServiceResources(EndpointsConfig endpointsConfig) {
        this.ratingDataService = endpointsConfig.getRatingDataService();
        this.version = endpointsConfig.getVersion();
    }

    public String getRatingResource() {
        return ratingDataService + version + RATINGS;
    }

    public String getWatchlistResource() {
        return ratingDataService + version + WATCHLIST;
    }
}
