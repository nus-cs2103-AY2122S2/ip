package duke.task;

import java.util.ArrayList;

import duke.ui.Ui;

/**
 * Contains the Ui and an ArrayList of Tag objects. Manages adding and deleting of tags.
 */
public class TagList {
    private ArrayList<Tag> tags;
    private Ui ui;

    /**
     * Instantiates a new TagList with new ArrayList.
     */
    public TagList(Ui ui) {
        tags = new ArrayList<Tag>();
        this.ui = ui;
    }

    /**
     * Instantiates a new TagList with given Tags and Ui.
     *
     * @param tags ArrayList of Tag.
     * @param ui   Ui Ui object for display.
     */
    public TagList(ArrayList<Tag> tags, Ui ui) {
        this.tags = tags;
        this.ui = ui;
    }

    /**
     * Adds the tag to this TagList.
     *
     * @param tagName String tag name.
     * @param task Task Task object.
     */
    public void addTag(String tagName, Task task) {
        tags.add(new Tag(tagName));
        ui.showUiForTag(tagName, task);
    }

    /**
     * Deletes a Tag from tags using a String input tagName.
     *
     * @param tagName String Name of the tag to be removed.
     */
    public void deleteTag(String tagName, Task task) {
        // Tag equality is based on Tag name.
        boolean isRemoved = tags.remove(new Tag(tagName));
        if (isRemoved) {
            ui.showUiForUntag(tagName, task);
        } else {
            ui.setNextMessage("That tag does not exist!");
        }
    }

    /**
     * Returns the String representation of this TagList.
     *
     * @return String String representation of this TagList.
     */
    @Override
    public String toString() {
        String result = "";
        if (tags.size() != 0) {
            result += " | ";
        }
        for (int k = 0; k < tags.size(); k++) {
            String tagString = tags.get(k).toString();
            if (k != tags.size() - 1) {
                result += tagString + " ";
            } else {
                result += tagString;
            }
        }
        return result;
    }
}
