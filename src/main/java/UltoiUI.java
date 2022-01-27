import java.util.Scanner;

public class UltoiUI {
    private Scanner sc;
    private static final String INDENT = "    ";
    private static final String LINE_BREAKER = INDENT + "======U======L======T======O======I======";

    public UltoiUI() {
        sc = new Scanner(System.in);
    }

    public String input() {
        return sc.nextLine();
    }

    public void output(String message) {
        System.out.println(message);
        return;
    }
}
