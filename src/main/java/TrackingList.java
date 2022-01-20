import java.util.ArrayList;
import java.lang.StringBuilder;

public class TrackingList {
    private ArrayList<String> trackingList;

    public TrackingList() {
        this.trackingList = new ArrayList<>();
    }

    public void addToList(String toAdd) {
        this.trackingList.add(toAdd);
    }

    @Override
    public String toString() {
        if (this.trackingList.size() == 0) {
            return "Empty list!";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= this.trackingList.size(); i++) {
            output.append(String.format("%d. %s\n", i, this.trackingList.get(i - 1)));
        }
        return output.toString();
    }
}
