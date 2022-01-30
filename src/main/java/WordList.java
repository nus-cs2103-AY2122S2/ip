import java.util.ArrayList;

public class WordList {
    private ArrayList<WordListItem> wordList;

    public WordList() {
        this.wordList = new ArrayList<>();
    }

    public void storeWord(String word) {
        this.wordList.add(new WordListItem(word));
        System.out.println("  ------------------------------------");
        System.out.println("  added: " + word);
        System.out.println("  ------------------------------------");
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
