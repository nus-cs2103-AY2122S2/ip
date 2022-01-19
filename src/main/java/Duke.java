import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner s = new Scanner(System.in);
        int j = 0;
        Task[] list = new Task[100];
        String[] input = s.nextLine().split(" ");
        while (!(input[0].toLowerCase().equals("bye"))) {
            if (input[0].toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < list.length && list[i - 1] != null; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
                input = s.nextLine().split(" ");
                continue;
            } else if (input[0].toLowerCase().equals("mark")) {
                int i = Integer.parseInt(input[1]);
                list[i - 1].mark();
                System.out.println("Nice! I've marked this task as done:\n" + list[i - 1]);
                input = s.nextLine().split(" ");
                continue;
            } else if (input[0].toLowerCase().equals("unmark")) {
                int i = Integer.parseInt(input[1]);
                list[i - 1].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + list[i - 1]);
                input = s.nextLine().split(" ");
                continue;
            } else {
                String name = "";
                if (input[0].toLowerCase().equals("todo")) {
                    for (int i = 1; i < input.length; i++) {
                        name += " " + input[i];
                    }
                    list[j] = new Todo(name);
                    System.out.println("Got it. I have added this task: " + list[j]);
                    j += 1;
                    System.out.println("Now you have " + j + " in the list");
                    input = s.nextLine().split(" ");
                    continue;
                } else {
                    String deadline = "";
                    if (input[0].toLowerCase().equals("deadline")) {
                        for (int i = 1; i < input.length; i++) {
                            if (input[i].equals("/by")) {
                                for (int k = i+1; k < input.length; k++) {
                                    deadline += " " + input[k];
                                }
                                break;
                            }
                            name += " " + input[i];
                        }
                        list[j] = new Deadline(name, deadline);


                    } else if (input[0].toLowerCase().equals("event")) {
                        for (int i = 1; i < input.length; i++) {
                            if (input[i].equals("/at")) {
                                for (int k = i+1; k < input.length; k++) {
                                    deadline += " " + input[k];
                                }
                                break;
                            }
                            name += " " + input[i];
                        }
                        list[j] = new Event(name, deadline);
                    }
                    System.out.println("Got it. I have added this task: " + list[j]);
                    j += 1;
                    System.out.println("Now you have " + j + " in the list");
                    input = s.nextLine().split(" ");
                    continue;
                }


                }
            }
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

