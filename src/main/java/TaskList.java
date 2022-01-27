import java.util.ArrayList;
import java.util.Iterator;

import Tasks.*;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> ls;

    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public Task get(int i) {
        return ls.get(i);
    }

    public void add(Task t) {
        ls.add(t);
    }

    public Task remove(int i) {
        return ls.remove(i);
    }

    public int size() {
        return ls.size();
    }


    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {
            private int currentIndex = 0;
            private int currentSize = ls.size();

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && ls.get(currentIndex) != null;
            }

            @Override
            public Task next() {
                return ls.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
