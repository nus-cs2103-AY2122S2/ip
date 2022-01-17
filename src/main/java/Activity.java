public class Activity {
    /** Name of the activity */
    String name;

    public Activity(String name) {
        this.name = name;
    }

    /**
     * Returns a string representing the activity's name.
     *
     * @return the name of the activity
     */
    @Override
    public String toString() {
        return this.name;
    }
}
