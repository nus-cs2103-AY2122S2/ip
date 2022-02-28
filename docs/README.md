# DUKE
Duke is a task management application which allows users to keep track of all their tasks.

## Features 

### List tasks

List all the tasks saved in Duke.

### Add task

Add a task to Duke, you can add a todo task, a deadline task or a event task.

### Delete task

Delete a task from Duke.

### Mark/Unmark task

Mark a task as completed or unmark it as not completed.

### Find task

Find a task using a keyword.

### Sort task

Sort the tasks by their task type, deadline and event tasks will also be sorted by their time.

## Usage

### `list` - Shows all tasks saved in Duke

Shows a list of tasks that you have added to Duke.

Example of usage: 

`list`

Expected outcome:

Displays a list of tasks with their mark/unmark status as well as the task type.

```
Here are the tasks in your list:
1. [D][ ] CS2106 Lab (by: Mar 05 2022 11:59 PM)
2. [T][ ] watch movie
3. [D][ ] CS2103 Assignment (by: Mar 01 2022 6:00 PM)
4. [E][ ] Career Fair (at: Mar 25 2022 9:00 AM)
```
### `todo` - Adds a todo task

Adds a todo task to the task list.

Example of usage: 

`todo go to the gym`

Expected outcome:

Displays the confirmation of the todo task added and the number of tasks in the list.

```
Got it. I've added this task:
 [T][ ] go to the gym
Now you have 5 tasks in the list.
```
### `deadline` - Adds a deadline task

Adds a deadline task to the task list.

Example of usage: 

`deadline CS2040s Lab /by 2022-03-21 2359`

Expected outcome:

Displays the confirmation of the deadline task added and the number of tasks in the list.

```
Got it. I've added this task:
 [D][ ] CS2040s Lab (by: Mar 21 2022 11:59pm)
Now you have 6 tasks in the list.
```
### `event` - Adds a event task

Adds a event task to the task list.

Example of usage: 

`event friends gathering /at 2020-02-23 1800`

Expected outcome:

Displays the confirmation of the event task added and the number of tasks in the list.

```
Got it. I've added this task:
 [E][ ] friends gathering (at: Feb 23 2022 6:00pm)
Now you have 7 tasks in the list.
```
### `mark` - mark a task as done

Mark a task as completed in the task list.

Example of usage: 

`mark 3`

Expected outcome:

Displays a confirmation that the task is marked.

```
Nice!. I've marked this task as done:
 [D][X] CS2040s Lab (by: Mar 21 2022 11:59pm)
```
### `unmark` - unmark a task as not done

Unmark a task as not completed in the task list.

Example of usage: 

`unmark 3`

Expected outcome:

Displays a confirmation that the task is unmarked.

```
Nice!. I've marked this task as not done yet:
 [D][] CS2040s Lab (by: Mar 21 2022 11:59pm)
```
### `find` - find tasks with a keyword

Find matching tasks in the task list that contains the keyword.

Example of usage: 

`find Lab`

Expected outcome:

Display a list of tasks that contains the keyword.

```
Here are the matching tasks in your list:
 1.[D][] CS2106 Lab (by: Mar 05 2022 11:59pm)
 2.[D][] CS2040s Lab (by: Mar 21 2022 11:59pm)
```

### `sort` - sort the task list

Sort the task list by grouping them according to their task type. The deadline tasks and event tasks will also be sorted by their date and time.

Example of usage: 

`sort`

Expected outcome:

Display the sorted task list.

```
Here are the matching tasks in your list:
 1.[T][] watch movie
 2.[T][] go to the gym
 3.[D][] CS2103 Assignment (by: Mar 01 2022 6:00pm)
 4.[D][] CS2106 Lab (by: Mar 05 2022 11:59pm)
 5.[D][] CS2040s Lab (by: Mar 21 2022 11:59pm)
 6.[E][] friends gathering (at: Feb 23 2022 6:00pm)
 7.[E][] Career Fair (at: Mar 25 2022 9:00am)
```
### `bye` - exits the applicantion

Exits the application.

Example of usage: 

`bye`

Expected outcome:

Display a bye message and the application closes after 3 seconds.

```
Ciao! Hope to see you again!
```
