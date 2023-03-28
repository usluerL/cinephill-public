package com.byusluer.movierest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="user_profile")
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="notified", columnDefinition = "boolean default false")
    private boolean notified;
}
