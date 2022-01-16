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
        aryLst[countLst] = new Task(userInput);
        countLst++;
        System.out.println(lines);
    }
    static private void list() {
        System.out.println(lines);
        System.out.println("You forgetful baka... here are your tasks: ");
        for (int i = 0; i < countLst; i++) {
            int num = i + 1;
            System.out.println(num + ". [" + aryLst[i].getStatusIcon()  +"] " + aryLst[i].getDescription());
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



}
