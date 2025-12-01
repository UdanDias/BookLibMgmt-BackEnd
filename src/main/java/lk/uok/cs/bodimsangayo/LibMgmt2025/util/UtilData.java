package lk.uok.cs.bodimsangayo.LibMgmt2025.util;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class UtilData {
    public static String generateBookId(){
        return "B/"+ UUID.randomUUID();
    }
    public static String generateStaffId(){
        return "S/"+ UUID.randomUUID();
    }
    public static String generateMemberID(){
        return "M/"+ UUID.randomUUID();
    }
    public static String generateLendingId(){
        return "L/"+ UUID.randomUUID();
    }

    // generate date and time
    public static LocalDate generateTodayDate(){
        return LocalDate.now();
    }

    public static Time generateCurrentTime(){
        return Time.valueOf(LocalTime.now());
    }

    public static LocalDate generateBookReturnDate(){
        return LocalDate.now().plusDays(7);
    }
    public static LocalDate generateBookReturnDateCalc(){
        return LocalDate.now().minusDays(7);
    }
}
