package duke;

import java.util.ArrayList;

public class HelpPage {
    private final ArrayList<String> helpPage;

    public HelpPage() {
        this.helpPage = new ArrayList<>();
        helpPage.add("1. Type 'todo <description>' to add a todo task\n");
        helpPage.add("2. Type 'deadline <description> <time>' to add a deadline task\n");
        helpPage.add("3. Type 'event <description> <time>' to add a event task\n");
        helpPage.add("4. Type 'mark <integer>' to mark a task as done\n");
        helpPage.add("5. Type 'unmark <integer>' to mark a task as undone\n");
        helpPage.add("6. Type 'delete <integer>' to delete a task from your list\n");
        helpPage.add("7. Type 'list' to list all your tasks\n");
        helpPage.add("8. Type 'find <keyword>' to find a task\n");
        helpPage.add("9. Type 'bye' to exit the app\n");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : helpPage) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
