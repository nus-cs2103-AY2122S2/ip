import java.io.IOException;
import java.util.Scanner;

public class UI {
    public UI() {
        // Nothing for now
    }

    public void printWelcome() {
        System.out.println("Welcome to Chi task bot nyan!!!");
    }

    public void requestInput(TaskList tl, Storage sge, Parser prs) {
        Scanner sn = new Scanner(System.in);
        String msg = sn.nextLine();
        while (!msg.equals("bye")) {
            try {
                System.out.println(prs.processMessage(msg,tl));
            } catch (ChiException e) {
                this.printErrorMsg(e.getMessage());
            }
            msg = sn.nextLine();
        }
        sn.close();
        try {
            sge.store(tl);
        } catch (IOException e) {
            this.printErrorMsg("Something went wrong while storing tasks to file nyaan!");
        }
    }

    public void printGoodbye() {
        System.out.println("Leaving already? Alright... see you soon nyan!!!");
    }

    public void printErrorMsg(String errMsg) {
        System.out.println("=V.V= " + errMsg + " =V.V=");
    }

}
