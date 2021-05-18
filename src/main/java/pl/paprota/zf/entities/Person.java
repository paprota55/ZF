package pl.paprota.zf.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(description = "Details about person")
@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person {
    @ApiModelProperty(notes = "The unique id of person")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "The person's name")
    private String name;

    @ApiModelProperty(notes = "The person's surname")
    private String surname;

    @ApiModelProperty(notes = "The person's age")
    private Integer age;
}
