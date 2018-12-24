
package com.cybersec.cybersec_project1.domain;

public class Post {
    private Long id;
    private String title;
    private String content;
    private Account user;

    public Post() {}
    public Post(Long id, String title, String content, Account user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user; 
    }
    public Post(String title, String content, Account user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
