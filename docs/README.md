# Mike (Task Manager) User Guide

## Introduction
Mike is a simple and efficient task manager app that works on desktops.
It uses a Command Line Interface to take in user commands but has the
added benefit of responding to the user on a Graphical User Interface for
more seamless interaction. It allows users to manage 3 types of tasks - Todos,
Events, and Deadlines.

## Feature List

### Add a todo task

Description: Add a new todo to the stored list of tasks.

Syntax: `todo [TODO_NAME]` OR `t [TODO_NAME]`

### Add an event task

Description: Add a new event to the stored list of tasks.

Syntax: `event [EVENT_NAME] /at [EVENT_DATE]` OR `e [EVENT_NAME] /at [EVENT_DATE]`

*EVENT_DATE should be in the format "YYYY-MM-DD"

### Add a deadline task

Description: Add a new deadline to the stored list of tasks.

Syntax: `deadline [DEADLINE_NAME] /by [DEADLINE_DATE]` OR `d [DEADLINE_NAME] /by [DEADLINE_DATE]`

*DEADLINE_DATE should be in the format "YYYY-MM-DD"

### List all tasks

Description: Presents a list of all the tasks ordered by their index number.

Syntax: `list`

### Remove a task

Description: Remove a task from the list of tasks by its index number.

Syntax: `remove [TASK_INDEX]`

### Mark a task as done

Description: Mark a task from the list of tasks as done by its index number.

Syntax: `mark [TASK_INDEX]`

### Unmark a task as done

Description: Unmark a task from the list of tasks as done by its index number.

Syntax: `unmark [TASK_INDEX]`

### Find tasks

Description: Find tasks in the list of tasks that have names containing
the keyword specified.

Syntax: `find [KEYWORD]`

### Save the data to hard drive

Description: The program automatically saves the task list to the hard drive
after each command that changes the data in the task list.

### Exit program

Description: Exit the program and close the window.

Syntax: `bye`

## Command Cheat Sheet

| Command            | Syntax|
|--------------------|------------------|
| **Add a Todo**     | `todo [TODO_NAME]` OR `t [TODO_NAME]`|
| **Add an Event**   | `event [EVENT_NAME] /at [EVENT_DATE]` OR `e [EVENT_NAME] /at [EVENT_DATE]`|
| **Add a Deadline** | `deadline [DEADLINE_NAME] /by [DEADLINE_DATE]` OR `d [DEADLINE_NAME] /by [DEADLINE_DATE]`|
| **View List**      | `list`|
| **Delete a Task**  | `remove [TASK_INDEX]`|
| **Mark a Task**    | `mark [TASK_INDEX]`|
| **Unmark a Task**  | `unmark [TASK_INDEX]`|
| **Find Tasks**     | `find [KEYWORD]`|
| **Exit Program**   | `bye`|