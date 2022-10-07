package org.example.repositories;

import org.example.entities.BasketEntity;
import org.example.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
}
