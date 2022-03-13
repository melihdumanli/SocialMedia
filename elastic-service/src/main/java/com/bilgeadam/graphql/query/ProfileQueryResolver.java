package com.bilgeadam.graphql.query;

import com.bilgeadam.repository.IProfileRepository;
import com.bilgeadam.repository.entity.Profile;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Query resolver is designed for query operations with the parameters.
 */

@Component
@RequiredArgsConstructor
public class ProfileQueryResolver implements GraphQLQueryResolver {

    private final IProfileRepository repository;

    public List<Profile> findByFirstnameLike(String firstname){
        return repository.findByFirstnameLike(firstname);
    }

    private Iterable<Profile> findAll(){
        return repository.findAll();
    }
}
