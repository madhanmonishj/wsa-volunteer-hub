package com.example.wsa.qualification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRespository extends JpaRepository<Qualification, Integer> {
}
