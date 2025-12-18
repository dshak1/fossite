<div align="center">

# 📚 Homework Tracker

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/PostgreSQL-15-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  <img src="https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/Thymeleaf-3.1-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white" alt="Thymeleaf"/>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3"/>
</p>

<p align="center">
  <strong>A fun and gamified Spring Boot web application to track homework uploads and earn badges! 🏆</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-active-success.svg" alt="Status"/>
  <img src="https://img.shields.io/badge/license-Educational-blue.svg" alt="License"/>
</p>

</div>

---

## 🎯 About

A simple Spring Boot web application to track homework uploads and earn badges.

## ✨ Features

- 👤 **User Accounts** - Simple username-based login system
- 🎨 **Profile Customization** - Choose from 3 cute animal avatars (Bear 🐻, Cat 🐱, Rabbit 🐰) with 3 color themes each
- 📸 **Homework Upload** - Take a photo or upload from files
- 🏆 **Gamification & Badges**:
  - 🌟 Daily Double - Upload more than once in a day
  - 🔥 Streak Badges - 2, 3, or 7 days in a row uploads
- 📚 **Upload History** - View all your homework with dates and times
- 💪 **Motivational Quotes** - Get inspired on the login page!

## 📋 Prerequisites

- ☕ Java 17 or higher
- 📦 Maven 3.9+ (optional, wrapper included/standard maven works)
- 🐘 PostgreSQL 12 or higher

## 🗄️ Database Setup

### ⚡ Quick Setup (macOS)
```bash
# Install PostgreSQL
brew install postgresql@15

# Start PostgreSQL
brew services start postgresql@15

# Create database
psql -U postgres -c "CREATE DATABASE homeworkdb;"
```

### 📖 Detailed Instructions
See [DATABASE_SETUP.md](DATABASE_SETUP.md) for complete installation and setup instructions for all platforms.

## 🚀 How to Run

1. **Make sure PostgreSQL is running** and the `homeworkdb` database is created
2. Open a terminal in the project root.
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Open your browser to `http://localhost:8080`.

## 📖 Usage

1. 🔐 Enter a username to login (e.g., "student1")
2. 💭 Get inspired by the motivational quote!
3. ⚙️ Go to **Settings** to choose your avatar
4. 📸 Upload a homework picture (use camera or select file)
5. 🏆 Check your badges and track your progress!

## ⚙️ Configuration

The default database configuration is in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/homeworkdb
spring.datasource.username=postgres
spring.datasource.password=password
```

Change these settings if you use different credentials.

## 🛠️ Technology Stack

| Category | Technology |
|----------|-----------|
| **Backend Framework** | Spring Boot 3.3.0 |
| **Language** | Java 17+ |
| **Database** | PostgreSQL 15 |
| **Template Engine** | Thymeleaf |
| **Styling** | Custom CSS with fun cartoon design |
| **File Storage** | Local file system |
| **Build Tool** | Maven 3.9+ |
| **ORM** | Spring Data JPA / Hibernate |

## 📁 Project Structure
```
src/
├── main/
│   ├── java/com/fossite/homework/
│   │   ├── controller/      # Web controllers
│   │   ├── model/           # Data models (User, Homework, Badge)
│   │   ├── repository/      # Database access
│   │   └── service/         # Business logic
│   └── resources/
│       ├── application.properties  # App configuration
│       ├── static/          # Static assets (uploads)
│       └── templates/       # HTML templates
└── uploads/                 # Uploaded homework files
```

## 🔧 Troubleshooting

### ❌ Cannot connect to database
- ✅ Verify PostgreSQL is running: `brew services list | grep postgresql`
- ✅ Check database exists: `psql -U postgres -l`
- ✅ See [DATABASE_SETUP.md](DATABASE_SETUP.md) for more help

### ❌ Maven command not found
- ✅ Install Maven: `brew install maven`
- ✅ Or use Java runtime directly with the JAR file

### ❌ Port 8080 already in use
- ✅ Stop other applications using port 8080
- ✅ Or change the port in `application.properties`:
  ```properties
  server.port=8081
  ```

## 📸 Screenshots

> Coming soon! The app features a fun, colorful cartoon design with floating stars and playful animations.

## 🤝 Contributing

This is an educational project. Feel free to fork and enhance it with your own features!

## 📝 License

Educational project for learning purposes.

---

<div align="center">
  <p>Made with ❤️ and lots of ☕</p>
  <p>Happy Homework Tracking! 📚✨</p>
</div>


