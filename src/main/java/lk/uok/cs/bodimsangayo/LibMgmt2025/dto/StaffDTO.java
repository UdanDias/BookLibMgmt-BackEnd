package lk.uok.cs.bodimsangayo.LibMgmt2025.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements Serializable {
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private LocalDate lastUpdateDate;
    private Time lastUpdateTime;
    private String phone;
    private Role role;

}
