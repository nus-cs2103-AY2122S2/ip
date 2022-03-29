
# User Guide
Luca is a chat bot that helps users manage their tasks. It is optimized for users who are familiar with the Command Line Interface (CLI). However, the application leverages the advantages of a GUI.

## Quick Start
1.    Make sure that java 11 or above is installed in your computer.
2.  Download the latest version of `luca.jar` from [here](https://github.com/thikhinab/ip/releases).
3. Copy the file to a folder you want to use as the home folder for your chat bot.
4. Open the Command Prompt/Terminal in the home folder of the chat bot.
5. Run the command `java -jar luca.jar` to launch the application.

## Features
### Adding a todo task: `todo`
Creates and tracks a todo task.  
Format: `todo DESCRIPTION`  
Example: `todo read Da Vinci Code`


### Adding a deadline task: `deadline`
Creates and tracks a deadline task.  
Format: `deadline DESCRIPTION /by yyyy-mm-dd [HHMM]`
-  input time must be in 24-hour format (e.g `2359`  is 11:59 pm)

Examples:
-  `deadline return book 2022-02-20 `
- `deadline submit assignment 2022-02-24 2359`

### Adding a event task: `event`
Creates and tracks an event task.  
Format: `event DESCRIPTION /by yyyy-mm-dd HHMM HHMM`
- input time must be in 24-hour format
- both start and end times are required
- default due time is 6:00 am

Example: `event team meeting 2022-02-26 1800 1700`

### Listing all tasks: `list`
Lists out all the recorded tasks.  
Format: `list`

### Deleting a task: `delete`
Deletes a task that is being tracked:  
Format: `delete INDEX`  
- Deletes task at a specified `INDEX`
-  `INDEX` refers to the positive index of the task in the displayed list

Example: `delete 1`

### Marking a task as done:`mark`
Marks a specified task as done.  
Format: `mark INDEX`
- Marks task at a specified `INDEX` as completed.
-  `INDEX` refers to the positive index of the task in the displayed list

Example: `mark 2`

### Unmarking a task as done: `unmark`
Unmarks a specified task as done.  
Format: `mark INDEX`
- Marks task at a specified `INDEX` as incomplete.
-  `INDEX` refers to the positive index of the task in the displayed list

Example: `unmark 3`

### Sort list of tasks: `sort`
Lists sorted deadlines and events either by descending, or the ascending order.  
Format: `sort TASK_TYPE [ORDER]`
- Supported `TASK_TYPE` are: `deadline` and `event`
- Keywords for `ORDER`: `ascending`, `descending`, `asc` and `desc`
- Default order is ascending

Examples:
- `sort deadline`
- `sort event ascending`
	 


