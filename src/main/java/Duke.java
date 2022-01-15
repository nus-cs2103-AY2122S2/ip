import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskArr = new ArrayList<>();
    public static BOT dot = BOT.JJBA;


    public static void main(String[] args) {
        String logo = "   ___   _________  ___  \n" +
                      "  |_  | |_  | ___ \\/ _ \\ \n" +
                      "    | |   | | |_/ / /_\\ \\\n" +
                      "    | |   | | ___ \\  _  |\n" +
                      "/\\__/ /\\__/ / |_/ / | | |\n" +
                      "\\____/\\____/\\____/\\_| |_/";
        Console.print(logo);
        Console.println("Hello, I'm JJBA\nWhat can I do for you?");

        String input = "";
        boolean exitProg = false;

        do {
            input = Console.read();
            String[] inputArg = input.split(" ", 2);

            switch (inputArg[0].toLowerCase()) {
                case "bye": {
                    switch(dot) {
                        case JJBA: {
                            Console.println("Goodbye! Good luck!");
                            break;
                        }
                        case DIO: {
                            Console.println("RODA ROLLA DA!");
                        }
                    }
                    exitProg = true;
                    break;
                }
                case "dio": {
                    dot = BOT.DIO;
                    Console.println("ZA WARUDO!");
                    break;
                }
                case "jjba": {
                    dot = BOT.JJBA;
                    Console.println("What can I do for you?");
                    break;
                }
                case "mark": {
                    Task curTask = taskArr.get(Integer.parseInt(inputArg[1]) - 1);
                    curTask.markTask();

                    switch(dot) {
                        case JJBA: {
                            Console.println("Good job on completing your task!\n   " + curTask.toString());
                            break;
                        }
                        case DIO: {
                            Console.println("KONO DIO DA!\n   " + curTask.toString());
                        }
                    }
                    break;
                }
                case "unmark": {
                    Task curTask = taskArr.get(Integer.parseInt(inputArg[1]) - 1);
                    curTask.unmarkTask();
                    switch(dot) {
                        case JJBA: {
                            Console.println("Done, remember to do your task.\n   " + curTask.toString());
                            break;
                        }
                        case DIO: {
                            Console.println("Hinjaku! Hinjaku!\n   " + curTask.toString());
                        }
                    }
                    break;
                }
                case "list": {

                    switch(dot) {
                        case JJBA: {
                            Console.printList("Here are the things you will need to do:", taskArr);
                            break;
                        }
                        case DIO: {
                            Console.printList("Oh? You're Approaching Me?", taskArr);
                        }
                    }
                    break;
                }
                default: {
                    taskArr.add(new Task(input));
                    switch(dot) {
                        case JJBA: {
                            Console.println("added: " + input);
                            break;
                        }
                        case DIO: {
                            Console.println("MUDA MUDA MUDA MUDA MUDA! " + input);
                        }
                    }
                    break;
                }
            }

        } while (!exitProg);

    }

    public enum BOT {
        JJBA,
        DIO
    }
}
