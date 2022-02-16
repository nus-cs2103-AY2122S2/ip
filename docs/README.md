# User Guide
jBot is a desktop app for managing tasks, optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**. If you can type fast and has a lot of tasks to manage, jBot is for you!

## Quick Start
1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest `jBot.jar` from [here](https://github.com/jessicajacelyn/ip/releases/download/v0.2/jBot.jar).
3. Copy the file to the folder you want to use as the home folder for your jBot.
4. Double-click the file to start the app. The GUI should appear in a few seconds. 
5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will list out the tasks that you have stored on your device.
   Some example commands you can try:
   - todo revise math : Adds a todo task with the description "revise math".
   - delete 3 : Deletes the 3rd task shown in the current list.
   - bye : Exits the app.
6. Refer to the Features below for details of each command.

---

## Features

#### Listing all tasks: `list`
Shows a list of all tasks saced on the device.<br />
Format: `list`

#### Adding a Todo: `todo`
Adds a todo task to the list.<br />
Format: `todo DESCRIPTION`

#### Adding a Event: `event`
Adds an event task to the list.<br />
Format: `event DESCRIPTION /at DD-MM-YYYY HHMM`

#### Adding a Deadline: `deadline`
Adds a deadline task to the list.<br />
Format: `deadline DESCRIPTION /by DD-MM-YYYY HHMM`

#### Mark task: `mark`
Marks task in the list as done.<br />
Format: `mark INDEX`

#### Unmark task: `unmark`
Unmarks task in the list as undone.<br />
Format: `unmark INDEX`

#### Deleting a task: `delete`
Deletes the specified task from the list.<br /> 
Format: `delete INDEX`

#### Locating task by name: `find`
Finds task whose description contain the given keyword. <br />
Format: `find KEYWORD`

#### Exiting the program: `bye`
Exits the program. <br />
Format: `bye`

