package pl.paprota.zf.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private Integer age;
}
