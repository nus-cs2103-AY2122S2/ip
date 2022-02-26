# User Guide

Duke is a **personal assistant chatbot desktop application** that can help you keep track of 3 types of tasks.

* ToDo: Tasks without any date/time attached
* Deadline: Tasks that need to be done before a specific date/time
* Event: Tasks that start at a specific date/time and ends at a specific time

It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

![](Ui.png)

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have `Java 11` installed on your Computer.
2. Download the latest release of `duke.jar` from [here](https://github.com/dannytayjy/ip/releases).
3. Copy the file to the folder you want to use as the home folder for the chatbot application.
4. Double-click the file to start the app.
5. Type the command in the text box and press Enter or click on the Send button to execute it. Some example commands you can try:
   * `list` : Lists all tasks.
   * `todo Clean Up Bedroom` : Adds a ToDo task with description `Clean Up Bedroom` to the task list. .
   * `bye` : Exits the chatbot application.
7. Refer to the [Features](#features) below for details of each command.

## Features

### Viewing help : `help` ###

Shows a message with the available commands and the respective formats.

Format: `help`

### Adding a ToDo task : `todo`

Adds a ToDo task to the task list.

Format: `todo <description>`

* `description` refers to the description of the ToDo task.

Example of usages and outcomes:

* `todo Clean Up Bedroom`

  ```
  Got it. I've added this task:
       [T][ ] Clean Up Bedroom
  Now you have 1 task in the list.
  ```
  
* `todo Wash Laundry`

  ```
  Got it. I've added this task:
       [T][ ] Wash Laundry
  Now you have 2 tasks in the list.
  ```

### Adding a Deadline task : `deadline`

Adds a Deadline task to the task list.

Format: `deadline <description> /by <date time>`
* `description` refers to the description of the Deadline task.
* `date time` refers to the date and time by which the Deadline task needs to be completed.
* `date time` can be in any format that the user prefers.
* For proper recognition of `date` and `time`,
  * `date` has to be entered first, followed by `time`.
  * `date` has to be in the format `d/yyyy-MM-dd` (e.g. `d/2022-01-21`).
  * `time` has to be in the format `hhmm` (e.g. `t/1800`) and can be optional.

Example of usages and outcomes:

* `deadline CS3240 G1 /by Week 6 Saturday 19 Feb 2pm`

  ```
  Got it. I've added this task:
       [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  Now you have 3 tasks in the list.
  ```

* `deadline Pay Town Council S&C Charges on AXS app /by d/2022-02-15`

  ```
  Got it. I've added this task:
       [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  Now you have 4 tasks in the list.
  ```

* `deadline CS2102 Week 6 Tutorial Q3b /by d/2022-02-15 t/0900`

  ```
  Got it. I've added this task:
       [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  Now you have 5 tasks in the list.
  ```

### Adding an Event task : `event`

Adds an Event task to the task list

Format: `event <description> /at <date time>`

* `description` refers to the description of the Event task.
* `date time` refers to the date and time at which the Event task is taking place.
* `date time` can be in any format that the user prefers.
* For proper recognition of `date` and `time`,
  * `date` has to be entered first, followed by `time`.
  * `date` has to be in the format `d/yyyy-MM-dd` (e.g. `d/2022-01-21`).
  * `time` has to be in the format `hhmm` (e.g. `t/1800`) or `hhmm-hhmm` (e.g. `t/1800-2000`) and can be optional.

Example of usages and outcomes:

* `event CS3230 Midterm Exam /at Week 7 Thursday 3 Mar 2pm`

  ```
  Got it. I've added this task:
       [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  Now you have 6 tasks in the list.
  ```

* `event Staycation /at d/2022-02-23`

  ```
  Got it. I've added this task:
       [E][ ] Staycation (at: Feb 23 2022)
  Now you have 7 tasks in the list.
  ```

* `event Meet Jason at Jewel for Lunch /at d/2022-02-20 t/1200`

  ```
  Got it. I've added this task:
       [E][ ] Meet Jason at Jewel for Lunch (at: Feb 20 2022, 12:00 PM)
  Now you have 8 tasks in the list.
  ```

* `event CS2102 Project Part 1 Evaluation /at d/2022-02-21 t/1700-1730`

  ```
  Got it. I've added this task:
       [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  Now you have 9 tasks in the list.
  ```

### Listing all tasks : `list`

Shows a list of all the tasks and their completion status.
* `[✓]` indicates the task is marked as done
* `[ ]` indicates the task is not marked as done yet

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
[Legend: T = todo, D = deadline, E = event]

1.   [T][ ] Clean Up Bedroom
2.   [T][ ] Wash Laundry
3.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
4.   [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
5.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
6.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
7.   [E][ ] Staycation (at: Feb 23 2022)
8.   [E][ ] Meet Jason at Jewel for Lunch (at: Feb 20 2022, 12:00 PM)
9.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
```

### Printing deadlines/events that occurs on a specific date : `print`

Prints deadlines/events occurring on a specific date.

Format: `print /on <date>`
* The search is date-sensitive, i.e., it can only search for tasks having `date` in the format of `yyyy-MM-dd` (e.g. `2022-01-21`).

Expected usage and outcome:

* `print /on 2022-02-15`

  ```
  Here is the task on this date (Feb 15 2022):
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

### Locating tasks by keyword: `find`

Finds existing tasks whose description contains the given keyword.

Format: `find <keyword>`
* The search is case-insensitive, i.e., `cs` will match `CS` and vice-versa.
* The search should contain only one keyword.
* The search will find any tasks whose descriptions contain the keyword partially.

Example of usage and outcome:

* `find cs`

  ```
  Here is the matching task in your list:
  [Keyword Search: cs]
  [Legend: T = todo, D = deadline, E = event]

  1.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  3.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  4.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```

### Marking a task as done : `mark`

Marks the specified task in the task list as done with a check mark. By default, tasks when added are initially marked as undone.

Format `mark INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples of usage and outcomes:

* `list` followed by `mark 2` marks the second task in the task list as done.

  ```
  Here are the tasks in your list:
  [Legend: T = todo, D = deadline, E = event]
    
  1.   [T][ ] Clean Up Bedroom
  2.   [T][ ] Wash Laundry
  3.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  4.   [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  5.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  6.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  7.   [E][ ] Staycation (at: Feb 23 2022)
  8.   [E][ ] Meet Jason at Jewel for Lunch (at: Feb 20 2022, 12:00 PM)
  9.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```

  ```
  Nice! I've marked this task as done:
       [T][✓] Wash Laundry
  ```

* `print /on 2022-02-15` followed by `mark 1` marks the first task in the results of the `print` command as done.

  ```
  Here is the task on this date (Feb 15 2022):
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

  ```
  Nice! I've marked this task as done:
       [D][✓] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  ```

  ```
  [FILTERED TASKS]

  Here is the task on this date (Feb 15 2022):
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][✓] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

* `find cs` followed by `mark 2` marks the second task in the results of the `find` command as done.

  ```
  Here is the matching task in your list:
  [Keyword Search: cs]
  [Legend: T = todo, D = deadline, E = event]

  1.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  3.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  4.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```
  
  ```
  Nice! I've marked this task as done:
       [D][✓] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

  ```
  [FILTERED TASKS]

  Here is the matching task in your list:
  [Keyword Search: cs]
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  2.   [D][✓] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  3.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  4.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```

### Marking a task as not done yet : `unmark`

Marks the specified task in the task list as not done yet by removing the check mark.

Format `unmark INDEX`

* Marks the task at the specified `INDEX` as not done yet.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples of usage and outcomes:

* `list` followed by `unmark 2` marks the second task in the task list as not done yet.

  ```
  Here are the tasks in your list:
  [Legend: T = todo, D = deadline, E = event]
    
  1.   [T][ ] Clean Up Bedroom
  2.   [T][✓] Wash Laundry
  3.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  4.   [D][✓] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  5.   [D][✓] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  6.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  7.   [E][ ] Staycation (at: Feb 23 2022)
  8.   [E][ ] Meet Jason at Jewel for Lunch (at: Feb 20 2022, 12:00 PM)
  9.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```
  
  ```
  OK! I've marked this task as not done yet:
       [T][ ] Wash Laundry
  ```

* `print /on 2022-02-15` followed by `unmark 1` marks the first task in the results of the `print` command as not done yet.

  ```
  Here is the task on this date (Feb 15 2022):
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][✓] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  2.   [D][✓] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

  ```
  OK! I've marked this task as not done yet:
       [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  ```

  ```
  [FILTERED TASKS]

  Here is the task on this date (Feb 15 2022):
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  2.   [D][✓] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

* `find cs` followed by `unmark 2` marks the second task in the results of the `find` command as done.

  ```
  Here is the matching task in your list:
  [Keyword Search: cs]
  [Legend: T = todo, D = deadline, E = event]

  1.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  2.   [D][✓] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  3.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  4.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```

  ```
  OK! I've marked this task as not done yet:
       [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

  ```
  [FILTERED TASKS]

  Here is the matching task in your list:
  [Keyword Search: CS]
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  3.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  4.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples of usage and outcomes:

* `list` followed by `delete 2` deletes the second task in the task list.

  ```
  Here are the tasks in your list:
  [Legend: T = todo, D = deadline, E = event]
    
  1.   [T][ ] Clean Up Bedroom
  2.   [T][ ] Wash Laundry
  3.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  4.   [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  5.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  6.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  7.   [E][ ] Staycation (at: Feb 23 2022)
  8.   [E][ ] Meet Jason at Jewel for Lunch (at: Feb 20 2022, 12:00 PM)
  9.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```

  ```
  Noted! I've removed this task:
       [T][ ] Wash Laundry
  Now you have 8 tasks in the list.
  ```

* `print /on 2022-02-15` followed by `delete 1` deletes the first task in the results of the `print` command.

  ```
  Here is the task on this date (Feb 15 2022):
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

  ```
  Noted! I've removed this task:
       [D][ ] Pay Town Council S&C Charges on AXS app (by: Feb 15 2022)
  Now you have 7 tasks in the list.
  ```

  ```
  [FILTERED TASKS]

  Here is the task on this date (Feb 15 2022):
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  ```

* `find cs` followed by `delete 2` marks the second task in the results of the `find` command.

  ```
  Here is the matching task in your list:
  [Keyword Search: cs]
  [Legend: T = todo, D = deadline, E = event]

  1.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  3.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  4.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```

  ```
  Noted! I've removed this task:
       [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
  Now you have 7 tasks in the list.
  ```

  ```
  [FILTERED TASKS]

  Here is the matching task in your list:
  [Keyword Search: cs]
  [Legend: T = todo, D = deadline, E = event]
  
  1.   [D][ ] CS3240 G1 (by: Week 6 Saturday 19 Feb 2pm)
  2.   [E][ ] CS3230 Midterm Exam (at: Week 7 Thursday 3 Mar 2pm)
  3.   [E][ ] CS2102 Project Part 1 Evaluation (at: Feb 21 2022, 05:00 PM to 05:30 PM)
  ```
  
### Exiting the program : `bye`

Exits the program in 10 seconds.

Format: `bye`

### Saving the data

Duke's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command Summary

| Action       | Format                                   | Examples                                                              |
|:-------------|:-----------------------------------------|:----------------------------------------------------------------------|
| **todo**     | `todo <description>`                     | `todo Clean Up Bedroom`                                               |
| **deadline** | `deadline <description> /by <date time>` | `deadline CS2102 Week 6 Tutorial Q3b /by d/2022-02-15 t/0900`         |
| **event**    | `event <description> /at <date time>`    | `event CS2102 Project Part 1 Evaluation /at d/2022-02-21 t/1700-1730` |
| **list**     | `list`                                   |                                                                       |
| **print**    | `print /on <date>`                       | `print /on 2022-02-15`                                                |
| **find**     | `find KEYWORD`                           | `find cs`                                                             |
| **mark**     | `mark INDEX`                             | `mark 2`                                                              |
| **unmark**   | `unmark INDEX`                           | `unmark 2`                                                            |
| **delete**   | `delete INDEX`                           | `delete 2`                                                            |
| **help**     | `help`                                   |                                                                       |
| **bye**      | `bye`                                    |                                                                       |
