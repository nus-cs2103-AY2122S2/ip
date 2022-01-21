import java.util.Scanner;


public class Commands {
    String[] fullCmd;
    static Scanner sc;
    public enum Mark {MARK, UNMARK};

    Commands() {
        sc = new Scanner(System.in);
    }

    private void next() {
        fullCmd = sc.nextLine().split(" ");
    }

    public void response() {
        next();
        String cmd = fullCmd[0];
        switch (cmd.toLowerCase()) {
            case "":
                response();
                break;
            case "bye":
                Apollo.stop();
                break;
            case "list":
                Apollo.printList();
                response();
                break;
            case "mark":
                Apollo.mark(Integer.parseInt(fullCmd[1]), Mark.MARK);
                response();
                break;
            case "unmark":
                Apollo.mark(Integer.parseInt(fullCmd[1]), Mark.UNMARK);
                response();
                break;
            default:
                Apollo.addItem(String.join(" ", fullCmd));
                response();
        }
    }
}
