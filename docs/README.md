# User Guide
**Dream** is a desktop application to assist anyone with their daily task tracking. It is optimised for use via a 
Command Line interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, 
Dream can schedule your tasks faster than traditional GUI apps.

## Features
### Overview
- Task Management
  - View all tasks
  - Add a task
    - Todo task
    - Event task
    - Deadline task
  - Delete a task
  - Mark a task
  - Unmark a task
  - Find a task
- Help page
- Exit the application

### Feature - View all tasks

View all the tasks from the task list.

### Feature - Add a task

Add a Todo, Event or Deadline task to the task list.

### Feature - Delete a task

Delete a task from the task list.

### Feature - Mark a task

Mark a task in the task list as done.

### Feature - Unmark a task

Mark a task in the task list as undone. 

### Feature - Find a task

Find a task from the task list with a keyword.

### Feature - Help page

Access the help page for all the commands.

### Feature - Exit the application

Exit the application.

## Usage

### `list` - Describe action

Type the command to list out all the tasks from the task list.

Example of usage:

`list`

Expected outcome:

A list consisting of all the current tasks in the list. Following is an example of a user with 4 tasks in the list

```
Here are the tasks in your list:
1.[T][] buy bread
2.[T][] buy dinner
3.[E][] birthday party (at: Monday)
4.[D][X] sweep the floor (by: Jan 1 2022)
Now you have 4 tasks in the list
```

### `todo` - Describe action

Type the command to add a Todo task to the task list.

Format:

`todo <Task description>`

Example of usage: 

`todo buy bread`

Expected outcome:

Add a Todo task, with a task description of "buy bread".

```
Got it. I've added this task:
[T][] buy bread
Now you have 4 tasks in the list
```

### `event` - Describe action

Type the command to add an Event task to the task list.

Format:

`event <Task description> /at <Day of week>`

Example of usage:

`event buy bread /at Monday`

Expected outcome:

Add a Todo task, with a task description "buy bread" and set a deadline of the task as "Monday".

```
Got it. I've added this task:
[E][] buy bread (at: Monday)
Now you have 4 tasks in the list
```

### `deadline` - Describe action

Type the command to add a Deadline task to the task list.

Format:

`deadline <Task description> /by <yyyy-mm-dd>`

Example of usage:

`deadline buy bread /by 2022-02-23`

Expected outcome:

Add a Deadline task, with a task description "buy bread" and set a deadline of the task as "2022-02-23".

```
Got it. I've added this task:
[D][] buy bread (by:Feb 23 2022)
Now you have 4 tasks in the list
```

### `delete` - Describe action

Type the command to delete a task from the task list.

Format:

`delete <Integer>`

Example of usage:

`delete 1`

Expected outcome:

Delete task 1 from the task list.

```
Noted. I've removed this task:
[T][] buy bread
Now you have 4 tasks in the list
```

### `mark` - Describe action

Type the command to mark a task in the task list as done.

Format:

`mark <Integer>`

Example of usage:

`mark 1`

Expected outcome:

Mark task 1 as done.

```
Nice! I've marked this task as done:
[T][X] buy bread
```

### `unmark` - Describe action

Type the command to mark a task in the task list as undone.

Format:

`unmark <Integer>`

Example of usage:

`Unmark 1`

Expected outcome:

Mark task 1 as undone.

```
Nice! I've marked this task as not done yet:
[T][] buy bread
```

### `find` - Describe action

Type the command to find a task from the task list with a keyword.

Format:

`find <keyword>`

Example of usage:

`find bread`

Expected outcome:

Find any task that has the word "bread" in the task description.

```
Here are the matching tasks in your list:
1.[T][] buy bread
```

### `help` - Describe action

Type the command to access the help page for all the commands.

Example of usage:

`help`

Expected outcome:

A help page consisting of all the commands.

```
1. Type 'todo <description>' to add a todo task
2. Type 'deadline <description> <time>' to add a deadline task
3. Type 'event <description> <time>' to add a event task
4. Type 'mark <integer>' to mark a task as done
5. Type 'unmark <integer>' to mark a task as undone
6. Type 'delete <integer>' to delete a task from your list
7. Type 'list' to list all your tasks
8. Type 'find <keyword>' to find a task
9. Type 'bye' to exit the app
```

### `bye` - Describe action

Type the command to exit the application.

Example of usage: 

`bye`

Expected outcome:

Exit the application.
