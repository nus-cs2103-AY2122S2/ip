import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String[] temp, temptemp;
        Task tempTask;
        String input, command;

        System.out.println("Hola! soy José\nQué puedo hacer por ti? UwU");

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            System.out.println("-------------------------------------");
            if (input.equals("bye")) {
                System.out.println("Adiós. Espero volver a verte pronto!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Aquí están las tareas en su lista:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ": " + tasks.get(i));
                }
            } else {
                temp = input.split(" ", 2);
                command = temp[0];

                if (command.equals("mark")) {
                    tempTask = tasks.get(Integer.parseInt(temp[1]) - 1);
                    tempTask.mark();
                    System.out.println("¡Bonito! He marcado esta tarea como hecha:\n" + tempTask);
                } else if (command.equals("unmark")) {
                    tempTask = tasks.get(Integer.parseInt(temp[1]) - 1);
                    tempTask.unmark();
                    System.out.println("Bien, he marcado esta tarea como aún no realizada:\n" + tempTask);
                } else {
                    if (command.equals("todo")) {
                        tempTask = new ToDo(temp[1]);
                        tasks.add(tempTask);
                        System.out.println("Entendido. he añadido esta tarea:\n" + tempTask);
                    } else if (command.equals("deadline")) {
                        temptemp = temp[1].split(" /by ");
                        tempTask = new Deadline(temptemp[0], temptemp[1]);
                        tasks.add(tempTask);
                        System.out.println("Entendido. he añadido esta tarea:\n" + tempTask);
                    } else if (command.equals("event")) {
                        temptemp = temp[1].split(" /at ");
                        tempTask = new Event(temptemp[0], temptemp[1]);
                        tasks.add(tempTask);
                        System.out.println("Entendido. he añadido esta tarea:\n" + tempTask);
                    } else {
                        System.out.println("Error");
                    }
                    System.out.println("Ahora tienes " + tasks.size() + " tareas en la lista.");
                }
            }
            System.out.println("-------------------------------------");
        }

        scanner.close();
    }
}
