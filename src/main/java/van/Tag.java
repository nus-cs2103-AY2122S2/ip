package van;

import java.util.ArrayList;

public class Tag {
    private ArrayList<String> tags;

    public Tag() {
        tags = new ArrayList<>();
    }

    public void add(String newTag) {
        if (!(tags.contains(newTag))) {
            tags.add(newTag);
        }
    }

    public void delete(String newTag) {
        int index = tags.indexOf(newTag);
        if (index != -1) {
            tags.remove(index);
        }
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < tags.size(); i++) {
            output =  output + "#" + tags.get(i) + " ";
        }
        return output;
    }
}
