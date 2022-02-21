# User Guide

---

Athena is a command-line desktop app to help you keep track of different types of tasks in your life.
All changes are **automatically saved** to the hard drive after each command for 100% peace of mind!

<img src="Ui.png" alt="A screenshot of Athena's GUI" width="350"/>

## Quick Start
1. Ensure that you have Java `11` or above installed in your computer.
2. Download the latest version of `athena.jar` from the repository [here](https://github.com/limweiliang/ip).
3. Copy the jar file to the folder you would like to use as the home folder for Athena.
4. Double-click on the jar file to run it!
5. Execute any of the following commands:
   * `list` : List all tasks currently stored by athena
   * `todo task_name`: Add a new todo task with the given `task_name`
   * `event event_name /at mm/dd/yyyy hhmm` : Add a new event
   * `deadline deadline_name /by mm/dd/yyyy hhmm` : Add a new deadline
   * `mark task_number`/`unmark task_number`: Mark/Unmark a task as complete
   * `delete task_number`: Delete a task
   * `find search_phrase` : Find tasks using a search phrase
   * `remind search_key` : Get reminders within a time period
   * `bye`: Quit the app

**Note**: A `data` folder will be created in the same directory to store your task data. If no data is found, 
Athena will create a new task list.

# Feature Guide

---

## Add New Tasks
Athena supports adding 3 different types of tasks to your task list!

### Add a Todo: `todo todo_name`

Adds a new to-do task with `todo_name` to the task list.

### Add an Event: `event event_name /at mm/dd/yyyy hhmm`

Adds a new event with `event_name` and the corresponding date-time to the task list.

Example:`event Ron's Birthday /at 5/2/2022 1330`

### Add a Deadline: `deadline deadline_name /by mm/dd/yyyy hhmm`

Adds a new deadline with `deadline_name` and the corresponding date-time to the task list.

Example:`deadline Pay my taxes /by 21/11/2022 0800`

## Manage Your Existing Tasks
Athena also contains many useful features to manage the tasks that you've added.

### Mark/Unmark Task: `mark task_number`/`unmark task_number`

Marks/unmarks the respective task as having been completed.

Example: `mark 2`

### List all Tasks: `list`

Displays all tasks that are currently in the task list.

### Delete a Task: `delete task_number`

Don't need a task anymore? Use this command to remove it from the task list permanently.

Example: `delete 5`

### Find tasks using keywords: `find search_phrase`

Find specific tasks in the task list by providing a case-sensitive search phrase! A search phrase can have multiple words.

Examples: 
* `find Ron's birthday`
* `find assignment`

### Get reminders for tasks: `remind search_key`
Gets reminders within the `search_key`. The following intuitive search keys are supported:
* today
* tomorrow
* this week
* next week

### Close the app: `bye`
Need I say more?

_Track your tasks better with Athena today!_