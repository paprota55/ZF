package pl.paprota.zf.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Employee extends Person{

    private Double salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostalAddress> addressList;
}
