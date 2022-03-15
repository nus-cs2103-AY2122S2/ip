# User Guide for Cleese
Not boujee enough to afford your own personal butler?
This is the next best thing for you!

## Features 

### Organise and manage your tasks
Cleese helps you to sort our your tasks by simplifying your life into three kinds of tasks.

1.Todo

2.Deadline

3.Event

You can add and delete them, and mark them as completed when you are done with it! You can even search for tasks using keywords!

### Experience what it is like to have a butler at your beck and call
Cleese is here to serve you 24/7. He is never rude, always ready and never sleeps. What more can you ask for?

## Usage

### `list` - Displays the list of tasks

Shows the current lists of tasks tracked by Cleese

### `mark` - Marks a task as done

Marks a task with 'X' to show that it has been completed

Check and obtain the taskNumber of the chosen task with `list`

Example of usage: `mark [taskNumber]`

### `unmark` - Removes the mark from a task

Removes the 'X' that marks a task as done to show that it has not yet been completed

Check and obtain the taskNumber of the chosen task with `list`

Example of usage: `unmark [taskNumber]`

### `delete` - Deletes the task from Cleese

Deletes the task from the current list of tasks that Cleese is tracking

Check and obtain the taskNumber of the chosen task with `list`

Example of usage: `delete [taskNumber]`

### `todo` - Creates a ToDo task for Cleese to track

ToDo tasks can be marked and unmarked.

ToDo tasks do not have any date or time.

Example of usage: `todo [task description]`

### `deadline` - Creates a Deadline task for Cleese to track

Deadline tasks can be marked and unmarked. They also have a date time to be completed by.

The format of deadline should be ‘YYYY-MM-DD HHMM’

Example of usage: `deadline [task description] /by [date time]`

### `event` - Creates a Event task for Cleese to track

Event tasks can be marked and unmarked. They also have a date time they are scheduled at.

The format of deadline should be ‘YYYY-MM-DD HHMM’

Example of usage: `event [task description] /at [date time]`

### `find` - Looks for tasks that contain the keyword you are looking for

Lists out all the tasks that contain the keyword you queried

Example of usage: `find [keyword]`

### `help` - View all available commands

Shows a helpful message of what commands are available for you to use

Example of usage: `help`

### `bye` - Exits the application

Saves the current state of the task list in Cleese and exits the application

Example of usage: `bye`
