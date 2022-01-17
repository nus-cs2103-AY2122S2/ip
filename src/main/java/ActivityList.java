import java.util.ArrayList;

public class ActivityList {
    /** Array of Activity in the list */
    ArrayList<Activity> activities;

    public ActivityList() {
        activities = new ArrayList<>();
    }

    /**
     * Adds and saves an activity.
     *
     * @param the name of the activity
     */
    public void add(String activity) {
        activities.add(new Activity(activity));
    }

    /**
     * Returns a string of the activities added to the list,
     * in order of addition.
     *
     * @return the list of activities if list is not empty
     */
    @Override
    public String toString() {
        if (activities.size() == 0) return "Nothing is added yet.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < activities.size(); i++) {
            sb.append((i + 1) + ". " + activities.get(i).toString() + "\n");
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
