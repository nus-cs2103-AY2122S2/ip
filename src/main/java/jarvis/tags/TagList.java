package jarvis.tags;

import java.util.ArrayList;

import jarvis.exceptions.TagNotFoundException;

public class TagList {
    private final ArrayList<Tag> tagList;

    /**
     * Constructor for tagList.
     */
    public TagList() {
        this.tagList = new ArrayList<>();
    }

    /**
     * Constructor for tagList.
     * @param tagList arraylist of tags
     */
    public TagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    /**
     * Get the tag list.
     *
     * @return array of tasks
     */
    public ArrayList<Tag> getTagList() {
        return this.tagList;
    }

    /**
     * Checks if a name exists in the list of tags.
     * @param tagName name of the tag
     * @return true or false
     */
    public boolean contains(String tagName) {
        for (Tag tag : tagList) {
            if (tag.getName().equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a tag by its name.
     * @param tagName name of the tag
     */
    public Tag getTag(String tagName) throws TagNotFoundException {
        if (!contains(tagName)) {
            throw new TagNotFoundException(tagName + " cannot be found in the list!");
        }
        for (Tag tag : tagList) {
            if (tag.getName().equals(tagName)) {
                return tag;
            }
        }
        return null;
    }

    /**
     * Add a new tag to the list.
     * @param tagName the name of the tag to add
     */
    public void add(String tagName) {
        Tag tag = new Tag(tagName);
        this.tagList.add(tag);
    }

    /**
     * Add a new tag to the list.
     * @param tag object to add to the list
     */
    public void add(Tag tag) {
        this.tagList.add(tag);
    }

    /**
     * Removes a new tag to the list.
     * @param tagName the name of the tag to add
     */
    public void remove(String tagName) throws TagNotFoundException {
        Tag tag = getTag(tagName);
        this.tagList.remove(tag);
    }

    /**
     * Removes a new tag to the list.
     * @param tag tag to remove
     */
    public void remove(Tag tag) {
        this.tagList.remove(tag);
    }

    @Override
    public String toString() {
        int size = this.tagList.size();
        StringBuilder result;

        if (size == 0) {
            result = new StringBuilder("List is empty. Add items to the list.");
        } else {
            result = new StringBuilder();
            for (int i = 0; i < size; i++) {
                result.append(String.format("%d. %s\n", i + 1, this.tagList.get(i)));
            }
        }

        return result.toString();
    }
}
