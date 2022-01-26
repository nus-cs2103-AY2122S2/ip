import exception.JukeException;

public class PrintHelper {
    private static final PrintHelper INSTANCE = new PrintHelper();
    
    public static PrintHelper getInstance() {
        return INSTANCE;
    }
    
    public void formattedPrint(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________\n");
    }
    
    public void errorPrint(Exception e) {
        System.out.println("____________________________________________________________");
        System.out.println("\ud83d\ude26 ERROR! " + e.getMessage());
        System.out.println("____________________________________________________________\n");
    }
    
    public void printMarker() {
        System.out.print("\u232c ");
    }
}
