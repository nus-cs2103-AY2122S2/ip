import java.util.ArrayList;
import java.util.Scanner;

public class Alfred {
    // constants
    private String GREETING = "Hello! My name is Alfred.\n"
            + "How can I be of service?\n";
    private String BYE = "Bye! Hope I was of service.\n";
    private int BREAK_CHAR_LENGTH = 100;;
    private String BREAK_LINE = this.line();

    // functional attributes
    ArrayList<String> entered;

    Alfred() {
        this.entered = new ArrayList<String>();
    }

    private String line() {
        String out = "";
        for (int i = 0; i < this.BREAK_CHAR_LENGTH; i++) {
            out += "-";
        }
        out += "\n";
        return out;
    }

    private String greeting() {
        String out = "";
        out += this.BREAK_LINE;
        out += this.GREETING;
        out += this.BREAK_LINE;
        return out;
    }

    private String bye() {
        String out = "";
        out += this.BREAK_LINE;
        out += this.BYE;
        out += this.BREAK_LINE;
        return out;
    }

    private String list_entered() {
        String out = "";
        out += this.BREAK_LINE;
        for (int i = 1; i < this.entered.size() + 1; i++) {
            out += i + ". " + this.entered.get(i - 1).toString() + "\n";
        }
        out += this.BREAK_LINE;
        return out;
    }

    private void read_input(String input) {
        if (input.equals("list")) {
            System.out.println(this.list_entered());
        } else {
            System.out.println(this.add_and_echo(input));
        }
    }

    private String add_and_echo(String input) {
        this.entered.add(input);
        String out = "";
        out += this.BREAK_LINE;
        out += "added: " + input + "\n";
        out += this.BREAK_LINE;
        return out;
    }

    private String echo(String input) {
        String out = "";
        out += this.BREAK_LINE;
        out += input + "\n";
        out += this.BREAK_LINE;
        return out;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Alfred bot = new Alfred();
        System.out.println(bot.greeting());
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            bot.read_input(input);
            input = sc.nextLine();
        }
        System.out.println(bot.bye());

    }
}
