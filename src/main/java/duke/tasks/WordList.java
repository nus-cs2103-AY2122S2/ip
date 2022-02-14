package duke.tasks;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.WordListItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

public class WordList {
    private ArrayList<WordListItem> wordList;

    public WordList() {
        this.wordList = new ArrayList<>();
    }

    public Todo storeTodo(String word, boolean isDone) {
        Todo todo = new Todo(word);
        if (isDone) {
            todo.markItem();
        }
        this.wordList.add(todo);
        return todo;
    }

    public Deadline storeDeadline(String word, LocalDateTime datetime, boolean isDone) {
        Deadline deadline = new Deadline(word, datetime);
        if (isDone) {
            deadline.markItem();
        }
        this.wordList.add(deadline);
        return deadline;
    }

    public Event storeEvent(String word, LocalDateTime datetime, boolean isDone) {
        Event event = new Event(word, datetime);
        if (isDone) {
            event.markItem();
        }
        this.wordList.add(event);
        return event;
    }

    public void markItem(int itemNumber) {
        this.wordList.get(itemNumber - 1).markItem();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + this.wordList.get(itemNumber - 1));
    }

    public void unmarkItem(int itemNumber) {
        this.wordList.get(itemNumber - 1).unmarkItem();
        System.out.println("Nice! I've marked this task as not done: ");
        System.out.println("  " + this.wordList.get(itemNumber - 1));
    }

    public WordListItem removeItem(int itemNumber) {
        WordListItem wordListItem = this.wordList.remove(itemNumber - 1);
        return wordListItem;
    }

    public void printList() {
        System.out.println(this);
    }

    public int length() {
        return this.wordList.size();
    }

    public void forEach(Consumer<? super WordListItem> action) {
        wordList.forEach(action);
    }

    @Override
    public String toString() {
        int i = 1;
        String str = "";
        str += "------------------------------------\n";
        for(WordListItem wordListItem: this.wordList) {
            str += i + ". " + wordListItem + "\n";
            i++;
        }
        str += "------------------------------------\n";
        return str;
    }
}
