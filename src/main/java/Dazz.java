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
        Dazz dazz = new Dazz(new Reminder());
        Scanner scanner = new Scanner(System.in);
        dazz.design.startUp();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(Design.HORIZONTAL_LINE);
            dazz.logic.run(input);
            System.out.println(Design.HORIZONTAL_LINE);
            if (input.startsWith("bye")) {
                break;
            }
        }
        scanner.close();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[dd/MM/yyyy]");
//        Deadline deadline = new Deadline("D", "yoyo", "02/12/2019");
//        System.out.println(deadline.matchDate("gsgs"));
//        System.out.println(deadline.dateFormat.toString());
//        System.out.println(LocalDate.parse("02/12/2019", formatter).toString());

    }
}

