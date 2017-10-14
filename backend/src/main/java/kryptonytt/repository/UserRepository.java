package kryptonytt.repository;

import kryptonytt.entity.KryptonyttUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<KryptonyttUser, Long> {
    Optional<KryptonyttUser> findByUsername(String username);
}
