# Travel Policy UI Automation Template (Java + Selenium 4 + TestNG)

A production-style template repo for UI automation:
- **Java 22 + Maven**
- **Selenium 4**
- **TestNG**
- **WebDriverManager**
- **Allure reporting**
- **GitHub Actions CI** (runs tests + publishes Allure report to **GitHub Pages**)

> Target site (default): https://digital.harel-group.co.il/travel-policy

---

## Quick start

### Prerequisites
- Java 22+
- Maven 3.9+
- Google Chrome installed

### Run locally (headed)
```bash
mvn test
```

### Team-friendly Windows run (neutralize global `JAVA_TOOL_OPTIONS` noise)
```powershell
.\run-tests.ps1
```

Headless:
```powershell
.\run-tests.ps1 test -Dheadless=true
```

Debug flow logs (local, only when needed):
```powershell
.\run-tests.ps1 test -Dheadless=true -DdebugFlow=true
```

> This clears `JAVA_TOOL_OPTIONS` only for this run process (does not change machine/global settings).
> Flow debug logs are disabled by default and enabled only with `-DdebugFlow=true`.

### Run locally (headless)
```bash
mvn test -Dheadless=true
```

### Override base URL
```bash
mvn test -DbaseUrl=https://digital.harel-group.co.il/travel-policy
```

### Generate & view Allure report locally
1) Run tests:
```bash
mvn test
```

2) Generate report:
```bash
mvn -DskipTests=true allure:report
```

3) Open the report (from `target/site/allure-maven-plugin/`):
- Open `index.html` in a browser

---

## Project structure

```
src/test/java
  config/      - runtime configuration (url/headless/timeouts)
  driver/      - driver lifecycle + ThreadLocal
  pages/       - Page Objects (UI interactions only)
  flows/       - business flows (scenario steps)
  tests/       - TestNG tests (assertions + orchestration)
  utils/       - waits, date utils, common helpers
src/test/resources
  testng.xml   - TestNG suite
  logback.xml  - logging
docs/          - step-by-step documentation
.github/       - CI workflow
```

---

## CI + Cloud report hosting

This repo includes a GitHub Actions workflow that:
1. Runs the tests on every push/PR
2. Generates an Allure report
3. Publishes it to GitHub Pages

### Which workflow to check
- **Run Tests (PR Signal)**
  - Purpose: fast cloud test signal (pass/fail) on PRs and non-main pushes
  - Where to see logs: **Actions → Run Tests (PR Signal) → test → Run tests**

- **Publish Test Report (Allure → Pages)**
  - Purpose: run tests on `main`, generate/publish Allure HTML to Pages
  - Trigger: push to `main` or manual `workflow_dispatch`
  - Optional manual input: `debug_flow=true` to enable `[FLOW]` / `[DATES]` logs

Latest report URL:
- `https://aviavi16.github.io/travel-policy-selenium-testng/#`

### Enable GitHub Pages
1. Go to **Settings → Pages**
2. Source: **Deploy from a branch**
3. Branch: `gh-pages` / root

After the first CI run, you'll get a public report URL.

---

## Notes about selectors

The page objects use a **selector strategy** with TODO markers:
- Prefer stable attributes (e.g. `data-testid`, `aria-label`)
- Fallback to text-based XPath for Hebrew UI
- Avoid brittle full XPaths

Update selectors in:
- `src/test/java/pages/*`

---

## License
MIT
