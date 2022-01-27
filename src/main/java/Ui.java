import java.util.List;
import java.util.Arrays;

public class Ui {
    public void print(String text) {
        List<String> lines = Arrays.asList(text.split("\n"));
        for (String line : lines) {
            System.out.println(Constants.GAP + line);
        }
        System.out.println(Constants.LINE);
    }

    public void initialGreet() {
        System.out.println(Constants.LINE);
        print("Hello from\n" + Constants.LOGO);
        print("I am a chat bot and I'm here to help you be productive :)\n" +
                "What can I do for you today?");
    }

    public void finalBye() {
        print("Bye. Hope to see you again soon!");
    }
}
