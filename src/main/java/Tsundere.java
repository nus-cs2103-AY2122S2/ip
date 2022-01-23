import java.util.*;

import java.time.LocalDate;

public class Tsundere {

    static final private String lines = "------------------------------------------------------------------------";
    static List<Task> aryLst = new ArrayList<>();
    static int countLst = 0;

    enum Command {
        BYE,
        LIST,
        UNMARK,
        MARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        UNKNOWN
    }
    public static void main(String[] args) {

        System.out.println("Hmph, it's you again...\n"); //+ logo);
        greet();

        boolean isBye = false;

        Scanner sc = new Scanner(System.in);
        while (!isBye) {
            String userInput =  sc.nextLine();
            String comUserInput = userInput.toUpperCase();
            Command switchCommand = Command.UNKNOWN;
            if (comUserInput.contains("BYE")) {
                switchCommand = Command.BYE;
            } else if (comUserInput.contains("LIST")) {
                switchCommand = Command.LIST;
            } else if (comUserInput.contains("UNMARK")) {
                switchCommand = Command.UNMARK;
            } else if (comUserInput.contains("MARK")) {
                switchCommand = Command.MARK;
            } else if (comUserInput.contains("TODO")) {
                switchCommand = Command.TODO;
            } else if (comUserInput.contains("DEADLINE")) {
                switchCommand = Command.DEADLINE;
            } else if (comUserInput.contains("EVENT")) {
                switchCommand = Command.EVENT;
            } else if (comUserInput.contains("DELETE")) {
                switchCommand = Command.DELETE;
            }
            try {
                String[] splitStr;
                String[] splitStr2;
                int num;
                switch (switchCommand) {
                    case BYE:
                         exit();
                        isBye = true;
                        break;
                    case LIST:
                        list();
                        break;
                    case UNMARK:
                        splitStr = userInput.split(" ");
                        if (splitStr.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example: unmark 1");
                        }
                        num = Integer.parseInt(splitStr[1]);

                        if (num > countLst) {
                            throw new TsundereException("Hmph you baka, you gave a invalid number!");
                        }
                        unmark(num);
                        break;
                    case MARK:
                        splitStr = userInput.split(" ");
                        if (splitStr.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example: mark 1");
                        }

                        num = Integer.parseInt(splitStr[1]);

                        if (num > countLst) {
                            throw new TsundereException("Hmph you baka, you gave a invalid number!");
                        }
                        mark(num);
                        break;
                    case TODO:
                        splitStr = userInput.split(" ", 2);
                        if (splitStr.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example, todo sleep");
                        }
                        todo(splitStr[1]);
                        break;
                    case DEADLINE:
                        splitStr = userInput.split("/by");
                        if (splitStr.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example, deadline sleep/by Sunday");
                        }
                        splitStr2 = splitStr[0].split(" ",2);
                        if (splitStr2.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example, deadline sleep/by Sunday");
                        }
                        deadline(splitStr2[1], splitStr[1]);
                        break;
                    case EVENT:
                        splitStr = userInput.split("/at");
                        if (splitStr.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example, event sleep/at Sunday");
                        }
                        splitStr2 = splitStr[0].split(" ",2);
                        if (splitStr2.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example, event sleep/at Sunday");
                        }
                        event(splitStr2[1], splitStr[1]);
                        break;
                    case DELETE:
                        splitStr = userInput.split(" ");

                        if (splitStr.length < 2) {
                            throw new TsundereException("Hmph you baka, gimme a correct format. For example: delete 1");
                        }

                        num = Integer.parseInt(splitStr[1]);

                        if (num > countLst) {
                            throw new TsundereException("Hmph you baka, you gave a invalid number!");
                        }
                        delete(num);
                        break;
                    default:
                            throw new TsundereException("I don't know what you want! Say something valid.");

                }
            } catch (TsundereException te) {
                System.out.println(te.getMessage());
                System.out.println(lines);
            }

        }
    }

    static private void greet() {
        System.out.println(lines);
        System.out.println("Heyy! What do you want?!");
        System.out.println(lines);
    }

    static private void echo(String userInput) {
        System.out.println(lines);
        System.out.println(userInput);
        System.out.println(lines);
    }
    static private void list() {
        System.out.println(lines);

        if (countLst == 0) {
            System.out.println("You got no task!!");
            return;
        }

        System.out.println("You forgetful baka... here are your tasks: ");
        for (int i = 0; i < countLst; i++) {
            int num = i + 1;
            System.out.println(num + ". " + aryLst.get(i).toString());
        }
        System.out.println(lines);
    }

    static private void exit() {
        System.out.println(lines);
        System.out.println("Finally, you're leaving!\nIt's not like i will miss you or anything...");
        System.out.println(lines);
    }

    static private void mark(int num) {
        System.out.println(lines);
        System.out.println("Alright! Aright, i will mark it down!");
        int realNum = num - 1;
        aryLst.get(realNum).markDone();
        System.out.println("[" +  aryLst.get(realNum).getStatusIcon()  +"] " +  aryLst.get(realNum).getDescription());
        System.out.println(lines);
    }

    static private void unmark(int num) {
        System.out.println(lines);
        System.out.println("You didn't actually finish?!");
        int realNum = num - 1;
        aryLst.get(realNum).markNotDone();
        System.out.println("[" +  aryLst.get(realNum).getStatusIcon()  +"] " +  aryLst.get(realNum).getDescription());
        System.out.println(lines);
    }

    static private void todo(String strTask) {
        System.out.println(lines);
        System.out.println("New task! You better do it.");
        aryLst.add(new ToDo(strTask));
        System.out.println(aryLst.get(countLst).toString());
        countLst++;
        System.out.println("You have " + countLst + " task(s) to do you lazy bum!");
        System.out.println(lines);
    }

    static private void deadline(String strTask, String by) {
        System.out.println(lines);
        System.out.println("New task! You better do it.");
        aryLst.add(new Deadline(strTask, by));
        System.out.println(aryLst.get(countLst).toString());
        countLst++;
        System.out.println("You have " + countLst + " task(s) to do you lazy bum!");
        System.out.println(lines);
    }

    static private void event(String strTask, String at) {
        System.out.println(lines);
        System.out.println("New task! You better do it.");
        aryLst.add(new Event(strTask, at));
        System.out.println(aryLst.get(countLst).toString());
        countLst++;
        System.out.println("You have " + countLst + " task(s) to do, you lazy bum!");
        System.out.println(lines);
    }

    static private void delete(int num) {
        System.out.println(lines);
        System.out.println("Deleting the following task. You're not being lazy, are you?");
        System.out.println(aryLst.get(num - 1).toString());
        aryLst.remove(num - 1);
        countLst--;
        System.out.println("You have " + countLst + " task(s) to do, you lazy bum!");
        System.out.println(lines);
    }



}
