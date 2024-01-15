package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accidents")
@Getter
@Setter
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "ACCIDENT_TYPE_ID_FK"))
    private AccidentType type;
    @ManyToMany
    @JoinTable(name = "accidents_rules",
    joinColumns = {@JoinColumn(name = "accident_id")},
    inverseJoinColumns = {@JoinColumn(name = "rule_id")})
    private Set<Rule> rules = new HashSet<>();
}