# Ghibli Task Manager User Guide

This project is a Studio-Ghibli Themed Task Manager that helps you to manage your todos, deadlines and events.

## List of Features

- [x] Add 3 types of Tasks: ToDo, Deadline and Event
- [x] Display task list
- [x] Mark task as done
- [x] Unmark task
- [x] Add notes to task
- [x] Show notes added to a task
- [x] Edit note added to task
- [x] Delete note added to task
- [x] Search for certain tasks based on key words 
- [x] Delete tasks 
- [x] Storing of tasks for easy access in next use
- [x] Exiting the program

## Feature Details 

### Add Tasks

#### Add ToDo
A ToDo is a task with a task name.

*Command: todo TASK_NAME*
   
Example of input:
   ```
   todo buy gift for my bestie 
   ```
Example of output:
   ```
   [T][ ] buy gift for my bestie
   ```

#### Add Deadline
A Deadline is a task that has a end date and time. 

*Command: deadline TASK_NAME /by YYYY-MM-DD hh:mm*
   
Example of input:
   ```
   deadline book venue for party /by 2022-02-14 21:20
   ```
Example of output:
   ```
   [D][ ] book venue for party (by: Feb. 14 2022 09:20 pm)
   ```

#### Add Event
An Event is a task that has a start date and time as well as a end date and time. 

*Command: event TASK_NAME /at YYYY-MM-DD hh:mm /to YYYY-MM-DD hh:mm*
   
Examples of input:
   ```
   event birthday party /at 2022-02-28 19:15 /to 2022-02-28 22:15
   ```
Example of output:
  ```
  [E][ ] birthday party (at: Feb. 28 2022 07:15 pm to Feb. 28 2022 10:15 pm)
  ```
   
### Display Task List

Use command ```list``` to see all tasks you have added.

*Command: list*
   
Examples of input:
   ```
   list
   ```
Example of output:
  ```
   Here is your to-do:
   1.[T][ ] buy gift for my bestie
   2.[D][ ] book venue for party (at: Feb. 28 2022 07:15 pm to Feb. 28 2022 10:15 pm)
   3.[E][ ] birthday party (at: Feb. 28 2022 07:15 pm to Feb. 28 2022 10:15 pm)
  ```
### Mark and Unmark Task 

#### Mark Task as Complete

Use command ```mark``` to mark a task as done. Task number of 1 refers to first task in the list.

*Command: mark <task number>*
   
Examples of input:
   ```
   mark 1
   ```
Example of output:
  ```
   Nice! I've marked this task as done: [T][X] buy gift for my bestie
  ```
#### Unmark Task 

Use command ```unmark``` to unmark a task to change its status back to undone.

*Command: unmark TASK_NUMBER*
   
Examples of input:
   ```
   unmark 1
   ```
Example of output:
  ```
   OK, I've marked this task as not done yet: [T][] buy gift for my bestie
  ```

### Notes 

#### Add note

*Command: add note to task TASK_NUMBER NOTE_MESSAGE*
   
Examples of input:
   ```
   add note to task 1 maybe a plant would be good?
   ```
Example of output:
  ```
   Got it, I've added a note to task [T][] buy gift for my bestie
   Use 'show note from <taskNumber>' to see the note
  ```
#### Show note

*Command: show note from task TASK_NUMBER*
   
Examples of input:
   ```
   show note from task 1
   ```
Example of output:
  ```
   [T][] buy gift for my bestie :
   Note 1: maybe a plant would be good?
  ```
### Edit note

*Command: edit note NOTE_NUMBER from task TASK_NUMBER NEW_NOTE_MESSAGE*
   
Examples of input:
   ```
   edit note 1 from task 1 maybe a cactus would be good?
   ```
Example of output:
  ```
   Got it! I've edited the note from [T][] buy gift for my bestie
  ```
   
Example of task list after edit note:
  ```
   [T][] buy gift for my bestie :
   Note 1: maybe a cactus would be good?
  ```
 ### Clear note

Deletes a note from a task.
   
*Command: clear note NOTE_NUMBER from task TASK_NUMBER*
   
Examples of input:
   ```
   clear note 1 from task 1
   ```
Example of output:
  ```
   Got it! I've deleted the note from [T][] buy gift for my bestie
  ```
   
Example of task list after edit note:
  ```
   [T][] buy gift for my bestie :
  ```  
   
## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* A skeleton JUnit test (`src/test/java/seedu/duke/DukeTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.
