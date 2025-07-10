# MyHadith Project

This is a full-stack Hadith application consisting of both Android frontend and backend components.

## Project Structure

```
MyHadith/
├── androidapp/          # Android application
│   ├── app/            # Main Android app module
│   ├── build.gradle.kts
│   ├── settings.gradle.kts
│   └── README.md       # Android-specific documentation
├── backend/            # Node.js backend server
│   ├── src/           # Source code
│   ├── drizzle/       # Database schema and configuration
│   ├── package.json
│   └── Dockerfile
└── README.md          # This file
```

## Components

### Android App (`androidapp/`)
- Native Android application built with Kotlin
- Modern UI using Jetpack Compose
- Features for browsing and sharing Hadith
- Contact management system
- User authentication with OTP

### Backend (`backend/`)
- Node.js server with TypeScript
- Database management with Drizzle ORM
- API endpoints for Android app
- Dockerized for easy deployment

## Getting Started

### Android App
```bash
cd androidapp
./gradlew build
```

### Backend
```bash
cd backend
npm install
npm start
```

## Development

This repository is now structured to support both frontend and backend development in a single codebase, making it easier to manage the full-stack application.
