# MIKE User Guide

## Introduction
Mike is a computer based chatbox designed to ease your life by managing your tasks conveniently. Mike is
* text-based
* simple
* efficient
* ~~fun~~ ***SUPER FUN***

## Quick Start
Before including Mike into your life, please ensure you have the following:
1. Java 11 or more advanced version of java installed on your computer.
2. Download the latest jar release from this repo.
3. Launch the jar file.
4. Interact with Mike with the commands specified below(**Usage**).

## Features 

### Add a task

Add any form of task to Mike.
Mike has divided tasks to the following three simple categories:
1. *todo*
2. *event*
3. *deadline*

### Update a task status

Update the status of your tasks by marking and unmarking them as you deem fit.

### Delete a task

Done with a task and do not want to see it on your list?
Delete it as you please.

### Tag a task

Mike gives you an option to tag a task with any label you desire.
You may choose not to use this feature.

### Find a task

Long list of tasks? No problem.
Find what you are looking for entering a keyword.

### View all tasks

Want to see everything that you have going on in your life?
Mike will display it to you on your command. 

## Usage

### `todo` - Adds todo task

Adds a task to the task list. 

Example of usage: 

`todo slides for GE project`

Expected outcome:

```
Got it. I've added this task:
[T][] slides for GE project
Now you have 1 tasks in the list.
```

### `deadline` - Adds deadline task

Adds a task with a deadline to the task list.

Example of usage: 

`deadline project 1 /by 2022-02-03 23:59`

Expected outcome:

```
Got it. I've added this task:
[D][] project 1 (by: Feb-03-2022 23:59 PM)
Now you have 2 tasks in the list.
```

### `event` - Adds event task

Adds an task with a location to the task list. 

Example of usage: 

`event wedding /at Hotel Rainbow`

Expected outcome:

```
Got it. I've added this task:
[E][] wedding (at: Hotel Rainbow)
Now you have 3 tasks in the list.
```

### `list` - Displays task list

Displays all tasks present in the tasklist.

Example of usage: 

`list`

Expected outcome:

```
Your list has 3 tasks:
1. [T][] slides for GE project
2. [D][] project 1 (by: Feb-03-2022 23:59 PM)
3. [E][] wedding (at: Hotel Rainbow)
```

### `mark` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

### `unmark` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

### `bye` - Terminates Mike

Describe the action and its outcome.

Example of usage: 

`bye`

Expected outcome:

Mike will diplay termination message and program will be terminated.

```

```
