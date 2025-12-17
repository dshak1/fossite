package com.fossite.homework.service;

import com.fossite.homework.model.Badge;
import com.fossite.homework.model.Homework;
import com.fossite.homework.model.User;
import com.fossite.homework.repository.BadgeRepository;
import com.fossite.homework.repository.HomeworkRepository;
import com.fossite.homework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;

    public BadgeService(BadgeRepository badgeRepository, HomeworkRepository homeworkRepository, UserRepository userRepository) {
        this.badgeRepository = badgeRepository;
        this.homeworkRepository = homeworkRepository;
        this.userRepository = userRepository;
    }

    public void initBadges() {
        createBadgeIfNotFound("DAILY_DOUBLE", "Daily Double", "Uploaded more than once in a day", "badge_daily_double.png");
        createBadgeIfNotFound("STREAK_2", "2 Day Streak", "Uploaded 2 days in a row", "badge_streak_2.png");
        createBadgeIfNotFound("STREAK_3", "3 Day Streak", "Uploaded 3 days in a row", "badge_streak_3.png");
        createBadgeIfNotFound("STREAK_7", "Weekly Warrior", "Uploaded 7 days in a row", "badge_streak_7.png");
    }

    private void createBadgeIfNotFound(String name, String displayName, String desc, String icon) {
        if (badgeRepository.findByName(name).isEmpty()) {
            badgeRepository.save(new Badge(name, displayName, desc, icon));
        }
    }

    public void checkAndAwardBadges(User user) {
        List<Homework> uploads = homeworkRepository.findByUserOrderByUploadDateTimeDesc(user);
        
        if (uploads.isEmpty()) return;

        LocalDate today = LocalDate.now();
        
        // Check > 1 upload today
        long uploadsToday = uploads.stream()
                .filter(h -> h.getUploadDateTime().toLocalDate().equals(today))
                .count();
        
        if (uploadsToday > 1) {
            awardBadge(user, "DAILY_DOUBLE");
        }

        // Calculate Streak
        // Get unique dates sorted descending
        List<LocalDate> uploadDates = uploads.stream()
                .map(h -> h.getUploadDateTime().toLocalDate())
                .distinct()
                .sorted(Comparator.<LocalDate>reverseOrder())
                .collect(Collectors.toList());

        // A streak must include today to be active/incremented today? 
        // Or if I uploaded today, I check if I uploaded yesterday, etc.
        
        int streak = 0;
        if (!uploadDates.isEmpty() && uploadDates.get(0).equals(today)) {
            streak = 1;
            for (int i = 0; i < uploadDates.size() - 1; i++) {
                if (uploadDates.get(i).minusDays(1).equals(uploadDates.get(i+1))) {
                    streak++;
                } else {
                    break;
                }
            }
        }

        if (streak >= 2) awardBadge(user, "STREAK_2");
        if (streak >= 3) awardBadge(user, "STREAK_3");
        if (streak >= 7) awardBadge(user, "STREAK_7");
    }

    private void awardBadge(User user, String badgeName) {
        Badge badge = badgeRepository.findByName(badgeName).orElse(null);
        if (badge != null && !user.getBadges().contains(badge)) {
            user.getBadges().add(badge);
            userRepository.save(user);
        }
    }
}

