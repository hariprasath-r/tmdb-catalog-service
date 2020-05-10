package in.hp.boot.moviecatalogservice.delegateproxies;

import in.hp.boot.moviecatalogservice.models.UserDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${user-info-service.name}", path = "${user-info-service.users-resource}")
public interface UserInfoServiceProxy {

    @GetMapping("/{email}")
    UserDetail getUserByEmail(@PathVariable String email);

}
