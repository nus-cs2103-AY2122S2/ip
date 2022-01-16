import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String[] temp;
        Task tempTask;
        String input;

        System.out.println("Hola! soy José\nQué puedo hacer por ti? UwU");

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Adiós. Espero volver a verte pronto!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Aquí están las tareas en su lista:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ": " + tasks.get(i));
                }
            } else {
                temp = input.split(" ");
                if (temp[0].equals("mark")) {
                    tempTask = tasks.get(Integer.parseInt(temp[1]) - 1);
                    tempTask.mark();
                    System.out.println("¡Bonito! He marcado esta tarea como hecha:\n" + tempTask);
                } else if (temp[0].equals("unmark")) {
                    tempTask = tasks.get(Integer.parseInt(temp[1]) - 1);
                    tempTask.unmark();
                    System.out.println("Bien, he marcado esta tarea como aún no realizada:\n" + tempTask);
                } else {
                    System.out.println("Agregado: " + input);
                    tasks.add(new Task(input));
                }                
            }
        }

        scanner.close();
    }
}
