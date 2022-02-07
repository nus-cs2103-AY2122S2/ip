import org.json.JSONWriter;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class WordList {
    private ArrayList<WordListItem> wordList;

    public WordList() {
        this.wordList = new ArrayList<>();
    }

    private void echoAddedItem(WordListItem wordListItem) {
        System.out.println("  ------------------------------------");
        System.out.println("  Got it. I've added this task: ");
        System.out.println("    " + wordListItem);
        System.out.println("  You currently have " + this.length() + " tasks in your list");
        System.out.println("  ------------------------------------");
    }

    private void echoRemovedItem(WordListItem wordListItem) {
        System.out.println("  ------------------------------------");
        System.out.println("  Noted. I've removed this task: ");
        System.out.println("    " + wordListItem);
        System.out.println("  You currently have " + this.length() + " tasks in your list");
        System.out.println("  ------------------------------------");
    }

    public void storeTodo(String word, boolean isDone, boolean echo) {
        WordListItem todo = new Todo(word);
        if (isDone) {
            todo.markItem();
        }
        this.wordList.add(todo);
        if (echo) {
            echoAddedItem(todo);
        }
    }

    public void storeDeadline(String word, String datetime, boolean isDone, boolean echo) {
        WordListItem deadline = new Deadline(word, datetime);
        if (isDone) {
            deadline.markItem();
        }
        this.wordList.add(deadline);
        if (echo) {
            echoAddedItem(deadline);
        }
    }

    public void storeEvent(String word, String datetime, boolean isDone, boolean echo) {
        WordListItem event = new Event(word, datetime);
        if (isDone) {
            event.markItem();
        }
        this.wordList.add(event);
        if (echo) {
            echoAddedItem(event);
        }
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

    public void removeItem(int itemNumber) {
        WordListItem wordListItem = this.wordList.remove(itemNumber - 1);
        echoRemovedItem(wordListItem);
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
