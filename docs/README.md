# User Guide

# DukePro
> "The important thing to remember, is not to forget"

DukePro frees your mind of having to remember things you need to do. It's,

* text-based
* easy to learn
* ~~FAST~~ SUPER FAST to use

All you need to do is,
1. download it from [here](https://github.com/fantablack/ip/releases/tag/A-Jar)
2. double-click it
3. add your tasks
4. let it manage your tasks for you 😜

And best of all, it is ***100% FREE!***

Features:
- [x] Managing tasks
- [x] Managing deadlines
- [x] Managing Events


If you are a Java programmer, you can use it to practice Java too. Here's the `main` method:

```
public static void main(String[] args) {
        Duke kizer = new Duke();
        kizer.run();
}
```

## Features 

### Todos

Manage your Todos!

### Events

Manage your Events!

### Deadlines

Manage your Deadlines!

## Usage

### Add a Todo
Adds a new todo `<todo-name>`

Syntax:

`todo <todo-name>`

Example of usage: 

`todo buy milk`

Expected outcome:

Adds a new Todo task `buy milk`.

### Add an Event
Adds a new event `<event-name>` at `dd/MM/yyyy HHmm`

Syntax:

`event <event-name> /at dd/MM/yyyy HHmm`

Example of usage:

`event wedding /at 11/12/2022 1830`

Expected outcome:

Adds a new Event `wedding` at `11/12/2022 1830`.


### Add a Deadline
Adds a Deadline `<deadline-name>` due by `dd/MM/yyyy HHmm`

Syntax:

`deadline <your-deadline> /by dd/MM/yyyy HHmm`

Example of usage:

`deadline math homework /by 15/3/2022 2359`

Expected outcome:

Adds a new Deadline `math homework` due by `15/3/2022 2359`

### List all Tasks
Syntax:

`list`

Expected Outcome:

Lists all tasks saved in the chatbot.

### Find
Find task(s) matching a `<search term>` fully or partially.

Example of usage:

`find <search term>`

Expected outcome:

List of task(s) matching `search term` fully or partially.

### Delete <task index>
Deletes a task at a specified `<index>`

Syntax:

`delete <index>`

Example of usage:

`delete 2`

Expected outcome:

Deletes the task at index `2` from the list of tasks

### Mark
Mark a task at `<index>` as completed

Syntax:

`mark <index>`

Example of usage:

`mark 1`

Expected outcome:
Marks the task at index `1` as completed.

## Unmark
Unmark a task at `<index>` to render it as incomplete.

Syntax:

`unmark <index>`

Example of usage:

`unmark 1`

Expected outcome:

Unmarks the task at index `1`; i.e. task `1` is rendered incomplete.
