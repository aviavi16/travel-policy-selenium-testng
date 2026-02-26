# Selectors & Waits

## Selector priority
1. `data-testid` (best)
2. `aria-label` / `role`
3. stable `id`
4. text-based XPath (Hebrew UI)
5. avoid brittle long XPaths

## Wait policy
- Use `WebDriverWait` + `ExpectedConditions`
- No `Thread.sleep`
- Wrap element interactions using `Waiter`
