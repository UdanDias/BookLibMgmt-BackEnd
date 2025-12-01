package lk.uok.cs.bodimsangayo.LibMgmt2025.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
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
