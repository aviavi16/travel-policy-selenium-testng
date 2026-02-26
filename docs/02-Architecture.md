# Architecture

## Goals
- Keep tests readable and stable
- Avoid flakiness with explicit waits
- Separate concerns (driver / pages / flows / tests)

## Layers
- `driver/`: driver lifecycle, ThreadLocal, chrome options
- `pages/`: page objects (locators + actions)
- `flows/`: higher-level scenario steps (business logic)
- `tests/`: assertions and orchestration
- `utils/`: date & wait helpers

## Why Flows?
Flows keep tests short:
- Tests express *intent* and validations
- Flows handle the detailed steps
