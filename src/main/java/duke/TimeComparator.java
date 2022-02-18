package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

class TimeComparator implements Comparator<Task> {

    //Reused from StackOverFlow https://stackoverflow.com/questions/35671959/sort-arraylist-with-times-in-java/35672291
    //@author ΦXocę 웃 Пepeúpa ツ
    //with minor modifications
    @Override
    public int compare(Task taskTime1, Task taskTime2) {
        try {
            String time1 = taskTime1.getTime();
            String time2 = taskTime2.getTime();
            return new SimpleDateFormat("hh:mm a").parse(time1).compareTo(new SimpleDateFormat("hh:mm a").parse(time2));
        } catch (ParseException e) {
            return 0;
        }
    }
}
