<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>选择Cron表达式</title>
    <script src="/static/plugins/cron/jquery-2.1.4.min.js"></script>
    <script src="/static/plugins/cron/bootstrap.min.js"></script>
    <link href="/static/plugins/cron/font/font-awesome.min.css" rel="stylesheet">
    <link href="/static/plugins/cron/bootstrap.min.css" rel="stylesheet">
    <script src="/static/plugins/cron/cronGen.css"></script>
    <script src="/static/plugins/cron/cronGen.js"></script>
</head>
<body>
<div class="container">
    <form role="form" class="form-inline">
        <div class="form-group">
            <label for="cron">Cron</label>
            <input id="cron" class="form-control" />
        </div>
    </form>
</div>
</body>
<script>
    $(document).ready(function() {
        $("#cron").cronGen({
            direction : 'right'
        });
    });
</script>
</html>
