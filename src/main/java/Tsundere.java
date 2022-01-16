import java.util.Scanner;

public class Tsundere {

    static final private String lines = "( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )( ˘︹˘ )";
    static Task[] aryLst = new Task[100];
    static int countLst = 0;

    public static void main(String[] args) {
        String logo = " ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⠤⠤⠤⠤⠤⠤⢤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢤⠖⠉⠠⠀⠀⠀⠁⠀⠀⠀⠁⠀⠈⠲⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣔⡋⣴⠀⢇⠢⡀⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠉⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⡿⠼⠢⠿⠀⣱⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠄⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡫⠛⣸⠗⡒⢴⠃⠒⠀⢲⠀⠀⠰⡀⠀⢣⠀⠀⠀⢇⠘⡌⠀⡵⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣏⠐⣱⡯⢪⣚⡏⠀⠀⠀⣼⠠⠀⡆⡋⡄⠈⡆⠀⠀⠸⣆⢳⢈⡔⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢨⣄⣿⢩⡉⣿⣸⢡⠀⢼⣹⢠⢸⡹⠀⠸⡀⢳⡆⠀⠀⣿⡈⡏⠐⢘⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢰⣸⡿⣴⢺⡿⣿⢎⠀⣾⠇⢠⡿⠁⠀⠀⢳⠀⣼⠀⠀⢸⡇⡇⢸⢘⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡌⣺⡇⠷⣿⡟⡇⡠⣰⡿⢀⡾⠁⠀⢀⠀⠜⣆⢻⣸⠀⢸⢻⣏⢸⣭⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢹⣿⡷⣶⣿⡁⡗⣨⣯⣫⢞⣠⡴⢾⣷⣶⠉⣇⢸⣿⠀⢬⢸⡏⢼⣯⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢨⣩⣿⣭⡿⣿⣧⣿⡳⠃⠀⠉⠀⡈⠛⠛⠜⢹⣹⡏⠀⢘⡞⡇⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡗⡖⢿⣿⠧⢸⠿⢫⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠃⠀⣿⣿⡇⢸⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢪⢺⣿⡀⠀⠀⠰⠄⠀⠀⠀⠀⠀⠀⠀⠀⣠⡟⠀⢀⣿⣿⡇⢸⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣗⣉⣿⣿⣆⡀⠀⠀⠄⣀⡀⠤⠀⠀⣠⠞⡿⠂⠀⢸⣿⢿⣷⣿⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⢹⢹⣿⡿⣿⣶⣤⣀⠈⠁⡠⡔⠷⡁⣼⠋⠀⠀⡇⢿⡿⣿⢿⠸⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣘⠿⣿⢿⣿⣿⣿⣿⣿⣿⣵⣿⡜⢹⠏⠀⠀⠰⡀⠜⠛⢻⡿⣇⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⣒⣭⡵⣟⡄⣿⣧⣿⣿⣿⣷⣿⣿⣿⣿⣿⡏⠀⠀⢠⣯⡀⡢⢔⠁⠀⣀⠽⠥⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⢊⣴⠞⠈⡑⢀⡷⢲⣿⣿⣿⣿⣿⣿⢾⡟⣿⣿⡿⠁⠀⠈⠎⠠⠛⢳⢦⣞⠑⢄⢀⡀⠌⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢊⣴⠉⠀⢀⠌⢤⣾⢏⣿⢟⠛⠿⣿⣿⣿⣿⣻⣟⣻⠃⠀⠀⡜⠔⠁⢀⠌⠀⢻⡷⣎⠑⢄⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⢡⡇⠁⠀⠈⢨⣴⢿⡼⢒⣵⠿⠓⣤⣈⣉⠉⠉⠉⣩⠏⠀⠀⢠⠋⠀⠔⠁⣠⠖⠁⠈⠪⢷⡀⠱⡀⠀⠀⠀⠀⠑⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢠⢡⡗⡰⠀⠀⣔⡷⠛⢁⣴⠗⠁⡠⠊⠀⠀⠀⠀⠐⣇⡿⠀⠀⢠⠇⡠⠀⢀⠞⠁⠀⠀⠀⠀⠙⡻⡄⠐⡄⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⡘⣿⠑⠁⢀⠜⠁⣠⡺⠋⢀⠔⠁⠀⠀⠀⠀⠀⠀⡪⢚⡇⢰⠀⣌⠂⠈⡂⠁⠀⠀⠀⠀⠀⠀⠀⠨⣳⡀⠰⠀⠀⠀⠀⠀⢡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠐⣾⠃⠉⠀⢅⣴⠞⠋⢀⠔⠁⠀⠀⠀⠀⠀⢀⢔⠕⠕⡿⢀⡇⢠⡁⠠⠈⠀⠑⢄⠀⠀⠀⠀⠀⠀⠀⢹⣧⠀⡇⠀⠀⠀⠀⠈⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢤⡎⡑⠒⢴⠀⣿⠁⠢⠀⡊⠋⣶⠶⣦⣄⡠⡂⠠⠊⠀⡇⡸⢱⢸⣿⡄⠀⠀⠀⠀⢂⠀⠀⠀⠀⠀⠀⠈⣿⡀⢰⠀⠀⠀⠀⠀⢡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠉⣾⣷⡄⠀⠀⠀⠾⣵⠒⠒⢺⠀⢿⠀⠀⠐⠀⠀⠥⢀⡉⠭⠎⣀⡣⠤⠀⢇⣇⠤⢽⣿⣿⡄⠀⠀⠀⠀⢃⠀⠀⠀⠀⠀⠀⡿⠇⢀⣀⠀⠠⠤⠤⠼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢧⣧⣕⢝⢷⣄⠀⠀⣙⠒⢲⣦⡆⢸⡇⢸⠀⠀⠀⠀⠀⠐⠀⠀⠐⠂⠁⠉⠘⢷⠒⢚⠻⣿⣷⠠⠤⠤⠤⠬⡀⠤⠒⠒⠈⠁⣽⡏⢡⠀⢀⡀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢪⣭⡭⣧⣪⠏⠀⢠⢡⣷⣼⣟⣧⢸⡇⢸⠀⠀⠀⠀⠀⢰⢠⢠⠀⠀⠀⠀⠀⠈⠁⠁⠀⠀⢉⡄⠠⠠⠤⠤⣤⠤⠔⠒⠒⠒⠰⣶⠘⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣼⢧⣔⣾⣣⣯⣫⡉⢺⡿⣿⣷⣿⣻⣧⡘⠀⠀⠀⠀⠀⠨⠸⢸⠀⠀⠀⠀⠀⠀⠰⠀⠀⢀⡮⢡⠤⠤⠠⠤⠤⡀⠤⠔⠒⠒⠒⣿⠐⡖⠊⠉⠁⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢆⣾⣠⣼⢝⠀⠙⢷⣼⣆⠊⢛⢟⣿⣼⠈⡀⠉⠉⠉⠉⠹⠐⢺⠤⢀⡀⠀⠀⠀⡆⠀⠀⣸⠁⠀⠂⠀⡠⠀⠤⢤⣤⣤⣤⣤⣤⣿⣤⣥⣴⠒⠉⠉⢉⠄⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠷⢿⣹⣵⣟⠆⠀⠀⢿⣿⣆⠠⢊⣿⣿⠀⡇⠀⠀⠀⠀⠈⠀⡀⠀⠀⠈⠐⢤⡀⠀⠀⢠⡯⠠⠤⢄⠎⠀⠀⠀⠘⣿⣿⣿⢷⣿⢿⣟⣿⣿⡠⣔⡄⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠿⠭⣾⣷⠋⠀⠀⠀⠊⢿⣿⡄⡄⢸⠛⡷⣇⡀⠠⠠⠤⠤⣇⡅⠀⠀⠀⠀⠀⢸⠁⠀⡼⠀⠀⠀⡌⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⡿⠟⠋⠁⠀⢸⡇⠀⣀⢅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠉⢫⠀⠇⠀⠀⠀⠀⠇⠈⠻⣥⠓⠆⣤⡇⡆⠀⠀⠀⠀⠀⡇⡇⠉⠓⠢⣄⠀⢆⡀⠠⡷⠀⠀⠂⠀⠀⠀⠀⠀⠀⣄⠉⠉⠁⢳⡄⢀⣠⠤⠖⢟⡇⠀⠀⠈⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n";
        System.out.println("Hmph, it's you again...\n" + logo);
        greet();
        boolean isBye = false;

        Scanner sc = new Scanner(System.in);
        while (!isBye) {
            String userInput =  sc.nextLine();
            String comUserInput = userInput.toUpperCase();
            switch (comUserInput) {
                case "BYE":
                    exit();
                    isBye = true;
                    break;
                case "LIST":
                    list();
                    break;
                default:
                    if (comUserInput.contains("UNMARK")) {
                        String[] splitStr = userInput.split(" ");
                        int num = Integer.parseInt(splitStr[1]);
                        unmark(num);
                    } else if (comUserInput.contains("MARK")) {
                        String[] splitStr = userInput.split(" ");
                        int num = Integer.parseInt(splitStr[1]);
                        mark(num);
                    } else if (comUserInput.contains("TODO")) {
                        String[] splitStr = userInput.split(" ", 2);
                        todo(splitStr[1]);
                    } else if (comUserInput.contains("DEADLINE")) {
                        String[] splitStr = userInput.split("/");
                        String[] splitStr2 = splitStr[0].split(" ",2);
                        deadline(splitStr2[1], splitStr[1]);
                    } else if (comUserInput.contains("EVENT")) {
                        String[] splitStr = userInput.split("/");
                        String[] splitStr2 = splitStr[0].split(" ",2);
                        event(splitStr2[1], splitStr[1]);
                    } else {
                        echo(userInput);
                    }
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
        System.out.println("You forgetful baka... here are your tasks: ");
        for (int i = 0; i < countLst; i++) {
            int num = i + 1;
            System.out.println(num + ". " + aryLst[i].toString());
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
        aryLst[realNum].markDone();
        System.out.println("[" + aryLst[realNum].getStatusIcon()  +"] " + aryLst[realNum].getDescription());
        System.out.println(lines);
    }

    static private void unmark(int num) {
        System.out.println(lines);
        System.out.println("You didn't actually finish?!");
        int realNum = num - 1;
        aryLst[realNum].markNotDone();
        System.out.println("[" + aryLst[realNum].getStatusIcon()  +"] " + aryLst[realNum].getDescription());
        System.out.println(lines);
    }

    static private void todo(String strTask) {
        System.out.println(lines);
        System.out.println("New task! You better do it.");
        aryLst[countLst] = new ToDo(strTask);
        System.out.println(aryLst[countLst].toString());
        countLst++;
        System.out.println("You have " + countLst + " to do you lazy bum!");
        System.out.println(lines);
    }

    static private void deadline(String strTask, String by) {
        System.out.println(lines);
        System.out.println("New task! You better do it.");
        aryLst[countLst] = new Deadline(strTask, by);
        System.out.println(aryLst[countLst].toString());
        countLst++;
        System.out.println("You have " + countLst + " to do you lazy bum!");
        System.out.println(lines);
    }

    static private void event(String strTask, String at) {
        System.out.println(lines);
        System.out.println("New task! You better do it.");
        aryLst[countLst] = new Event(strTask, at);
        System.out.println(aryLst[countLst].toString());
        countLst++;
        System.out.println("You have " + countLst + " to do, you lazy bum!");
        System.out.println(lines);
    }

}
