package in.hp.boot.moviecatalogservice.controllers;

import in.hp.boot.moviecatalogservice.models.UserCatalog;
import in.hp.boot.moviecatalogservice.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/{email}")
    public UserCatalog getUserCatalog(@PathVariable String email) {
        return catalogService.getUserCatalog(email);
    }

    @PostMapping
    public void addUserRating() {

    }
}
