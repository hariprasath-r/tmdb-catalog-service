package in.hp.boot.moviecatalogservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("User Movie Catalog")
public class UserCatalog {

    private String name;

    private String email;

    @ApiModelProperty(name = "List of movies", position = 2)
    private List<MovieInfo> movieInfos;
}
