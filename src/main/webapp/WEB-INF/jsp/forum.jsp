
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Very Secure Forum</title>
    </head>
    <body>
        <h1>=== Secure Cyber Security Forum ===</h1>
        
        <sec:authentication property="principal.username" var="username" />
        <sec:authorize access="hasAuthority('ADMIN')" var="isAdmin" />
        
        <sec:authorize access="isAnonymous()">
            <a href="/login">Login</a>            
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <br />
            <p>Logged in as <i>${username}</i>&nbsp; 
                <a href="/logout">Logout</a></p>
            <h3>New post:</h3>
            <form action="/posts" method="POST">
                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                <label for="title">Title</label>
                <input type="text" name="title" id="title" />
                <label for="content">Content</label>
                <input type="text" name="content" id="content" />
                <input type="submit" value="Post" />
            </form>
        </sec:authorize>
            <h3>Search posts:</h3>
            <c:if test="${searchTerm != null}">
                <h4>results for: '${searchTerm}'</h4>
            </c:if>
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
                    <sec:authorize access="isAuthenticated()">
                       <th style="padding:10px">Delete</th>
                    </sec:authorize>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${posts}" >
                    <c:set var="canDelete" value="false" />
                    <tr>
                        <td style="padding:10px">${post.user.username}</td>
                        <td style="padding:10px">
                            <b>${post.title}</b>
                            <br />
                            ${post.content}
                        </td>
                        <sec:authorize access="isAuthenticated()">
                            <td style="padding:10px">
                                <c:if test="${username == post.user.username || isAdmin}">
                                    <form action="/posts/delete" method="POST">
                                        <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="id" value="${post.id}" />
                                        <input type="submit" value="Delete" />
                                    </form>
                                </c:if>
                            </td>
                         </sec:authorize>
                    </tr>
                </c:forEach>    
            </tbody>
        </table>        
    </body>    
</html>