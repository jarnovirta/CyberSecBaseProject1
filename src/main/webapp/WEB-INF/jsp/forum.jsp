
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Very Secure Forum</title>
    </head>
    <body>
        <h1>=== Secure Cyber Security Forum ===</h1>
        
        <c:if test="${user == null}">
            Login
        </c:if>
        <c:if test="${user != null}">
            Post new post in posts
        </c:if>
        <table style="width: 600px;">
            <thead>
                <tr style=" background-color: lightgrey">
                    <th style="padding:10px">User</th>
                    <th style="padding:10px">Post</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${posts}" >
                    <tr>
                        <td style="padding:10px">${post.user.username}</td>
                        <td style="padding:10px">
                            <b>${post.title}</b>
                            <br />
                            ${post.content}
                        </td>
                    </tr>
                </c:forEach>    
            </tbody>
        </table>
    </body>
</html>

