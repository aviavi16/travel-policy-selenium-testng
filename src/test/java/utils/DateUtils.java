package utils;

import java.time.LocalDate;

public final class DateUtils {
    private DateUtils() {}

    private static final int DEFAULT_DEPARTURE_OFFSET_DAYS = 7;
    private static final int DEFAULT_TRIP_LENGTH_DAYS = 30;

    public static LocalDate departureDate() {
        int offset = Integer.parseInt(System.getProperty("departureOffsetDays", String.valueOf(DEFAULT_DEPARTURE_OFFSET_DAYS)));
        return LocalDate.now().plusDays(offset);
    }

    public static LocalDate returnDate(LocalDate departure) {
        int tripDays = Integer.parseInt(System.getProperty("tripLengthDays", String.valueOf(DEFAULT_TRIP_LENGTH_DAYS)));
        return departure.plusDays(tripDays);
    }
}
