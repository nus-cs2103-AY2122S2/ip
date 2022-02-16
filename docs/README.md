# User Guide For YQ Chatbot

*YQ Chatbot simplifies the way you manage your tasks so that you can focus on what's important to you.*

![YQ Chatbot Image](https://yuqitanyq.github.io/ip/Ui.png)

## Table of Contents
1. [How to launch application](#how-to-launch-application)
2. [Features](#features)
3. [Usage](#usage)
    - [Usage example of todo](#usage-example-of-todo)
    - [Usage example of event](#usage-example-of-event)
    - [Usage example of deadline](#usage-example-of-deadline)
    - [Usage example of list](#usage-example-of-list)
    - [Usage example of delete](#usage-example-of-delete)
    - [Usage example of mark](#usage-example-of-mark)
    - [Usage example of unmark](#usage-example-of-unmark)
    - [Usage example of find](#usage-example-of-find)

## How to launch application
1. Make sure that you have Java 11 installed.
2. Choose a desired directory of choice, for example your desktop.
3. Create an empty folder, for example, "chatbot".
4. Place the YQ Chatbot.jar file inside the empty folder.
5. Go to your command line/Terminal, and go to the directory which 
your jar file is located. (In this case, as per step 2, this directory will be the chatbot folder.)
6. Run the command `java -jar YQ\ Chatbot.jar` in command line/Terminal.  

Alternatively, after step 4, you may double-click the YQ Chatbot.jar file to launch the application.
However, do take note for Mac users who want to see the tasks saved in the data folder that will be created,
please follow step 5 and 6 instead.


## Features

### Feature - list tasks 

The `list` feature lists all the tasks that are left to do.

### Feature - todo tasks

The `todo` feature allows the addition of a task to be done.

### Feature - event tasks

The `event` feature allows the addition of an event that needs to be attended.

### Feature - deadline tasks

The `deadline` feature allows the addition of a task with a stipulated deadline.

### Feature - delete tasks

The `delete` feature allows a particular task in the list to be deleted.

### Feature - mark tasks

The `mark` feature allows a particular task in the list to be marked as done.

### Feature - unmark tasks

The `unmark` feature allows a particular task in the list to be unmarked as not done.

### Feature - find tasks

The `find` feature allows a particular task in the list to be searched for. 
Relevant results from the lists will be shown.

### Feature - closing the application

The `bye` command closes the application.

### Feature - Saving tasks

The chatbot automatically saves the tasks, even after closing the application.

### Feature - Retrieving past tasks

The chatbot automatically retrieves the past tasks in the list when the application is restarted again.

### Feature - Detecting duplicate task being added

The chatbot automatically checks if the task being added already exists in the list.
If it exists, it will not add it.

## Usage  

### Usage example of todo 
todo - `todo Watch lectures`

Expected outcome:
Adds a task to be done.
```
Got it. I've added this task:
  [T][ ] Watch lectures
Now you have 1 tasks in the list.
```  
### Usage example of event
event - `event Attend meeting /at 2022-02-15`

Expected outcome:
Adds an event to be attended.
```
Got it. I've added this task:
  [E][ ] Attend meeting (at: Feb 15 2022)
Now you have 2 tasks in the list.
```
### Usage example of deadline
deadline - `deadline read book /by 2022-02-16`

Expected outcome:
Adds a task with a stipulated deadline.
```
Got it. I've added this task:
  [D][ ] read book (by: Feb 16 2022)
Now you have 3 tasks in the list.
```
### Usage example of list
list - `list`

Expected outcome:
Adds an event to be attended.
```
Here are the tasks in your list:
1.[T][ ] Watch lectures
2.[E][ ] Attend meeting (at: Feb 15 2022)
3.[D][ ] read book (by: Feb 16 2022)
```
### Usage example of delete
delete - `delete 1`

Expected outcome:
Deletes the first event.
```
Noted. I've removed this task:
1.[T][ ] Watch lectures
Now you have 2 tasks in the list.
```
### Usage example of mark
mark - `mark 1`

Expected outcome:
Marks the first task as done.
```
Nice! I've marked this task as done:
[E][X] Attend meeting (at: Feb 15 2022)
```
### Usage example of unmark
unmark - `unmark 1`

Expected outcome:
Unmarks the first task as not done.
```
OK, I've marked this task as not done yet:
[E][] Attend meeting (at: Feb 15 2022)
```
### Usage example of find
find - `find meeting`

Expected outcome:
Returns the first task as it is a relevant search.
```
Here are the matching tasks in your list:
1.[E][] Attend meeting (at: Feb 15 2022)
```