package in.hp.boot.moviecatalogservice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInfoServiceResources {
    public final String USERS = "/users";
    private final String userInfoService;
    private final String version;

    @Autowired
    public UserInfoServiceResources(EndpointsConfig endpointsConfig) {
        this.userInfoService = endpointsConfig.getUserInfoService();
        this.version = endpointsConfig.getVersion();
    }

    public String getUsersResource() {
        return userInfoService + version + USERS;
    }
}
