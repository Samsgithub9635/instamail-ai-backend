# InstaMail AI - Backend Service

![Java](https://img.shields.io/badge/Java-17-blue.svg?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-success.svg?style=for-the-badge&logo=spring)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red.svg?style=for-the-badge&logo=apache-maven)
![Docker](https://img.shields.io/badge/Docker-blue?style=for-the-badge&logo=docker&logoColor=white)

This repository contains the backend service for **InstaMail AI**, a full-stack application designed to provide intelligent, context-aware email reply suggestions. This service is built with Java and the Spring Boot framework, exposing a secure REST API for the frontend client.

**Live API Endpoint:** `[(https://instamail-ai.onrender.com/)]`  

---

## ✨ Features

* **RESTful API:** A clean and simple API for generating email replies.
* **AI Integration:** Securely connects to the Google Gemini API to power intelligent text generation.
* **Scalable & Robust:** Built with Spring Boot, ensuring a reliable and maintainable codebase.
* **Containerized:** Fully containerized with Docker for consistent and portable deployments.
* **CI/CD Ready:** Deployed on Render with auto-deploys triggered by commits to the `main` branch.

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Build Tool:** Apache Maven
* **Core Dependencies:**
    * `Spring Web`: For building REST APIs.
    * `Spring WebFlux`: For making non-blocking, reactive API calls to the Gemini service.
    * `Lombok`: To reduce boilerplate code.
* **Testing:** JUnit 5
* **Deployment:** Docker, Render

---

## 🔌 API Documentation

The service exposes one primary endpoint for generating email replies.

### Generate Email Reply

* **URL:** `/api/email/generate`
* **Method:** `POST`
* **Description:** Generates a suggested email reply based on the provided email content and a desired tone.

#### Request Body (`application/json`)

| Field          | Type     | Description                                     | Required |
| -------------- | -------- | ----------------------------------------------- | -------- |
| `emailContent` | `String` | The text content of the original email.         | Yes      |
| `tone`         | `String` | The desired tone for the reply (e.g., "Professional"). | Yes      |

**Example Request:**
```json
{
  "emailContent": "Hi, thank you for your application. We'd like to schedule an interview for next week. Please let us know your availability.",
  "tone": "Professional"
}

Success Response (200 OK)
Content-Type: text/plain

Body: A String containing the generated email reply.

Example Response:

Thank you for reaching out. I would be delighted to interview next week. I am generally available on Monday and Wednesday afternoons. Please let me know what time works best for you.

Best regards,
[Your Name]

⚙️ How to Run Locally
Follow these instructions to get the project running on your local machine for development and testing.

Prerequisites
Java Development Kit (JDK) 17 or later.

Apache Maven 3.8 or later.

A Google Gemini API Key.

Installation & Setup
Clone the repository:

git clone [https://github.com/Samsgithub9635/instamail-ai-backend.git](https://github.com/Samsgithub9635/instamail-ai-backend.git)
cd instamail-ai-backend

Configure Environment Variables:
This project uses environment variables to handle secret keys, which is a security best practice. You can configure these in your IDE's Run Configuration.

In IntelliJ IDEA, go to Run -> Edit Configurations....

Find the "Environment variables" field and add the following:

GEMINI_URL: https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent

GEMINI_KEY: [Your Secret Gemini API Key]

Build the project:

mvn clean install

Run the application:
You can run the main application class InstaMailAiApplication.java from your IDE, or use the Maven command:

mvn spring-boot:run

The application will start on http://localhost:8080.

🚀 Deployment
This service is configured for continuous deployment on Render using a multi-stage Dockerfile.

The deployment configuration is defined in the render.yaml file.

Any push to the main branch will automatically trigger a new build and deployment on Render.

Secret keys are managed securely as environment variables in the Render dashboard.

📂 Project Structure
.
├── .mvn/               # Maven Wrapper files
├── src/
│   ├── main/
│   │   ├── java/       # Main application source code
│   │   └── resources/  # application.properties
│   └── test/           # Test source code
├── .dockerignore       # Files to ignore in the Docker build
├── .gitignore          # Files to ignore for Git
├── Dockerfile          # Instructions for building the Docker container
├── mvnw                # Maven Wrapper executable (Linux/Mac)
├── mvnw.cmd            # Maven Wrapper executable (Windows)
├── pom.xml             # Maven project configuration
└── render.yaml         # Render deployment configuration
