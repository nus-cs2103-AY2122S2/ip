package duke.tasks;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.WordListItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * An object storing the list of words/tasks.
 * WordList is able to store, remove, and find items.
 * WordList can also be iterated through using forEach()
 * @see #forEach(Consumer)
 */
public class WordList {
    private ArrayList<WordListItem> wordList;

    /**
     * Construct an empty WordList.
     */
    public WordList() {
        this.wordList = new ArrayList<>();
    }

    /**
     * Store a todo into the wordlist. Also returns the todo as well.
     * @see Todo
     * @param word description of the task.
     * @param isDone status of the task.
     * @return Todo object
     */
    public Todo storeTodo(String word, boolean isDone) {
        Todo todo = new Todo(word);
        if (isDone) {
            todo.markItem();
        }
        this.wordList.add(todo);
        return todo;
    }

    /**
     * Store a deadline into the wordlist. Also returns the deadline as well.
     * @see Deadline
     * @param word description of the task.
     * @param datetime datetime of the task.
     * @param isDone status of the task.
     * @return Deadline object
     */
    public Deadline storeDeadline(String word, LocalDateTime datetime, boolean isDone) {
        Deadline deadline = new Deadline(word, datetime);
        if (isDone) {
            deadline.markItem();
        }
        this.wordList.add(deadline);
        return deadline;
    }

    /**
     * Store a event into the wordlist. Also returns the event as well.
     * @see Event
     * @param word description of the task.
     * @param datetime datetime of the task.
     * @param isDone status of the task.
     * @return Event object
     */
    public Event storeEvent(String word, LocalDateTime datetime, boolean isDone) {
        Event event = new Event(word, datetime);
        if (isDone) {
            event.markItem();
        }
        this.wordList.add(event);
        return event;
    }

    /**
     * Mark the task with the index/itemNumber in the wordlist as done.
     * @param itemNumber index of the task in the wordlist.
     * @return string response statement
     */
    public String markItem(int itemNumber) {
        this.wordList.get(itemNumber - 1).markItem();
        String s = "Nice! I've marked this task as done: \n";
        s += "  " + this.wordList.get(itemNumber - 1);
        return s;
    }

    /**
     * Mark the task with the index/itemNumber in the wordlist as not done.
     * @param itemNumber index of the task in the wordlist.
     * @return string response statement
     */
    public String unmarkItem(int itemNumber) {
        this.wordList.get(itemNumber - 1).unmarkItem();
        String s = "Nice! I've marked this task as not done: \n";
        s += "  " + this.wordList.get(itemNumber - 1);
        return s;
    }

    /**
     * Remove the task with the index/itemNumber in the wordlist. Also returns the task removed.
     * @param itemNumber index of the task in the wordlist.
     * @return the task removed
     */
    public WordListItem removeItem(int itemNumber) {
        WordListItem wordListItem = this.wordList.remove(itemNumber - 1);
        return wordListItem;
    }

    /**
     * Find items with the keyword as the substring.
     * @param keyword the keyword used to detect the tasks.
     * @return an array of tasks.
     */
    public WordListItem[] findItems(String keyword) {
        return wordList.stream().filter(wordListItem -> wordListItem.getDescription().contains(keyword))
                .toArray(WordListItem[]::new);
    }

    /**
     * Get the size of the current wordlist.
     * @return the length.
     */
    public int length() {
        return this.wordList.size();
    }

    /**
     * Iterate through the tasks inside of the wordlist.
     * @param action a consumer which consumes the tasks.
     */
    public void forEach(Consumer<? super WordListItem> action) {
        wordList.forEach(action);
    }

    @Override
    public String toString() {
        int i = 1;
        String str = "";
        str += "Here is your current tasks:\n";
        for(WordListItem wordListItem: this.wordList) {
            str += i + ". " + wordListItem + "\n";
            i++;
        }
        return str;
    }
}
