package apollo.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WelcomeTest {

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream stdout = System.out;

    @BeforeAll
    static void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void printLogo() {
        String logo = "Hello from\n"
                + "       @@@%      @@@@@@*.      :@@@@@.    %@@@@@+    @@@@@@        #@@@@=       \n"
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
        Welcome.printLogo();
        assertEquals(outputStream.toString(), logo.replace("\n", System.lineSeparator()));
    }

    @Test
    void welcomeMessage_morning() {
        LocalTime morning1 = LocalTime.of(10, 0);
        LocalTime morning2 = LocalTime.of(11, 0);

        String morningLoad = "Welcome back sir. \nHow can I help you on this wonderful morning? ";
        String morningNoLoad = "Good morning sir, I am Apollo. \nHow can I help you on this wonderful morning? ";

        assertEquals(morningLoad, Welcome.welcomeMessage(true, morning1));
        assertEquals(morningLoad, Welcome.welcomeMessage(true, morning2));
        assertEquals(morningNoLoad, Welcome.welcomeMessage(false, morning1));
        assertEquals(morningNoLoad, Welcome.welcomeMessage(false, morning2));
    }

    @Test
    void welcomeMessage_afternoon() {
        LocalTime afternoon1 = LocalTime.of(14, 0);
        LocalTime afternoon2 = LocalTime.of(15, 0);

        String afternoonLoad = "Welcome back sir. \nHow can I help you on this wonderful afternoon? ";
        String afternoonNoLoad = "Good afternoon sir, I am Apollo. \nHow can I help you on this wonderful afternoon? ";

        assertEquals(afternoonLoad, Welcome.welcomeMessage(true, afternoon1));
        assertEquals(afternoonLoad, Welcome.welcomeMessage(true, afternoon2));
        assertEquals(afternoonNoLoad, Welcome.welcomeMessage(false, afternoon1));
        assertEquals(afternoonNoLoad, Welcome.welcomeMessage(false, afternoon2));
    }

    @Test
    void welcomeMessage_evening() {
        LocalTime evening1 = LocalTime.of(17, 0);
        LocalTime evening2 = LocalTime.of(18, 30);

        String eveningLoad = "Welcome back sir. \nHow can I help you on this wonderful evening? ";
        String eveningNoLoad = "Good evening sir, I am Apollo. \nHow can I help you on this wonderful evening? ";

        assertEquals(eveningLoad, Welcome.welcomeMessage(true, evening1));
        assertEquals(eveningLoad, Welcome.welcomeMessage(true, evening2));
        assertEquals(eveningNoLoad, Welcome.welcomeMessage(false, evening1));
        assertEquals(eveningNoLoad, Welcome.welcomeMessage(false, evening2));
    }

    @Test
    void welcomeMessage_night() {
        LocalTime night1 = LocalTime.of(3, 0);
        LocalTime night2 = LocalTime.of(4, 0);

        String nightLoad = "Welcome back sir. \nHow can I help you on this wonderful night? ";
        String nightNoLoad = "Good night sir, I am Apollo. \nHow can I help you on this wonderful night? ";

        assertEquals(nightLoad, Welcome.welcomeMessage(true, night1));
        assertEquals(nightLoad, Welcome.welcomeMessage(true, night2));
        assertEquals(nightNoLoad, Welcome.welcomeMessage(false, night1));
        assertEquals(nightNoLoad, Welcome.welcomeMessage(false, night2));
    }

    @AfterAll
    static void tearDown() {
        System.setOut(stdout);
    }
}
