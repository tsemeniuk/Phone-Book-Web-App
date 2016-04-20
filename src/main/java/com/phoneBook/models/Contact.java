package com.phoneBook.models;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Size(min = 4)
    @Column(name = "firstname")
    private String firstName;

    @NotEmpty
    @Size(min = 4)
    @Column(name = "secondname")
    private String secondName;

    @NotEmpty
    @Size(min = 4)
    @Column(name = "lastname")
    private String lastName;

    @Pattern(regexp = "^((8|\\+38)-?)?(\\(?\\d{3}\\)?)\\s?-?\\d{3}-?\\d{2}-?\\d{2}$")
    @NotEmpty
    @Column(name = "phonemobile")
    private String phoneMobile;

    @Pattern(regexp = "^((8|\\+38)-?)?(\\(?\\d{3}\\)?)\\s?-?\\d{3}-?\\d{2}-?\\d{2}$")
    @Column(name = "phonehome")
    private String phoneHome;

    @Column(name = "address")
    private String address;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    public Contact() {

    }

    public Contact(String firstName, String secondName, String lastName, String phoneMobile, String phoneHome, String address, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneMobile = phoneMobile;
        this.phoneHome = phoneHome;
        this.address = address;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", phoneHome='" + phoneHome + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}