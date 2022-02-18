package gene.location;

public class Location {
    private final String locationName;
    private final String postalCode;
    private boolean isMarked;
    private final String type;

    public Location(String locationName, String postalCode, String type) {
        this.locationName = locationName;
        this.postalCode = postalCode;
        this.type = type;
        this.isMarked = false;
    }

    public Location(String locationName, String postalCode, String type, boolean isMarked) {
        this.locationName = locationName;
        this.postalCode = postalCode;
        this.type = type;
        this.isMarked = isMarked;
    }

    public Location markLocation() {
        assert this.isMarked = false : "Cannot mark marked locations";
        if (!this.isMarked) {
            this.isMarked = true;
        }

        return this;
    }

    public Location unmarkLocation() {
        assert this.isMarked = true : "Cannot unmark unmarked locations";
        if (this.isMarked) {
            this.isMarked = false;
        }

        return this;
    }

    @Override
    public String toString() {
        String mark = this.isMarked ? "[X]" : "[ ]";
        String toReturn = "[L]" + mark + " " + this.locationName + ": "
                + this.postalCode + ", " + this.type;
        return toReturn;
    }

    public boolean containsKeyword(String keyword) {
        return this.locationName.contains(keyword);
    }
}
