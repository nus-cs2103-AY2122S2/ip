package duke.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import duke.data.TasksEditor;
import duke.data.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Finds and lists all tasks in task list whose description contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command{

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose description contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " book";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        final List<Task> tasksFound = getTasksWithDescriptionContainingAnyKeyword(this.keywords, tasksEditor);
        return ui.find(tasksFound);
    }

    /**
     * Retrieves all tasks in the address book whose description contain some of the specified keywords.
     *
     * @param keywords for searching.
     * @param tasksEditor The task list editor to handle task operations.
     * @return List of tasks found.
     */
    private List<Task> getTasksWithDescriptionContainingAnyKeyword(Set<String> keywords, TasksEditor tasksEditor) {
        final List<Task> matchedTasks = new ArrayList<>();
        for (Task t : tasksEditor.toList()) {
            final Set<String> wordsInName = new HashSet<>(t.getWordsInDescription());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedTasks.add(t);
            }
        }
        return matchedTasks;
    }
}
