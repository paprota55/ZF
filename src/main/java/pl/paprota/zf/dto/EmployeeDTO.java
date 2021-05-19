package pl.paprota.zf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@ApiModel(description = "Details about employee. Sent from client application. Request data from any endpoints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @ApiModelProperty(notes = "The employee's salary. Minimum value is 0")
    @Min(value = 0,message = "Provide a valid salary. Minimum value is 0")
    private Double salary;

    @ApiModelProperty(notes = "The employee's name. Cannot be null or blank")
    @NotNull(message = "Provide a valid name. Cannot be null")
    @Size(min = 1, max = 50, message = "Name size should be between 1 and 50")
    private String name;

    @ApiModelProperty(notes = "The employee's surname. Cannot be null or blank")
    @NotNull(message = "Provide a valid surname. Cannot be null")
    @Size(min = 1, max = 50, message = "Surname size should be between 1 and 50")
    private String surname;

    @ApiModelProperty(notes = "The employee's age. Cannot be null, should be between 16 and 150")
    @NotNull(message = "Provide a valid age. Cannot be null")
    @Min(value = 16, message = "Age should be greater than 16")
    @Max(value = 150, message = "Age should be less than 150")
    private Integer age;
}
