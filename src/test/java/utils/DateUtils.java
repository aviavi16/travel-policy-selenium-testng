package utils;

import java.time.LocalDate;

public final class DateUtils {
    private DateUtils() {}

    public static LocalDate departureDate() {
        return LocalDate.now().plusDays(7);
    }

    public static LocalDate returnDate(LocalDate departure) {
        return departure.plusDays(30);
    }
}
