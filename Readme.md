Animal Shelter Backend - Spring Boot
Project Description

This project consists of developing the backend of a web application for an animal shelter called Animal Shelter. Its main purpose is to facilitate the pet adoption process through a platform that allows users to see ads for pets looking for a home and make adoptions. In addition, the application allows you to accept donations and has an authentication and authorization system implemented with Spring Security and JWT (JSON Web Token).
Project Objectives

Reinforce API creation concepts: RESTful endpoints were built to manage pets, adoptions, and donations.

Develop an authentication and authorization system with Spring Security and JWT: Users can authenticate to gain additional permissions and manage content.

Functional Requirements
Pet Management

List Pets: Users can get a list of all pets available for adoption.
Pet Details: Consult detailed information about a specific pet.
Add Pets: Admins can add new pets to the system.
Update Pets: Admins can modify existing pet information.
Remove Pets: Administrators can remove pets from the system.

Donation Management

Create Donations: Users can make donations through a form.
List Donations: Administrators can view a list of donations made.
See Donations: View details for a specific donation.  
Edit or Delete Donations: Admins can edit or delete donations from the system.

Authentication and Authorization

Authentication: Login system with Spring Security and JWT.
Authorization:
Users can view pets and make donations without authentication.
Admins have additional permissions to manage pets and donations.

Technologies Used

Java (JDK 11+)
Spring Boot (v2.7.0+)
Spring Security and JWT for authentication and authorization.
Maven as a dependency management tool.
Hibernate for ORM and database management.
Docker for containerization and deployment.
Postman for automated endpoint testing.

Installation and Configuration
Prerequisites

Have Java JDK 11+ installed.
Have Maven installed.
Have Docker installed (optional for containerization).