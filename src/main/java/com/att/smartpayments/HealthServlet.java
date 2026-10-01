package com.att.smartpayments;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Health endpoint used by load balancers and blue/green deployment checks. */
@WebServlet("/health")
public class HealthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-store");
        resp.getWriter().write(String.format(
                "{\"status\":\"UP\",\"version\":\"%s\",\"environment\":\"%s\",\"hostname\":\"%s\"}",
                SystemInfo.json(SystemInfo.appVersion()),
                SystemInfo.json(SystemInfo.environment()),
                SystemInfo.json(SystemInfo.hostname())));
    }
}
