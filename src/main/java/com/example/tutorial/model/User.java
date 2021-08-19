package com.example.tutorial.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String email;

  public long getId() {
        return id;
    }

  public void setId(long id) {
        this.id = id;
    }

  public String getFirstName() {
        return firstName;
    }

  public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

  public String getLastName() {
        return lastName;
    }

  public void setLastName(String lastName) {
        this.lastName = lastName;
    }

  public String getEmail() {
        return email;
    }

  public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
