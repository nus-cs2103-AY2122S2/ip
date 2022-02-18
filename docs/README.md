# DatoDato User Guide

## Introduction

DatoDato is a personal assistant bot that allows you to manage your taskings.

## Setup

1. [Download](https://openjdk.java.net/projects/jdk/11/) and install OpenJRE 11 if you do not have a working installation.
2. Download the latest JAR release of DatoDato from the GitHub project [release page] (https://github.com/laughingkid-sg/ip/releases).
3. Run the JAR file to start. 

```
💡 Please note that the application requires read/write access to the directory it is running in.
```

## Features

### Taskings
DatoDato supports 3 different kinds of tasks (ToDo, Event, Deadline). You can simply add, view or remove any of these 3 tasks easily with the GUI interface. Furthermore, DatoDatao allows you to mark your task as complete. 

### Persistent Storage
DataData autosave your task list so that the next time you run it, it will remember everything for you.

### Search by Keywords 
Looking for a specific task? Easily use the search function to find what you need.

### Tasks Reminders
DatoDato is able to remind you about upcoming deadline tasks in a specific number of days.

### Date and Time Support
Have a problem selecting a date-time? DatoDato supports only the most common date time format to allow you to remain consistent through your taskings.

## Usage

> The usage of the bot commands can also be browsed using the `help` command in the bot. 

### Section 1 - Adding new taskings

There are three types of tasks. They are as follows

```
💡 The first pair brackets in the output signifies the type of the task, Todo - T, Event - E and Deadline - D. 
```

### `todo`

Todo represents simple tasks with only the description field.

Usage: `todo <description>` 

Example: `todo eat lunch with mum`

Expected outcome:
```
Got it. I've added this task:
  [T][] eat lunch with mum
Now you have 1 task on the list.
```

### `event`

The event represents tasks happening at a certain time with the description field.

Usage: `event <description> /at <DD-MM-YYYY HH:MM>`

Example: `event project meeting /at 30-05-2023 13:59`

Expected outcome:
```
Got it. I've added this task:
  [E][] project meeting (at: 30 May 2023 13:59)
Now you have 1 task on the list.
```

### `deadline`

Deadline represents tasks that are due by a certain date time with the description field.

Usage: `deadline <description> /by <DD-MM-YYYY HH:MM>`

Example: `deadline do homework /by 30-05-2023 13:59`

Expected outcome:
```
Got it. I've added this task:
  [D][] do homework (by: 30 May 2023 13:59)
Now you have 1 task on the list.
```

### Section 2 - Listing taskings

There are three ways of listings taskings. They are as follows

### `list`

To list all task(s), use the list command.

Usage: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][] eat lunch with mum
2. [E][] project meeting (at: 30 May 2023 13:59)
3. [D][] do homework (by: 30 May 2023 13:59)
```

### `reminders`

To list all reminder(s) in day range.

Usage: `reminders <days>`

Example: `reminders 7`

Expected outcome:
```
Here are the upcoming deadlines in 7 days:
1. [D][] do homework (by: 30 May 2023 13:59)
```

### `find`

To find specific task(s), use the find command.

Usage: `find <search term>`

Example: `find homework`

Expected outcome:
```
Here are the matching task(s) in your list:
1. [E][] project meeting (at: 30 May 2023 13:59)
```

### Section 3 - Updating Taskings

There are two types of updates you can do to taskings. You can mark a task as done or undone to update its status and you can completely remove a task.

```
💡 Use the `list` command to find the task number
   A task is considered complete when the second pair bracket consists of an X.
   Example
   Incomplete: [D][ ] do homework (by: 30 May 2023 13:59)
   Completed: [D][X] do homework (by: 30 May 2023 13:59)
```

### `done`

To mark a task as complete, use the done command followed by the task number.

Usage: `done <Task Id>`

Example: `done 3`

Expected outcome:
```
Alright! I've updated the task:
  [D][X] do homework (by: 30 May 2023 13:59)
```

### `undo`

To mark a task as incomplete, use the undo command followed by the task number.

Usage: `undo <Task Id>`

Example: `undo 3`

Expected outcome:
```
Alright! I've updated the task:
  [D][ ] do homework (by: 30 May 2023 13:59)
```

### `delete`

To delete a task, use the delete command followed by the task number.

Usage: `delete <Task Id>`

Example: `delete task 3`

Expected outcome:
```
Noted. I've removed this task:
  [D][ ] do homework (by: 30 May 2023 13:59)
Now you have 2 tasks in the list.
```

### Section 4 - Closing the Application

### `bye`

To close the application, use the bye command. Then click the send button.

Usage: `bye`

Expected outcome:
```
Bye! Check out another cool bot @katokatoBot on Telegram.
Hope to see you again soon!
(Click 'Send' to close)
```

---

AY21/22 CS2103T ip by [Goh Zheng Teck](https://github.com/laughingkid-sg/ip) | email: [zhengt@comp.nus.edu.sg](mailto:zhengt@comp.nus.edu.sg)

---
