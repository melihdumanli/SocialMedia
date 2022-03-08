package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Profile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface IProfileRepository extends ElasticsearchRepository<Profile,String> {
    /**
     * Search process will be completed by firstname. By the way, search keyword will be like "A*"
     */

    List<Profile> findByFirstnameLike(String firstname);
}
