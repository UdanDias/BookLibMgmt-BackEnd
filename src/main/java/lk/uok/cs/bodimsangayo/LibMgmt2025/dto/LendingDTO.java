package lk.uok.cs.bodimsangayo.LibMgmt2025.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LendingDTO implements Serializable {
    private String lendingId;
    private String book;
    private String member;
    private LocalDate lendingDate;
    private LocalDate returnDate;
    private Boolean isActiveLending;
    private Long overdueDays;
    private Double fineAmount;
}
