package com.example.transactionpractice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "year")
    Integer yearOfCreation;

    @Column(name = "director")
    String director;

    @Column(name = "duration")
    String duration;

    @Column(name = "country")
    String country;

    @Column(name = "description")
    String description;

    @Column(name = "likes")
    Integer likes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_id")
    Cinema cinema;
}
