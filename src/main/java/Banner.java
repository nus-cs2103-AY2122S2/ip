import java.time.LocalTime;

public class Banner extends Apollo{

    public static String dayTime() {
        // define the border values
        LocalTime eleven = LocalTime.of(11, 0);
        LocalTime four = LocalTime.of(4, 0);
        LocalTime fifteen = LocalTime.of(15, 0);
        LocalTime eighteenThirty = LocalTime.of(18, 30);

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
        String logo = "       @@@%      @@@@@@*.      :@@@@@.    %@@@@@+    @@@@@@        #@@@@=       \n"
                + "       @@@@=     @@@@@@@@@    @@@@@@@@#   @@@@@@*    @@@@@@.     -@@@@@@@@      \n"
                + "       @@@@@     @@@@@@@@@*  %@@@@@@@@@+  @@@@@@*    @@@@@@.    .@@@@@@@@@@     \n"
                + "      #@@@@@     +@@@@+@@@@  @@@@%+%@@@@   @@@@       @@@@      @@@@@**@@@@=    \n"
                + "      @@@@@@+     %@@@  @@@ =@@@#   @@@@:  @@@@       @@@@      @@@@    @@@@    \n"
                + "      @@@.@@@     #@@@ @@@@ %@@@     @@@#  @@@@       @@@%      @@@*    @@@@    \n"
                + "     .@@@ @@@     *@@@@@@@= @@@@     @@@@  @@@@       @@@#      @@@-    @@@@    \n"
                + "     =@@@@@@@*    *@@@@@@%  @@@@     @@@@  @@@@       @@@*      @@@-    @@@@    \n"
                + "     @@@@@@@@@    +@@@@%    #@@@.    @@@+  @@@@  @@%  @@@+  @@* @@@@    @@@@    \n"
                + "     @@@-:-@@@.   +@@@      -@@@@   @@@@   @@@@  @@@  @@@=  @@% @@@@*  *@@@%    \n"
                + "     @@@.  @@@#   +@@@       @@@@@@@@@@@  -@@@@-=@@@ -@@@*-*@@% #@@@@@@@@@@.    \n"
                + "    @@@@@:@@@@@@ @@@@@@@     =@@@@@@@@@   @@@@@@@@@@ @@@@@@@@@%  @@@@@@@@@%     \n"
                + "    @@@@@-@@@@@@ @@@@@@@      =@@@@@@@-   @@@@@@@@@@ @@@@@@@@@%   @@@@@@@@      \n"
                + "    @%#*+:+%#*+= -=+++++        =%@%=     --======== --=======-    :*@@*.       \n";
        System.out.println("Hello from\n" + logo);
        String daytime = dayTime();
        String msg = String.format("Good %s Sir, I am Apollo. \nHow can I help you on this wonderful %s? ",
                daytime, daytime.toLowerCase());
        Apollo.printMessage(msg);
    }
}
