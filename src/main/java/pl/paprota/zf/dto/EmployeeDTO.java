package pl.paprota.zf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Details about employee. Sent from client application. Request data from any endpoints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @ApiModelProperty(notes = "The employee's salary")
    private Double salary;

    @ApiModelProperty(notes = "The employee's name")
    private String name;

    @ApiModelProperty(notes = "The employee's surname")
    private String surname;

    @ApiModelProperty(notes = "The employee's age")
    private Integer age;
}
