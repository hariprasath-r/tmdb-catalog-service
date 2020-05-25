package in.hp.boot.moviecatalogservice.delegateproxies;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import in.hp.boot.moviecatalogservice.models.MovieInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${movie-info-service.name}", path = "${movie-info-service.movie-info-resource}")
public interface MovieInfoServiceProxy {

    @GetMapping("{movieId}")
    MovieInfo getInfo(@PathVariable String movieId);
}

@Component
class MovieInfoFallbackFactory implements FallbackFactory<MovieInfoServiceProxy> {

    @Override
    public MovieInfoServiceProxy create(Throwable throwable) {
        return new MovieInfoServiceFallback(throwable);
    }
}

@Slf4j
class MovieInfoServiceFallback implements MovieInfoServiceProxy {

    private final Throwable throwable;

    public MovieInfoServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public MovieInfo getInfo(String movieId) {
        if (this.throwable instanceof FeignException) {
            FeignException feignException = (FeignException) this.throwable;
            log.error("Exception [{}] when getInfo was called with movieId [{}], Exception message [{}].",
                    feignException.status(), movieId, feignException.contentUTF8());
        } else {
            log.error("Exception when getInfo was called with movieId [{}].", movieId);
        }
        log.error("Returning default fallback response");

        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setMovieId(movieId);
        return movieInfo;
    }
}