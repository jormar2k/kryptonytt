package kryptonytt.repository;

import kryptonytt.entity.CoinHib;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoinRepository extends JpaRepository<CoinHib, Long> {
}
