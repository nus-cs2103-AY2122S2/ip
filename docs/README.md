![Ui png](https://user-images.githubusercontent.com/70706659/154679760-3c8fa8cb-f3cc-4242-9c6b-a3f9c9310ceb.PNG)


# Duke

A desktop task management application that has the benefits of a Graphical User Interface (GUI) while being optimized for usage like a Command Line Interface (CLI). This application makes managing your task as easy as counting from 1 to 10.

## Setup

1. Ensure that you have installed Java 11 in your computer.
2. Download the latest release here.
3. Copy the .jar file into the folder you've chosen as the application's home folder.
4. Double-click the .jar file to start the application.

## Features
- Add tasks
- Delete tasks
- View tasks
- Mark/Unmark tasks as done
- Find tasks
- Update tasks

## Usages
Tasks are categorized as either a Todo, Event or Deadline.

### Add Todo
Format:
- `todo TASK_DESC`
- TASK_DESC cannot be empty.

Example:
- `todo study for quiz`

### Add Event
Format:
- `event TASK_DESC /at YYYY-MM-DD`
- TASK_DESC and YYYY-MM-DD cannot be empty.

Example:
- `event engineering quiz /at 2022-03-03`

### Add Deadline
Format:
- `deadline TASK_DESC /by YYYY-MM-DD`
- TASK_DESC and YYYY-MM-DD cannot be empty.

Example:
- `deadline engineering homework /by 2022-03-05`

### Mark task
Format:
- `mark TASK_INDEX`
- TASK_INDEX cannot be empty.

Example:
- `mark 2`

### Unmark task
Format:
- `ummark TASK_INDEX`
- TASK_INDEX cannot be empty.

Example:
- `ummark 2`

### Delete task
Format:
- `delete TASK_INDEX`
- TASK_INDEX cannot be empty.

Example:
- `delete 3`

### Find task
Format:
- `find SEARCH_TERM`
- SEARCH_TERM cannot be empty.

Example:
- `find homework`

### Update task
Can update either only the task description or only the date or both. <br/>

Format: <br/>

Update only task description
- `update TASK_INDEX TASK_DESC`
- TASK_INDEX and TASK_DESC cannot be empty.

Example:
- `update 3 study for quiz`

Update only date
- `update TASK_INDEX /date YYYY-MM-DD`
- TASK_INDEX and YYYY-MM-DD cannot be empty.

Example:
- `update 3 /date 2022-05-01`

Update both task description and date
- `update TASK_INDEX TASK_DESC /date YYYY-MM-DD`
- TASK_INDEX, TASK_DESC and YYYY-MM-DD cannot be empty.

Example:
- `update 3 study for quiz /date 2022-05-01`
