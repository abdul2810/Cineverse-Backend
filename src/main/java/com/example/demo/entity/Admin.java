package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Admin {

    @Id
    private String email;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must not exceed 60")
    private Integer age;

    @Size(min = 4, max = 50, message = "Password must be 4-50 characters")
    @Column(length = 50)
    private String password;


    private String gender;
    private String location;
    private String userType;

    // ✅ No-Args Constructor
    public Admin() {
        super();
    }

    // ✅ All-Args Constructor
    public Admin(String email, String name, Integer age, String password, String gender, String location, String userType) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.location = location;
        this.userType = userType;
    }

    // ✅ Getters and Setters

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Admin [email=" + email + ", name=" + name + ", age=" + age +
                ", gender=" + gender + ", location=" + location +
                ", password=" + password + ", userType=" + userType + "]";
    }
}
