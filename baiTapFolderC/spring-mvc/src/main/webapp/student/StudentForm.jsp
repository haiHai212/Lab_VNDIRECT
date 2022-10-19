<%--
  Created by IntelliJ IDEA.
  User: phung
  Date: 9/29/2022
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New Student</title>
</head>
<body>
<h2>Please input Student information</h2>
<form:form method="post" action="save">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" /><form:errors path="name"/></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><form:input path="age" type="number"/> <form:errors path="age"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <form:button>Save</form:button>
            </td>
        </tr>
    </table>
</form:form>
<c:if test="${id!=null}">
    <h1>Please upload a image</h1>
    <form method="post" action="../avatar/save" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${id}">
        <input type="file" name="file">
        <input type="submit" value="Upload">
    </form>
</c:if>
</body>
</html>
