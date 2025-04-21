package com.walerider.pcconfigurer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Getter
    @Column(name = "id", nullable = false)
    private Integer id;

    @Setter
    @Getter
    @Column(name = "username", length = Integer.MAX_VALUE)
    private String username;

    @Setter
    @Getter
    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Setter
    @Getter
    @Column(name = "password", length = Integer.MAX_VALUE)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
