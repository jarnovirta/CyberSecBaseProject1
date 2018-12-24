
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
            <a href="/login">Login</a>            
        </c:if>
        <c:if test="${user != null}">
            <br />
            <h3>New post:</h3>
            <form action="/posts" method="POST">
                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                <label for="title">Title</label>
                <input type="text" name="title" id="title" />
                <label for="content">Content</label>
                <input type="text" name="content" id="content" />
                <input type="submit" value="Post" />
            </form>
            
        </c:if>
            <h3>Search posts:</h3>
            <form action="/search" method="GET">
                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                <label for="searchTerm">Search for</label>
                <input type="text" name="searchTerm" id="title" />
                <input type="submit" value="Search" type="button"/>
                <c:url value="/" var="url" />
                <input type="button" onclick="location.href='${url}';" value="Clear" />
                
            </form>        
        <br /><br />
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

