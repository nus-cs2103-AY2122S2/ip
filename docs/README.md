# DatoDato User Guide

## Introduction

DatoDato is a personal assistant bot that allows you to management your taskings.

## Features

- Welcome and closing messages
- Task creation and listing
- Task completion tracking
- Task classifation (ToDo, Event, Deadline)
- Error handling
- Task deleteion
- Saving and loading of task(s) from local presistent stroage
- Date and time formatting for tasks
- Search of keywords in task
- Grahpics User Inference
- List upcoming tasks in given days

## Usage

> The usage of the bot commands can also be browsed using the `help` command in the bot. 

### Section 1 - Adding new taskings

There are three types of task. They are as follows

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
  [T][] eat lnch with mum
Now you have 1 tasks in the list.
```

### `event`

Event represents tasks happening at a certain time with the description field.

Usage: `event <description> /at <DD-MM-YYYY HH:MM>`

Example: `event project meeting /at 30-05-2023 13:59`

Expected outcome:
```
Got it. I've added this task:
  [E][] project meeting (at: 30 May 2023 13:59)
Now you have 1 tasks in the list.
```

### `deadline`

Deadline represents tasks that are due by certain date time with the description field.

Usage: `deadline <description> /by <DD-MM-YYYY HH:MM>`

Example: `deadline do homework /by 30-05-2023 13:59`

Expected outcome:
```
Got it. I've added this task:
  [D][] do homework (by: 30 May 2023 13:59)
Now you have 1 tasks in the list.
```

### Section 2 - Listing taskings

There are three ways of listings taskings. They are as follows

### `list`

To list all task(s), use the list command.

Usage: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][] eat lnch with mum
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

To find specifics task(s), use the find command.

Usage: `find <search term>`

Example: `find homework`

Expected outcome:
```
Here are the matching task(s) in your list:
1. [E][] project meeting (at: 30 May 2023 13:59)
```

### Section 3 - Updating Taskings

There are two types of updates you can do to taskings. You can mark a task as done or undone to update it status and you can completely remove a task.

```
💡 Use the `list` command to find the task number
   A task is consider complete when the second pair bracket consist of a X.
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
Bye! Checkout another cool bot @katokatoBot on Telegram.
Hope to see you again soon!
(Click 'Send' to close)
```

---

AY21/22 CS2103T ip by [Goh Zheng Teck](https://github.com/laughingkid-sg/ip) | email: [zhengt@comp.nus.edu.sg](mailto:zhengt@comp.nus.edu.sg)

---
