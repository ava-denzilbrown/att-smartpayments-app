package com.att.smartpayments;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Runtime information displayed on the portal and returned by /health.
 *
 * Application version and environment are resolved from (in order):
 * Java system property, environment variable, default value.
 */
public final class SystemInfo {

    private SystemInfo() {
    }

    public static String appVersion() {
        return setting("app.version", "APP_VERSION", "1.0.0");
    }

    public static String environment() {
        return setting("app.environment", "APP_ENVIRONMENT", "local");
    }

    public static String hostname() {
        String host = System.getenv("HOSTNAME");
        if (host == null || host.isBlank()) {
            host = System.getenv("COMPUTERNAME");
        }
        if (host == null || host.isBlank()) {
            try {
                host = InetAddress.getLocalHost().getHostName();
            } catch (IOException e) {
                host = "unknown";
            }
        }
        return host;
    }

    public static String buildVersion() {
        Properties props = new Properties();
        try (InputStream in = SystemInfo.class.getResourceAsStream("/build-info.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            // fall through to default
        }
        return props.getProperty("build.version", "unknown");
    }

    private static String setting(String propertyName, String envName, String defaultValue) {
        String value = System.getProperty(propertyName);
        if (value == null || value.isBlank()) {
            value = System.getenv(envName);
        }
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    /** Escapes text for safe inclusion in HTML. */
    public static String html(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            switch (c) {
                case '&' -> sb.append("&amp;");
                case '<' -> sb.append("&lt;");
                case '>' -> sb.append("&gt;");
                case '"' -> sb.append("&quot;");
                case '\'' -> sb.append("&#39;");
                default -> sb.append(c);
            }
        }
        return sb.toString();
    }

    /** Escapes text for safe inclusion in a JSON string value. */
    public static String json(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                default -> {
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
