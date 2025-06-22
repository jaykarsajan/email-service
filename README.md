# 📧 Spring Boot Email Scheduler Service

This is a Spring Boot 3+ application (Java 17) that sends HTML-formatted emails via:

- ✅ A REST API endpoint
- 🕒 A scheduled cron job (daily at 5:40 PM)

---

## 🚀 Features

- Send HTML emails using `JavaMailSender`
- Dynamic content injection into email templates
- Configurable recipients via `application.properties`
- Validated request body using `@Valid` and `@NotBlank`
- Cron-based scheduling for automatic email dispatch
- Logging with SLF4J

---

## 🔧 Technologies Used

- Java 17
- Spring Boot 3+
- Spring Web
- Spring Mail
- Maven

---

## 📦 API Endpoint

### POST `/send`

Send an email manually.

#### Request Body:

```json
{
  "templateName": "default",
  "content": "This is the email body content."
}
