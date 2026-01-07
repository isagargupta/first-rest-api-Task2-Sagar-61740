package pl.edu.vistula.first_rest_api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.vistula.first_rest_api.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

