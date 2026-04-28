package com.ice.realtimecache.Product.DTO;

import com.ice.realtimecache.Product.Entity.ProductStatus;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private ProductStatus status;
}
