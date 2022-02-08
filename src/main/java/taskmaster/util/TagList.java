package taskmaster.util;

import java.util.ArrayList;

public class TagList {
    /** **/
    protected final ArrayList<String> TAGS;

    public TagList() {
        this.TAGS = new ArrayList<>();
    }

    public void addTag(String tag) {
        this.TAGS.add(tag);
    }

    public String getAllTags() {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (String tag : TAGS) {
            sb.append(count + ". " + tag + "\n");
            count++;
        }
        return sb.toString();
    }

}
