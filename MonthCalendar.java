import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

public interface MonthCalendar {
    Integer ZERO = 0;
    default Integer skipDay(){
        return ZERO;
    }

    Integer WEEKEND_DAYS = 2;

     default List<LocalDate> findNonAvailableDatesForMonth(LocalDate today) {
        var holidays = findHolidays();
        var nextMonthFirstDay = getNextMonth(today);
        return today.datesUntil(nextMonthFirstDay)
                .filter(givenDate -> isWeekend(givenDate.getDayOfWeek()) ||
                        beforeGivenDayBetweenToday(givenDate,today,skipDay()) ||
                        holidays.contains(givenDate)
                )
                .collect(Collectors.toList());
    }

    default List<LocalDate> findAvailableDatesForMonth(LocalDate today) {
        var holidays = findHolidays();
        var nextMonthFirstDay = getNextMonth(today);
        return today.datesUntil(nextMonthFirstDay)
                .filter(givenDate -> !(isWeekend(givenDate.getDayOfWeek()) ||
                        beforeGivenDayBetweenToday(givenDate,today,skipDay()) ||
                        holidays.contains(givenDate))
                )
                .collect(Collectors.toList());
    }

    private static boolean beforeGivenDayBetweenToday(LocalDate givenDate, LocalDate today, Integer minWorkdays) {
        while (!isWeekend(today.getDayOfWeek())){
            today = today.plus(1, ChronoUnit.DAYS);
            --minWorkdays;
        }
        return today.plus(minWorkdays + WEEKEND_DAYS, ChronoUnit.DAYS).isAfter(givenDate);
    }

    private static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }

    private static LocalDate getNextMonth(LocalDate today) {
        return today.with(TemporalAdjusters.firstDayOfNextMonth());
    }

    List<LocalDate> findHolidays();
}
