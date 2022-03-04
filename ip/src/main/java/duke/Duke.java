package duke;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import duke.Exceptions.*;


public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        Ui ui = new Ui(taskList);
        Parser parser = new Parser(storage, ui, taskList, sc);
        ui.greet();

        try {
            storage.load();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        String input = sc.nextLine();
        while (!input.startsWith("bye")) {
            try {
                parser.parse(input);
            } catch (DeadlineException e){
                System.out.println(e.getMessage());
            }catch (EventException e){
                System.out.println(e.getMessage());
            }catch (InvalidInstructionException e){
                System.out.println(e.getMessage());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }
        ui.farewell();
        sc.close();
    }
}