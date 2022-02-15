# The User Guide for Puke

Note that Puke requires Java 11 to run


What is stopping you from being productive?  

a good puke  
and cat photos of course

![Puke Screenshot](Ui.png)

## Features

### Manage Tasks

Puke allows you to manage THREE types of tasks:
- Todo
- Deadline
- Event  

Comes with operations such as adding, deleting, marking, sorting, and much more!  

### Cat Therapy

Just by simply looking at the beautiful interface, you will feel motivated to actually complete your tasks. Remember, you have 99 problems but at least task management ain't one.

<br />

## Download
To get started on Puke-ing, simple download the [JAR file](https://github.com/likeabowx/ip/releases/download/A-Release/puke.jar), place it in an empty folder and double click it to run!

Note that Puke requires Java 11 to run.

<br />

## Commands
Note that all commands are case-sensitive



### `list` - View the list of tasks

<br />

### `todo` - Adds a 'to-do' task
To-do tasks do not have any date/time!

Usage: `todo [task name]`

<br/>

### `deadline` - Adds a 'deadline' task
A deadline has a specified deadline to complete by.

Usage: `deadline [task name] \by [date time]`  

The format of date time should be 'yyyy-mm-dd hh:mm'.

<br/>

### `event` - Adds an 'event' task
An event has a specified date and time when it happens.

Usage: `event [task name] \by [date time]`

The format of date time should be 'yyyy-mm-dd hh:mm'.

<br/>

### `mark` - Marks a task as done

Usage: `mark [task number]`

The task number can be checked with `list`.

<br/>

### `unmark` - Unmarks a task as done

Usage: `unmark [task number]`

The task number can be checked with `list`.

<br/>

### `delete` - Deletes a task

Usage: `delete [task number]`

The task number can be checked with `list`.

<br/>

### `find` - Finds a task by a keyword in the name

Usage: `find [keyword]`

Note that the keyword doesn't have to match by a whole word.

<br />

### `sort` - Sorts the list of tasks

Usage: `sort [by] (order)`

[by] can either be `date`, `name` or `type`

For sorting by date and name, you can specify `asc` or `dsc` to sort by ascending or descending order.

<br />

### `help` - View user guide

Usage: `help (command)`

You can optionally add in the command to vie the guide for.
