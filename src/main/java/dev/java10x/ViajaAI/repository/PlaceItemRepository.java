package dev.java10x.ViajaAI.repository;

import dev.java10x.ViajaAI.model.PlaceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceItemRepository extends JpaRepository<PlaceItem, Long> {
}
