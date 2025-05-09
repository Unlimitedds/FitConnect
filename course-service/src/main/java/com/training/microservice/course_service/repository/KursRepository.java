package com.training.microservice.course_service.repository;

import com.training.microservice.course_service.model.Kurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KursRepository extends JpaRepository<Kurs, Long> {
}