package com.fossite.homework.service;

import com.fossite.homework.model.Homework;
import com.fossite.homework.model.User;
import com.fossite.homework.repository.HomeworkRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final FileStorageService fileStorageService;
    private final BadgeService badgeService;

    public HomeworkService(HomeworkRepository homeworkRepository, FileStorageService fileStorageService, BadgeService badgeService) {
        this.homeworkRepository = homeworkRepository;
        this.fileStorageService = fileStorageService;
        this.badgeService = badgeService;
    }

    public void uploadHomework(User user, MultipartFile file) {
        String filename = fileStorageService.store(file);
        
        Homework homework = new Homework();
        homework.setUser(user);
        homework.setFilePath(filename);
        homework.setUploadDateTime(LocalDateTime.now());
        
        homeworkRepository.save(homework);
        
        badgeService.checkAndAwardBadges(user);
    }
    
    public List<Homework> getUserHomework(User user) {
        return homeworkRepository.findByUserOrderByUploadDateTimeDesc(user);
    }
}

