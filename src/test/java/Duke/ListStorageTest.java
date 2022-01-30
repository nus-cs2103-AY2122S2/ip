package Duke;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListStorageTest {
    @Test
    public void lengthTest() {
        assertEquals(0, new ListStorage().length());
    }
    @Test
    public void addToListTest() {
        assertEquals("added: run 10km", new ListStorage().addToList(new Task("run 10km")));
    }
    @Test
    public void printListTest() {
        ListStorage myStorage = new ListStorage();
        myStorage.addToList(new ToDo("run 10km"));
        assertEquals("    1.[T][ ] run 10km\n", myStorage.printList());
    }
    @Test
    public void printTaskTest() {
        ListStorage myStorage = new ListStorage();
        myStorage.addToList(new ToDo("run 10km"));
        assertEquals("    [T][ ] run 10km\n", myStorage.printTask(1));
    }
    @Test
    public void findTaskTest() {
        ListStorage myStorage = new ListStorage();
        myStorage.addToList(new ToDo("run 10km"));
        assertEquals("[T][ ] run 10km", myStorage.findTask(1).toString());
    }
    @Test
    public void deleteTaskTest() {
        ListStorage myStorage = new ListStorage();
        myStorage.addToList(new ToDo("run 10km"));
        assertEquals(1, myStorage.length());
        myStorage.deleteTask(1);
        assertEquals(0, myStorage.length());
    }
}
