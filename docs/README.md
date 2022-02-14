# User Guide
**_JJBA_** (Duke) is an app for **managing your own tasks**. **_JJBA_** has the benefits 
of a Command Line Interface (CLI) and a Graphical User Interface (GUI) at the same time.
You can input commands to communicate with/control **_JJBA_** however you want 
~~or even let **_DIO_** control the bot~~.

---

## Starting the App (Setup)
1. Ensure that you have `Java 11` or above installed on your computer.
2. Download the latest version of _**JJBA**_ [here](https://github.com/JonathanHoshi/ip/releases).
3. Move the file into a folder you want to run **_JJBA_** in.
4. Run the file.
   1. Double-clicking it or, 
   2. Entering `java -jar JJBA.jar` into the command line.
5. Chat with JJBA by typing in the command box and click send.

Simple commands to try:
- [`todo`](https://jonathanhoshi.github.io/ip/#todo-task---add) `watch jojo stone ocean`: Adds a task of 
`watch jojo stone ocean` into **_JJBA_**'s list.
- [`list`](https://jonathanhoshi.github.io/ip/#list-task---list): Shows the current tasks in **_JJBA_**'s list.
- [`mark`](https://jonathanhoshi.github.io/ip/#mark/unmark-task---mark-unmark) `1`: Marks the first task in **_JJBA_**'s list as completed.
- [`bye`](https://jonathanhoshi.github.io/ip/#exit-app---exit): Exits the application.

---

## Features 
> 💡 _Additional information:_
> - Feature description format
>   - `FEATURE` - `COMMAND_TYPE`
> - _`UPPER_CASE` words in format are placeholder text to be replaced by the user._
>   - _Example: `COMMAND` `DESCRIPTION` > `todo read book`_
> - _Format and order of input shown cannot be changed/reordered._
> - _User Guide only shows expected output of **JJBA** bot._
> - _`X` can refer to any amount/quantity when using **JJBA**._

---

### Todo Task - `ADD`
Adds a todo task into **_JJBA_**'s list.

Command Format: `todo` `DESCRIPTION`

#### Example of usage:
User input: `todo watch jojo stone ocean`

Expected output:
```
Task added:
  [T][ ] watch jojo stone ocean
You have X task in your list.
```

---

### Deadline Task - `ADD`
Adds a deadline task into **_JJBA_**'s list.

Command Format: `deadline` `DESCRIPTION` `/by` `DATE` `TIME`

> ⚠️ _DATE/TIME format_
> - `DATE`: _dd-mm-yyyy_
>   - _Example: `24-09-2022` > 24 September 2022_
> - `TIME`: _HHmm_ (24 hr format)
>   - _Example: `2359` > 11:59pm_

#### Example of usage:
User input: `deadline finish iP /by 18-02-2022 2359`

Expected output:
```
Task added:
  [D][ ] finish iP (by: 18 Feb 2022 11:59PM)
You have X task in your list.
```

---

### Event Task - `ADD`
Adds an event task into **_JJBA_**'s list.

Command Format: `event` `DESCRIPTION` `/at` `DATE` `START_TIME`-`END_TIME`

> ⚠️ _DATE/TIME format_
> - `DATE`: _dd-mm-yyyy_
>   - _Example: `14-02-2022` > 14 February 2022_
> - `TIME`: _HHmm_ (24 hr format)
>   - _Example: `1730` > 5:30pm_

#### Example of usage:
User input: `event sc yae miko /at 16-02-2022 2030-2130`

Expected output:
```
Task added:
  [E][ ] sc yae miko (at: 16 Feb 2022 8:30PM - 9:30PM)
You have X task in your list.
```

---

### List Tasks - `LIST`
Shows your current tasks in **_JJBA_**'s list.

Command Format: `list`

#### Example of Usage:
User input: `list`

Expected output:
```
Here are the tasks in your list:
  1. [T][ ] watch jojo stone ocean
  2. [D][ ] finish iP (by: 18 Feb 2022 11:59PM)
  3. [E][ ] sc yae miko (at: 16 Feb 2022 8:30PM - 
9:30PM)
```

---

### Mark/Unmark Task - `MARK` `UNMARK`
Marks or Unmarks selected task in **_JJBA_**'s list.

Command Format:
- `mark` `INDEX`
- `unmark` `INDEX`

> ℹ️ _INDEX refers to the task number in the list_
> - _Refer to the number displayed after executing_ 
    > [`list`](https://jonathanhoshi.github.io/ip/#list-task---list) 

#### Example of Usage:
User input: `mark 2`

Expected output:
```
Good job on completing your task!
  [D][X] finish iP (by: 18 Feb 2022 11:59PM)
```

User input: `unmark 2`

Expected output:
```
Done, remember to do your task. 
  [D][ ] finish iP (by: 18 Feb 2022 11:59PM)
```

--- 

### Find Task - `FIND`
Finds and Shows matching tasks in **_JJBA_**'s list based on user's given keyword.

Command Format: `find` `KEYWORD`

#### Example of usage:
User input: `find i`

Expected output:
```
Here are the matching task in your list:
  1. [D][ ] finish iP (by: 18 Feb 2022 11:59PM)
  2. [E][ ] sc yae miko (at: 16 Feb 2022 8:30PM - 
9:30PM)
```

---

### Delete Task - `DELETE`
Delete selected task in **_JJBA_**'s list.

Command Format: `delete` `INDEX`
> ℹ️ _INDEX refers to the task number in the list_
> - _Refer to the number displayed after executing_
    > [`list`](https://jonathanhoshi.github.io/ip/#list-task---list)

#### Example of usage:
User input: `delete 2`

Expected output:
```
Task removed!
  [D][ ] finish iP (by: 18 Feb 2022 11:59PM)
```

---

### View Statistics - `STATS`
Shows the user's information being tracked by **_JJBA_**.

Details:
- Number of current tasks being tracked.
- Number of tasks completed.
- Date of first usage of **_JJBA_**.

Command Format: `stats`

#### Example of usage:
User input: `stats`

Expected output:
```
Here is your profile information:
  Current tasks: X
  Task completed: X
  Date joined: 12 Feb 2022
```

---

### Change Bot - `BOT`
Change the bot that controls **_JJBA_** output. 
There are currently 2 type of bots available, `JJBA` and `DIO`

Command Format:
- `jjba`
- `dio`

#### Example of usage:
User input: `jjba`

Expected output:
```
What can i do for you?
```

User input: `dio`

Expected output:
```
ZA WARUDO!
```

---

### Exit App - `EXIT`
Close **_JJBA_** application.

Command Format: `bye`

#### Example of usage:
User input: `bye`

Expected output:
```
Goodbye! Thanks for using JJBA Bot!
```
