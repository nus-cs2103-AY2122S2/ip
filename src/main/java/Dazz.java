import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Dazz {
    private final Design design;
    private final Logic logic;

    public Dazz(Reminder reminder) {
        this.design = new Design();
        this.logic = new Logic(reminder);
    }

    public static void main(String[] args) {
//        Dazz dazz = new Dazz(new Reminder());
//        Scanner scanner = new Scanner(System.in);
//        dazz.design.startUp();
//        while (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            System.out.println(Design.HORIZONTAL_LINE);
//            dazz.logic.run(input);
//            System.out.println(Design.HORIZONTAL_LINE);
//            if (input.startsWith("bye")) {
//                break;
//            }
//        }
//        scanner.close();

        // Testing of datetime
//        Task deadline1 = new Deadline("D", "return book", "02/12/2019 1900");
//        Task deadline2 = new Deadline("D", "return book", "ASAP");
//        Task deadline3 = new Deadline("D", "return book", "02-12-2019 1900");
//        System.out.println(deadline1.dateTime);
//        System.out.println(deadline1.getDateTime());
//        System.out.println(deadline2.dateTime);
//        System.out.println(deadline2.getDateTime());
//        System.out.println(deadline3.dateTime);
//        System.out.println(deadline3.getDateTime());

    }
}

