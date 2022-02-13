# User Guide
**Wensleydale** is a *GUI-based* (Graphical User Interface) desktop app for managing tasks, 
including time-sensitive tasks such as deadlines and events.
## Features

### Command List

Displays a list of commands, including their respective format.

Format: `help`

### List of Tasks

List all the tasks currently in your task list.

Format: `list`

### Basic Task

Add a basic 'ToDo' task.

Format: `todo [description]`

### Deadline

Add a task with a specific deadline and/or time.

Format: `deadline [Description] /by yyyy-mm-dd (optionally add /HH:mm for time)`

### Event

Add an event task that starts and ends between a specified time.

Format: `event [Description] /at yyyy-mm-dd/HH:mm/HH:mm`

### Mark/Un-mark Tasks

Marks or Un-marks a task on the task list

***To mark a task:***  
Format: `mark [index]`  
***To un-mark a task:***  
Format: `unmark [index]`

### Delete

Remove a task from the list.

Format: `delete [index]`

### Find by Keyword

Finds a list of tasks that match a given keyword.

Format: `find [keyword]`

### Sort the List

Sorts the list of tasks based on the given condition.

Format: `sort [condition]`
###### List of Conditions:

***Sort list chronologically:*** `chronologically`  
***Sort list alphabetically:*** `alphabetically`  
***Sort list by marked tasks:*** `done`
