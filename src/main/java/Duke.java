import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class Duke {
    private static void format(String output) {
        String border = "   ____________________________________________________________";
        System.out.println(border + "\n      " + output + "\n" +  border);
    }

    public static void main(String[] args) {
        final String SPACE = "\n      ";
        final String TASKSPACE = "\n        ";
        String logo =
                " _____           _ _   \n" +
                "|  ___|         (_) |   \n" +
                "| |__ _ __   ___ _| | __\n" +
                "|  __| '_ \\ / __| | |/ /\n" +
                "| |__| | | | (__| |   < \n" +
                "\\____/_| |_|\\___|_|_|\\_\\\n";
        System.out.println("GOOD MORNING GENNERMEN from\n" + logo);

        String border = "   ____________________________________________________________";
        String welcome =
                "GOOD MORNING CHAO RECRUIT! YOU MAY CALL ME ENCIK ENCIK" + SPACE +
                "WHAT YOU WANT?";
        format(welcome);

        //Checks if prev. tasks exist
        String logPath = "data/log.txt";
        DukeFile log = new DukeFile(logPath);

        //Create ArrayList to store prev tasks
        ArrayList<Task> tasks = log.readTasks();

        Scanner input = new Scanner(System.in);
        String toEcho = input.nextLine();
        while (!toEcho.equals("bye")) {
            try {
                //Add name of task to str for easy printing
                StringBuilder str = new StringBuilder();
                str.append("NEED ME TO REMIND YOU AH?");
                if (toEcho.equals("list")) {
                    int size = tasks.size();
                    if (size == 0) {
                        format("NOTHING TO DO AH?" + SPACE +
                                "YOU BETTER FIND SOMETHING TO DO BEFORE I CONFINE YOU CHAO RECRUIT!");
                    } else {
                        for (int i = 0; i < size; i++) {
                            str.append(SPACE + (i + 1) + "." + tasks.get(i));
                        }
                        format(str.toString());
                    }
                } else {
                    //Split cmd into 2 parts, the type of task, and remaining text
                    String[] temp = toEcho.split(" ", 2);
                    String cmd = temp[0];
                    //placeholder in case temp[1] is empty and cannot be initialised
                    String rem = "";
                    boolean incomplete = false;
                    if (temp.length < 2 || temp[1].isBlank()) {
                        incomplete = true;
                    } else {
                        rem = temp[1];
                    }

                    //Assuming input correct
                    if (cmd.equals("mark")) {
                        if (incomplete) {
                            throw new DukeException("WHAT YOU WANT MARK? WEAR HELMET CANNOT THINK ALREADY AH?");
                        } else {
                            int num = Integer.parseInt(rem) - 1;
                            tasks.get(num).mark();
                            String confirm =
                                    "THIS ONE" + TASKSPACE + tasks.get(num) + SPACE +
                                    "FINISH ALREADY AH? SWEE CHAI BUTTERFLY RECRUIT!";
                            format(confirm);
                        }
                    } else if (cmd.equals("unmark")) {
                        if (incomplete) {
                            throw new DukeException("WHAT YOU WANT UNMARK? WEAR HELMET CANNOT THINK ALREADY AH?");
                        } else {
                            int num = Integer.parseInt(rem) - 1;
                            tasks.get(num).unmark();
                            String confirm =
                                    "I THOUGHT THIS ONE" + TASKSPACE + tasks.get(num) + SPACE +
                                    "FINISH ALREADY? NEVER MIND THIS WEEKEND CONFINE!";
                            format(confirm);
                        }
                    } else if (cmd.equals("delete")) {
                        if (incomplete) {
                            throw new DukeException("YOU TRYING TO LEPAK IS IT? WAKE UP YOUR BLOODY IDEA!");
                        } else {
                            int num = Integer.parseInt(rem) - 1;
                            Task toDelete = tasks.get(num);
                            tasks.remove(num);
                            int size = tasks.size();
                            String confirm =
                                    "YOU DON'T WANT DO THEN SAY DON'T DO AH?" + TASKSPACE + toDelete.toString() +
                                    SPACE + "WAKE UP YOUR BLOODY IDEA CHAO RECRUIT!";
                            if (size == 0) {
                                confirm += SPACE + "NOTHING ELSE TO DO CAN RILEK ALREADY AH RECRUIT? DOWN 20!";
                            } else {
                                confirm += SPACE + size + " MORE TASKS REMAINING! YOU BETTER ONE TIMES GOOD ONE!";
                            }
                            format(confirm);
                        }
                    } else if (cmd.equals("deadline")) {
                        //deadline
                        if (incomplete) {
                            throw new DukeException("WHAT YOU WANT DO? NOTHING AH HELLOOOOOO?");
                        } else {
                            //DateTimeFormatter pattern for reading date and time
                            DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            DateTimeFormatter timeInputFormatter = DateTimeFormatter.ofPattern("HHmm");
                            String task = rem.split(" /by ")[0];
                            String dateTime = rem.split(" /by ")[1];
                            String date = dateTime.split(" ")[0];
                            String time = dateTime.split(" ")[1];
                            LocalDate d1 = LocalDate.parse(date, dateInputFormatter);
                            LocalTime t1 = LocalTime.parse(time, timeInputFormatter);
                            Task tempTask = new Deadline(task, d1, t1);
                            tasks.add(tempTask);
                            String confirm =
                                    "YOU BETTER FINISH THIS AH:" + TASKSPACE + tempTask + SPACE +
                                    "YOU STILL GOT " + tasks.size() +
                                    " THINGS TO DO AH BETTER NOT FORGET!";
                            format(confirm);
                        }
                    } else if (cmd.equals("event")) {
                        //DateTimeFormatter pattern for reading date and time
                        DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        DateTimeFormatter timeInputFormatter = DateTimeFormatter.ofPattern("HHmm");
                        //event
                        if (incomplete) {
                            throw new DukeException("WHAT YOU WANT DO? NOTHING AH HELLOOOOOO?");
                        } else {
                            String task = rem.split(" /at ")[0];
                            String dead = rem.split(" /at ")[1];
                            String date = dead.split(" ")[0];
                            String[] time = dead.split(" ")[1].split("-");
                            LocalDate d1 = LocalDate.parse(date, dateInputFormatter);
                            LocalTime timeFrom = LocalTime.parse(time[0], timeInputFormatter);
                            LocalTime timeTo = LocalTime.parse(time[1], timeInputFormatter);
                            Task tempTask = new Event(task, d1, timeFrom, timeTo);
                            tasks.add(tempTask);
                            String confirm =
                                    "YOU BETTER REMEMBER THIS AH:" + TASKSPACE + tempTask + SPACE +
                                    "YOU STILL GOT " + tasks.size() +
                                    " THINGS TO DO AH BETTER NOT FORGET!";
                            format(confirm);
                        }
                    } else if (cmd.equals("todo")) {
                        //todo
                        if (incomplete) {
                            throw new DukeException("WHAT YOU WANT DO? NOTHING AH HELLOOOOOO?");
                        } else {
                            Task tempTask = new ToDo(rem);
                            tasks.add(tempTask);
                            String confirm =
                                    "YOU WANT TO DO THIS AH:" + TASKSPACE + tempTask + SPACE +
                                    "VERY GOOD! YOU STILL GOT " + tasks.size() +
                                    " THINGS TO DO AH BETTER NOT FORGET!";
                            format(confirm);
                        }
                    } else {
                        throw new DukeException("WHAT TALKING YOU? CHAO RECRUIT YOU BETTER WAKE UP YOUR IDEA!");
                    }

                    // Updates log file
                    try {
                        log.updateTasks(tasks);
                    } catch (IOException e) {
                        System.out.printf(e.getMessage());
                    }
                }
            } catch (DukeException e) {
                format(e.getMessage());
            }
            toEcho = input.nextLine();
        }
        format("BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!");
    }
}
