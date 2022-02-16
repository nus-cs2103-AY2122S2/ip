# User Guide

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest mcbot.jar from [here](https://github.com/AD-NAP/ip).
3. Copy the file to the folder you want to use as the home folder for your McBot.
4. Double-click the file to start the app. 
5. Type the command in the command box and press Enter to execute it.
6. Refer to the [Features](#features) below for details of each command.

## Features

### List

List all your tasks - saved conveniently in this bot! 

### Add a ToDo, Deadline or Event task 

Choose one of the three types of tasks to be added to your list! 

### Delete a task

Delete your task easily with a single command! 

### Mark or Unmark

Mark your task as complete! (Unmarking is possible too).

### Find

Can't find your task in your endless list? Use the find feature! 

## Usage
ℹ️ Notes about command format:
- Words in `[Square Brackets]` are parameters that must be given.
- Items in `(Round Brackets)` are optional.

### `list` - List all your tasks

List down all your task saved in the list.

### `mark [num]` - mark the task as complete 

Mark the task that is numbered as `[num]` as completed.

Example of usage:
`mark 2`

### `unmark [num]` - unmark the task

Unmark the task that is numbered as `[num]` as incomplete.

Example of usage:
`unmark 3`


### `todo [TaskName]` - Adds a todo task

Adds a todo task to the list.

Example of usage:
`todo Read A Book`

### `deadline [TaskName] /by [DD/MM/YYYY] (HHHH)` - Adds a deadline task

Adds a deadline task to the list.

Example of usage:
`deadline Important Task /by 12/03/2022 2359`

### `event [TaskName] /at [DD/MM/YYYY] (HHHH)` - Adds a event task

Adds a event task to the list.

Example of usage:
`event Important Task /by 12/03/2022 2359`

### `find [TaskName]` - Finds the task

Find the task that contains the task name.

Example of usage:
`find that one task I have been searching for`

## Command Summary

| Command  | Function                                                            |
|----------|---------------------------------------------------------------------|
| list     | list all the task<br/>eg. `list`                                    |
| mark     | mark the task as complete<br/>eg. `mark 2`                          |
| unmark   | unmark the task that is completed<br/>eg. `unmark 2`                |
| delete   | delete the task specified<br/>eg. `delete 2`                        | 
| todo     | adds a todo task<br/>eg. `todo my task name`                        |
| deadline | adds a deadline task<br/>eg. `deadline Task A /by 12/03/2022`       |
| event    | adds an event task<br/>eg. `event Task B /at 15/03/2022 1330`       |
| find     | finds the task that contains the same name<br/>eg. `find That Task` |

[Back to top](#user-guide)