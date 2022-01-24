import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class DateTable {
    private HashMap<LocalDate, ArrayList<Task>> dateMap = new HashMap<>();
    private final BotException exception = new BotException();
    private final Ui ui;

    public DateTable(Ui ui) {
        this.ui = ui;
    }

    public void getEventOnDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString,
                DateTimeFormatter.ofPattern("d/M/yyyy"));
        if (dateMap.containsKey(date)) {
            ArrayList<Task> eventList = dateMap.get(date);
            ui.showDate(eventList);
        } else {
            exception.dateNotFound();
        }
    }

    public void addDate(Task task) {
        LocalDate localDate = task.getTime();
        if (dateMap.containsKey(localDate)) {
            ArrayList<Task> eventList = dateMap.get(localDate);
            eventList.add(task);
        } else {
            ArrayList<Task> eventList = new ArrayList<>();
            eventList.add(task);
            dateMap.put(localDate, eventList);
        }
    }
}
