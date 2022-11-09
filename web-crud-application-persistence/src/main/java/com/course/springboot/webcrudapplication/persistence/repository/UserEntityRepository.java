package com.course.springboot.webcrudapplication.persistence.repository;

import com.course.springboot.webcrudapplication.persistence.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
}
