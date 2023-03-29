package com.example.transactionpractice.entity;


import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "repertoire_of_sessions")
    Date reportoireOfSessions;

    @Column(name = "likes")
    Integer likes;
}
