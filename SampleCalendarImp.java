import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class SampleCalendarImp implements MonthCalendar {
    @Override
    public Integer skipDay() {
        return 3;
    }

    @Override
    public List<LocalDate> findHolidays() {
        return
                List.of(
                        LocalDate.of(2022, Month.DECEMBER, 5),
                        LocalDate.of(2022, Month.DECEMBER, 14),
                        LocalDate.of(2022, Month.DECEMBER, 29),
                        LocalDate.of(2022, Month.DECEMBER, 30),
                        LocalDate.of(2022, Month.DECEMBER, 31)
                );
    }
}
