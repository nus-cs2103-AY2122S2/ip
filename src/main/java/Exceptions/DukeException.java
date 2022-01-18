package Exceptions;

public abstract class DukeException extends Exception {
    public final static String LINE = "    ____________________________________________________________\n";
    public final static String INDENT = "     ";

    public abstract void printError();

    public static void printFormatted(String[] msg){
        System.out.print(LINE);
        for(int i = 0; i < msg.length; i++){
            if(msg[i]!=null) {
                System.out.println(INDENT + msg[i]);
            }
        }
        System.out.println(LINE);
    }
}
