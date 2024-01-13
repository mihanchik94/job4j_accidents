package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String authority;
}