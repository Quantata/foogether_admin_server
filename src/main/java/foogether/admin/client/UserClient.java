package foogether.admin.client;


import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
// Hsytrix -> fallback method
//@FeignClient(url="http://rest-post-app-service:8082", name="rest-post-app-service")
//@FeignClient(url="http://localhost:8082", name = "user-service")
@FeignClient(url="http://foogether.us:9090", name = "user-service")
public interface UserClient {

    @PostMapping("/users/list")
    ResponseEntity<DefaultResponse<List<UserResponseDto>>> getUserList(
            @RequestBody List<Integer> UserIdxList);

    @GetMapping("/users/{userIdx}")
//    OwnerDto getUserInfo(@RequestHeader String header);
//    UserResponseDto getUserInfo(@RequestHeader(value = "Authorization") String header);
    ResponseEntity<DefaultResponse<UserResponseDto>> getUserInfo(
//            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable("userIdx") int userIdx);

}
