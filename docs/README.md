# User Guide
***
Lucifer is a friendly chatbot to keep track of your desires at hand. 😈

Here's how you can interact with him!

## Quick Start
***
1. Ensure you have Java 11 installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/yumengtan/ip/releases/tag/A-Release)
3. Double-click the file to start the app.
4. Type your commands in the text box at the bottom and press desire to execute it. 
5. Refer to the Features below for details of each command Lucifer is able to grant.

## Features 
***
- keep track of your todos, deadlines and events
- clears your current tasks
- finds the desire you are looking for

### `list` - Lists all your current tasks.
***
Example of usage:

`list`

Expected Outcome:
```text
Here are the desires in your list my love:
1. [E][] meeting (at: Feb 19 2022 02:00PM)
2. [D][] Assignment 1 (by: Feb 19 2022 12:00PM)
3. [T][] run
```
***

### `find` - Finds task based on keyword.
Example of usage:

`find project`

any tasks with "project" in its description will be shown.

Expected outcome:
```text
Here are the matching desires in your list my love:
1.[E][] meeting (at: Feb 19 2022 02:00PM)
```
***
### `deadline` - Adds a task with a deadline.
Example of usage:

`deadline Assignment 1 /by 19-02-2022 1400`

Adds a task with a deadline in a specific date and time format.

Expected outcome:
```text
Got it. I have added this to your desires:
[D][] Assignment 1 (by: Feb 19 2022 12:00PM)
Currently you have 2 things yet to be desired. 
```

***
### `event` - Adds a task that is an event.
Example of usage:

`event meeting /by 19-02-2022 1200`

Adds a task that is an event in a specific date and time format.

Expected outcome:
```text
Got it. I have added this to your desires:
[E][] meeting (at: Feb 19 2022 12:00PM)
Currently you have 2 things yet to be desired. 
```

***
### `todo` - Adds a task that without a deadline.
Example of usage:

`todo run`

Adds a task without a deadline.

Expected outcome:
```text
Got it. I have added this to your desires:
[T][] run
Currently you have 2 things yet to be desired. 
```

***

### `!help` - Provides the list of commands Lucifer can take in.
Example of usage:

`!help`

Provides the list of commands and examples that Lucifer chatbot can take in.

Expected outcome:
```text
👿🔱 Alright love, here are the list of desires I can grant:
list:  I will show you what your current desires are.
delete (number):  I will remove this desires from your current list.
todo (desire):  I will add this desire to your todo list.
event (desire) /at 31-12-2022 1800:  I will add this desire to your list with the date & time.
deadline (desire) /by 31-12-2022 1800:  I will add this desire to your list with its deadline.
mark (number):  I can mark this desire in your list as done.
unmark (number):  I can unmark this desire in your list as not done.
bye: I will end our lovely little conversation for now
find: I will find the desire you are looking for in your current desires
clear: I will remove all your current desires
Now let's let back to what we are doing now shall we? 
```

***
### `clear` - Clears the user current task list
Example of usage:
`clear`

removes all the tasks in the list.

Expected outcome:
```text
Got it. I have cleared all your desires in your list.
```

***
### `mark and unmark` - Mark and unmark a task as done.
Example of usage:
`mark 1`
`unmark 1`

marks and unmarks a task in the current list.

Expected outcome:
```text
Nice! I've marked this task as done.
[X] Assignment 1

OK, I've marked this task as not done yet:
[ ] Assignment 1
```

***
### `bye` - Exits the application.
Example of usage:
`bye`

ends the chatbot with a farewell message and exits the application.

Expected outcome:
```text
👿🔱 Oh no! I hate to see you go:(
Have I granted your desires?
Please come back again!
```

***

