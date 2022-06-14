package com.mono.api.abo.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AboRepo extends JpaRepository<AboEntity, Integer> {
}
