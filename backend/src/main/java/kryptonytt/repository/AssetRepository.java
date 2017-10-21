package kryptonytt.repository;

import kryptonytt.entity.AssetHib;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetRepository extends JpaRepository<AssetHib, Long> {
}
