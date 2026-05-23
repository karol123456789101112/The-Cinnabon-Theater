package org.example.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.domain.AppUser;
import java.util.List;

@Transactional
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findByLastName(String lastName);
    AppUser findById(long id);
    AppUser findByEmail(String email);

}

