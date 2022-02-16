# Aeromon User Guide

Your one-stop task tracking app. Encourages you when you complete a task and scolds you when you don't.

## Features 

Ability to do many things in this application!

### Add Tasks

Add different types of tasks to your task list.

### Edit Tasks

Edit the tasks in your task list.

### Find Tasks

Find the tasks in your task list by the keywords.

### View Task List

List out all the tasks currently in your task list.

## Usage

### `todo` - Adds todo task

Adds the todo task into your task list, only if the task is not a duplicate.
Todo tasks are tasks without any date/time attached to it, i.e. only descriptions.

Example of usage: 

`todo visit dog cafe`

Expected outcome:

```
Nicely! I've added for you:
[T][ ] visit dog cafe
You currently have 1 tasks on the list >.< Jiayous
```

### `deadline` - Adds deadline task

Adds the deadline task into your task list, only if the task is not a duplicate.
Deadline tasks are tasks that need to be done before a specific date/time.
The date format is in yyyy-mm-dd (e.g., 2019-10-15).

Example of usage:

`deadline watch lecture 7 /by 2022-02-14`

Expected outcome:

```
Nicely! I've added for you:
[D][ ] watch lecture 7 (by: Feb 14 2022)
You currently have 2 tasks on the list >.< Jiayous
```

### `event` - Adds event task

Adds the event task into your task list, only if the task is not a duplicate.
Event tasks are tasks that start at a specific time and ends at a specific time.
The date format is in yyyy-mm-dd (e.g., 2019-10-15).

Example of usage:

`event birthday celebration /at 2022-03-12`

Expected outcome:

```
Nicely! I've added for you:
[E][ ] event birthday celebration (at: Mar 12 2022)
You currently have 3 tasks on the list >.< Jiayous
```

### `list` - Lists out the tasks

Displays the task lists with all the tasks and their current status.

Example of usage:

`list`

Expected outcome:

```
Konnichiwassup! Look at how much work you have to do!
1. [T][ ] visit dog cafe
2. [D][ ] watch lecture 7 (by: Feb 14 2022)
3. [E][ ] event birthday celebration (at: Mar 12 2022)
You currently have 3 tasks on the list >.< Jiayous
```

### `mark` - Marks the task as done

Changes the status of the specified task to done.

Example of usage:

`mark 1`

Expected outcome:

```
Naisu! You've completed: 
1. [T][X] visit dog cafe
You currently have 3 tasks on the list >.< Jiayous
```

### `unmark` - Marks the task as not done

Changes the status of the specified task to not done.

Example of usage:

`unmark 1`

Expected outcome:

```
OI! What happened to completing:
1. [T][ ] visit dog cafe
You currently have 3 tasks on the list >.< Jiayous
```

### `delete` - Deletes a task

Deletes the task from the task list, as specified by the index.

Example of usage:

`delete 3`

Expected outcome:

```
Okies! I have deleted:
3. [E][ ] event birthday celebration (at: Mar 12 2022)
You currently have 2 tasks on the list >.< Jiayous
```

### `find` - Finds a task

Finds a task using keywords.

Example of usage:

`find cafe`

Expected outcome:

```
M-m-m-m-matching tasks found:
1. [T][ ] visit dog cafe
Phew, that was a good search, remember to complete your tasks! 
```

### `ftodo` - Force add the todo task

Adds the todo task regardless of whether the task is a duplicate.

Example of usage:

`ftodo visit dog cafe`

Expected outcome:

```
Nicely! I've added for you:
[T][ ] visit dog cafe
You currently have 3 tasks on the list >.< Jiayous
```

### `fdeadline` - Force add the deadline task

Adds the deadline task regardless of whether the task is a duplicate.
The date format is in yyyy-mm-dd (e.g., 2019-10-15).

Example of usage:

`fdeadline watch lecture 7 /by 2022-02-14`

Expected outcome:

```
Nicely! I've added for you:
[D][ ] watch lecture 7 (by: Feb 14 2022)
You currently have 4 tasks on the list >.< Jiayous
```

### `fevent` - Force add the event task

Adds the event task regardless of whether the task is a duplicate.
The date format is in yyyy-mm-dd (e.g., 2019-10-15).

Example of usage:

`fevent birthday celebration /at 2022-03-12`

Expected outcome:

```
Nicely! I've added for you:
[E][ ] event birthday celebration (at: Mar 12 2022)
You currently have 5 tasks on the list >.< Jiayous
```

### `bye` - Terminates the Aeromon bot

Ends the chatbot and exits the GUI.

Example of usage:

`bye`

Expected outcome:

```
Buai Buai! Ciao for now!
```