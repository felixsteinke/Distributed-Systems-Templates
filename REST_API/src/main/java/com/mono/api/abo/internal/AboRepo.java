package com.mono.api.abo.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Object Relational Mapper (ORM) with injected implementation.
 * <p>Package private and managed by the {@link AboManagement}.</p>
 */
@Repository
interface AboRepo extends JpaRepository<AboEntity, Integer> {
}
