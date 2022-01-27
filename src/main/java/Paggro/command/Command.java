import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public abstract class Command {
    String parameters;

    public Command() {
    }

    public Command(String parameters) {
        this.parameters = parameters;
    }

    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    public abstract void execute(Lister lister, Ui ui, Storage storage) throws PaggroException;
//        int i;
//        Task task;
//        switch (cType) {
//        case BYE:
//            ui.showGoodbye();
//            break;
//        case LIST:
//            ui.showList(lister.tasks);
//            break;
//        case LISTONDATE:
//            try {
//                LocalDate date = LocalDate.parse(this.parameters);
//                    if (!lister.dateMap.containsKey(date)) {
//                        ui.showEmptyDate();
//                    } else {
//                        NotableDate nDate = lister.dateMap.get(date);
//                        ui.showList(nDate.tasks);
//                    }
//            } catch (DateTimeParseException e) {
//                throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
//                            "      YYYY-MM-DD"));
//            }
//            break;
//        case MARK:
//            try {
//                i = Integer.parseInt(this.parameters);
//            } catch (NumberFormatException e) { // parameter was not a number
//                throw new PaggroException("    Really? Can you input an actual number this time... =.=");
//            }
//            lister.mark(i);
//            task = lister.tasks.get(i - 1);
//            ui.showMarked(task);
//
//            try {
//                storage.markInStorage(i, task);
//            } catch (IOException e) {
//                throw new PaggroException("    Could not mark in paggro.txt =.=");
//            }
//            break;
//        case UNMARK:
//            try {
//                i = Integer.parseInt(this.parameters);
//                if (i < 0 || i > lister.tasks.size()) {
//                    throw new PaggroException("    Invalid entry number entered! =.=");
//                }
//            } catch (NumberFormatException e) { // parameter was not a number
//                throw new PaggroException("    Really? Can you input an actual number this time... =.=");
//            }
//
//            lister.unmark(i);
//            task = lister.tasks.get(i - 1);
//            ui.showUnmarked(task);
//
//            try {
//                storage.unmarkInStorage(i, task);
//            } catch (IOException e) {
//                throw new PaggroException("    Could not unmark in paggro.txt =.=");
//            }
//            break;
//        case DELETE:
//            try {
//                i = Integer.parseInt(this.parameters);
//                if (i < 0 || i > lister.tasks.size()) {
//                    throw new PaggroException("    Invalid entry number entered! =.=");
//                }
//            } catch (NumberFormatException e) { // parameter was not a number
//                throw new PaggroException("    Really? Can you input an actual number this time... =.=");
//            }
//
//            task = lister.tasks.get(i - 1);
//
//            if (task instanceof Event) {
//                Event e = (Event) task;
//                NotableDate nDate = e.date;
//                nDate.tasks.remove(task); // remove task from NotableDate tasklist
//                if (nDate.tasks.isEmpty()) {
//                    lister.dateMap.remove(nDate.localDate); // if date is empty, remove from datemap
//                }
//
//            } else if (task instanceof Deadline) {
//                Deadline d = (Deadline) task;
//                NotableDate nDate = d.date;
//                nDate.tasks.remove(task); // remove task from NotableDate tasklist
//                if (nDate.tasks.isEmpty()) {
//                    lister.dateMap.remove(nDate.localDate); // if date is empty, remove from datemap
//                }
//            }
//
//            lister.delete(i);
//            ui.showDeleted(task);
//            ui.showNumber(lister.tasks.size());
//
//
//            try {
//                storage.deleteFromStorage(i);
//            } catch (IOException e) {
//                throw new PaggroException("    Could not delete in paggro.txt =.=");
//            }
//            break;
//        case TODO:
//            task = new ToDo(this.parameters);
//            lister.add(task);
//
//            try {
//                storage.addToStorage(task);
//            } catch (IOException e) {
//                throw new PaggroException("    Could not add to paggro.txt =.=");
//            }
//
//            ui.showAdded(task);
//            ui.showNumber(lister.tasks.size());
//            break;
//        case EVENT:
//            String[] desArr = parameters.split(" /", 2);
//            try {
//                String des = desArr[0];
//                String dateTimeString = desArr[1];
//                String[] dateTimeArr = dateTimeString.split(" ");
//                String dateString = dateTimeArr[0];
//                String timeString = null;
//                LocalDate date = LocalDate.parse(dateString);
//                NotableDate nDate = lister.checkDate(date);
//                if (dateTimeArr.length > 1) {
//                    timeString = dateTimeArr[1];
//                    try {
//                        LocalTime time = LocalTime.parse(timeString);
//                        task = (new Event(des, nDate, time, false));
//                    } catch (DateTimeParseException e) {
//                        throw new PaggroException("    Really? =.= Time inputs must be in this format:\n" +
//                                "      HH:MM");
//                    }
//                } else {
//                    task = new Event(des, nDate, false);
//                }
//                nDate.addTask(task); //potential bug
//            } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
//                throw new PaggroException("    Really? =.= The use of the event command must be as follows:\n" +
//                        "      event <DESCRIPTION> /<DATE AND/OR TIME>");
//            } catch (DateTimeParseException e) {
//                throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
//                        "      YYYY-MM-DD"));
//            }
//            lister.add(task);
//
//            try {
//                storage.addToStorage(task);
//            } catch (IOException e) {
//                throw new PaggroException("    Could not add to paggro.txt =.=");
//            }
//
//            ui.showAdded(task);
//            ui.showNumber(lister.tasks.size());
//            break;
//        case DEADLINE:
//            String[] desArr1 = parameters.split(" /", 2);
//            try {
//                String des = desArr1[0];
//                String dateTimeString = desArr1[1];
//                String[] dateTimeArr = dateTimeString.split(" ");
//                String dateString = dateTimeArr[0];
//                String timeString = null;
//                LocalDate date = LocalDate.parse(dateString);
//                NotableDate nDate = lister.checkDate(date);
//                if (dateTimeArr.length > 1) {
//                    timeString = dateTimeArr[1];
//                    try {
//                        LocalTime time = LocalTime.parse(timeString);
//                        task = (new Deadline(des, nDate, time, false));
//                    } catch (DateTimeParseException e) {
//                        throw new PaggroException("    Really? =.= Time inputs must be in this format:\n" +
//                                "      HH:MM");
//                    }
//                } else {
//                    task = new Deadline(des, nDate, false);
//                }
//                nDate.addTask(task); //potential bug
//            } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
//                throw new PaggroException("    Really? =.= The use of the deadline command must be as follows:\n" +
//                        "      deadline <DESCRIPTION> /<DATE AND/OR TIME>");
//            } catch (DateTimeParseException e) {
//                throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
//                        "      YYYY-MM-DD"));
//            }
//            lister.add(task);
//
//            try {
//                storage.addToStorage(task);
//            } catch (IOException e) {
//                throw new PaggroException("    Could not add to paggro.txt =.=");
//            }
//
//            ui.showAdded(task);
//            ui.showNumber(lister.tasks.size());
//            break;
//        default:
//            throw new PaggroException("    Invalid command error! =.=");
//        }
}
