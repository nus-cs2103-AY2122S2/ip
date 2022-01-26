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
                //System.out.println("=========================================");
                System.out.println(prs.processMessage(msg,tl,sge));
                System.out.println("=========================================");
            } catch (ChiException e) {
                this.printErrorMsg(e.toString());
            } catch (IOException e) {
                this.printErrorMsg("Something went wrong while storing task to file nyaan!");
            }
            msg = sn.nextLine();
        }
        sn.close();
    }

    public void printGoodbye() {
        System.out.println("Leaving already? Alright... see you soon nyan!!!");
    }

    public void printErrorMsg(String errMsg) {
        System.out.println("=V.V= " + errMsg + " =V.V=");
    }

}
