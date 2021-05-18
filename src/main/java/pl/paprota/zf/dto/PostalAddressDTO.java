package pl.paprota.zf.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Details about postal address. Sent from client application. Request data from any endpoints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {
    private String town;
    private String postalCode;
    private String street;

    @ApiModelProperty(notes = "The unique id of employee")
    private Long employeeId;
}
