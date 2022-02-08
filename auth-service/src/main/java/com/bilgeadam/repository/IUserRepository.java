package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    /**
     * Getting email and password info to let user login.
     * @param username  (e-mail info)
     * @param password  (password info)
     * @return */
    Optional<User> findByUsernameAndPassword(String username, String password);

    /**
     * Lists users by status
     * @param status
     * @return
     * */
    List<User> findByStatus(int status);
}
