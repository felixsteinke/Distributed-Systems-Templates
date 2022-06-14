package com.mono.api.product.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
}
