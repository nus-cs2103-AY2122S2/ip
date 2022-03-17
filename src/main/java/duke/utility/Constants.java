package duke.utils;

import duke.utility.datetime.DayFormat;
import duke.utility.datetime.StandardTimeFormat;
import duke.utility.datetime.StandardTimeStandard;
import duke.utility.datetime.TimeStandard;

import java.util.Arrays;
import java.util.List;


public class Constants {

    public static final String GREETING =
            "Hi I'm Duke\n"
                    + "What can I do for you?"
            + "\n If not sure please type \"help\" ";

    public static final String DF_LOCAL_TIME = "yyyy-mm-dd hh:mm (hh:mm is optional) ?";

    public static final int WIDTH = 50;

    public static final String EXIT = "EXIT";

    public static final List<TimeStandard> DATE_TIME_FORMAT_LIST =
            Arrays.asList(new DayFormat(), new StandardTimeStandard(), new StandardTimeFormat());

}