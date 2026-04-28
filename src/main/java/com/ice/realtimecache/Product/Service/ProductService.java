package com.ice.realtimecache.Product.Service;

import com.ice.realtimecache.Product.DTO.ProductRequest;
import com.ice.realtimecache.Product.DTO.ProductResponse;
import com.ice.realtimecache.Product.Entity.Product;
import com.ice.realtimecache.Product.Entity.ProductStatus;
import com.ice.realtimecache.Product.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    @CachePut(value = "products", key = "#result.id")
    @CacheEvict(value = "products", key = "'all'", beforeInvocation = true)
    public ProductResponse createProduct(ProductRequest request)
    {
        Product p = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .status(ProductStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product product = productRepo.save(p);

        return toResponse(product);
    }

    @CachePut(value = "products", key = "#result.id")
    @CacheEvict(value = "products", key = "'all'", beforeInvocation = true)
    public ProductResponse updateProduct(Long id, ProductRequest request)
    {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));

        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setPrice(request.getPrice());
        p.setStock(request.getStock());
        p.setStatus(request.getStatus());
        p.setUpdatedAt(LocalDateTime.now());
        Product product = productRepo.save(p);

        return toResponse(product);
    }

    @Cacheable(value = "products", key = "'all'")
    public List<ProductResponse> getAllProduct()
    {
        List<Product> products = productRepo.findAll();

        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product : products)
            productResponses.add(toResponse(product));

        return productResponses;
    }

    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProductById(Long id)
    {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));
        return toResponse(product);
    }

    @Caching(evict = {
            @CacheEvict(value = "products", key = "#id"),
            @CacheEvict(value = "products", key = "'all'")
    })
    public void deleteProduct(Long id)
    {
        productRepo.deleteById(id);
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
