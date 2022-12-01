package com.example.studentinternshipapp;

public class Recruiter {
    private String email,password,company;

    public Recruiter(){

    }
    public Recruiter(String email, String password, String company) {
        this.email = email;
        this.password = password;
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
