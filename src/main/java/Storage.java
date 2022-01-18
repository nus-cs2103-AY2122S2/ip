/**
 * Storage helps to store tasks given by the user. Storage is contained in
 * the Bot class.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the storage currently.
 */

public class Storage {
    String[] tasks;
    int id = 0;

    /**
     * Constructs a Storage containing an empty array to contain tasks
     */
    Storage() {
        this.tasks = new String[100];
    }

    /**
     * Adds a given input to the tasks array
     * @param input String, given by user
     */
    void add(String input) {
        System.out.printf("added: %s\n", input);
        tasks[id] = input;
        id++;
    }

    /**
     * Prints out every item contained in the tasks array
     */
    void list() {
        for (int i = 0; i < id; i++) {
            System.out.printf("%d. %s\n", i + 1, tasks[i]);
        }
    }
}
