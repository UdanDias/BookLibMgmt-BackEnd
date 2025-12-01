package lk.uok.cs.bodimsangayo.LibMgmt2025.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    private String memberId;
    private String name;
    private String email;
    private LocalDate membershipDate;
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LendingEntity>lendings;
}
