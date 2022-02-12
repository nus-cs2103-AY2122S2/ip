# User Guide

Burp is a desktop app for managing your tasks, optimised for use via a Command Line Interface while still having the benefits of a Graphical User Interface.  
You will be able to manage your to-do lists much easier if you can type fast using Burp!

## Features 
- [Add Tasks](#add-tasks)
  - Generic to-dos
  - Events
  - Deadlines
- [Deleting Tasks](#deleting-tasks)
- [Marking of Tasks](#marking-of-tasks)
  - Mark as done
  - Mark as not done
- [List Tasks](#listing-tasks)
- [Find Tasks](#finding-tasks)
- [Bye](#bye)


### Add Tasks

Burp has 3 different types of Tasks. These are To-Dos, Events, and Deadlines.  
To-Dos are generic tasks that only have a description of what it is.  
Events are things such as "family dinner", and they have a location, and an optional time.  
Deadlines are things such as "homework", and they have a deadline, and optional time.


#### `todo` - adds a new ToDo Task

This tells Burp to add a new To-Do task.

Example of usage:

`todo Math homework`

Expected outcome:

A new To-Do task with the description, "Math homework" will be added to your list.

```
Got it. I've added this task:  
[T][] Math homework  
Now you have 1 tasks in the list.
```
------------------------------------------
#### `event` - adds a new Event Task

This tells Burp to add a new Event task.

Example of usage:

`event Family dinner /at Home, 7pm`

Expected outcome:

A new Event task with the description, "Family dinner" with location "Home" and time "7pm" will be added to your list.

```
Got it. I've added this task:  
[E][] Family dinner (at Home, 7pm)
Now you have 2 tasks in the list.
```
---------------------------------------------
#### `deadline` - adds a new Deadline Task

This tells Burp to add a new Deadline task.

Example of usage:

`deadline Thank you letter (for interview) /by 13/02/2022 1600`

Expected outcome:

A new deadline task with the description, "Thank you letter (for interview)", with a deadline by 13/02/2022, 1600hrs will be added to your list.

```
Got it. I've added this task:  
[D][] Thank you letter (for interview) (by 13-02-2022, 4pm) 
Now you have 3 tasks in the list.
```
--------------------
### Deleting Tasks

You can ask Burp to delete tasks you no longer wish to keep track of.

#### `delete` - deletes a task in the list

This tells Burp to delete a particular task, by index.

Example of usage:

`delete 3`

Expected outcome:

Deletes task at index 3 in your list, and tells you how many tasks are left

```
Noted. I've removed this task:
[D][] Thank you letter (for interview) (by 13-02-2022, 4pm) 
Now you have 2 tasks in the list.
```
---------------
### Marking of Tasks

You can ask Burp to mark tasks as being done, or if certain requirements change at any point, Burp can also help you to mark these tasks as being **not** done


### `mark` - marks a task

This tells Burp to mark a task as done.

Example of usage:

`mark 1`

Expected outcome:

The first task in the list will be marked as done.

```
OK, I've marked this task as done:  
[T][X] Math homework  
```
------------------------
#### `unmark` - marks a task

This tells Burp to mark a task as **not done**.

Example of usage:

`unmark 1`

Expected outcome:

The first task in the list will be marked as done.

```
OK, I've marked this task as not done yet:  
[T][] Math homework  
```
------------------
### Listing Tasks

You can ask Burp to list out all the tasks in your current to-do list.

#### `list` - deletes a task in the list

Burp will list out all items in your to-do list.

Example of usage:

`list`

Expected outcome:

Deletes task at index 3 in your list, and tells you how many tasks are left

```
(Total 2) Here's what you have:
[T][] Math homework
[E][] Family dinner (at Home, 7pm)
```
--------------------------
### Finding Tasks

You can search for certain tasks in your to-do list

#### `find` - finds all tasks matching to a search term in the to-do list

Burp lists out all items matching the given search term

Example of usage:

`find dinner`

Expected outcome:

Finds all items in the to-do list matching the search term "dinner".   
If there is nothing, then Burp will tell you there is no terms matching the given search term. 

```
Here are the matching tasks in your list:
[E][] Family dinner (at Home, 7pm)
```
--------------------------
### Bye

Tells Burp to shut down

#### `bye` - closes burp

Burp will shutdown and close

Example of usage:

`bye`

Expected outcome:

The Graphical User Interface will close.

--------------------------