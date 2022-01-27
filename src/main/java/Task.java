
public class Task {
    private final String name;
    private boolean marked;
    private final Type type;
    private String time = "";

    Task(String name, Type type) {
        this.marked = false;
        this.name = name;
        this.type = type;
    }

    public void mark() {
        this.marked = true;
    }

    public void setTime(String time) {
        time = identifyTime(time);
        if(this.type == Type.EVENT)
        this.time = "(at: " + time + ")";
        else if (this.type == Type.DEADLINE) {
            this.time = "(by: " + time + ")";
        }
    }

    private String identifyTime(String time) {
        String result = time;
        String[] dayAndTime = time.split(" ", 2);
        String[] split = dayAndTime[0].split("[-/]", 3);
        int parts = split.length;
        try {
            if (parts == 3) {
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
            suggest();
            return result;
        }
        return result + "  " + (dayAndTime.length == 2 ? dayAndTime[1] : "");
    }

    private void suggest() {
        System.out.println("I would appreciate if you write date in the form of yyyy-mm-dd time\n" +
                "eg: 2022-01-27 6pm");
    }

    @Override
    public String toString() {
        return "[" + type + "][" + (marked? "X" : " ") + "] " + name + time;
    }
}
