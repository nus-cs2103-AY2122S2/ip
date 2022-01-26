package Ui;

public class Ui {
	public final static String LINE = "    ____________________________________________________________\n";
	public final static String INDENT = "     ";

	public Ui(){}

	public void printLine(){
		System.out.print(LINE);
	}

	public void welcomeMessage(){
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
		printFormatted(new String[]{"Hello! I'm Duke", "What can I do for you?"});
	}

	public static void printFormatted(String[] msg) {
		System.out.print(LINE);
		for (int i = 0; i < msg.length; i++) {
			if (msg[i] != null) {
				System.out.println(INDENT + msg[i]);
			}
		}
		System.out.println(LINE);
	}
}
