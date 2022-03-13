package com.bilgeadam.graphql.mutation;


import com.bilgeadam.graphql.model.ProfileInput;
import com.bilgeadam.repository.IProfileRepository;
import com.bilgeadam.repository.entity.Profile;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mutation resolver is designed for CRUD operations with required parameters.
 */

@Component
@RequiredArgsConstructor
public class ProfileMutationResolver implements GraphQLMutationResolver {

    private final IProfileRepository repository;

    public void createProfileElastic(ProfileInput profileInput){
        repository.save(Profile.builder()
                .country(profileInput.getCountry())
                .email(profileInput.getEmail())
                .firstname(profileInput.getFirstname())
                .lastname(profileInput.getLastname())
                .city(profileInput.getCity())
                .profileId(profileInput.getProfileId())
                .build());
    }
}
