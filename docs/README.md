# User Guide
Welcome to the user guide for Poogie! Poogie is **a desktop app for tracking your daily tasks, optimised for use via 
Command-Line Interface (CLI)**. Poogie helps you in your everyday life by acting as your personal assistant, such that 
you can focus on the things that matter.

## Quick Start
1. If you do not have Java installed on your computer, head down to the 
[Java download site](https://www.java.com/en/download/) and follow the instructions to get Java version 11 and above
installed on your system.
2. Once you have Java installed, grab a copy of the latest JAR file for Poogie
[here](https://github.com/tobihy/ip/releases).
3. Double-click on the JAR file to get started! A program should appear in a few seconds.

## Features

### Add/delete tasks

Add and delete tasks that you wish to track.

### Mark/unmark tasks as done

Cleared a deliverable? Check it off your list!

### List tasks

List out all your tasks and gain a bird's-eye view of your schedule.

### Find tasks

Using keywords, search through your list of tasks and never lose track of your schedule.

### Edit tasks

Made a mistake? Fret not, make the appropriate changes easily.

## Usage
Arguments in Egyptian braces `{}` are to be **supplied by the user**.

### `todo` - add a *todo*

Add a *todo* to your list of tasks.

Format: `todo {description of task}`

Examples of usage:
- `todo clean up room`

### `deadline` - add a *deadline*

Add a *deadline* to your list of tasks. 

You must provide Poogie with either date and time, date or time in the following
formats:
- `yyyy-mm-dd HHmm` if including date and time, where HHmm is the time in 24-hour format
- `yyyy-mm-dd` if including only date
- `HHmm` if including only time, where HHmm is the time in 24-hour format

Format: `deadline {description of deadline} /by {date and/or time}`

Examples of usage:
- `deadline submit report /by 2022-03-05 1300`
- `deadline cook dinner /by 1900`
- `deadline return book to library /by 2022-02-22`

### `event` - add an *event*

Add an *event* to your list of tasks.

You must provide Poogie with either date and time, date or time in the following
formats:
- `yyyy-mm-dd HHmm` if including date and time, where HHmm is the time in 24-hour format
- `yyyy-mm-dd` if including only date
- `HHmm` if including only time, where HHmm is the time in 24-hour format

Format: `event {description of event} /at {date and/or time}`

Examples of usage:
- `event project zoom meeting /at 2022-02-18 2000`
- `event sam's birthday /at 2022-12-26`
- `event visit to ryan's house /at 1830`

### `mark` - mark a task as done

Mark a task as done in your list of tasks.

Format: `mark {task number}`

### `unmark` - mark a task as undone

Mark a task as undone in your list of tasks.

Format: `unmark {task number}`

### `delete` - delete a task

Delete a task from your list of tasks.

Format: `delete {task number}`

### `edit` - edit a task

Edit a task on your list of tasks.

For edits concerning date and/or time, you must provide them in the following formats:
- `yyyy-mm-dd HHmm` if including date and time, where HHmm is the time in 24-hour format
- `yyyy-mm-dd` if including only date
- `HHmm` if including only time, where HHmm is the time in 24-hour format

Note that you can only make a **single edit** at a time. (multiple edits coming soon!)

Format:
- `edit {task number} desc/{new description of task}`
- `edit {task number} dt/{date and/or time}`

### `bye` - exit the program

Exits the program.

Format: `bye`