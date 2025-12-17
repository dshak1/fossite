package com.fossite.homework.controller;

import com.fossite.homework.model.User;
import com.fossite.homework.service.BadgeService;
import com.fossite.homework.service.HomeworkService;
import com.fossite.homework.service.QuoteService;
import com.fossite.homework.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    private final UserService userService;
    private final HomeworkService homeworkService;
    private final BadgeService badgeService;
    private final QuoteService quoteService;

    public MainController(UserService userService, HomeworkService homeworkService, BadgeService badgeService, QuoteService quoteService) {
        this.userService = userService;
        this.homeworkService = homeworkService;
        this.badgeService = badgeService;
        this.quoteService = quoteService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("quote", quoteService.getRandomQuote());
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        User user = userService.getOrCreateUser(username);
        session.setAttribute("userId", user.getId());
        // Initialize badges on first login mostly just to ensure they exist in DB
        badgeService.initBadges(); 
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";

        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("homeworks", homeworkService.getUserHomework(user));
        return "dashboard";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";

        User user = userService.getUser(userId);
        homeworkService.uploadHomework(user, file);
        return "redirect:/dashboard";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";

        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String animal, @RequestParam String color, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";

        userService.updateUserAvatar(userId, animal, color);
        return "redirect:/dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}