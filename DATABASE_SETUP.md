# PostgreSQL Database Setup

This guide will help you set up PostgreSQL for the Homework Tracker application.

## Installation

### macOS (using Homebrew)
```bash
# Install PostgreSQL
brew install postgresql@15

# Start PostgreSQL service
brew services start postgresql@15

# Verify installation
psql --version
```

### macOS (using Postgres.app)
1. Download from https://postgresapp.com/
2. Move to Applications folder
3. Double-click to start
4. Click "Initialize" to create a new server

### Linux (Ubuntu/Debian)
```bash
# Update package list
sudo apt update

# Install PostgreSQL
sudo apt install postgresql postgresql-contrib

# Start PostgreSQL service
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

### Windows
1. Download installer from https://www.postgresql.org/download/windows/
2. Run the installer
3. Follow the setup wizard (default port 5432)
4. Remember the password you set for the `postgres` user

## Database Creation

### Option 1: Using Command Line
```bash
# Connect to PostgreSQL
psql -U postgres

# Create the database
CREATE DATABASE homeworkdb;

# Verify database was created
\l

# Exit PostgreSQL
\q
```

### Option 2: Using GUI (pgAdmin)
1. Open pgAdmin (installed with PostgreSQL)
2. Right-click "Databases"
3. Select "Create" → "Database"
4. Enter name: `homeworkdb`
5. Click "Save"

## Configuration

The application is configured to connect to PostgreSQL with these settings:

- **Host**: `localhost`
- **Port**: `5432` (default PostgreSQL port)
- **Database**: `homeworkdb`
- **Username**: `postgres`
- **Password**: `password`

### Changing the Password

If you want to use a different password:

1. Update in PostgreSQL:
```bash
psql -U postgres
ALTER USER postgres WITH PASSWORD 'your-new-password';
\q
```

2. Update `src/main/resources/application.properties`:
```properties
spring.datasource.password=your-new-password
```

## Verifying the Setup

1. Make sure PostgreSQL is running:
```bash
# macOS
brew services list | grep postgresql

# Linux
sudo systemctl status postgresql

# Windows - check Services app for "PostgreSQL"
```

2. Test connection:
```bash
psql -U postgres -d homeworkdb
```

If successful, you'll see the PostgreSQL prompt:
```
homeworkdb=#
```

## Running the Application

Once PostgreSQL is set up and the database is created:

1. Make sure PostgreSQL is running
2. Start the Spring Boot application:
```bash
mvn spring-boot:run
```

The application will automatically create the necessary tables on first run!

## Troubleshooting

### Connection Refused
- Make sure PostgreSQL is running
- Check if port 5432 is available: `lsof -i :5432`

### Authentication Failed
- Verify username and password in `application.properties`
- Check PostgreSQL user permissions

### Database Does Not Exist
- Make sure you created the `homeworkdb` database
- Run: `CREATE DATABASE homeworkdb;` in psql

### Tables Not Created
- Check application logs for errors
- Verify `spring.jpa.hibernate.ddl-auto=update` in properties file

## Managing the Database

### View all tables
```bash
psql -U postgres -d homeworkdb
\dt
```

### View data
```sql
SELECT * FROM app_user;
SELECT * FROM homework;
SELECT * FROM badge;
```

### Backup database
```bash
pg_dump -U postgres homeworkdb > homework_backup.sql
```

### Restore database
```bash
psql -U postgres homeworkdb < homework_backup.sql
```

## Production Considerations

For production deployment, consider:
- Using strong passwords
- Creating a dedicated database user (not `postgres`)
- Enabling SSL connections
- Setting up automated backups
- Using connection pooling
- Configuring proper access controls

## Free PostgreSQL Hosting Options

If you want to deploy online:
- **Supabase** - Free tier with 500MB storage
- **ElephantSQL** - Free tier with 20MB storage
- **Neon** - Serverless PostgreSQL with free tier
- **Heroku Postgres** - Free tier available
- **Railway** - Free tier with PostgreSQL

All are free to get started! 🚀
