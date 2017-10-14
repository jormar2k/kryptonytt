package kryptonytt.repository;

import kryptonytt.entity.KryptonyttUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<KryptonyttUser, Long> {

}
