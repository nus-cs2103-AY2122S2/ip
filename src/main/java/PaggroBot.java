import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class PaggroBot {
    public static void main(String[] args) throws PaggroException {
        Lister paggro = new Lister();
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
                            NotableDate nDate;
                            if (!paggro.dateMap.containsKey(date)) { // checks if NotableDate has already been initialised
                                nDate = new NotableDate(date);
                                paggro.dateMap.put(date, nDate);
                            } else  {
                                nDate = paggro.dateMap.get(date);
                            }
                            if (dateTimeArr.length > 1) {
                                timeString = dateTimeArr[1];
                                try {
                                    LocalTime time = LocalTime.parse(timeString);
                                    paggro.add(new Deadline(des, nDate, time));
                                } catch (DateTimeParseException e) {
                                    throw new PaggroException("    Really? =.= Time inputs must be in this format:\n" +
                                            "      HH:MM");
                                }
                            } else {
                                paggro.add(new Deadline(des, nDate));
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
                            NotableDate nDate;
                            if (!paggro.dateMap.containsKey(date)) { // checks if NotableDate has already been initialised
                                nDate = new NotableDate(date);
                                paggro.dateMap.put(date, nDate);
                            } else  {
                                nDate = paggro.dateMap.get(date);
                            }
                            if (dateTimeArr.length > 1) {
                                timeString = dateTimeArr[1];
                                try {
                                    LocalTime time = LocalTime.parse(timeString);
                                    paggro.add(new Event(des, nDate, time));
                                } catch (DateTimeParseException e) {
                                    throw new PaggroException("    Really? =.= Time inputs must be in this format:\n" +
                                            "      HH:MM");
                                }
                            } else {
                                paggro.add(new Event(des, nDate));
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
