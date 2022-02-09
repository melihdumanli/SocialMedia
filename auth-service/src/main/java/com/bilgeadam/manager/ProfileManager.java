package com.bilgeadam.manager;

import static com.bilgeadam.constant.RestApiUrls.*;

import com.bilgeadam.dto.request.ProfileRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**URL's may change in the future because of test and development environment.
 * To manage the URL's, we use FeignClient annotation and gettin the URL's from the application.yml file.
 * */
@FeignClient(url = "${myapplication.userservice}" + VERSION + PROFILE, name ="profileFeignClient", decode404 = true)
public interface ProfileManager {

    @PostMapping(SAVE)
    public ResponseEntity<String> save(@RequestBody ProfileRequestDto dto);
}