package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.models.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserInfoService {

    @Autowired
    private RestTemplate restTemplate;

    public UserDetail getUser(String email) {
        return restTemplate.getForObject("http://localhost:8100/v1/users/" + email, UserDetail.class);
    }
}
