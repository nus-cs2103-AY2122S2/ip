# User Guide

Duke is an organisational tool that helps you **stay clutter free and keep track of your tasks!**
- [Quick Start](#quick-start) 
- [Features](#features)
  - [Adding Tasks](#adding-tasks)
    - [Todo](#todo-todo)
    - [Deadlines](#deadlines-deadline)
    - [Events](#events-event)
  - [Mark/Unmark](#markunmark)
  - [List](#list-list)
  - [Delete](#delete-delete)
  - [Find](#find-find)
  - [Priority](#priority-priority)
  - [Bye](#bye-bye)


## Quick start
Ensure you have Java 11 or above installed in your Computer.

Download the latest addressbook.jar from [here](insert link here).

Copy the file to the folder you want to use as the home folder for duke.

Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.



## Features 

## Adding Tasks
Task are split into 3 different types: Todo, Deadline and Event. Adding each one has its unique syntax.


## Todo `todo`
Todos are tasks that have no deadline. 

Syntax to add them is:

`todo <Name of task>`

>todo Read Book

## Deadlines `deadline`

Deadlines are tasks that have are supposed to be done by a specific deadline.

Syntax to add them is:

`deadline <Name of task> /by YYYY/MM/DD HH:MM`

>todo Read Book /by 2022/02/21 13:00


## Events `event`

Events are tasks that have are supposed to be done **at** a specific time.

Syntax to add them is:

`event <Name of task> /at YYYY/MM/DD HH:MM`

>todo Book Club /at 2022/02/21 13:00

## Mark/Unmark
Marks or Unmarks a task as done.

## Mark `mark`
Marks a task as done.
Syntax:
`mark <index>`

Example:
>mark 1

## Unmark `unmark`

Syntax:
`unmark <index>`

Example:
>unmark 2


## List `list`
Lists down the tasks that the user has added.

Syntax:
`list`

Example:
> list
> 
> 1. [L][T][ ] Read Book
> 
> 2.  [L][E][ ] Bookclub (at: 21 February 2022 13:00)

## Delete `delete`
Deletes a task from the list
Syntax:
`delete <index>`

Example:
>delete 1


## Find `find`
Finds a task that contains a search term
Syntax:
`find <searchterm>`

Example:
>find book


## Priority `priority`
Tasks have 3 different priority levels: low, medium, high.

Tasks have a default priority of low.

To set priority, use the syntax: `priority <index> <priority>`
>priority 1 high

## Bye `bye`
Exits the program.

Syntax: `bye`


##Command Summary

[todo](#todo-todo): `todo <task>`

[event](#events-event): `event <title> /at YYYY/MM/DD HH:MM`

[deadline](#deadlines-deadline): `deadline <title> /by YYYY/MM/DD HH:MM`

[mark](#markunmark): `mark <index>`

[unmark](#markunmark): `unmark <index`

[list](#list-list): `list`

[delete](#delete-delete): `delete <index>`

[find](#find-find): `find <term>`

[priority](#priority-priority): `priority <index> <priority>`

[bye](#bye-bye): `bye`