package com.example.worktest.Work_Test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTestRepository extends JpaRepository<WorkTest, Long> {

}


