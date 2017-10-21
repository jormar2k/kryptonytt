package kryptonytt.repository;

import kryptonytt.entity.KryptonyttUserHib;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<KryptonyttUserHib, Long> {
    Optional<KryptonyttUserHib> findByUsername(String username);
}
