<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.att.smartpayments.Payment, com.att.smartpayments.SystemInfo" %>
<%
    long pending = Payment.SAMPLE.stream().filter(p -> p.status().equals("Pending")).count();
    long completed = Payment.SAMPLE.stream().filter(p -> p.status().equals("Completed")).count();
    long failed = Payment.SAMPLE.stream().filter(p -> p.status().equals("Failed")).count();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Smart Payments</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="header">
    <div class="container header-inner">
        <h1>Smart Payments</h1>
        <span class="env-badge"><%= SystemInfo.html(SystemInfo.environment()) %></span>
    </div>
</header>

<main class="container">
    <section class="cards">
        <div class="card">
            <div class="card-label">Payments Today</div>
            <div class="card-value"><%= Payment.SAMPLE.size() %></div>
        </div>
        <div class="card pending">
            <div class="card-label">Pending</div>
            <div class="card-value"><%= pending %></div>
        </div>
        <div class="card completed">
            <div class="card-label">Completed</div>
            <div class="card-value"><%= completed %></div>
        </div>
        <div class="card failed">
            <div class="card-label">Failed</div>
            <div class="card-value"><%= failed %></div>
        </div>
    </section>

    <section class="panel">
        <h2>Recent Payments</h2>
        <table>
            <thead>
            <tr><th>Payment ID</th><th>Customer</th><th class="num">Amount</th><th>Status</th></tr>
            </thead>
            <tbody>
            <% for (Payment p : Payment.SAMPLE) { %>
            <tr>
                <td><%= p.id() %></td>
                <td><%= p.customer() %></td>
                <td class="num"><%= p.amount() %></td>
                <td><span class="status <%= p.status().toLowerCase() %>"><%= p.status() %></span></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>

    <section class="panel">
        <h2>System Information</h2>
        <dl class="sysinfo">
            <dt>Application Version</dt><dd><%= SystemInfo.html(SystemInfo.appVersion()) %></dd>
            <dt>Environment</dt><dd><%= SystemInfo.html(SystemInfo.environment()) %></dd>
            <dt>Hostname</dt><dd><%= SystemInfo.html(SystemInfo.hostname()) %></dd>
            <dt>Build Version</dt><dd><%= SystemInfo.html(SystemInfo.buildVersion()) %></dd>
        </dl>
    </section>
</main>

<footer class="footer">
    <div class="container">Smart Payments &mdash; demonstration application. Sample data only.</div>
</footer>
</body>
</html>
