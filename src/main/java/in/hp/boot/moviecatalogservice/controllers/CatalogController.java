package in.hp.boot.moviecatalogservice.controllers;

import in.hp.boot.moviecatalogservice.models.UserCatalog;
import in.hp.boot.moviecatalogservice.services.CatalogService;
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

    @GetMapping("/{email}")
    public ResponseEntity<UserCatalog> getUserCatalog(@PathVariable String email) {
        return ResponseEntity.ok().body(catalogService.getUserCatalog(email));
    }
}
