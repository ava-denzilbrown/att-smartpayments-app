# att-smartpayments-app

**Smart Payments** is a deliberately small proof-of-concept web application: a fictional
payments operations portal. It exists only to produce a WAR artifact for demonstrating an
enterprise CI/CD, image-build (Packer / Azure Compute Gallery), HCP Terraform, Azure VM and
blue/green deployment process.

> It contains **no** real payment processing, authentication, database or external integrations.
> All payment data is static sample data.

## Requirements

- Java 21 (JDK)
- Maven 3.9+
- Apache Tomcat 10.1+ (Jakarta Servlet 6 / `jakarta.*` namespace)

## Project layout

```
pom.xml
src/main/java/com/att/smartpayments/
    HealthServlet.java    # GET /health -> JSON health status
    Payment.java          # static sample payment data
    SystemInfo.java       # version / environment / hostname / build version
src/main/resources/build-info.properties   # build version (filled in by Maven)
src/main/webapp/
    index.jsp             # the portal page
    css/style.css
    WEB-INF/web.xml
```

## Build

```bash
mvn clean package
```

The WAR is created at `target/smartpayments.war`.

The build version shown on the page defaults to `<project.version>-<timestamp>`. CI pipelines
can override it, for example:

```bash
mvn clean package -Dbuild.version=1.0.0-${GITHUB_RUN_NUMBER}
```

## Deploy to Tomcat

1. Copy the WAR into Tomcat's `webapps` directory:

   ```bash
   cp target/smartpayments.war $CATALINA_HOME/webapps/
   ```

   (To serve the app at the root context `/`, copy it as `webapps/ROOT.war` instead.)

2. Start Tomcat:

   ```bash
   $CATALINA_HOME/bin/startup.sh
   ```

3. Open:
   - Portal: <http://localhost:8080/smartpayments/>
   - Health: <http://localhost:8080/smartpayments/health>

## Configuration

| Setting             | Java system property | Environment variable | Default |
|---------------------|----------------------|----------------------|---------|
| Application version | `app.version`        | `APP_VERSION`        | `1.0.0` |
| Environment         | `app.environment`    | `APP_ENVIRONMENT`    | `local` |

System properties take precedence over environment variables. With Tomcat, set them in
`$CATALINA_HOME/bin/setenv.sh`, e.g.:

```bash
export APP_VERSION=1.0.0
export APP_ENVIRONMENT=green
# or
export CATALINA_OPTS="$CATALINA_OPTS -Dapp.version=1.0.0 -Dapp.environment=green"
```

The **hostname** is resolved at runtime from the server (`HOSTNAME`/`COMPUTERNAME`
environment variable, falling back to the local host name), so you can see whether the
Blue or Green VM is serving the request.

## Health endpoint

`GET /health` returns HTTP 200:

```json
{"status":"UP","version":"1.0.0","environment":"green","hostname":"vm-smartpayments-green"}
```
