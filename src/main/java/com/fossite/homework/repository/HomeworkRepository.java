package com.fossite.homework.repository;

import com.fossite.homework.model.Homework;
import com.fossite.homework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findByUserOrderByUploadDateTimeDesc(User user);
    
    // Find uploads within a specific time range for a user
    List<Homework> findByUserAndUploadDateTimeBetween(User user, LocalDateTime start, LocalDateTime end);
}

