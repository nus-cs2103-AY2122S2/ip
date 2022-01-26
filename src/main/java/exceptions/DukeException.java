package exceptions;

public abstract class DukeException extends Exception {
	public final static String LINE = "    ____________________________________________________________\n";
	public final static String INDENT = "     ";

	public abstract void printError();

	public static void printFormatted(String[] msg) {
		for (int i = 0; i < msg.length; i++) {
			if (msg[i] != null) {
				System.out.println(INDENT + msg[i]);
			}
		}
	}
}
