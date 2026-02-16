# pinot-playground

A Java playground project for exploring and experimenting with [Apache Pinot](https://pinot.apache.org), a real-time distributed OLAP datastore designed for low-latency, high-throughput analytics.

## About

This project provides a development environment to learn and experiment with Apache Pinot APIs and features. It includes examples and tests demonstrating various Pinot functionality, such as filesystem abstractions (PinotFS) and proxy patterns.

## Prerequisites

- **Java 17** or higher (configured in `.sdkmanrc` for Java 21)
- **Apache Maven 3.x**

## Getting Started

### Building the Project

To compile the project and download dependencies:

```bash
mvn clean compile
```

### Running Tests

To run the test suite:

```bash
mvn test
```

The project uses JUnit 5 for testing with parallel execution enabled (8 forks).

## Project Structure

```
pinot-playground/
├── src/test/java/io/github/sullis/pinot/playground/
│   ├── PinotTest.java           # Basic Pinot tests
│   ├── PinotFSProxy.java        # Proxy implementation for PinotFS
│   └── PinotFSProxyTest.java    # Tests for PinotFS proxy
├── pom.xml                       # Maven configuration
└── README.md                     # This file
```

## Dependencies

- **Apache Pinot** 1.4.0
  - pinot-common
  - pinot-s3
  - pinot-spi
- **JUnit Jupiter** 6.1.0-M1
- **AssertJ** 4.0.0-M1
- **Apache Commons Lang3** 3.20.0

## What You Can Learn

This playground helps you explore:

- Apache Pinot SPI (Service Provider Interface)
- PinotFS filesystem abstraction
- Proxy patterns for Pinot components
- Testing strategies for Pinot-based applications

## Resources

### Videos
- 2024: [Overview of Pinot 1.1 features](https://www.youtube.com/watch?v=wSwPtOajsGY) - April 2024
- 2023: [Scaling Uber's Metric System from Elasticsearch to Pinot](https://www.youtube.com/watch?v=u82r_eqUaiI) - May 2023
- 2022: [Apache Pinot and Apache Pulsar](https://www.youtube.com/watch?v=lq0-8J3Y7PY) - Devoxx October 2022
- 2022: [Optimizing Speed and Scale of User-Facing Analytics Using Apache Kafka and Pinot](https://www.youtube.com/watch?v=1YU8iU1sKBw) - July 2022
- 2022: [Apache Pinot: OLAP at OLTP scale](https://www.youtube.com/watch?v=2_leJs8VzpQ) - HTAP Summit 2022
- 2020: [History of Apache Pinot](https://www.youtube.com/watch?v=luMLCDANxiU) - May 2020

### Documentation
- [Apache Pinot Official Documentation](https://docs.pinot.apache.org/)
- [Apache Pinot GitHub Repository](https://github.com/apache/pinot)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
