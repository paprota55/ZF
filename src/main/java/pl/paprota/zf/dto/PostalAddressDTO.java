package pl.paprota.zf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "Details about postal address. Sent from client application. Request data from any endpoints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {

    @ApiModelProperty(notes = "Cannot be null or blank")
    @NotNull(message = "Provide a valid town. Cannot be null")
    @NotBlank(message = "Provide a valid town. Cannot be blank")
    private String town;

    @ApiModelProperty(notes = "Pattern for postal code is XX-XXX")
    @Pattern(message = "You entered the wrong zip code template is: XX-XXX", regexp = "[0-9]{2}\\-[0-9]{3}")
    private String postalCode;

    @ApiModelProperty(notes = "Cannot be null or blank")
    @NotNull(message = "Street cannot be null")
    @NotBlank(message = "Provide a valid street. Cannot be blank")
    private String street;

    @ApiModelProperty(notes = "The unique id of employee cannot be null")
    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;
}
