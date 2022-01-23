import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

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
                        //                    System.out.println("   ________________________________________");
                        //                    System.out.println("    Really? Can you input an actual number this time... =.=");
                        //                    System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? Can you input an actual number this time... =.=");
                    } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                        //                    System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? mark has to be used with a number... =.=");
                        //                    System.out.println("   ________________________________________");
                    }
                } else if (command.equals("unmark")) {
                    try {
                        String parameters = inputArr[1];
                        int i = Integer.parseInt(parameters);
                        paggro.unmark(i);
                    } catch (NumberFormatException e) { // parameter was not a number
                        //                    System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? Can you input an actual number this time... =.=");
                        //                    System.out.println("   ________________________________________");
                    } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                        //                    System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? unmark has to be used with a number... =.=");
                        //                    System.out.println("   ________________________________________");
                    }
                } else if (command.equals("todo")) {
                    try {
                        String parameters = inputArr[1];
                        paggro.add(new ToDo(parameters));
                    } catch (ArrayIndexOutOfBoundsException e) { // no description given
                        //                        System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? The description of a todo cannot be empty... =.=");
                        //                        System.out.println("   ________________________________________");
                    }
                } else if (command.equals("deadline")) {
                    try {
                        String parameters = inputArr[1];
                        String[] desArr = parameters.split(" /", 2);
                        try {
                            String des = desArr[0];
                            String dateString = desArr[1];
                            LocalDate date = LocalDate.parse(dateString);
                            NotableDate nDate;
                            if (!paggro.dateMap.containsKey(date)) {
                                nDate = new NotableDate(date);
                                paggro.dateMap.put(date, nDate);
                            } else  {
                                nDate = paggro.dateMap.get(date);
                            }
                            paggro.add(new Deadline(des, nDate));
                        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
                            //                            System.out.println("   ________________________________________");
                            throw new PaggroException("    Really? =.= The use of the deadline command must be as follows:\n" +
                                    "      deadline <DESCRIPTION> /<DATE AND/OR TIME>");
                            //                            System.out.println("   ________________________________________");
                        } catch (DateTimeParseException e) {
                            throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                                    "      YYYY-MM-DD"));
                        }
                    } catch (ArrayIndexOutOfBoundsException e) { // no description given
                        //                        System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? The description of a deadline cannot be empty... =.=");
                        //                        System.out.println("   ________________________________________");
                    }
                } else if (command.equals("event")) {
                    try {
                        String parameters = inputArr[1];
                        String[] desArr = parameters.split(" /", 2);
                        try {
                            String des = desArr[0];
                            String dateString = desArr[1];
                            LocalDate date = LocalDate.parse(dateString);
                            NotableDate nDate;
                            if (!paggro.dateMap.containsKey(date)) {
                                nDate = new NotableDate(date);
                                paggro.dateMap.put(date, nDate);
                            } else  {
                                nDate = paggro.dateMap.get(date);
                            }
                            paggro.add(new Event(des, nDate));
                        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
                            //                            System.out.println("   ________________________________________");
                            throw new PaggroException("    Really? =.= The use of the event command must be as follows:\n" +
                                    "      event <DESCRIPTION> /<DATE AND/OR TIME>");
                            //                            System.out.println("   ________________________________________");
                        } catch (DateTimeParseException e) {
                            throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                                    "      YYYY-MM-DD"));
                        }
                    } catch (ArrayIndexOutOfBoundsException e) { // no description given
                        //                        System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? The description of an event cannot be empty... =.=");
                        //                        System.out.println("   ________________________________________");
                    }
                } else if (command.equals("delete")) {
                    try {
                        String parameters = inputArr[1];
                        int i = Integer.parseInt(parameters);
                        paggro.delete(i);
                    } catch (NumberFormatException e) { // parameter was not a number
                        //                    System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? Can you input an actual number this time... =.=");
                        //                    System.out.println("   ________________________________________");
                    } catch (ArrayIndexOutOfBoundsException e) { // no parameter given
                        //                    System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? delete has to be used with a number... =.=");
                        //                    System.out.println("   ________________________________________");
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
                        //                        System.out.println("   ________________________________________");
                        throw new PaggroException("    Really? The date of a listOnDate cannot be empty... =.=");
                        //                        System.out.println("   ________________________________________");
                    } catch (DateTimeParseException e) {
                        throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                                "      YYYY-MM-DD"));
                    }

                } else { // command not recognised
                    //                    System.out.println("   ________________________________________");
                    throw new PaggroException("    Come on... You don't actually expect me to understand that right... =.=");
                    //                    System.out.println("   ________________________________________");
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
