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

Displays all the current tasks you have added to your list thus far.

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

### `todo/deadline/event` - Adds a specific task to task list

Duke adds a specific type of task to the task list, based on the command given.

`YOUR_TASK` is a string of characters representing the task that you want to add.

`DATE` is a string of characters representing the date of the task. Format is given in **yyyy-mm-dd HH:mm**

Example of usage:

`todo YOUR_TASK` - `todo go to gym`

`deadline YOUR_TASK /by DATE` - `deadline finish project /by 2022-01-01 1900:00`

`event YOUR_TASK /at DATE` - `event project meeting /at 2022-10-15 18:00`

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


