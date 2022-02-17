# **CortanaTodo**

> “Productivity is being able to do things that you were never able to do before.” – Franz Kafka ([source](https://dansilvestre.com/productivity-quotes/))

## User Guide

Cortana Todo is a **desktop app for managing all your tasks, optimized for use via a Command Line Interface (CLI)** while having a chatbot-like Graphical User Interface (GUI). Cortana Todo took inspiration from the Microsoft Cortana, resembling the looks and feels of Cortana herself. **Wait no more, let Cortana Todo free up your mind!**

---

## **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest version of `Cortana Todo.jar` from [here](https://github.com/9teMare/ip/releases).

3. Copy the file to the folder you want to use as the *home folder* for your `Cortana Todo`.

4. Double click it to start the app. The GUI similar to the below should appear in a few seconds.
   
   <img src="file:///C:/Users/wangz/AppData/Roaming/marktext/images/2022-02-17-23-09-59-image.png" title="" alt="" width="316">

5. Type the command in the input box and press Send or hit enter to execute it.
   
   Some example commands you can try (see in detail at [Features](#features)):
   
   - `list` : List all tasks.
   
   - `mark 1 2` : Mark the first and second task in the list as done.
   
   - `unmark 2` : Mark the second task in the list as undone.
   
   - `delete 1` : Delete the first task shown in the list.
   
   - `clear all` : Delete all tasks.
   
   - `todo read book` : Add a task of type `Todo` with description `read book` .
   
   - `deadline return book /by 2022-02-18 1800` : Add a task of type `Deadline` with description `return book` and deadline of date `2022-02-18` and time `1800` .
   
   - `event attend conference /at 2022-02-20`: Add a task of type `Event`with description`attend conference`and event date`2022-02-18` .
   
   - `find book` : Find all tasks containing the word `book` .
   
   - `show all 2022-02-20 1800` : Show all tasks on `2022-02-20` at `1800` .
   
   - `view schedules 2022-02-18` : View schedules on `2022-02-18` with tasks listed in chronological order.
   
   - `bye` : Exit the app.

6. Free your mind, let Cortana manage your tasks for you 😆

--- 

## Features

### Add tasks

You can add tasks into your todo list. A task in `Cortana Todo` can be of the following three types:

- [ ] `todo` : task that only contains description

- [ ] `deadline` : task that contains description with deadline date and time

- [ ] `event` : task that contains description with event date and time

### Manage tasks

- [ ] `list` : List all tasks.

- [ ] `mark` : Mark certain task(s) as done.

- [ ] `unmark` : Mark certain task(s) as undone.

- [ ] `delete` : Delete certain task(s).

- [ ] `clear all` : Delete all tasks.

- [ ] `find` : Find all tasks containing a specific keyword .

- [ ] `show all` : Show all tasks on a certain date (optional: and certain time) .

- [ ] `view schedules` : View schedules on a certain date in chronological order.

### Storage

- [ ] Automatically save all your tasks into a .txt file locally, so that you can continue whenever you left last time.

---

## Usage

1. `todo` - **Add a task of type `todo`**
   
   Type `todo` followed by the task description, a todo task will be added.
   
   *Example of usage*:
   
   `todo read book`
   
   *Expected outcome*:
   
   Your todo task will be added to the list, with the following prompt together with the total number of tasks in the list.
   
   ```
   Got it. I've added this task:
    [T][ ] read book
   Now you have 1 task in the list.
   ```

2. `deadline` - **Add a task of type `deadline`**
   
   Type `deadline` with the task description, followed by the `/by` keyword and deadline date and time, a deadline task will be added.
   
   *Example of usage*:
   
   `deadline return book /by 2022-02-18`
   
   *Expected outcome*:
   
   Your deadline task will be added to the list, with the following prompt together with the total number of tasks in the list.
   
   ```
   Got it. I've added this task:
    [D][ ] return book (by: Friday, February 18, 2022)
   Now you have 2 tasks in the list.
   ```

3. `event` - **Add a task of type `event`**
   
   Type `event` with the task description, followed by the `/at` keyword and event date and time, a event task will be added.
   
   *Example of usage*:
   
   `event attend meeting /at 2022-02-19 1900`
   
   *Expected outcome*:
   
   Your event task will be added to the list, with the following prompt together with the total number of tasks in the list.
   
   ```
   Got it. I've added this task:
    [E][ ] attend meeting (at: Saturday, February 19, 2022 0700PM)
   Now you have 3 tasks in the list.
   ```

4. `list` - **List all tasks**
   
   Type `list`, all tasks stored locally in the list will be displayed in the order of adding.
   
   *Example of usage*:
   
   `list`
   
   *Expected outcome*:
   
   All your tasks will be displayed with index.
   
   ```
   1.[T][ ] read book
   2.[D][ ] return book (by: Friday, February 18, 2022)
   3.[E][ ] attend meeting (at: Saturday, February 19, 2022 0700PM)
   ```

5. `mark` - **Mark certain task(s) as done**
   
   Type `mark` followed by the index(es) of the task(s), the task(s) of choice will be marked as done
   
   *Example of usage*:
   
   `mark 1 2 3`
   
   *Expected outcome*:
   
   Tasks with index 1, 2 and 3 will be marked as done with the following prompt.
   
   ```
   Nice! I've marked the following tasks as done:
   
   [T][X] read book
   [D][X] return book (by: Friday, February 18, 2022)
   [E][X] attend meeting (at: Saturday, February 19, 2022 0700PM)
   ```

6. `unmark` - **Mark certain task(s) as undone**
   
   Type `unmark` followed by the index(es) of the task(s), the task(s) of choice will be marked as undone
   
   *Example of usage*:
   
   `unmark 2 3`
   
   *Expected outcome*:
   
   Tasks with index 2 and 3 will be marked as undone with the following prompt.
   
   ```
   OK, I've marked the following tasks as not done yet:
   
   [D][ ] return book (by: Friday, February 18, 2022)
   [E][ ] attend meeting (at: Saturday, February 19, 2022 0700PM)
   ```

7. `delete` - **Delete certain task(s)**
   
   Type `delete` followed by the index(es) of the task(s), the task(s) of choice will be removed from the list
   
   *Example of usage*:
   
   `delete 1`
   
   *Expected outcome*:
   
   Tasks with index 1 will be removed with the following prompt.
   
   ```
   Noted, the following task is deleted:
   
   [T][X] read book
   ```

8. `find` - **Find all tasks containing a specific keyword**
   
   Type `find` followed by the keyword you wish to search for, all tasks containing the keyword in their descriptions will be displayed.
   
   *Example of usage*:
   
   `find book`
   
   *Expected outcome*:
   
   All tasks containing the keyword "book" will be displayed with the following prompt, together with the number of task found.
   
   ```
   Found 1 task containing keyword "book":
   
   [D][ ] return book (by: Friday, February 18, 2022)
   ```

9. `clear all` - **Delete all tasks**
   
   Type `clear all` , all tasks stored locally in the list will be removed, this action is not reversible!
   
   *Example of usage*:
   
   `clear all`
   
   *Expected outcome*:
   
   All tasks will be removed with the following prompt.
   
   ```
   All tasks have been removed!
   ```

10. `show all` - **Show all tasks on the same date (optional: and time)**
    
    Type `show all` followed by the date (and time), all tasks on the same date (and time) will be displayed
    
    *Example of usage*:
    
    `show all 2022-02-18`
    
    *Expected outcome*:
    
    All tasks on `2022-02-18` will be displayed with the following prompt, together with the number of task found.
    
    ```
    Found 2 tasks with date/time 2022-02-18: 
    
    [D][ ] homework (by: Friday, February 18, 2022 0700PM)
    [E][ ] group meeting (at: Friday, February 18, 2022)
    ```
    
    *Example of usage*:
    
    `show all 2022-02-18 1900`
    
    *Expected outcome*:
    
    All tasks on `2022-02-18 1900` will be displayed with the following prompt, together with the number of task found.
    
    ```
    Found 1 tasks with date/time 2022-02-18 1900:
    
    [D][ ] homework (by: Friday, February 18, 2022 0700PM)
    ```

11. `view schedules` - **View schedules on a date**
    
    Type `view schedules` followed by the date, the schedules of the day will be shown, all tasks on that date will be displayed in chronological order.
    
    *Example of usage*:
    
    `view schedules 2022-02-18`
    
    *Expected outcome*:
    
    Tasks on `2022-02-18` will be displayed in chronological order with the following prompt, together with the number of tasks on the day.
    
    ```
    You have 3 tasks on 2022-02-18:
    (Tasks on this date without time will be placed at the end of the list)
    
    [E][ ] lecture (at: Friday, February 18, 2022 0200PM)
    [D][ ] homework (by: Friday, February 18, 2022 0700PM)
    [E][ ] group meeting (at: Friday, February 18, 2022)
    ```
