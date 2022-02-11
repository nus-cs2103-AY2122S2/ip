package notes;

public class Note {

    String description;

    public Note(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
