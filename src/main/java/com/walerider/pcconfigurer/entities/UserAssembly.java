package com.walerider.pcconfigurer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_assemblies")
public class UserAssembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "assembly", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAssemblyComponents> userAssemblyComponents;

    @Column(name = "price")
    private Integer price;

    public UserAssembly(String name, User user,Integer price) {
        this.name = name;
        this.user = user;
        this.price = price;
    }

    @Override
    public String toString() {
        return "UserAssembly{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user.toString() +
                '}';
    }
}
