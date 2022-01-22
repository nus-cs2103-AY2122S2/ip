import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        displayStartUpMessage();
        Driver dr = new Driver();
        dr.execute();
    }

    private static void displayStartUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "\n" + "What can I do for you?\n" + "____________________________________________________________");
    }
}
