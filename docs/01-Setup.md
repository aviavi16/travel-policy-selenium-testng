# Setup

## Prerequisites
- Java 22+
- Maven 3.9+
- Google Chrome

## Install & run
```bash
mvn test
```

## Headless
```bash
mvn test -Dheadless=true
```

## Troubleshooting
- If Chrome cannot start on CI: ensure `--no-sandbox` and `--disable-dev-shm-usage` are present (they are included in this template).
- If element actions are flaky: update selectors to stable attributes (preferred) and rely on explicit waits only.
