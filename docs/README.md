# User Guide

## Features 

### Feature - Stores a list of tasks to be done on drive, even when application is closed.

## Usage

### `help` - Displays a list of commands that can be used to interact with the application.

### `todo [some activity]` - Adds an activity to be done.

"todo [an activity] " will add the activity to be done into the list of tasks.

Example of usage: 

`todo read up on dinosaurs`

### `deadline [some task] /by [dd/mm/yyyy-hh:mm]` - Adds a task that can be expressed as a deadline, with a corresponding time.

"deadline [task] /by [time]" will add a deadline to the list of tasks.

Example of usage: 

`deadline assignment /by [10/03/2022-12:00]`

### `event [some event] /at [dd/mm/yyyy-hh:mm]` - Adds a task that can be expressed as an event, with a corresponding time.

"event [event description] /by [time]" will add an event to the list of tasks.

Example of usage: 

`event birthday party /at [04/04/2022-08:15]`

### `lend [some amount] /to [name]` - Adds a reminder to lend a person an amount of money.

"lend [amount] /to [person]" will add a reminder to lend money to the list of tasks.

Example of usage: 

`lend 5 /to Bob`

### `borrow [some amount] /from [name]` - Adds a reminder to borrow an amount of money from someone.

"borrow [amount] /from [person]" will add a reminder to borrow money to the list of tasks.

Example of usage: 

`borrow 50 /from Simon`

### `list` - Views all saved tasks.

### `find [keywords]` - Searches saved tasks by given keywords.

"find [keywords]" will add search all saved tasks to look for tasks with the given keywords.

Example of usage: 

`find birthday`

### `mark [task number]` - Marks a given task as completed.

"mark [task number]" will set a task as completed, where the task number is obtainable after runnning the `list` command.

Example of usage: 

`mark 2`

### `unmark [task number]` - Unmarks a marked task.

"unmark [task number]" will set a task as not completed, where the task number is obtainable after runnning the `list` command.

Example of usage: 

`unmark 2`

### `delete [task number]` - Deletes a task.

"delete [task number]" will delete a task from storage, where the task number is obtainable after runnning the `list` command.

Example of usage: 

`delete 2`
