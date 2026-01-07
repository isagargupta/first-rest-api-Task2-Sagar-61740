package pl.edu.vistula.first_rest_api.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.first_rest_api.product.api.dto.ProductRequest;
import pl.edu.vistula.first_rest_api.product.api.dto.ProductResponse;
import pl.edu.vistula.first_rest_api.product.domain.Product;
import pl.edu.vistula.first_rest_api.product.exception.ProductNotFoundException;
import pl.edu.vistula.first_rest_api.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = new Product(request.getName());
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return toResponse(product);
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        existing.setName(request.getName());
        Product saved = productRepository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(existing.getId());
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName());
    }
}

