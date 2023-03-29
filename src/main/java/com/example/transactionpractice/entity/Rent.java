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
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "row")
    String row;

    @Column(name = "place")
    String place;

    @Column(name = "is_rent")
    Boolean isRent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusRent statusRent;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    Movie movie;
}
