# User Guide
**_JJBA_** (Duke) is an app for **managing your own tasks**. **_JJBA_** has the benefits 
of a Command Line Interface (CLI) and a Graphical User Interface (GUI) at the same time.
You can input commands to communicate with/control **_JJBA_** however you want 
~~or even let **_DIO_** control the bot~~.

## Starting the App (Setup)
1. Ensure that you have `Java 11` or above installed on your computer.
2. Download the latest version of _**JJBA**_ [here](https://github.com/JonathanHoshi/ip/releases).
3. Move the file into a folder you want to run **_JJBA_** in.
4. Run the file.
   1. Double-clicking it or, 
   2. Entering `java -jar JJBA.jar` into the command line.
5. Chat with JJBA by typing in the command box and click send.

Simple commands to try:
- [`todo`](https://jonathanhoshi.github.io/ip/#todo) `watch jojo stone ocean`: Adds a task of 
`watch jojo stone ocean` into **_JJBA_**'s list.
- [`list`](https://jonathanhoshi.github.io/ip/#list): Shows the current tasks in **_JJBA_**'s list.
- [`mark`](https://jonathanhoshi.github.io/ip/#mark) `1`: Marks the first task in **_JJBA_**'s list as completed.
- [`bye`](https://jonathanhoshi.github.io/ip/#bye): Exits the app.

## Features 
> _Additional information:_
> - Feature description format
>   - `FEATURE` - `COMMAND_TYPE`
> - _`UPPER_CASE` words in format are placeholder text to be replaced by the user._
>   - _Example: `COMMAND` `DESCRIPTION` > `todo read book`_
> - _Format and order of input shown cannot be changed/reordered._
> - _User Guide only shows expected output of **JJBA** bot._
> - _`X` can refer to any amount/quantity when using **JJBA**._

### Todo Task - `ADD`
Adds a todo task into **_JJBA_**'s list.

Command Format: `todo` `DESCRIPTION`

Example of usage: `todo watch jojo stone ocean`

Expected outcome:
```
Task added:
  [T][ ] watch jojo stone ocean
You have X task in your list.
```

### Deadline Task - `ADD`
Adds a deadline task into **_JJBA_**'s list.

Command Format: `deadline` `DESCRIPTION` `/by` `DATE` `TIME`

> ⚠️ _DATE/TIME format_
> - `DATE`: _dd-mm-yyyy_
>   - _Example: `24-09-2022` > 24 September 2022_
> - `TIME`: _HHmm_ (24 hr format)
>   - _Example: `2359` > 11:59pm_

Example of usage: `deadline finish iP /by 18-02-2022 2359`

Expected outcome:
```
Task added:
  [D][ ] finish iP (by: 18 Feb 2022 11:59PM)
You have X task in your list.
```

### Event Task - `ADD`
Adds an event task into **_JJBA_**'s list.

Command Format: `event` `DESCRIPTION` `/at` `DATE` `START_TIME`-`END_TIME`

> ⚠️ _DATE/TIME format_
> - `DATE`: _dd-mm-yyyy_
    >   - _Example: `14-02-2022` > 14 February 2022_
> - `TIME`: _HHmm_ (24 hr format)
    >   - _Example: `1730` > 5:30pm_

Example of usage: `event sc yae miko /at 16-02-2022 2030-2130`

Expected outcome:
```
Task added:
  [E][ ] sc yae miko (at: 16 Feb 2022 8:30PM - 9:30PM)
You have X task in your list.
```