package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CatalogService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RatingDataService ratingDataService;

    @Autowired
    private MovieInfoService movieInfoService;

    private UserDetail validateUser(String email) {
        log.debug("validateUser");
        return userInfoService.getUserByFeign(email);
    }

    public UserCatalog getUserRatingCatalog(String email) {
        log.info("getUserRatingCatalog");
        UserDetail userDetail = validateUser(email);
        log.info("getUserRatingCatalog: User [{}] validated.", email);

        log.debug("getUserRatingCatalog: fetching ratings for user");
        RatingsResponse ratingsResponse = ratingDataService.getUserRatingFeign(email);
        log.info("getUserRatingCatalog: fetched ratings for user: [{}]", ratingsResponse);

        List<MovieInfo> movieInfos = ratingsResponse.getRatings().stream().map(rating -> {
            MovieInfo movieInfo = movieInfoService.getMovieInfoFeign(rating.getMovieId());
            movieInfo.setUser_rating(rating.getRating());
            return movieInfo;
        }).collect(Collectors.toList());
        log.info("getUserRatingCatalog: fetched movie details");

        UserCatalog userCatalog = new UserCatalog();
        userCatalog.setMovieInfos(movieInfos);
        userCatalog.setEmail(email);
        userCatalog.setName(userDetail.getFname() + " " + userDetail.getLname());
        log.info("getUserRatingCatalog done: [{}]", userCatalog);
        return userCatalog;
    }

    public UserCatalog getUserWatchlistCatalog(String email) {
        log.info("getUserWatchlistCatalog");
        UserDetail userDetail = validateUser(email);
        log.info("getUserWatchlistCatalog: User [{}] validated.", email);

        log.debug("getUserWatchlistCatalog: fetching watchlist for user");
        WatchlistResponse watchlist = ratingDataService.getUserWatchlistFeign(email);
        log.info("getUserWatchlistCatalog: fetched watchlist for user: [{}]", watchlist);

        List<MovieInfo> movieInfos = watchlist.getMovies().stream()
                .map(movieId -> movieInfoService.getMovieInfoFeign(movieId))
                .collect(Collectors.toList());
        log.info("getUserWatchlistCatalog: fetched movie details");

        UserCatalog userCatalog = new UserCatalog();
        userCatalog.setMovieInfos(movieInfos);
        userCatalog.setEmail(email);
        userCatalog.setName(userDetail.getFname() + " " + userDetail.getLname());
        log.info("getUserWatchlistCatalog done: [{}]", userCatalog);
        return userCatalog;
    }
}
