# CyberSecBaseProject1

Project I for Helsinki Open University/F-Secure [ Cyber Security Base 2018 Course (Part III)](https://cybersecuritybase.mooc.fi/project/).

## 1. The Assignment:

In the first course project, your task is to create a web application that has at least five different flaws from the [OWASP Top Ten List](https://www.owasp.org/images/7/72/OWASP_Top_10-2017_%28en%29.pdf.pdf). 

You will then write a brief (1000 words) report that outlines how the flaws can be first identified and then fixed. For the identification process, we suggest that you use tools that have been used in the course, such as Owasp ZAP. 


## 2. Project Report:
### Installing and running the application:

The application is a Java Spring Boot application, with server responding on port 8080.
1. Clone the repository from https://github.com/jarnovirta/CyberSecBaseProject1.git
2. Start the application by clicking Run in the Netbeans IDE or from command line with `mvn spring-boot:run`. 
3. Navigate to http://localhost:8080/


### Issue 1: Broken authentication (hard-coded weak admin password), OWASP Top 10 A2
#### Steps to reproduce:

1. Go to http://localhost:8080/
2. You will see by the existing post that there is an admin account with username 'admin'.
3. The correct password can be found using a fuzzer (OWASP ZAP) and a list of common passwords (the correct password is 'password') as follows:
    - using ZAP as a proxy to log traffic, navigate to localhost:8080/login
    - try to log in as 'admin' with some random password
    - in ZAP, in the "History" tab, select the POST request generated by the login attempt and use it as a basis for the fuzzer to run through the password guesses. 
    - in the fuzzer results, look for the *first* response with HTTP code 302 and a JSESSIONID cookie
    - the correct password is the password value in the corresponding request. 
4. An additional problem may be the fact that the username 'admin' and the
password hash are hard-coded. 

#### Steps to fix the issue:

1. Use a stronger password for the admin account.
2. It is not advisable to include hard-coded admin credentials in source code as they may be discovered on GitHub. In this case, the password is hashed using Bcrypt and it should therefore be safe though. A better solution would be to insert admin account username and password hash into the database manually upon installing the app, or to include such data in a separate file which is excluded from the Git repository.


### Issue 2: Cross-site scripting vulnerability, OWASP Top 10 A7
#### Steps to reproduce:

1. Navigate to http://localhost:8080/
2. Click 'Login' and log in as username 'admin', password 'password'
3. Under 'New post' enter into the 'Title' or the 'Content' input field the following:
      `<script>alert("You have been pwned!");</script>`
4. Click 'Post'
5. The page refreshes, the JavaScript is executed and an alert appears in your
browser window. 

#### Steps to fix the issue: 

HTML (such as <script> tags) needs to be escaped in the app's web views when rendering user provided content. 

Most view template frameworks provide tools for escaping HTML in rendered data.

In my project, with JSP's, the fix would be as follows:
1. Open webapp/WEB-INF/jsp/forum.jsp
2. Replace `${post.title}` and `${post.content}` with `<c:out value="${post.title}" />`
and `<c:out value="${post.content}" />`


### Issue 3: Broken access control (user can delete another user's post), OWASP Top 10 A5
#### Steps to reproduce:

1. Open your browser's developer tools view (/console) and the "Network" tab.
2. Navigate to localhost:8080/ and click on "Login"
3. Log in as 'kevinmitnick', password '123456'
4. Add a post. A 'Delete' button is shown next to Kevin's new post, not next to the Admin's post. Kevin is not an admin.
5. Open an HTTP request in the developer tools / network tab and copy the value of the JSESSIONID session cookie.
6. Using an application for generating HTTP requests (I use the ARC add-on in Chrome), make a POST request to localhost:8080/posts/delete (it should be delete but I took a shortcut because a DELETE request would require using an AJAX request which would require tinkering with the CSRF token...). Set header `Content-Type` to `application/x-www-form-urlencoded`. In request body, set parameter "id" to 1 (`id=1`).
7. Refresh your browser view. Kevin was able to delete a post by user 'admin'.

#### Steps to fix the issue:

Access should be controlled on the server side. The way to fix the issue would be to check in PostController.java that the post to be deleted was made by the user deleting it or the user is an admin user. Never trust requests or data coming from the user but instead apply the necessary controls on the server side!


### Issue 4: SQL injection, OWASP Top 10 A1
#### Steps to reproduce:

1. Go to localhost:8080/frontPage
2. Enter the following into the 'Search for' input field:
    `'; DROP TABLE accounts; --`
3. Click 'Search'
4. The ACCOUNTS table has been deleted from database and you cannot log in (username 'admin', password 'password')

#### Steps to fix the issue:

Do not ever concatenate an SQL query string! Always use parameterized queries. They allow for a separation of commands and user input so that the latter cannot be interpreted as commands by the database engine. In my code I use basic Java JDBC code with concatenated query strings. The code could be fixed by using [java.sql.PreparedStatement](https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)

Basically any library or framework will have standard query methods which allow you to prevent this vulnerability. It was actually quite a lot of work to include this vulnerability as the Spring Boot framework uses by default the Hibernate JPA library and it does not seem to allow multiple SQL (or rather JPQL) statements to be run at once, separated by a semicolon. That's why I used basic JDBC instead of JPA. What is possible to do with Hibernate, though, is to inject malicious SQL such as `' OR 1=1`into a SELECT query to get too many results from the database as long as the injection is a single JPQL statement. I wanted to go for the nuclear option of DROP TABLE, though.


### Issue 5: Insufficient logging, OWASP Top 10 A10

My application very little logging and the log is not saved to a file. It would be impossible to know if the site has been hacked, when, how, by whom and what the resulting damage might be. Also, any non-intentional problems which might be due to bugs or misconfigurations, would not be easily spotted. 

#### Steps to fix the issue:

1. Configure sufficient logs to be written to a file. In Spring Boot this can be done by adding the logging.file property to the application.properties file ([instructions](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-logging.html)
under 'File output')
2. Configure sufficient logging levels. For Spring Boot see for example the instructions [here](https://stackoverflow.com/questions/20485059/spring-boot-how-can-i-set-the-logging-level-with-application-properties)
3. Configure logs to include information on incoming HTTP requests. With Spring this is best done by configuring an interceptor to intercept all incoming requests. See for example [here](https://www.baeldung.com/spring-http-logging).

In my project, adding these configurations to application.properties would be a start:
```
logging.level.org.springframework.web=ERROR  
logging.level.com.cybersec.cybersec_project1=DEBUG  
logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n  
logging.pattern.file= %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n  
logging.file=logs/application.log 
```

