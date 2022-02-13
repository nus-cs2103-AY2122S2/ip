# User Guide for Duke

*Let Duke help you manage your todos, events, deadlines and notes, so 
you never have to do it yourself!*

![Duke Image](../docs/Ui.png)

## Table of Contents
1. [Application Features](#application-features)
2. [Task List command usages](#task-list-command-usages)
   - [Viewing task list](#viewing-task-list)
   - [Creating a new todo](#creating-a-new-todo)
   - [Creating a new deadline](#creating-a-new-deadline)
   - [Creating a new event](#creating-a-new-event)
   - [Marking a task as complete](#marking-a-task-as-complete)
   - [Marking a task as incomplete](#marking-a-task-as-incomplete)
   - [Deleting a task](#deleting-a-task)
   - [Deleting all tasks](#deleting-all-tasks)
   - [Finding tasks](#finding-tasks)
3. [Note List command usages](#note-list-command-usages)
   - [Viewing note list](#viewing-note-list)
   - [Creating a new note](#creating-a-new-note)
   - [Deleting a note](#deleting-a-note)
   - [Deleting all notes](#deleting-all-notes)
   - [Finding notes](#finding-notes)
4. [General command usages](#general-command-usages)
   - [Asking for help](#asking-for-help)
   - [Closing the application](#closing-the-application)

## Application Features
1. Create and add new `todos`, `deadlines`, `events` and `notes` to your task and note lists.
2. Mark/unmark `todos`, `deadlines` and `events` in your task list.
3. Delete tasks and notes from your lists.
4. Filter tasks and notes in your lists by `keyword`.

## Task List Command Usages

### Viewing task list

`task list` allows you to view your task list.

Format and Example:
`task list`

### Creating a new todo

`task new todo` allows you to add a new todo to your task list.

Format:
`task new todo <todo description>`

Example:
`task new todo Create a resume`

### Creating a new deadline

`task new deadline` allows you to add a new deadline to your task list.

Format:
`task new deadline <deadline description> by <date in YYYY-MM-DD format>`

Example:
`task new deadline Submit CS2103T quiz by 2022-02-13`

### Creating a new event

`task new event` allows you to add a new event to your task list.

Format:
`task new event <event description> on <date in YYYY-MM-DD format>`

Example:
`task new event Attend CS seminar on 2020-02-26`

### Marking a task as complete

`task mark` allows you to set the status of a task as completed.

Format:
`task mark <index number of task to mark>`

Example:
`task mark 2`

### Marking a task as incomplete

`task unmark` allows you to set the status of a task to incomplete.

Format:
`task unmark: <index number of task to unmark>`

Example:
`task mark 2`

### Deleting a task

`task delete` allows you to delete a specific task in your task list.

Format:
`task delete <index number>`

Example:
`task delete 2`

### Deleting all tasks

`task delete all` allows you to delete all tasks in your task list.

Format and Example:
`task delete all`

### Finding tasks

`task find` allows you to find all tasks in your task list that match a specific keyword.

Format:
`task find <keyword>`

Example:
`task find seminar`

## Note List Command Usages

### Viewing note list

`note list` allows you to view your note list.

Format and Example:
`note list`

### Creating a new note

`note new` allows you to add a new note to your note list.

Format:
`note new <note description>`

Example:
`note new Buy new books from bookstore.`

### Deleting a note

`note delete` allows you to delete a specific note in your note list.

Format:
`note delete <index number>`

Example:
`note delete 2`

### Deleting all notes

`note delete all` allows you to delete all notes in your note list.

Format and Example:
`note delete all`

### Finding notes

`note find` allows you to find all notes in your note list that match a specific keyword.

Format:
`note find <keyword>`

Example:
`note find books`

## General Command Usages

### Asking for help

`help` allows you to view all duke commands.

Format and Example: 
`help`

### Closing the application

`bye` closes the application.

Format and Example:
`bye`