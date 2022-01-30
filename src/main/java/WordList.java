import java.util.ArrayList;

public class WordList {
    private ArrayList<WordListItem> wordList;

    public WordList() {
        this.wordList = new ArrayList<>();
    }

    private void echoAddedItem(WordListItem wordListItem) {
        System.out.println("  ------------------------------------");
        System.out.println("  Got it. I've added this task: ");
        System.out.println("  " + wordListItem);
        System.out.println("  ------------------------------------");
    }

    public void storeTodo(String word) {
        WordListItem todo = new Todo(word);
        this.wordList.add(todo);
        echoAddedItem(todo);
    }

    public void storeDeadline(String word, String datetime) {
        WordListItem deadline = new Deadline(word, datetime);
        this.wordList.add(deadline);
        echoAddedItem(deadline);
    }

    public void storeEvent(String word, String datetime) {
        WordListItem event = new Event(word, datetime);
        this.wordList.add(event);
        echoAddedItem(event);
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

    public void printList() {
        System.out.println(this);
    }

    public int length() {
        return this.wordList.size();
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
