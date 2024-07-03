<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    chkMessage1
    <spring:message code="hello" text="33"/>
    <spring:message code="hello.title" text="-"/>
</body>
</html>