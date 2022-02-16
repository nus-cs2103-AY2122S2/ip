# TaskBot User Guide
**TaskBot** helps you with the hassle of managing all your task!

**TaskBot** has the added benefit of having a command line interface for users accustomed to it.

---
## QuickStart (Setup)
1. Download `Java JDK 11` on your computer from `https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html`
2. Download the latest version of **TaskBot** from `https://github.com/xMashedxTomatox/ip/releases`
3. Place the downloaded jar file into a empty folder
4. Run cmd in that folder and enter `java -jar duke.jar`
5. Leave your task management to **TaskBot** and enjoy your interactions!

---

## Features 
>_Important information:_ 💡
>- Special `KEYWORD` that are used in commands will be in full caps in the User Guide
>- `DATE` represents a valid date entered in the form of `YYYY-MM-DD`
>- `TIME` represents a valid time entered in the form of `HH:MM`
>- `DESCRIPTION` represents a non empty text
>- `NUMBER` represents a number
>- Any `KEYWORD` with a `*` is optional and may be omitted e.g. `TIME*`

---

### Adding a todo task - `todo`
Creates a task todo and adding it into the task list.
#### Command Format - `todo DESCRIPTION`
#### Example - `todo Buy book`
#### Expected Outcome -
```
Got it. I've added this task:
    [T][] Buy book
Now you have 1 task(s) in the list.
```
---
### Adding a deadline task - `deadline`
Creates a deadline task and adding it into the task list.
#### Command Format - `deadline DESCRIPTION /by DATE TIME*`
#### Example - `deadline Return book /by 2022-05-03 06:00`
#### Expected Outcome -
```
Got it. I've added this task:
    [D][] Return book (by: May 3 2022 06:00 AM)
Now you have 1 task(s) in the list.
```
---
### Adding an event task - `event`
Creates an event task and adding it into the task list.
#### Command Format - `event DESCRIPTION /at DATE TIME-TIME`
#### Example - `event Borrow book /at 2022-05-03 06:00-08:00`
#### Expected Outcome -
```
Got it. I've added this task:
    [E][] Borrow book (at: May 3 2022 06:00 AM to 08:00 AM)
Now you have 1 task(s) in the list.
```
---
### Listing all tasks - `list`
Displaying all tasks added into the list and their information
#### Command Format - `list`
#### Example - `list`
#### Expected Outcome -
```
Here are the tasks in your list:
1. [T][] Buy book
2. [D][] Return book (by: May 3 2022 06:00 AM)
3. [E][] Borrow book (at: May 3 2022 06:00 AM to 08:00 AM)
```
---
### Deleting a task - `delete`
Deleting and removing a task from the task list
#### Command Format - `delete NUMBER`

>_Note:_ 💡
>- You can only enter numbers corresponding to a task number

#### Example - `delete 1`
#### Expected Outcome -
```
Noted I have removed this task:
    [T][] Buy book
Now you have 2 task(s) in the list.
```
---
### Marking a task - `mark`
Marking an existing task in the task list as completed
#### Command Format - `mark NUMBER`

>_Note:_ 💡
>- You can only enter numbers corresponding to a task number

#### Example - `mark 1`
#### Expected Outcome -
```
Done! I've marked this task as done
    [D][X] Return book (by: May 3 2022 06:00 AM)
```
---
### Unmarking a task - `unmark`
Marking an existing task in the task list as not completed
#### Command Format - `unmark NUMBER`

>_Note:_ 💡
>- You can only enter numbers corresponding to a task number

#### Example - `unmark 1`
#### Expected Outcome -
```
Done! I've marked this task as undone
    [D][ ] Return book (by: May 3 2022 06:00 AM)
```
---
### Finding tasks - `find`
Finding and listing tasks that contains description similar to the keyword provided
#### Command Format - `find DESCRIPTION`
#### Example - `find Bo`
#### Expected Outcome -
```
Here are the matching tasks in your list:
 1. [E][] Borrow book (at: May 3 2022 06:00 AM to 08:00 AM)
 2. [T][] Watch Bob the builder
```
---
### Sorting the tasks - `sort`
Sorting the tasks in the task list in a given format

>_Note:_ 💡
> 
> `SORTFUNCTION` is a `KEYWORD` unique to this command
>> This is the list of the available `SORTFUNCTION`
>> - alphabetical
>> - chronological

#### Command Format - `sort SORTFUNCTION`
#### Example - `sort alphabetical`
#### Expected Outcome -
```
Task list sorted! Here are the tasks in your list:
1. [E][] Borrow book (at: May 3 2022 06:00 AM to 08:00 AM)
2. [T][] Buy book
3. [D][] Return book (by: May 3 2022 06:00 AM)
```
---
### Exit the program - `bye`
Tells the bot that you are done and exits the program
#### Command Format - `bye`
#### Example - `bye`
#### Expected Outcome -
```
Bye. Hope to see you again soon!
```
