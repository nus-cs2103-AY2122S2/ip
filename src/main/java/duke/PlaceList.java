package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of places
 */
public class PlaceList {

    private final ArrayList<Place> list;

    /**
     * Constructs a new place list.
     */
    public PlaceList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a place list from pre-existing data.
     *
     * @param fileName A string containing the file path to the existing data.
     * @throws IOException if an I/O error occurs.
     */
    public PlaceList(String fileName) throws IOException {
        this.list = PlaceList.populateList(fileName);
    }

    /**
     * Checks if the place list is empty.
     *
     * @return true if the place list is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Fetches the place at the given index.
     *
     * @param idx An integer representing the index of the place to get.
     * @return A Place at the given index of the place list.
     */
    public Place get(int idx) {
        return list.get(idx);
    }

    /**
     * Fetches the number of places in the list.
     *
     * @return An integer representing the number of places in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes the place at the given index from the place list.
     *
     * @param placeNum An integer representing the index of the place to be removed.
     * @return the Place that is removed.
     */
    public Place remove(int placeNum) {
        assert placeNum < this.size() && placeNum > 0 : "Please provide a valid index";
        return list.remove(placeNum);
    }

    /**
     * Replaces a place at the given index with a new place.
     *
     * @param idx An integer representing the index of the place to be replaced.
     * @param place the Place to replace the existing Place with.
     * @return the Place that was replaced.
     */
    public Place set(int idx, Place place) {
        assert place != null : "A proper place should be set!";
        return list.set(idx, place);
    }

    /**
     * Adds a place to the end of the place list.
     *
     * @param place the Place to be added to the place list.
     * @return true if the place has been added.
     */
    public boolean add(Place place) {
        assert place != null : "A proper place should be added!";
        return list.add(place);
    }

    /**
     * Fetches the whole place list.
     *
     * @return an ArrayList of places.
     */
    public ArrayList<Place> getList() {
        return this.list;
    }

    /**
     * Populates the place list with pre-existing data.
     *
     * @param fileName A string representing the file path to the saved places.
     * @return an ArrayList of the saved places.
     * @throws IOException if an I/O error occurs.
     */
    public static ArrayList<Place> populateList(String fileName) throws IOException {
        ArrayList<Place> list = new ArrayList<Place>();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        while (line != null) {
            String name = line.substring(1, line.indexOf("]"));
            String description = line.substring(name.length() + 3);
            Place place = new Place(name, description);
            list.add(place);
            line = file.readLine();
        }
        file.close();
        return list;
    }
}
