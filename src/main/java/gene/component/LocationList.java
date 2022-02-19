package gene.component;

import java.util.ArrayList;

import gene.location.Location;

/**
 * The task list class. handles all of gene's task list actions.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class LocationList {
    private ArrayList<Location> itemList = new ArrayList<>(0);
    private final LocationStorage geneLocsStorage;

    /**
     * Constructor for taskList
     *
     * @param geneLocsStorage
     */
    public LocationList(LocationStorage geneLocsStorage) {
        this.geneLocsStorage = geneLocsStorage;
    }

    /**
     * read file and initialize arraylist: if dont have existing file, create.
     */
    public void initFile() {
        geneLocsStorage.createDirectory();
        geneLocsStorage.createFile();
        this.itemList = geneLocsStorage.readFile();
    }

    /**
     * wrapper for array list set
     *
     * @param index
     * @param targetLoc
     */
    public void set(int index, Location targetLoc) {
        assert (index >= 0);
        assert (index < itemList.size());
        this.itemList.set(index, targetLoc);
    }

    /**
     * wrapper for array list get
     *
     * @param index
     */
    public Location get(int index) {
        assert (index >= 0);
        assert (index < itemList.size());
        return this.itemList.get(index);
    }

    /**
     * wrapper for array list add
     *
     * @param targetLoc
     */
    public void add(Location targetLoc) {
        this.itemList.add(targetLoc);
    }

    /**
     * wrapper for array list size
     *
     */
    public int size() {
        return this.itemList.size();
    }


    /**
     * wrapper for array list remove
     *
     * @param index
     */
    public void remove(int index) {
        assert (index >= 0);
        assert (index < itemList.size());
        this.itemList.remove(index);
    }
}
