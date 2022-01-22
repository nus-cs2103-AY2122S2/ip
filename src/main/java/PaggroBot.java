import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                char type = taskString.charAt(0);
                String[] details;
                boolean isDone;
                String des;
                String time;
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
                    details = taskString.split(" ");
                    isDone = Boolean.parseBoolean(details[2]);
                    des = details[4];
                    time = details[6];
                    tasks.add(new Event(des, time, isDone));
                    break;
                case 'D':
                    details = taskString.split(" ");
                    isDone = Boolean.parseBoolean(details[2]);
                    des = details[4];
                    time = details[6];
                    tasks.add(new Deadline(des, time, isDone));
                    break;
                default:
                    System.out.println("File format error!");
                }
            }
            paggro = new Lister(tasks, paggroData);
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
                    paggro.list();
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
                            String time = desArr[1];
                            paggro.add(new Deadline(des, time));
                        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
                            //                            System.out.println("   ________________________________________");
                            throw new PaggroException("    Really? =.= The use of the deadline command must be as follows:\n" +
                                    "      deadline <DESCRIPTION> /<DATE AND/OR TIME>");
                            //                            System.out.println("   ________________________________________");
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
                            String time = desArr[1];
                            paggro.add(new Event(des, time));
                        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
                            //                            System.out.println("   ________________________________________");
                            throw new PaggroException("    Really? =.= The use of the event command must be as follows:\n" +
                                    "      event <DESCRIPTION> /<DATE AND/OR TIME>");
                            //                            System.out.println("   ________________________________________");
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
                } else { // command not recognised
                    //                    System.out.println("   ________________________________________");
                    throw new PaggroException("    Come on... You don't actually expect me to understand that right... =.=");
                    //                    System.out.println("   ________________________________________");
                }
        } catch(PaggroException e){
            System.out.println("   ________________________________________");
            System.out.println(e.getMessage());
            System.out.println("   ________________________________________");
        } finally{
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
