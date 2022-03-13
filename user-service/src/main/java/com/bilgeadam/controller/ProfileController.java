package com.bilgeadam.controller;

import static com.bilgeadam.constant.RestApiUrls.*;

import com.bilgeadam.dto.request.FindByAuthId;
import com.bilgeadam.dto.request.IsProfileExistsDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.rabbitmq.producer.ElasticProfileProducer;
import com.bilgeadam.repository.entity.Profile;
import com.bilgeadam.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(VERSION + PROFILE)
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ElasticProfileProducer elasticProfileProducer;

    @PostMapping(SAVE)
// @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid ProfileRequestDto dto){
        String id = profileService.save(dto);
        return ResponseEntity.ok(id);
    }

    @PostMapping(FINDBYAUTHID)
    public ResponseEntity<String> findByAuthId(@RequestBody @Valid FindByAuthId dto){
        Optional<Profile> profile = profileService.findByAuthId(dto.getAuhtid());
        if(profile.isPresent()){
            return ResponseEntity.ok(profile.get().getId());
        }else{
            return ResponseEntity.ok("");
        }
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(profileService.findall());
    }

    @PostMapping("/isprofileexistbyid")
    public ResponseEntity<Boolean> isProfileExistById(@RequestBody IsProfileExistsDto dto){
        Optional<Profile> profile = profileService.findById(dto.getProfileId());
        if(profile.isPresent()){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }
}