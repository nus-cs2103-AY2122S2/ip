# User Guide

## Features 

### Assign Tasks

3 types of tasks to choose from - ToDo, Event and Deadline. Users will be able to keep track of all their 
tasks at their fingertips, customised for their own use. Users can also assign priority levels, dates for the tasks they've entered. 

### Mark Tasks
Mark tasks as complete unmark tasks as not completed.

### Display list 
View all the tasks all at one go. 

### Delete Tasks
The list of tasks are dynamic where you are able to delete tasks that you no longer need

### Priority Level
Attach a priority level to your tasks to help you differentiate which tasks are more important to be completed.


## Usage

### `todo` - create a new ToDo task

Create a new todo task in your personalised task list.

Example of usage: 

`todo <description> <$priority level>`

`todo clear laundry $LOW`

Expected outcome:
A new todo task has been created in the system. The system will show you how many tasks are there left in the system. 

### `deadline` - create a new Deadline task

Create a new deadline task in your personalised task list.

Example of usage:

`deadline <description> </by date> <$priority level>`

`todo CS2103 Assignment /by 18/02/22 11:59 PM $HIGH`

Expected outcome:
A new deadline task has been created in the system. The system will show you how many tasks are there left in the system.

### `event` - create a new Event task

Create a new deadline task in your personalised task list.

Example of usage:

`event <description> </at date> <$priority level>`

`todo Singapore Airshow 2022 /at 15/02/22 10:00 AM $MEDIUM`

Expected outcome:
A new event task has been created in the system. The system will show you how many tasks are there left in the system.

### `list` - shows the list of tasks

Shows the list of tasks available. 

Example of usage:

`list`

Expected outcome:
Shows you the complete list of tasks with their status (completed/uncompleted). 

### `/help` - shows available command in the system

Shows the user guide of the available commands in the system. 

Example of usage:

`/help`

Expected outcome:
Shows you the complete list of tasks with their status (completed/uncompleted). 

### `delete` - shows available command in the system

Delete particular entry in the system. 

Example of usage:

`delete 2`

Expected outcome:
Shows you that you have successfully deleted the task, and how many tasks are left in the system. 

### `mark/unmark` - mark/unmark a task

Mark a particular task completed or unmark a particular task. 

Example of usage:

`mark 2`

Expected outcome:
Shows you that you have successfully marked/unmarked the task. 


## Acknowledgements 

### Images 
Images are used according to pixabay's guidelines: Free for commercial use

[Duke image](https://pixabay.com/vectors/android-robot-152699/)

[Tiger image](https://pixabay.com/vectors/tiger-happy-euphoric-cat-nature-160601/)

### Codes
`DialogBox.setDialogColour()` was adapted from https://github.com/jonfoocy/ip/blob/master/src/main/java/DialogBox.java with modifications`