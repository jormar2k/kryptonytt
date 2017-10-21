package kryptonytt.repository;

import kryptonytt.entity.PortfolioHib;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PortfolioRepository extends JpaRepository<PortfolioHib, Long> {

}
