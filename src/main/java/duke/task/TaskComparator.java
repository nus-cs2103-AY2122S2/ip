package duke.task;

import java.util.Comparator;

//Solution below adapted from https://stackoverflow.com/questions/27784735/class-to-store-multiple-comparators
public enum TaskComparator implements Comparator<Task> {
    ObjComparator {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.objective.compareTo(t2.objective);
        }
    },
    MarkComparator {
        @Override
        public int compare(Task t1, Task t2) {
            return Boolean.compare(t1.isDone, t2.isDone);
        }
    },
    DateComparator {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.compareTo(t2);
        }
    }
}
