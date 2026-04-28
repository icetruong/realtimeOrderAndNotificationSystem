package com.ice.realtimecache.Product.Controller;

import com.ice.realtimecache.Product.DTO.ProductRequest;
import com.ice.realtimecache.Product.DTO.ProductResponse;
import com.ice.realtimecache.Product.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products")
    public ProductResponse createProduct(@RequestBody ProductRequest request)
    {
        return productService.createProduct(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/products/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest request)
    {
        return productService.updateProduct(id, request);
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProduct()
    {
        return productService.getAllProduct();
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }
}
