# Shopping Application

## Project Description
This project, "Shopping Application," is a practical implementation showcasing the use of Spring technologies within a microservices architecture. It's designed as a multi-module Maven project, demonstrating the integration and functioning of various services in a shopping application context.

## Project Structure
The application is divided into several services, each representing a core functionality of the shopping application:
- **Inventory Service**: Manages inventory-related operations.
- **Order Service**: Handles order processing and management.
- **Product Service**: Deals with product information and cataloging.
- **API Gateway**: Facilitates routing and load balancing of microservices using Spring Cloud Gateway.

Each service, including the Discovery Server and API Gateway, operates as an independent module, highlighting the decoupled nature of microservices.

## Technologies Used
- **Java**: The primary programming language.
- **Spring Framework**: Used for building the services in a modular and efficient manner.
- **Spring Eureka Server**: For service discovery and registration in the `discovery-server`.
- **Spring Cloud Gateway**: Employed in the `api-gateway` for routing and load balancing.
- **Maven**: For dependency management and project building.
- **Docker Compose**: For orchestrating database containers and services.

## Getting Started
1. **Clone the Repository**: Execute `git clone https://github.com/devluisvargas/shopping-application.git` to obtain the project.
2. **Build the Project**: Use Maven for building the project. For example, run `mvn clean install`.
3. **Run with Docker Compose**: Start the services using `docker-compose up`.

## Contributing
Contributions to enhance or improve the project are warmly welcomed. To contribute:
1. Fork the repository.
2. Create a new branch for your changes.
3. Make your changes and perform tests.
4. Submit a pull request with a detailed description of your modifications.