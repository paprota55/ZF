package pl.paprota.zf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Double salary;

    private String name;

    private String surname;

    private Integer age;
}
