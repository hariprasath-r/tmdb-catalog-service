package in.hp.boot.moviecatalogservice.delegateproxies;

import in.hp.boot.moviecatalogservice.models.RatingsResponse;
import in.hp.boot.moviecatalogservice.models.WatchlistResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${rating-data-service.name}", path = "${endpoints.version}")
public interface RatingDataServiceProxy {

    @GetMapping("${rating-data-service.ratings}/{userId}")
    RatingsResponse getRatingsForUser(@PathVariable String userId);

    @GetMapping("${rating-data-service.watchlist}/{userId}")
    public WatchlistResponse getWatchlistForUser(@PathVariable String userId);
}
