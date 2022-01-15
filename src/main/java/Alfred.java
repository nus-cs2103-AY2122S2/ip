import java.util.ArrayList;
import java.util.Scanner;

public class Alfred {
    // attributes
    String GREETING = "Hello! My name is Alfred.\n"
            + "How can I be of service?\n";
    String BYE = "Bye! Hope I was of service.\n";
    int BREAK_CHAR_LENGTH = 100;;
    String BREAK_LINE = this.line();

    public String greeting() {
        String out = "";
        out += this.BREAK_LINE;
        out += this.GREETING;
        out += this.BREAK_LINE;
        return out;
    }

    public String bye() {
        String out = "";
        out += this.BREAK_LINE;
        out += this.BYE;
        out += this.BREAK_LINE;
        return out;
    }


    public String line() {
        String out = "";
        for (int i = 0; i < this.BREAK_CHAR_LENGTH; i++) {
            out += "-";
        }
        out += "\n";
        return out;
    }

    public String echo(String input) {
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
            System.out.println(bot.echo(input));
            input = sc.nextLine();
        }
        System.out.println(bot.bye());

    }
}
