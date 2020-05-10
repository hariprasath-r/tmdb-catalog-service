package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RatingDataService ratingDataService;

    @Autowired
    private MovieInfoService movieInfoService;

    private UserDetail validateUser(String email) {
        return userInfoService.getUserByFeign(email);
    }

    public UserCatalog getUserRatingCatalog(String email) {
        UserDetail userDetail = validateUser(email);

        RatingsResponse ratingsResponse = ratingDataService.getUserRatingFeign(email);

        List<MovieInfo> movieInfos = ratingsResponse.getRatings().stream().map(rating -> {
            MovieInfo movieInfo = movieInfoService.getMovieInfo(rating.getMovieId());
            movieInfo.setUser_rating(rating.getRating());
            return movieInfo;
        }).collect(Collectors.toList());

        UserCatalog userCatalog = new UserCatalog();
        userCatalog.setMovieInfos(movieInfos);
        userCatalog.setEmail(email);
        userCatalog.setName(userDetail.getFname() + " " + userDetail.getLname());
        return userCatalog;
    }

    public UserCatalog getUserWatchlistCatalog(String email) {
        UserDetail userDetail = validateUser(email);

        WatchlistResponse watchlist = ratingDataService.getUserWatchlistFeign(email);

        List<MovieInfo> movieInfos = watchlist.getMovies().stream()
                .map(movieId -> movieInfoService.getMovieInfo(movieId))
                .collect(Collectors.toList());

        UserCatalog userCatalog = new UserCatalog();
        userCatalog.setMovieInfos(movieInfos);
        userCatalog.setEmail(email);
        userCatalog.setName(userDetail.getFname() + " " + userDetail.getLname());
        return userCatalog;
    }
}
