package duke.bot;

import duke.task.Task;
import duke.task.TaskList;

public class DioBotMessage extends BotMessage {
    @Override
    public String getListMessage(boolean isEmpty) {
        return isEmpty ? "I reject my humanity, Jojo!" : "Oh? You're Approaching Me?";
    }

    @Override
    public String getInvalidCommandMessage() {
        return "RODA ROLLA DA!";
    }

    @Override
    public String getAddMessage(TaskList taskList, Task task) {
        return "WRYYYYYYYYYYYY! \n   " + task + "\n" + getTaskLeft(taskList);
    }

    @Override
    public String getDeleteMessage(TaskList taskList, Task task) {
        return "*throws knifes\n   " + task;
    }

    @Override
    public String getMarkMessage(Task task) {
        return "KONO DIO DA!\n   " + task.toString();
    }

    @Override
    public String getUnmarkMessage(Task task) {
        return "Hinjaku! Hinjaku!\n   " + task.toString();
    }

    @Override
    public String getExitMessage() {
        return "What?! I-Impossible... I-I am Dio... I am the mighty Dio!";
    }

    @Override
    public String getBotMessage() {
        return "ZA WARUDO!";
    }


    private String getTaskLeft(TaskList taskList) {
        String muda = "";
        for (int i = 0; i < taskList.size(); i++) {
            muda += (i == taskList.size() - 1) ? "MUDA!" : "MUDA ";
        }

        if (muda.isEmpty()) {
            return "NANI!";
        } else {
            return muda;
        }
    }

}
