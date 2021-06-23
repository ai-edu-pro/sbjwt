package org.zerock.sbjwt.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.sbjwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @EntityGraph(attributePaths = {"roles"})
    @Query("select u from User u left outer join u.roles where u.email = :email")
    User login(String email);

}
