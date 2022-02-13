# User Guide
## Introduction
Duke is a task managing chatbot that frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

## Quick Start
1. Ensure you have Java 11 or above installed on your Computer
2. Download the latest duke.jar release from the repo
3. Double-click on the duke.jar to launch the application
4. Start by interacting with any commands
5. Exit the application with the "bye" command

## Features

### Add a task
Add up to 3 different types of tasks!  
A todo, event or deadline.
### View all tasks
View the list of tasks you have created and keep them on track!
### Update your tasks
Mark and unmask your tasks as completed.
### Remove a task
Delete a task that is no longer needed.
### Search for tasks
Too many tasks to handle?  
Easily search for tasks using a keyword!

### Reminders
Always reminds you of incomplete deadlines and upcoming events.

## Usage

### `list` - Display all the tasks
Shows all your existing tasks on the application.

Example of usage:  
`list`  

Expected outcome:
```
Here are the tasks in your list:
1.[E][] end of year party(at: Dec 31 2022 23:59)
2.[D][] homework(by: May 2 2022 23:59)
```
***
### `todo` - Add a todo
Adds a todo to your list.

Example of usage:  
`todo shop for books` 

Expected outcome:
```
Got it. I have added this task:
[T][] shop for books
Now you have 3 tasks.
```
***
### `event` - Add an event
Adds an event task to your lists.  
The input format for date and time is `dd/MM/yyyy HH:mm`.  
Description and date with time is separated with the `/at` keyword.

Example of usage:  
`event housewarming /at 15/03/2022 13:00`  

Expected outcome:
```
Got it. I have added this task:
[E][] housewarming(at: Mar 15 2022 13:00)
Now you have 4 tasks.
```
***
### `deadline` - Add a deadline
Adds a deadline task to your lists.  
The input format for date and time is `dd/MM/yyyy HH:mm`.  
Description and date with time is separated with the `/by` keyword.

Example of usage:  
`deadline 2103 ip /by 18/02/2022 23:59`

Expected outcome:
```
Got it. I have added this task:
[D][] 2103 ip(by: Feb 18 2022 23:59)
Now you have 5 tasks.
```
***
### `mark` - Add a deadline
Marks a task as done with "X" using the task index from the list.

Example of usage:  
`mark 5`

Expected outcome:
```
I have marked this as done.
[D][X] 2103 ip(by: Feb 18 2022 23:59)
```
***
### `unmark` - Add a deadline
Unmarks a task by removing "X" using the task index from the list.

Example of usage:  
`unmark 5`

Expected outcome:
```
I have unmarked this task.
[D][] 2103 ip(by: Feb 18 2022 23:59)
```
***
### `delete` - Delete a task
Deletes a task from the list.

Example of usage:  
`delete 5`

Expected outcome:
```
Noted. I've removed this task:
[D][] 2103 ip(by: Feb 18 2022 23:59)
Now you have 4 tasks.
```
***
### `find` - Search for a task
Searches for tasks using a keyword.

Example of usage:  
`find shop`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][] shop for books
2.[T][] shop for shirts 
```
*** 
### `bye` - Exit the program
Exit the application after a 1 second delay.

Example of usage:  
`bye`

Expected outcome:
```
don't leave me don't leave me.
```
***
### Managing data
* All data used in Duke's program will be saved in a text file at `/ip/data/duke.txt`.
* To transfer your data to another device, please copy the `duke.txt` file from the current device 
and paste it in the same directory `/ip/data/` of the new device.
* If data is corrupted, please delete the `duke.txt` file and restart the program.