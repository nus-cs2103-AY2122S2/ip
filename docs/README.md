# User Guide for Cortana

*Let Cortana manage all your tasks! Let's get the banished off this ring!*

![Duke Image](https://Halpfrog.github.io/ip/Ui.png)

## Table of Contents
1. [Application Features](#application-features)
2. [How to run Cortana](#How-to-run-Cortana)
3. [Task List command usages](#task-list-command-usages)
    - [Viewing task list](#viewing-task-list)
    - [Creating a new todo](#creating-a-new-todo)
    - [Creating a new deadline](#creating-a-new-deadline)
    - [Creating a new event](#creating-a-new-event)
    - [Marking a task as complete](#marking-a-task-as-complete)
    - [Marking a task as incomplete](#marking-a-task-as-incomplete)
    - [Deleting a task](#deleting-a-task)
    - [Clearing all tasks](#clearing-all-tasks)
    - [Finding tasks](#finding-tasks)
    - 
4. [General commands](#general-commands)
   - [Help](#Asking for help)
   - [Closing the application](#closing-the-application)

## Application Features
1. Create and add new `todos`, `deadlines` and `events` to your task lists
2. Mark/unmark `todos`, `deadlines` and `events` in your task list.
3. Delete tasks and notes from your lists.
4. Filter tasks and notes in your lists by `keyword` or `substring`.

## How to run Cortana
1. Make sure to have Java 11 installed
2. Download the Duke.jar file to your desired directory
3. Navigate to the chosen directory using terminal/powershell
4. Run the command `java -jar Duke.java` in terminal/powershell

## Task List Command Usages

### Viewing task list

`list` allows you to view your task list.

Format and Example:
`list`

### Creating a new todo

`todo` allows you to add a new todo to your task list.

Format:
`todo <description>`

Example:
`todo Rescue the Pilot`

### Creating a new deadline

`deadline` allows you to add a new deadline to your task list.

Format:
`deadline <description> /by <date in dd/MM/yyyy HHmm format>`

Example:
`deadline take down the tower /by 12/12/2560 2359`

### Creating a new event

`event` allows you to add a new event to your task list.

Format:
`event <description> on <date in dd/MM/yyyy HHmm format>`

Example:
`event The endless will unleash /at 15/12/2560 HHmm`

### Marking a task as complete

`mark` allows you to set the status of a task as completed.

Format:
`mark <index number of task to mark>`

Example:
`mark 4`

### Marking a task as incomplete

`unmark` allows you to set the status of a task to incomplete.

Format:
`unmark: <index number of task to unmark>`

Example:
`unmark 3`

### Deleting a task

`delete` allows you to delete a specific task in your task list.

Format:
`delete <index number>`

Example:
`delete 2`

### Clearing all tasks

`clear` allows you to clear all your tasks

Format and Example:
`clear`

### Finding tasks

`find` allows you to find all tasks in your task list that match a specific keyword.

Format:
`find <keyword>`

Example:
`find Tower`

### Flexible Find

`F find` allows you to find all tasks that have partial matches

Example:
`F find Esch`

##General Commands

### Asking for help

`help` allows you to view all cortana commands.

Format and Example:
`help`

### Closing the application

`bye` closes the application.

Format and Example:
`bye`