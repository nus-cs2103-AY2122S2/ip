# User Guide
*Duke* is a task manager that helps you improve your productivity! It has a Graphical User Interface (GUI) that resembles a classic messenger application.

## Features 

### Feature-Task Management

With *Duke*, you never have to worry about forgetting your next task. *Duke* supports 3 types of tasks:
- ToDo
- Deadlines
- Events

You can **Add, Remove, Mark** or **Unmark** your tasks.

### Feature-Storage

In between *Duke* sessions, *Duke* **automatically** stores your tasks from previous usage into a `.txt` file, and loads them upon next usage! You don't have to worry about losing tasks in between sessions.

### Feature-Searching

You can search for tasks by a keyword. Let *Duke* help you by finding the tasks for you by your keyword!

## Usage

### `List` - Shows all current tasks in list

*Duke* displays all the current tasks you have added to your list thus far.

Example of usage: 

`list`

Expected outcome:

Generates a list of all current tasks that you have so far. Tasks can be marked as done or undone.

```
Here are the tasks in your list:
1.[T][X] CS Leetcode Practice 207
2.[E][ ] Charlie's Birthday (at: Mar 03 2022 1900)
3.[T][X] Buy Milk
```

### `todo`/`deadline`/`event` - Adds a specific task to task list

*Duke* adds a specific type of task to the task list, based on the command given.

`[YOUR_TASK]` is a string of characters representing the task that you want to add.

`[DATE]` is a string of characters representing the date of the task. Format is given in **yyyy-mm-dd HH:mm**

Example of usage:

`todo [YOUR_TASK]` - `todo go to gym`

`deadline [YOUR_TASK] /by [DATE]` - `deadline finish project /by 2022-01-01 1900:00`

`event [YOUR_TASK] /at [DATE]` - `event project meeting /at 2022-10-15 18:00`

Expected outcome:

*Duke* adds the specified task to the task list.

```
Roger, I got you. I've added this task:
[T][ ] go to gym
Now you have 4 tasks in the list

Roger, I got you. I've added this task:
[E][ ] project meeting (at: Oct 15 2022 1800)
Now you have 5 tasks in the list.
```

### `delete` - Removes a specific task in the task list

*Duke* deletes a specifc task in the task list.

`[NUM]` is a integer that represents the position of the task to be deleted (1 - based index).

Example of usage:

`delete [NUM]` - `delete 2`

Expected outcome:

*Duke* removes the specified task from teh task list.

```
Noted, I have removed this task:
[E][ ] project meeting (at: Oct 15 2022 1800)
Now you have 4 tasks in the in list.
```

### `mark`/`unmark` - Marks a specific task a completed

*Duke* toggles the status of the task specified. 

`mark` sets the task to be completed.

`unmark` sets the task to be incomeplete.

`[NUM]` is a integer that represents the position of the task to be set (1 - based index).

Example of usage:

`mark [NUM]` - `mark 2`

`unmark [NUM]` - `unmark 2`

Expected outcome:

*Duke* sets the status of the task as either completed if `mark` is used, else as incomplete if `unmark` is used.

```
Cool! You seemed to have been productive just like me! I've marked this task as done:
[T][X] CS Leetcode Practice 207

Did you mess up something? I'll mark it as undone -- but I believe you can do it!:
[T][ ] CS Leetcode Practifce 207
```

### `find` - Find tasks by keyword

*Duke* finds a list of tasks that matches with the keyword given.

`[YOUR_TASK]` is a string of characters representing the keyword that you want to search for.

Example of usage:

`find [YOUR_TASK]` - `find Buy`

Expected outcome:

*Duke* returns a list of tasks that matches the given keyword.

```
Here are the matching tasks in your list:
3.[T][X] Buy Milk
5.[T][ ] Buy pencil
```




