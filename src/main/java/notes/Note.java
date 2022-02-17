package notes;

public class Note {

    protected String description;

    public Note(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
