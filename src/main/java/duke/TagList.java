package duke;

import java.util.ArrayList;

/**
 * This class contains the task list.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class TagList {
    // ArrayList to store all your tags
    private ArrayList<Tag> list;

    /**
     * Initialises the tag list
     *
     */
    public TagList() {
        list = new ArrayList<>();
    }

    /**
     * Gets a specific tag from the list based on position.
     *
     * @param index the position of the tag in the list.
     * @return a tag object.
     */
    public Tag getIndex(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Tag> getList() {
        return list;
    }

    /**
     * Adds tags into list.
     *
     * @param tag the tag to be added.
     */
    public void addTag(Tag tag) {
        list.add(tag);
    }
}
