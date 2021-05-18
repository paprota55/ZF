package pl.paprota.zf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel(description = "Details about the postal address")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostalAddress {

    @ApiModelProperty(notes = "The unique id of postal address")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String town;

    private String postalCode;

    private String street;

    @ApiModelProperty(notes = "The unique id of employee")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
