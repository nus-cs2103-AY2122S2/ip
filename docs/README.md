# User Guide

## Features

### Add
Tell Yale your tasks with `todo`, `deadline`, `event` and `period`.
### View
Yale lists out all your tasks for viewing with `list`.
### Mark/Unmark
Yale helps you keep track of your tasks with `mark` and `unmark`.
### Find
Yale helps you find your desired task with `find`.
### Delete
Tell Yale to delete a task with `delete`.




## Usage

### `todo` - Adds a ToDo task to your list.
Once added, Yale will keep track of your todo task.

Example of usage: 

`todo Buy Groceries`

Expected outcome:

The todo task will be added to the list.

```
Got it! I've added this task:
    [T][✘] Buy Groceries
```
### `deadline` - Adds a Deadline task to your list.
Deadline represents a Task to do by a certain date.
Once added, Yale will keep track of your deadline task.

Example of usage:

`deadline Buy Rollerblades /by 2022-02-21`

Expected outcome:

The deadline task will be added to the list.

```
Got it! I've added this task:
    [D][✘] Buy Rollerblades (by: Feb 21 2022)
```
### `event` - Adds an Event task to your list.
Event represents a task that occurs on a certain date.
Once added, Yale will keep track of your event task.

Example of usage:

`event Open House /at 2022-02-25`

Expected outcome:

The event task will be added to the list.

```
Got it! I've added this task:
    [E][✘] Open House (at: Feb 25 2022)
```
### `period` - Adds a DoWithin task to your list.
DoWithin represents a task to be completed between a certain period.
Once added, Yale will keep track of your dowithin task.

Example of usage:

`period Submit Assignment /between 2022-02-21 and 2022-02-28` 

Expected outcome:

The event task will be added to the list.

```
Got it! I've added this task:
    [DW][✘] Submit Assignment (between: Feb 21 2022 and Feb 28 2022)
```
### `list` - Lists all your tasks managed by Yale.
Shows all tasks according to when it was added.

Example of usage:

`list`

Expected outcome:

A list of all tasks that have been added.
Each task is accompanied by its task number.

```
Here are the tasks in your list:
1. [T][✘] Buy Groceries
2. [D][✘] Buy Rollerblades (by: Feb 21 2022)
3. [E][✘] Open House (at: Feb 25 2022)
4. [DW][✘] Submit Assignment (between: Feb 21 2022 and Feb 28 2022)
```
### `mark` - Marks a task as done.
Yale marks the specified task as done.
Specify the task to mark via its task number.

Example of usage:

`mark 2`

Expected outcome:

Marks the task with that task number as done.

```
Nice. I've marked this task as done:
    [D][✓] Buy Rollerblades (by: Feb 21 2022)
```
### `unmark` - Marks a task as **not** done.
Yale marks the specified task as **not** done.
Specify the task to unmark via its task number.

Example of usage:

`unmark 2`

Expected outcome:

Marks the task with that task number as **not** done.

```
OK, I've marked this task as not done yet:
    [D][✘] Buy Rollerblades (by: Feb 21 2022)
```
### `find` - Finds a task from your list.
Yale finds the specified task from your list.
Specify the task to find via its keyword.

Example of usage:

`find Buy`

Expected outcome:

All tasks with keyword `Buy` in the description will be displayed.
```
Here are the matching tasks in your list:
1. [T][✘] Buy Groceries
2. [D][✘] Buy Rollerblades (by: Feb 21 2022)
```
### `delete` - Deletes a task from your list.
Yale deletes the specified task from your list.
Specify the task to delete via its task number.

Example of usage:

`delete 4`

Expected outcome:

Deletes the specified task from the list.

```
Noted. I've removed this task:
    [DW][✘] Submit Assignment (between: Feb 21 2022 and Feb 28 2022)
Now you have 3 tasks in the list.
```
## Acknowledgements
Yale.png - Picture downloaded from [here](https://clipart.world/yoda-clipart/baby-yoda-clipart-transparent-1/).

User.png - Picture downloaded from [here](https://en.m.wikipedia.org/wiki/File:Sample_User_Icon.png).

send.png - Picture downloaded from [here](https://www.pinclipart.com/pindetail/xbTiJR_send-message-icon-white-clipart-computer-icons-clip/).


