# Client and Purchase Management Platform

## Overview

A comprehensive web application for managing client relationships, purchases, and customer service interactions. This platform provides robust tools for customer data management, purchase tracking, and complaint handling, built with modern Java enterprise technologies.

## Features

### Customer Management

- Complete client profile management
- Contact information and personal details storage
- Purchase history tracking
- Client interaction logging

### Purchase System

- Purchase recording and tracking
- Product inventory management
- Transaction history
- Purchase analytics

### Customer Service

- Complaint management system
- Client request tracking
- Response management
- Service history logging

### Security

- Secure authentication system
- Role-based access control
- Protected client data
- Secure session management

## Technology Stack

### Backend

- **Java 17**
- **Spring Boot 3.2.5**
  - Spring Web (REST APIs)
  - Spring Data JPA (Database operations)
  - Spring Security (Authentication & Authorization)
  - Spring Validation (Input validation)
- **Maven** (Dependency management)

### Frontend

- **Thymeleaf** (Server-side templating)
- **HTML5/CSS3**
- **Bootstrap** (Responsive design)
- **JavaScript** (Client-side interactions)

### Database

- **MySQL** (Production database)
- **H2** (Testing and development)

### Development Tools

- **Lombok** (Boilerplate reduction)
- **JUnit** (Testing framework)
- **Git** (Version control)

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6 or later
- MySQL 8.0 or later

### Installation

1. Clone the repository

```bash
git clone https://github.com/yourusername/Platform-de-gestion-des-clients-et-achats.git
```

2. Configure MySQL database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Prjdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project

```bash
mvn clean install
```

4. Run the application

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

### Directory Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── ProjetClient/
│   │       ├── dao/
│   │       │   ├── entities/
│   │       │   └── repositories/
│   │       ├── presentation/
│   │       ├── security/
│   │       └── service/
│   └── resources/
│       ├── static/
│       └── templates/
└── test/
    └── java/
```

### Help and Support

For help and support, please:
- Check the documentation in the `docs` folder
- Create an issue in the repository
- Contact the development team

### Contributing Guidelines

We welcome contributions! Please:
1. Fork the repository
2. Create a feature branch
3. Submit a pull request

### License Information

This project is licensed under the MIT License. See the LICENSE file for details.

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── ProjetClient/
│   │       ├── dao/
│   │       │   ├── entities/
│   │       │   └── repositories/
│   │       ├── presentation/
│   │       ├── security/
│   │       └── service/
│   └── resources/
│       ├── static/
│       └── templates/
└── test/
    └── java/
```

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License - see the LICENSE file for details

## Contact
For any inquiries, please reach out to [seqqam.rida1@gmail.com]-de-gestion-des-clients-et-achats
Développement d'une plateforme de gestion des clients :
      - Création d'une base de données client avec des informations telles que les coordonnées, les achats précédents, etc.
      - Mise en place d'un système de suivi des interactions et des historiques avec les clients
      - Intégration d'un système de gestion des plaintes et des requêtes client
       - Développement d'un système de segmentation et de ciblage des clients pour des campagnes marketing personnalisées

Les technologies à utiliser: 
Spring boot (spring data jpa, spring validation, spring security, spring MVC avec thymeleaf comme un moteur de template)
SGBD: MySQL
