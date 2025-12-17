package com.fossite.homework.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    
    private final List<String> encouragingQuotes = Arrays.asList(
        "Every expert was once a beginner. Keep learning! 🌟",
        "You're braver than you believe, and smarter than you think! 💪",
        "Mistakes are proof that you're trying! 🚀",
        "Believe in yourself and you can achieve anything! ✨",
        "Today is a great day to learn something new! 📚",
        "Small steps every day lead to big achievements! 🎯",
        "Your hard work will pay off! Keep going! 🌈",
        "You've got this! Let's make today count! 💫",
        "Learning is an adventure - enjoy the journey! 🗺️",
        "Be proud of how far you've come! 🏆",
        "Your brain is like a muscle - exercise it daily! 🧠",
        "Dream big, work hard, stay focused! 🌠",
        "You're doing amazing! Keep up the great work! 🎨",
        "Every homework completed is a step closer to your goals! 🎓",
        "Success is built one day at a time! 🏗️"
    );
    
    private final Random random = new Random();
    
    public String getRandomQuote() {
        return encouragingQuotes.get(random.nextInt(encouragingQuotes.size()));
    }
}