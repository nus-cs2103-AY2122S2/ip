# User Guide

## Features 

### Manage your tasks effectively in a virtual tasklist

Johnny accepts and stores three different kinds of tasks 
- Todos
- Deadlines
- Events

### Smart scheduling clash detection

Johnny will alert you when it detects a scheduling clash

### Search for task

Look for a specific task in Johnny's database using a text query

## Usage

### `Keyword` - todo/event/deadline

Adds a todo, event or deadline to the Johnny's list
of tasks. Note that date format for deadlines and event 
should be in 'yyyy-mm-dd'

Example of usage: 

`deadline submit CS2103T iP/2020-02-18`

Expected outcome:

Johnny adds a deadline task due on 18th Feb 2020

```
Got it! I've added this task:
[D][] submit CS2103T iP (Feb 18 2020)
Now you have 1 task in your list
```

### `Keyword` - mark/unmark

Marks or unmarks a specific task as done or 
undone

Example of usage:

`mark 1`

Expected outcome:

Johnny marks the 1st task in the list as done

```
Nice! I have marked this task as done:
[D][X] submit CS2103T iP (Feb 18 2020)
```

### `Keyword` - delete

Removes a specific task from the list

Example of usage:

`delete 1`

Expected outcome:

Johnny removes the 1st task in the list

```
Noted. I've removed this task:
[D][X] submit CS2103T iP (Feb 18 2020)
```

### `Keyword` - list

Prints the list of tasks saved

Example of usage:

`list`

Expected outcome:

Johnny displays all the tasks in the list

```
Here are the tasks in your list:
1. [D][X] submit CS2103T iP (Feb 18 2020)
2. [T][] drop books at library
```

### `Keyword` - save

Save all changes made to the list since last save

Example of usage:

`save`

Expected outcome:

Johnny saves changes made to list

```
Your changes have been saved!
```