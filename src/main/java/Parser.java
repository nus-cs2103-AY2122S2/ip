public class Parser {
    
    private String[] splitStringIntoParts(String s) {
        String[] splitBasedOnDelimiter = s.split("\\|");
        for (int i = 0; i < splitBasedOnDelimiter.length; i++)
            splitBasedOnDelimiter[i] = splitBasedOnDelimiter[i].trim();
        
        return splitBasedOnDelimiter;
    }


    private Task handlesEventTasks(int isMark, String description, String eventAt) {
        EventTask eventTask = new EventTask(description, eventAt);
        if (isMark == 1)
            eventTask.markTask();
        return eventTask;
     }

    
    private Task handlesDeadlineTasks(int isMark, String description, String deadlineBy) {
        DeadlineTask deadlineTask = new DeadlineTask(description, deadlineBy);
        if (isMark == 1)    
            deadlineTask.markTask();
        return deadlineTask;
    }

    

    private Task handlesTodoTasks(int isMark, String description) {
        TodoTask todoTask = new TodoTask(description);
        if (isMark == 1)
            todoTask.markTask();
        return todoTask;
    }



    /**
     *  ParseTask parses each line from the file to be read 
     *  and determines which task the task is, before it allows
     *  the program to add it into 
     * 
     * 
     * @param str Line from the file that is to be read
     * @return
     */
    
    
    public Task parseTask(String str) {

        //Container file for returning Task
        Task taskToBeReturned = null;
        char taskType = str.charAt(0);
        String[] stringIntoParts = splitStringIntoParts(str);
        try {
            if (taskType == 'T' || taskType == 'D' || taskType == 'E') {
                //Handle Wrong Inputs
                //Case 1: user only specified Task type
                if (stringIntoParts.length == 1) 
                    throw new DukeExceptions("ERROR! You've only specified the task type, you need to specify whether task is completed or not and the description of the task \n");

                //Case 2: If second input(completed/uncompleted) is not an integer, throw exception
                int index = Integer.parseInt(stringIntoParts[1]);

                //Case 3: There is only 2 inputs
                if (stringIntoParts.length == 2) {
                    throw new DukeExceptions("ERROR! You've only specified the task type and whether it is incomplete or complete \n");
                } 
                //Case 4: Second input is not 0 or 1
                if (index != 0 && index != 1) {
                    throw new DukeExceptions("ERROR! Second input has to be 0 or 1!\n");
                }

                //If code reaches here, it is confirmed to have 3 or more parameters
                // Deadline Task or Event Task Handling
                if (taskType == 'D' || taskType == 'E') {
                    //Case 4: Assume that the 3rd input is the description of the task and the user is missing the deadline by/time of event
                    if (stringIntoParts.length != 4) {
                        throw new DukeExceptions("ERROR! Deadline and Event tasks is missing the deadline/time which the event occur on");
                    } else {
                        if (taskType == 'D') {
                            taskToBeReturned = handlesDeadlineTasks(index, stringIntoParts[2], stringIntoParts[3]);
                        } else {
                            taskToBeReturned = handlesEventTasks(index, stringIntoParts[2], stringIntoParts[3]);
                        }
                    }  
                } else {
                //Todo Task
                    taskToBeReturned = handlesTodoTasks(index, stringIntoParts[2]);

                }
            } else {
                //If Task type is not T/D/E    
                throw new DukeExceptions("ERROR! I do not recognise that task type!");
            }

        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("What? Second input has to be an integer! Eg T | 1 | read book, D | 0 | return book | June 6th \n");
        }

        return taskToBeReturned;

        
    
    }
            
            
           
        

    

}
