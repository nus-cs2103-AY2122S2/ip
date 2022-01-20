import core.IOManager;

public class Dooke {
    public static void main(String[] args) {
        String logo =
                "8888b.   dP\"Yb   dP\"Yb  88  dP 888888\n" +
                 "8I  Yb dP   Yb dP   Yb 88odP  88__\n"+
                 "8I  dY Yb   dP Yb   dP 88\"Yb  88\"\"\n"+
                "8888Y\"   YbodP   YbodP  88  Yb 888888";

        System.out.println("Hello from\n" + logo);

        IOManager ioManager = IOManager.getInstance();
        ioManager.start();
    }
}
