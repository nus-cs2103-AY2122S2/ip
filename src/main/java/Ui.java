import java.util.Scanner;

public class Ui {
    private static final String logo = "\n" +
            ".        :       ...     :::.    :::. :::  .   .,::::::\n" +
            ";;,.    ;;;   .;;;;;;;.  `;;;;,  `;;; ;;; .;;,.;;;;''''\n" +
            "[[[[, ,[[[[, ,[[     \\[[,  [[[[[. '[[ [[[[[/'   [[cccc\n" +
            "$$$$$$$$\"$$$ $$$,     $$$  $$$ \"Y$c$$_$$$$,     $$\"\"\"\"\n" +
            "888 Y88\" 888o\"888,_ _,88P  888    Y88\"888\"88o,  888oo,__\n" +
            "MMM  M'  \"MMM  \"YMMMMMP\"   MMM     YM MMM \"MMP\" \"\"\"\"YUMMM\n";
    private static final String intro = "I MONKE. WHAT WANT?";
    private final Scanner sc = new Scanner(System.in);

    public void closeScanner() {
        this.sc.close();
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showIntro() {
        System.out.println(wrap(logo + "\n" + intro));
    }

    public void showMessage(String message) {
        System.out.println(wrap(message));
    }

    public void showError(DukeException e) {
        e.printStackTrace();
    }

    public void showBye() {
        System.out.println(wrap("BYE!!!"));
    }

    private String wrap(String text) {
        String line = "____________________________________________________________\n";
        return line + text + "\n" + line;
    }
}
