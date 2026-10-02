# Smart Payments POC instructions

- Follow the existing Java 21/Maven WAR and Tomcat 10.1+ (Jakarta Servlet) structure. This is a static-data demo portal, not a real payment, authentication, database, or integration service; see [README.md](../README.md) for layout and runtime details.
- Make only changes required by the current request. Do not refactor unrelated code, or rename existing resources, files, variables, workflows, or components unless specifically requested. When asked to change one setting, change only that setting unless another change is strictly required.
- Preserve the existing architecture and repository structure unless explicitly asked to change them. Keep the POC intentionally simple and within its established scope; do not introduce frameworks, dependencies, Azure services, or tools unless necessary, and explain why before doing so.
- Preserve AT&T POC naming, including the `att` prefix (for example, `att-smartpayments-app` and `com.att.smartpayments`), and existing `smartpayments` artifact and resource names.
- Build with `mvn clean package`; the expected output is `target/smartpayments.war`. Preserve the existing GitHub Actions build, artifact upload, and Azure Blob upload workflow unless the request requires a change. Do not add deployment steps without a specific request.
- Preserve working authentication and security configuration unless specifically asked to modify it. Never introduce passwords, client secrets, access keys, SAS tokens, or other long-lived credentials into source code. Prefer OIDC/workload identity for Azure automation; the current workflow uses GitHub OIDC and Azure CLI `--auth-mode login` for storage.
- Do not execute destructive or billable infrastructure actions unless explicitly requested.
- Before changing an existing working configuration to resolve an issue, identify the cause first and prefer the smallest corrective change.
