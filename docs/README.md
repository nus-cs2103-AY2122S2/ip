# Duke

 Welcome to Duke.  The only application you need to track all your tasks, including ToDos, events and deadlines. It is optimised for CLI usage, and also has a GUI for you to view your tasks.

## Quick Start

 1. Ensure you have Java 11 installed on your computer.
 2. Download Duke from [here.](https://github.com/goel-a/ip/releases/tag/A-Release)
 3. Copy the jar file into an empty folder
 4. Open a command/terminal window in that folder
 5. Run the command `java -jar duke.jar`
 6. Type any command in the command box, and press enter/send

## Features

 Here are a list of features & commands that you can use, along with examples:

 - **list** 

   Displays a list containing all your tasks, events and deadlines
	
     Format: `list`	 
	
 - **mark**

   Marks the task at the specified index as completed. 

     Format: `mark [index]`
	
     Example: `mark 1` (marks the 1st task as complete)

 - **unmark**

   Marks the task at the specified index as not complete. 

     Format: `unmark [index]`
	
     Example: `unmark 1` (marks the 1st task as not complete)

 -  **todo**

    Adds a new ToDo to your list.

     Format: `todo [task description]`
    
     Example: `todo Homework` (adds Homework to the list)

 - **deadline**

   Adds a new deadline to your list.

     Format: `deadline [deadline description] /by [date in YYYY-MM-DD]`
	
     Example: `deadline iP Final Submission /by 2022-02-19` 
	
     (creates a deadline for ip Final Submission on 19 Feb 2022)

 - **event**
 
    Adds a new event to your list.

      Format: `event [event description] /at [date in YYYY-MM-DD]`
	
      Example: `event CS2103T Lecture /at 2022-02-10` 
	
      (creates an event for CS2103T Lecture on 10 Feb 2022)

 - **delete**

    Deletes the task at the specified index from the list.

      Format: `delete [index]`
	
      Example: `delete 1` (deletes the first task from the list)

 - **find**

    Finds all tasks that contain the specified keyword

      Format: `find [keyword]`
   
      Example: `find CS2103T` (returns all tasks containing CS2103T)

 - **sort  deadline**

    Returns a list of deadlines sorted by date (earliest first)

      Format: `sort deadline`

 - **sort  events**

    Returns a list of events sorted by date (earliest first)

      Format: `sort event`

 - **bye**

    Exits duke

      Format: `bye`

   

 



