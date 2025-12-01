package lk.uok.cs.bodimsangayo.LibMgmt2025.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO implements Serializable {
    private String memberId;
    private String name;
    private String email;
    private LocalDate membershipDate;
}
