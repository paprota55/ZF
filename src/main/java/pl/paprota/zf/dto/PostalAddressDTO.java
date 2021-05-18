package pl.paprota.zf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {
    private String town;
    private String postalCode;
    private String street;
    private Long employeeId;
}
