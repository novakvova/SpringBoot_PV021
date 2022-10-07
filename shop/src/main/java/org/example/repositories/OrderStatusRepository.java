package org.example.repositories;

import lombok.Data;
import org.example.entities.OrderEntity;
import org.example.entities.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {
}
