package com.ice.realtimecache.Product.DTO;

import com.ice.realtimecache.Product.Entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponse {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private ProductStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
