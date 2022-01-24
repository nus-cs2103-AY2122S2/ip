import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class PaggroBot {
    public static void main(String[] args) throws IOException {
        File paggroData = new File("../../../data/paggro.txt");
        Lister paggro = null;
        if (!paggroData.exists()) {
            try {
                paggroData.createNewFile();
                paggro = new Lister(paggroData);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            Scanner sc = new Scanner(paggroData);
            ArrayList<Task> tasks = new ArrayList<>();
            HashMap<LocalDate, NotableDate> dateMap = new HashMap<>();
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                char type = taskString.charAt(0);
                String[] details;
                boolean isDone;
                String des;
                LocalDate lDate;
                LocalTime lTime;
                NotableDate nDate;
                Task t;
                switch (type) {
                case 'T':
                    details = taskString.split(" ");
                    isDone = Boolean.parseBoolean(details[2]);
                    des = details[4];
                    System.out.println(des);
                    System.out.println(isDone);
                    tasks.add(new ToDo(des, isDone));
                    break;
                case 'E':
                    details = taskString.split(" \\| ");
                    isDone = Boolean.parseBoolean(details[1]);
                    des = details[2];
                    lDate = LocalDate.parse(details[3]);
                    if (!dateMap.containsKey(lDate)) { // checks if NotableDate has already been initialised
                        nDate = new NotableDate(lDate);
                        dateMap.put(lDate, nDate);
                    } else  {
                        nDate = dateMap.get(lDate);
                    }
                    if (details.length > 4) {
                        lTime = LocalTime.parse(details[4]);
                        t = new Event(des, nDate, lTime, isDone);
                    } else {
                        t = new Event(des, nDate, isDone);
                    }
                    tasks.add(t);
                    nDate.addTask(t);
                    break;
                case 'D':
                    details = taskString.split(" \\| ");
                    isDone = Boolean.parseBoolean(details[1]);
                    des = details[2];
                    lDate = LocalDate.parse(details[3]);
                    if (!dateMap.containsKey(lDate)) { // checks if NotableDate has already been initialised
                        nDate = new NotableDate(lDate);
                        dateMap.put(lDate, nDate);
                    } else  {
                        nDate = dateMap.get(lDate);
                    }
                    if (details.length > 4) {
                        lTime = LocalTime.parse(details[4]);
                        t = new Deadline(des, nDate, lTime, isDone);
                    } else {
                        t = new Deadline(des, nDate, isDone);
                    }
                    tasks.add(t);
                    nDate.addTask(t);
                    break;
                default:
                    System.out.println("File format error!");
                }
            }
            paggro = new Lister(tasks, dateMap, paggroData);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("   ________________________________________");
        System.out.println("    Hi I'm PaggroBot =.=\n    What do you want? =.=");
        System.out.println("   ________________________________________");
        String input = sc.nextLine();
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    paggro.list(paggro.tasks);
                } else if (command.equals("mark")) {
                    try {
                        String parameters = inputArr[1];
                        int i = Integer.parseInt(parameters);
                        paggro.mark(i);
                    } catch (NumberFormatException e) { // parameter was not a number
                        throw new PaggroException("    Really? Can you input an actual number this time... =.=");
                    } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                        throw new PaggroException("    Really? mark has to be used with a number... =.=");
                    }
                } else if (command.equals("unmark")) {
                    try {
                        String parameters = inputArr[1];
                        int i = Integer.parseInt(parameters);
                        paggro.unmark(i);
                    } catch (NumberFormatException e) { // parameter was not a number
                        throw new PaggroException("    Really? Can you input an actual number this time... =.=");
                    } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                        throw new PaggroException("    Really? unmark has to be used with a number... =.=");
                    }
                } else if (command.equals("todo")) {
                    try {
                        String parameters = inputArr[1];
                        paggro.add(new ToDo(parameters));
                    } catch (ArrayIndexOutOfBoundsException e) { // no description given
                        throw new PaggroException("    Really? The description of a todo cannot be empty... =.=");
                    }
                } else if (command.equals("deadline")) {
                    try {
                        String parameters = inputArr[1];
                        String[] desArr = parameters.split(" /", 2);
                        try {
                            String des = desArr[0];
                            String dateTimeString = desArr[1];
                            String[] dateTimeArr = dateTimeString.split(" ");
                            String dateString = dateTimeArr[0];
                            String timeString = null;
                            LocalDate date = LocalDate.parse(dateString);
                            NotableDate nDate = paggro.checkDate(date);
                            if (dateTimeArr.length > 1) {
                                timeString = dateTimeArr[1];
                                try {
                                    LocalTime time = LocalTime.parse(timeString);
                                    paggro.add(new Deadline(des, nDate, time, false));
                                } catch (DateTimeParseException e) {
                                    throw new PaggroException("    Really? =.= Time inputs must be in this format:\n" +
                                            "      HH:MM");
                                }
                            } else {
                                paggro.add(new Deadline(des, nDate, false));
                            }
                        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
                            throw new PaggroException("    Really? =.= The use of the deadline command must be as follows:\n" +
                                    "      deadline <DESCRIPTION> /<DATE AND/OR TIME>");
                        } catch (DateTimeParseException e) {
                            throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                                    "      YYYY-MM-DD"));
                        }
                    } catch (ArrayIndexOutOfBoundsException e) { // no description given
                        throw new PaggroException("    Really? The description of a deadline cannot be empty... =.=");
                    }
                } else if (command.equals("event")) {
                    try {
                        String parameters = inputArr[1];
                        String[] desArr = parameters.split(" /", 2);
                        try {
                            String des = desArr[0];
                            String dateTimeString = desArr[1];
                            String[] dateTimeArr = dateTimeString.split(" ");
                            String dateString = dateTimeArr[0];
                            String timeString = null;
                            LocalDate date = LocalDate.parse(dateString);
                            NotableDate nDate = paggro.checkDate(date);
                            if (dateTimeArr.length > 1) {
                                timeString = dateTimeArr[1];
                                try {
                                    LocalTime time = LocalTime.parse(timeString);
                                    paggro.add(new Event(des, nDate, time, false));
                                } catch (DateTimeParseException e) {
                                    throw new PaggroException("    Really? =.= Time inputs must be in this format:\n" +
                                            "      HH:MM");
                                }
                            } else {
                                paggro.add(new Event(des, nDate, false));
                            }
                        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
                            throw new PaggroException("    Really? =.= The use of the event command must be as follows:\n" +
                                    "      event <DESCRIPTION> /<DATE AND/OR TIME>");
                        } catch (DateTimeParseException e) {
                            throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                                    "      YYYY-MM-DD"));
                        }
                    } catch (ArrayIndexOutOfBoundsException e) { // no description given
                        throw new PaggroException("    Really? The description of an event cannot be empty... =.=");
                    }
                } else if (command.equals("delete")) {
                    try {
                        String parameters = inputArr[1];
                        int i = Integer.parseInt(parameters);
                        paggro.delete(i);
                    } catch (NumberFormatException e) { // parameter was not a number
                        throw new PaggroException("    Really? Can you input an actual number this time... =.=");
                    } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                        throw new PaggroException("    Really? delete has to be used with a number... =.=");
                    }
                } else if (command.equals("listOnDate")) {
                    try {
                        String dateString = inputArr[1];
                        LocalDate date = LocalDate.parse(dateString);
                        if (!paggro.dateMap.containsKey(date)) {
                            System.out.println("   ________________________________________");
                            System.out.println("    Nothing happening on this date... =.=");
                            System.out.println("   ________________________________________");
                        } else {
                            NotableDate nDate = paggro.dateMap.get(date);
                            paggro.list(nDate.tasks);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) { // no description given
                        throw new PaggroException("    Really? The date of a listOnDate cannot be empty... =.=");
                    } catch (DateTimeParseException e) {
                        throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                                "      YYYY-MM-DD"));
                    }

                } else { // command not recognised
                    throw new PaggroException("    Come on... You don't actually expect me to understand that right... =.=");
                }
            } catch (PaggroException e) {
                System.out.println("   ________________________________________");
                System.out.println(e.getMessage());
                System.out.println("   ________________________________________");
            } finally {
                input = sc.nextLine();
                inputArr = input.split(" ", 2);
                command = inputArr[0];
            }
        }
        System.out.println("   ________________________________________");
        System.out.println("    Oh finally. Please don't come back anytime soon. =.=");
        System.out.println("   ________________________________________");
    }
}
