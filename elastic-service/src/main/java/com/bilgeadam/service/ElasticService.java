package com.bilgeadam.service;

import com.bilgeadam.repository.IProfileRepository;
import com.bilgeadam.repository.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElasticService {

    private final IProfileRepository iProfileRepository;

    public void save (Profile profile) {
        iProfileRepository.save(profile);
    }

    public void update (Profile profile) {
        iProfileRepository.save(profile);
    }

    public void delete (Profile profile) {
        iProfileRepository.delete(profile);
    }

    public Optional<Profile> findById (String id) {
        return iProfileRepository.findById(id);
    }

    public List<Profile> findByFirstNameLike(String firstName) {
        return iProfileRepository.findByFirstnameLike(firstName);
    }
}
