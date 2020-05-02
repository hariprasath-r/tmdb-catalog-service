package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.models.MovieInfo;
import in.hp.boot.moviecatalogservice.models.RatingsResponse;
import in.hp.boot.moviecatalogservice.models.UserCatalog;
import in.hp.boot.moviecatalogservice.models.UserDetail;
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
        return userInfoService.getUser(email);
    }

    public UserCatalog getUserCatalog(String email) {
        UserDetail userDetail = validateUser(email);

        RatingsResponse ratingsResponse = ratingDataService.getUserRating(email);

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
}
