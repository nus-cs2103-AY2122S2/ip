import java.time.LocalTime;

public class Banner {
    static String logo = "       @@@%      @@@@@@*.      :@@@@@.    %@@@@@+    @@@@@@        #@@@@=       \n" +
            "       @@@@=     @@@@@@@@@    @@@@@@@@#   @@@@@@*    @@@@@@.     -@@@@@@@@      \n" +
            "       @@@@@     @@@@@@@@@*  %@@@@@@@@@+  @@@@@@*    @@@@@@.    .@@@@@@@@@@     \n" +
            "      #@@@@@     +@@@@+@@@@  @@@@%+%@@@@   @@@@       @@@@      @@@@@**@@@@=    \n" +
            "      @@@@@@+     %@@@  @@@ =@@@#   @@@@:  @@@@       @@@@      @@@@    @@@@    \n" +
            "      @@@.@@@     #@@@ @@@@ %@@@     @@@#  @@@@       @@@%      @@@*    @@@@    \n" +
            "     .@@@ @@@     *@@@@@@@= @@@@     @@@@  @@@@       @@@#      @@@-    @@@@    \n" +
            "     =@@@@@@@*    *@@@@@@%  @@@@     @@@@  @@@@       @@@*      @@@-    @@@@    \n" +
            "     @@@@@@@@@    +@@@@%    #@@@.    @@@+  @@@@  @@%  @@@+  @@* @@@@    @@@@    \n" +
            "     @@@-:-@@@.   +@@@      -@@@@   @@@@   @@@@  @@@  @@@=  @@% @@@@*  *@@@%    \n" +
            "     @@@.  @@@#   +@@@       @@@@@@@@@@@  -@@@@-=@@@ -@@@*-*@@% #@@@@@@@@@@.    \n" +
            "    @@@@@:@@@@@@ @@@@@@@     =@@@@@@@@@   @@@@@@@@@@ @@@@@@@@@%  @@@@@@@@@%     \n" +
            "    @@@@@-@@@@@@ @@@@@@@      =@@@@@@@-   @@@@@@@@@@ @@@@@@@@@%   @@@@@@@@      \n" +
            "    @%#*+:+%#*+= -=+++++        =%@%=     --======== --=======-    :*@@*.       \n";

    // define the border values
    static LocalTime eleven = LocalTime.of(11, 0);
    static LocalTime four = LocalTime.of(4, 0);
    static LocalTime fifteen = LocalTime.of(15, 0);
    static LocalTime eighteenThirty = LocalTime.of(18, 30);

    public static String dayTime() {
        LocalTime now = LocalTime.now();
        // check if the time is after four and either before or exactly eleven
        if (now.isAfter(four) &&
                (now.isBefore(eleven) || now.equals(eleven)))
            return "Morning";
            // check if the time is after eleven and either before or exactly fifteen
        else if (now.isAfter(eleven) &&
                (now.isBefore(fifteen) || now.equals(fifteen)))
            return "Afternoon";
            // check if the time is after fifteen and either before or exactly eighteen thirty
        else if (now.isAfter(fifteen) &&
                (now.isBefore(eighteenThirty) || now.equals(eighteenThirty)))
            return "Evening";
            // otherwise, it's night
        else return "Night";
    }

    public static void welcomeMsg() {
        System.out.println("Hello from\n" + logo);
        String daytime = dayTime();
        String msg = String.format("Good %s Sir, I am Apollo. \n" +
                "How can I help you on this wonderful %s? ", daytime, daytime.toLowerCase());
        Apollo.printMsg(msg);
    }
}
