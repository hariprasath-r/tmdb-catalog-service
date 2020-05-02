package in.hp.boot.moviecatalogservice.models;

import lombok.Data;

import java.util.List;

@Data
public class UserCatalog {
    private String name;
    private String email;
    private List<MovieInfo> movieInfos;
}
