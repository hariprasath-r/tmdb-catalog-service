package in.hp.boot.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.FeignException;
import in.hp.boot.moviecatalogservice.configs.UserInfoServiceResources;
import in.hp.boot.moviecatalogservice.delegateproxies.UserInfoServiceProxy;
import in.hp.boot.moviecatalogservice.exceptions.RestTemplateResponseException;
import in.hp.boot.moviecatalogservice.models.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@Slf4j
public class UserInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserInfoServiceResources userInfoServiceResources;

    @Autowired
    private UserInfoServiceProxy userInfoServiceProxy;

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

    @HystrixCommand(fallbackMethod = "getUserByFeignFallback")
    public UserDetail getUserByFeign(String email) {
        try {
            return userInfoServiceProxy.getUserByEmail(email);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception: getUserByFeign Email: [{}], Status: [{}]", email, status);
            throw new RestTemplateResponseException(status, e.getMessage(),
                    e.contentUTF8());
        }
    }

    public UserDetail getUserByFeignFallback(String email) {
        log.error("getUserByFeignFallback [{}]", email);
        return new UserDetail();
    }
}
