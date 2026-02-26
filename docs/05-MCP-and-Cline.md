# MCP + Cline (Optional)

## Safe usage pattern
- Use Cline to scaffold boilerplate (pages/flows/tests)
- Use MCP tooling to help identify stable selectors and page structure
- Always validate selectors manually in DevTools

## Suggested workflow
1) Build initial POM + waits
2) Capture selector map per page (stable attributes)
3) Implement pages with small, composable actions
4) Implement flow for the business scenario
5) Add assertions + Allure attachments
