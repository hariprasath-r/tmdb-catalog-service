package in.hp.boot.moviecatalogservice.services;

import in.hp.boot.moviecatalogservice.configs.UserInfoServiceResources;
import in.hp.boot.moviecatalogservice.exceptions.RestTemplateResponseException;
import in.hp.boot.moviecatalogservice.models.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class UserInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserInfoServiceResources userInfoServiceResources;

    public UserDetail getUser(String email) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(userInfoServiceResources.getUsersResource())
                    .pathSegment(email)
                    .build().toUri();

            ResponseEntity<UserDetail> response = restTemplate.getForEntity(uri, UserDetail.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RestTemplateResponseException(e.getStatusCode(), e.getMessage(),
                    e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }
}
