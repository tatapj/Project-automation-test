# Project Automation Test

Automation testing framework menggunakan Java, Gradle, Selenium, REST Assured, dan Cucumber.

## Test Coverage

### API Testing
API testing menggunakan REST Assured dan Cucumber dengan endpoint DummyAPI.

### Web UI Testing
Web UI testing menggunakan Selenium WebDriver dan Cucumber pada DemoBlaze.

## Project Structure

```text
src
└── test
    ├── java
    │   └── com.automation
    │       ├── api
    │       │   ├── runner
    │       │   └── steps
    │       └── web
    │           ├── runner
    │           ├── steps
    │           └── pages
    │
    └── resources
        └── features
            ├── api
            └── web