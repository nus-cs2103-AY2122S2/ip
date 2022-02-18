# User Guide

## Table of Contents
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)\
   3.1. [Adding A Task: `todo`, `deadline`, `event`](#31-adding-new-task)\
   3.2. [Listing Tasks: `list`](#32-listing-task)\
   3.3. [Marking A Task As Done/Not Done: `mark`, `unmark`](#33-marking-tasks-as-done-or-not-done)\
   3.4. [Deleting A Task: `delete`](#34-deleting-task)\
   3.5. [Finding Tasks: `find`](#35-finding-tasks)\
   3.6. [Viewing the schedule: `view`](#36-viewing-the-schedule)
4. [Usage](#4-usage)
5. [FAQ](#5-faq)

## 1. Introduction
- Duke is an Assistant Chatbot that helps user to keep track of task.   
- Duke is optimised for those who prefer typing and can type fast.

## 2. Quick Start

1. Ensure you have Java `11` installed in your computer
2. Download the latest `duke.jar` [here](https://github.com/hieunm1821/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as _home folder_ for Duke.
4. Launch the `jar` file using `java -jar duke.jar` to start the app.
5. Type the valid command into the chat box.

## 3. Features 

### 3.1. Adding new task

You can add and delete 3 different types of task.  
Duke supports `Todo`, `Deadline` and `Event` tasks. Each type is denoted by its prefix (`[T]`, `[D]` or `[E]`).  
`Deadline` and `Event` must include the time information.

### 3.2. Listing task

Lists all tasks stored in Duke with numbering according to the order they are added (1-based index).

### 3.3. Marking tasks as done or not done:

You can mark the task as done/not done.  
Task completion is denoted by `[ ]` and `[X]` which indicates the task has not been done or has been done, respectively.

### 3.4. Deleting task 

Deletes the specified task from the tasks list.

### 3.5. Finding tasks:

Finds the tasks of which description contains the given keyword.

### 3.6. Viewing the schedule:

Views the schedule for a specified day.

## 4. Usage

### 4.1. `todo` - Add a new todo task 

Adds a new task with only description and returns confirmation and number of tasks in list.

Format: `todo DESCRIPTION`

Example of usage: `todo cooking`

Expected outcome:

```
Got it. I've added this task:
[T][] cooking
Now you have 6 tasks in the list.
```
### 4.2. `deadline` - Add a new deadline task

Adds a new task with description and deadline and returns confirmation and number of tasks in list.

Format: `deadline DESCRIPTION /by YYYY-MM-DD HHMM`

Example of usage: `deadline ip week 6 /by 2022-02-20 2359`

Expected outcome:

```
Got it. I've added this task:
[D][ ] ip week 6  (by: Feb 20 2022 23:59)
Now you have 7 tasks in the list.
```

### 4.3. `deadline` - Add a new deadline task

Adds a new task with description and a date time of the event and returns confirmation and number of tasks in list.

Format: `event DESCRIPTION /at YYYY-MM-DD HHMM`

Example of usage: `event project meeting /at 2022-02-20 1700`

Expected outcome:

```
Got it. I've added this task:
[E][ ] project meeting  (at: Feb 20 2022 17:00)
Now you have 8 tasks in the list.
```

### 4.4. `list` - Listing tasks 

Returns a list of tasks  

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] ip week 4  (by: Feb 18 2022 10:00)
3.[E][ ] project meeting  (at: Feb 18 2022 14:00)
4.[E][ ] breakfast  (at: Feb 18 2022 09:00)
5.[E][ ] cohesion  (at: Feb 17 2022 17:00)
6.[D][ ] ip week 6  (by: Feb 20 2022 23:59)
7.[E][ ] project meeting  (at: Feb 20 2022 17:00)
```

### 4.5. `mark` - Marking a task as done 

Marks a task with specified index from the list and return confirmation.

Format: `mark INDEX`

Example of usage: `mark 5`

Expected outcome:

```
Nice! I've marked this task as done:
[E][X] cohesion  (at: Feb 17 2022 17:00)
```

### 4.6. `unmark` - Marking a task as not done

Marks a task with specified index from the list and return confirmation.

Format: `unmark INDEX`

Example of usage: `unmark 5`

Expected outcome:

```
OK, I've marked this task as not done yet:
[E][ ] cohesion  (at: Feb 17 2022 17:00)
```

### 4.7. `delete` - Deletes a task

Deletes a task with specified index from the list and return confirmation.

Format: `delete INDEX`

Example of usage: `delete 5`

Expected outcome:

```
Noted. I've removed this task:
[E][ ] cohesion  (at: Feb 17 2022 17:00)
Now you have 6 tasks in the list.
```

### 4.8. `find` - Finding tasks

Returns list of tasks which descriptions have the given keyword.

Format: `find KEYWORD`

Example of usage: `find ip`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][ ] ip week 4  (by: Feb 18 2022 10:00)
2.[D][ ] ip week 6  (by: Feb 20 2022 23:59)
```

### 4.9. `view` - Viewing schedule

Returns list of tasks in a specified day.

Format: `view YYYY-MM-DD`

Example of usage: `view 2022-02-18`

Expected outcome:

```
Here is your schedule for Feb 18 2022:
09:00 breakfast 
10:00 ip week 4 
14:00 project meeting 
```

### 4.10. `bye` - Exiting Duke

Exits the program.

Format: `bye`


## 5. FAQ

Below are the answers to some of frequently asked questions about Duke.

**Q:** What will happen to my data if Duke crashed?\
**A:** Data is saved automatically to `data/duke.txt` whenever you change your tasks. Therefore, your data will be safely saved even though Duke is crashed.

**Q:** Can I exit Duke without using the `bye` command?\
**A:** Yes, you can. As mentioned above, your data will be saved automatically if a change of the tasks occurs, so you can exit Duke worry-free.