package pl.paprota.zf.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@ApiModel(description = "More details about employee")
@Entity
@Data
public class Employee extends Person{

    @ApiModelProperty(notes = "The employee's salary")
    private Double salary;

    @ApiModelProperty(notes = "The employee's postal addresses")
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostalAddress> addressList;
}
