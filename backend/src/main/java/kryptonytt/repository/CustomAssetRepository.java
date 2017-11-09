package kryptonytt.repository;

import kryptonytt.entity.CustomAssetHib;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomAssetRepository extends JpaRepository<CustomAssetHib, Long> {
}
