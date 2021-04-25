package za.co.shilton.reactivequote.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shilton.reactivequote.entity.ProductConfig;

@Repository
public interface ProductRepository extends JpaRepository<ProductConfig, Integer> {

  Optional<ProductConfig> findProductConfigByProductIdEquals(UUID id);
}
