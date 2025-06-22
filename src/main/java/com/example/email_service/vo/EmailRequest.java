package com.example.email_service.vo;



public class EmailRequest {

    private String templateName;

    private String content;

    public  String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
