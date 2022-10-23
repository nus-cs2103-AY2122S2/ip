# User Guide

Luke is a chatbot that helps you manage all your tasks! With the force, it is able to help you recur some tasks too! 

* Table of Contents
{:toc}

## Features

### Adding a todo task: `todo`

Adds a general todo task.

Format: `todo DESCRIPTION`

Example: `todo destory death star`

### Adding a deadline task: `deadline`

Adds a task with a specified deadline, `DATETIME` specified in `DD/MM/YYYY HH:mm` format.   
Other acceptable formats can be found [in the appendix](#acceptable-datetime-format).

Format: `deadline DESCRIPTION /by DATETIME`

Example: `deadline buy weapons /by 15/04/2022 23:59`

### Adding a event task: `event`

Adds a task with a specified date and time of an event, `DATETIME` specified in `DD/MM/YYYY HH:mm` format.   
Other acceptable formats can be found [in the appendix](#acceptable-datetime-format).

Format: `event DESCRIPTION /at DATETIME`

Example: `event star wars /at 16/04/2022 00:00`

### Adding a recurring task: `recur`

Adds a recurring task of todo, deadline or event. The acceptable recurrence pattern are `day`, `week`, `month` and `year`. For todo tasks, the recurrence would occur from the time it is created. For deadline and event tasks, the recurrence would occur from the date specified in the `by` or `at` argument respectively.

Format: `recur TASK /every RECURRENCE_PATTERN`

Example: 
1. `recur todo train jedi /every day`
    - creates a recurring todo task every day based on the date the task is created.
1. `recur deadline test jedi /by 14/04/2022 23:59 /every month`
    - creates a recurring deadline task every month starting from 14/04/2022 23:59.
1. `recur event Luke's birthday /at 25/09/2022 00:00 /every year`
    - creates a recurring event task every year starting from 25/09/2022 00:00.

### Listing all tasks: `list`

Shows a list of all the stored tasks.

Format: `list`

### Marking a task as done: `mark`

Marks a particular task as completed.

Format: `mark INDEX`

Example: `mark 3`: If the 3rd task exists in the list, marks the task as completed.

### Marking a task as not done: `unmark`

Marks a particular task as incomplete.

Format: `unmark INDEX`

Example: `unmark 3`: If the 3rd task exists in the list, marks the task as incomplete.

### Deleting a task: `delete`

Deletes a particular task from the list.

Format: `delete INDEX`

Example: `delete 3`: If the 3rd task exists in the list, delete the task.

### Finding tasks with a specific keyword: `find`

Finds the tasks with the specific keywords and display the filtered list.

Format: `find KEYWORD`

Example: `find weapons`: Lists all tasks which contains the word `weapons`.

### Exiting the program: `bye`

Disables the user input text field and send button.  
The program will automatically close after 3 seconds.

Format: `bye`

## Appendix

### Acceptable datetime format

1. `DD/MM/YYYY HH:mm`
1. `DD-MM-YYYY HH:mm`
1. `DD.MM.YYYY HH:mm`
1. `YYYY/MM/DD HH:mm`
1. `YYYY-MM-DD HH:mm`
1. `YYYY.MM.DD HH:mm`
1. `DD.MM.YYYY HHmm`
1. `DD/MM/YYYY HHmm`
1. `DD-MM-YYYY HHmm`
1. `YYYY/MM/DD HHmm`
1. `YYYY-MM-DD HHmm`
1. `YYYY.MM.DD HHmm`