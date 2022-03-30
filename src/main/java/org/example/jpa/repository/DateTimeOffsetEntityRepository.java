package org.example.jpa.repository;

import org.example.jpa.entity.DateTimeOffsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTimeOffsetEntityRepository extends JpaRepository<DateTimeOffsetEntity, Long> {
}
