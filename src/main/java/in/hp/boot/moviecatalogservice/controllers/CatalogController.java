package in.hp.boot.moviecatalogservice.controllers;

import in.hp.boot.moviecatalogservice.models.UserCatalog;
import in.hp.boot.moviecatalogservice.services.CatalogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @ApiOperation(value = "Retrieves movies rated by given user", response = UserCatalog.class)
    @GetMapping("/ratings/{email}")
    public ResponseEntity<UserCatalog> getUserCatalog(@PathVariable String email) {
        return ResponseEntity.ok().body(catalogService.getUserRatingCatalog(email));
    }

    @ApiOperation(value = "Retrieves watchlist movie info for given user", response = UserCatalog.class)
    @GetMapping("/watchlist/{email}")
    public ResponseEntity<UserCatalog> getUserWatchlist(@PathVariable String email) {
        return ResponseEntity.ok(catalogService.getUserWatchlistCatalog(email));
    }
}
