package duke.data;

import duke.common.Type;

public class Task {
    private final String name;
    private boolean isMarked;
    private final Type type;
    private String time = "";

    public Task(String name, Type type) {
        this.isMarked = false;
        this.name = name;
        this.type = type;
    }

    /**
     * Mark this task as done.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Set the time of the task.
     * @param time The time that the task should be done.
     **/
    public void setTime(String time) throws ArrayIndexOutOfBoundsException {
        time = identifyTime(time);
        if(this.type == Type.EVENT)
        this.time = "(at: " + time + ")";
        else if (this.type == Type.DEADLINE) {
            this.time = "(by: " + time + ")";
        }
    }

    /**
     * Check if the prefix matches this task.
     * @param prefix the prefix used to check.
     **/
    public boolean match(String prefix) {
        return this.name.contains(prefix);
    }

    public boolean matchTime(String prefix) {
        return this.time.contains(prefix);
    }

    /**
     * Transfer a time format to another format.
     * @param time the time in String.
     **/
    public static String identifyTime(String time) throws ArrayIndexOutOfBoundsException {
        String result = time;
        String[] dayAndTime = time.split(" ", 2);
        String[] split = dayAndTime[0].split("[-/]", 3);
        int numberOfParts = split.length;
        try {
            if (numberOfParts == 3) {
                int year = Integer.parseInt(split[0]);
                int month = Integer.parseInt(split[1]);
                int day = Integer.parseInt(split[2]);
                if (day > 31) {
                    System.out.println("we don't have " + day +" days in a month!");
                    throw new NumberFormatException("");
                }
                String m;
                switch (month) {
                    case 1: {
                        m = "Jan.";
                        break;
                    }
                    case 2: {
                        m = "Feb.";
                        break;
                    }
                    case 3: {
                        m = "Mar.";
                        break;
                    }
                    case 4: {
                        m = "Apr.";
                        break;
                    }
                    case 5: {
                        m = "May";
                        break;
                    }
                    case 6: {
                        m = "Jun.";
                        break;
                    }
                    case 7: {
                        m = "Jul.";
                        break;
                    }
                    case 8: {
                        m = "Aug.";
                        break;
                    }
                    case 9: {
                        m = "Sep.";
                        break;
                    }
                    case 10: {
                        m = "Oct.";
                        break;
                    }
                    case 11: {
                        m = "Nov.";
                        break;
                    }
                    case 12: {
                        m = "Dec.";
                        break;
                    }
                    default: {
                        throw new NumberFormatException("");
                    }
                }
                result =  m + day + " " + year;
            } else {
                throw new NumberFormatException("");
            }
        } catch (NumberFormatException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return result + "  " + (dayAndTime.length == 2 ? dayAndTime[1] : "");
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Task && o.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        return "[" + type + "][" + (isMarked ? "X" : " ") + "] " + name + time;
    }
}
