package com.mono.api.product.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Object Relational Mapper (ORM) with injected implementation.
 * <p>Package private and managed by the {@link ProductManagement}.</p>
 */
@Repository
interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
}
