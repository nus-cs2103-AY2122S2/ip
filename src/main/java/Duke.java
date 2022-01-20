import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arrlst = new ArrayList<>();
        String DASH = "____________________________________________________________";
        Action act = new Action();

        act.greet();

        while (sc.hasNext()) {
            String phrase = sc.nextLine();
            System.out.println(DASH);

            if (phrase.equals("list")) {
                act.showList(arrlst);
            } else if (phrase.equals("bye")) {
                act.bye();
                System.out.println(DASH);
                break;
            } else {
                String[] arrWords = phrase.split(" ");
                try { // mark or unmark
                    int num = Integer.valueOf(arrWords[1]);
                    if (arrWords[0].equals("mark")) {
                        arrlst.get(num - 1).markAsDone();
                    } else if (arrWords[0].equals("unmark")) {
                        arrlst.get(num - 1).markAsNotDone();
                    } else {
                        System.out.println("siao eh");
                    }
                } catch (NumberFormatException nfe) { // add new task like read book, return book
                    String gotIt = "Got it. I've added this task:";

                    if (arrWords[0].equals("todo")) {
                        String remainingWords = "";
                        for (int i = 1; i < arrWords.length; i++) {
                            remainingWords = remainingWords + " " + arrWords[i];
                        }
                        Todo t = new Todo(remainingWords);
                        arrlst.add(new Task(t.description, t.sym));
                        System.out.println(gotIt);
                        System.out.println(t);
                        String noOfTask = String.format("Now you have %d tasks in the list.", arrlst.size());
                        System.out.println(noOfTask);
                    } else if (arrWords[0].equals("deadline")) {
                        String remainingWords = "";
                        String dLine = "";
                        for (int i = 1; i < arrWords.length; i++) {
                            if (arrWords[i].equals("/by")) {
                                for (int j = i + 1; j < arrWords.length; j++) {
                                    dLine = dLine + " " + arrWords[j];
                                }
                                break;
                            } else {
                                remainingWords = remainingWords + " " + arrWords[i];
                            }
                        }
                        Deadline t = new Deadline(remainingWords, dLine);
                        arrlst.add(new Task(t.description, t.sym));
                        System.out.println(gotIt);
                        System.out.println(t);
                        String noOfTask = String.format("Now you have %d tasks in the list.", arrlst.size());
                        System.out.println(noOfTask);
                    } else if (arrWords[0].equals("event")) {
                        String remainingWords = "";
                        String dayAndTime = "";
                        for (int i = 1; i < arrWords.length; i++) {
                            if (arrWords[i].equals("/at")) {
                                for (int j = i + 1; j < arrWords.length; j++) {
                                    dayAndTime = dayAndTime + " " + arrWords[j];
                                }
                                break;
                            } else {
                                remainingWords = remainingWords + " " + arrWords[i];
                            }
                        }
                        Event t = new Event(remainingWords, dayAndTime);
                        arrlst.add(new Task(t.description, t.sym));
                        System.out.println(gotIt);
                        System.out.println(t);
                        String noOfTask = String.format("Now you have %d tasks in the list.", arrlst.size());
                        System.out.println(noOfTask);
                    }
                } catch (ArrayIndexOutOfBoundsException aioobe) { // echo
                    act.echo(phrase);
                    System.out.println("sehh, what is this?");
                }
            }
            System.out.println(DASH);
        }
    }
}
