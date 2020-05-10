package in.hp.boot.moviecatalogservice.delegateproxies;

import in.hp.boot.moviecatalogservice.models.MovieInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${movie-info-service.name}", path = "${movie-info-service.movie-info-resource}")
public interface MovieInfoServiceProxy {

    @GetMapping("{movieId}")
    MovieInfo getInfo(@PathVariable String movieId);
}
