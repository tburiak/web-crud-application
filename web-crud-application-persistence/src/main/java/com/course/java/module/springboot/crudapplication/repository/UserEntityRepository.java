package com.course.java.module.springboot.crudapplication.repository;

import com.course.java.module.springboot.crudapplication.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
}
