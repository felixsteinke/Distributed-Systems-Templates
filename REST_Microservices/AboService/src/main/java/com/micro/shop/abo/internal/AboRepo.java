package com.micro.shop.abo.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboRepo extends JpaRepository<AboEntity, Integer> {
}
