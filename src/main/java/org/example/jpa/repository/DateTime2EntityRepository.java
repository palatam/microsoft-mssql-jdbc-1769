package org.example.jpa.repository;

import org.example.jpa.entity.DateTime2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTime2EntityRepository extends JpaRepository<DateTime2Entity, Long> {
}
