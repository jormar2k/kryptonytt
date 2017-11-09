package kryptonytt.repository;

import kryptonytt.entity.FiatHib;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FiatRepository extends JpaRepository<FiatHib, Long> {
}
