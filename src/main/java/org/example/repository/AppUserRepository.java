package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.domain.AppUser;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findById(long id);
    Optional<AppUser> findByEmail(String email);
    List<AppUser> findByStatus(UserStatus status);
    List<AppUser> findByStatusOrderByIdAsc(UserStatus status);
}

