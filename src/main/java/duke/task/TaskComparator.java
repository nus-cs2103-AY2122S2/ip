package duke.task;

import java.util.Comparator;

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
            return Boolean.compare(t1.done, t2.done);
        }
    },
    DateComparator {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.compareTo(t2);
        }
    }
}
