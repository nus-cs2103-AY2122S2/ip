# User Guide for the task manager chatbot - Spike

## Features 

### 1 - adding various types of task

3 types of task could be added to your task list:
- Todo
- Event (with date and time)
- Deadline (with date and time)

### 2 - Change completion status of tasks
You can mark a task as done/undone.

### 3 - Automactic saving of changes
Changes done will be automatically saved to a file call `spike.txt` locally in a folder called `data` and retreieved next time.

After you become familar with the data format, you can edit the data file directly or import your own data (see later section).

## Usage

### `todo` - Add a to-do task
Adds a to-do task to the task list.

Format: `todo <task description>`

Example of usage:
`todo Buy eggs from FairPrice`

Expected outcome:

<img width="291" alt="image" src="https://user-images.githubusercontent.com/77217780/153711730-2679cc8b-b60e-4414-9a65-e2637c38c682.png">

### `event` - Add an event
Adds a event with date and time specified.

Format: `event <task description> /at <yyyy-MM-dd HHmm>`

Example of usage:
`event Meet Jessie at Central Library /at 2022-02-14 1800`

Expected outcome:

<img width="436" alt="image" src="https://user-images.githubusercontent.com/77217780/153711809-96e5c43b-e95b-4ae2-b3e3-52a14d5f74ab.png">

### `deadline` - Add a deadline
Adds a deadline with date and time specified.

Format: `deadline <task description> /by <yyyy-MM-dd HHmm>`

Example of usage:
`deadline Sumbit assignment 1 /by 2022-02-20 2359`

Expected outcome:

<img width="386" alt="image" src="https://user-images.githubusercontent.com/77217780/153711885-bcdad112-a7f7-4b03-88d1-edd067707fd0.png">

### `delete` - Delete a task
Deletes the task with the specified numbering.

Format: `delete INDEX`
- The index must be a **positive integer** 1, 2, 3,...
- The index must be valid (cannot delete task 7 if there are only 6 tasks)

### `mark` - Mark a task as done
Marks a task with the specified numbering as done.

By default, tasks when added are intiallly marked as undone.

Task marked as done will have `[X]` displayed in front of task description when listed.

Format: `mark INDEX`
- The index must be a **positive integer** 1, 2, 3,...
- The index must be valid (cannot mark task 7 as done if there are only 6 tasks)

### `unmark` - Mark a task as undone
Marks a task with the specified numbering as undone.

Task marked as undone will have `[ ]` displayed in front of task description when listed.

Format: `unmark INDEX`
- The index must be a **positive integer** 1, 2, 3,...
- The index must be valid (cannot mark task 7 as undone if there are only 6 tasks)

### `list` - List down all tasks
Prints all tasks and their completion status.
- `[X]` indicates the task is marked as done
- `[ ]` indicates the task is not markes as done yet

Format: `list`

### `find` - Search for tasks
Prints tasks whose decription contain the keyword(s).

Format: `find <keyword>`
- The search is case-insensitive. e.g. only `aPpLy` will match `apply`
- The search term could contains multiple words separated by space

Example of usage:
`find CS`

Expected outcome:

<img width="402" alt="image" src="https://user-images.githubusercontent.com/77217780/153713414-cba24c9b-e3e8-48cb-ada4-059c7d869dd5.png">



### `remind` - Get reminders for deadlines and event coming soon
Prints deadlines and events happening within a certain day range (default range is 1 day).

Format: `remind <day range in integer>`
- The number supplied must be a **positive integer** 1, 2, 3,...
- The range for reminding will be calculated from the last time when the command is executed
- Upon app startup, this command will be invoked as `remind 1` to print tasks due within 1 day from the app startup time

Example of usage:

`remind 1`

Expected outcome:

<img width="440" alt="image" src="https://user-images.githubusercontent.com/77217780/153713404-e931bc85-6b36-4bbe-a283-435fd72300ed.png">


### `bye` - Say goodbye to Spike
Triggers file saving, user can close the application safely, or continue to talk to the chatbot.

Format: `bye`

### Editing the data file
Task data are saved as: `<Type> | <status> | <description> | <date and time>`
- `Type`The mapping of task to type is as follows
  - Todo - `T`
  - Event - `E`
  - Deadline - `D`
- `status`
  - `0` indicates task is undone
  - `1` indicates task is done
- `description` cannot be empty
- `date and time` is only for events and deadlines

❗ The data format **must be followed strictly** or the app could simply create a new task list and overwrite current data with this new list or behave abnormally (e.g. not displaying task information correctly).

Tip: Try adding a few tasks and inspect the data format and follow them.

## Command summary

Action | Format
--------|------------------
**todo** | `todo <task description>`
**event** | `event <task description> /at <yyyy-MM-dd HHmm>`
**deadline** | `deadline <task description> /by <yyyy-MM-dd HHmm>`
**delete** | `delete INDEX`
**mark** | `mark INDEX`
**unmark** | `unmark INDEX`
**list** | `list`
**find** | `find <keyword(s)>`
**remind** | `remind <integer day range>`
**bye** | `bye`
