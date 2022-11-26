import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var sampleCalendar = new SampleCalendarImp();

        System.out.println("For November 2022");
        sampleCalendar.findNonAvailableDatesForMonth(LocalDate.now())
                .forEach(System.out::println);

        System.out.println("For December 2022");
        sampleCalendar.findAvailableDatesForMonth(LocalDate.of(2022,12,3))
                .forEach(System.out::println);
    }
}
