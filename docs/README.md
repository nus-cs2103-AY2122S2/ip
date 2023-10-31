#

# **User Guide** _for Jin Lin's Duke_

#### _Duke manages your list of tasks for you. Be it to-dos, deadlines or events, Duke's got you._

#

## **Capabilities**

#

### Track your list of tasks with their details!

- Note down the tasks that you need to do and the relevant date and time applicable to them!

  The types of tasks supported are:

  - To-dos _(with no date time details)_
  - Deadlines _(with details on the date and time of the deadline)_
  - Events (with details on the date and time of the event)

- Manage the individual tasks in the list of tasks through the following actions:
  - Marking the task as completed
  - Updating the task description
  - Updating the task date and line _(if applicable)_
  - Deleting the task
- Store the tasks in a local file to be loaded the next time the application is launched.

#

## **Usage**

#

### **`list` - shows the list of tasks**

_Prints out the list of tasks recorded. The message is limited to the first 4 tasks recorded._

#### **Example of usage:**

`list`

#### **Example outcome:**

A message listing the list of tasks.

If there are no tasks, this will be expected:

```
There are currently no elements in the list!
```

otherwise, this will be expected:

```
These are the tasks currently stored in the list:
1. Todo - Cut hair
2. Deadline - CS2103/T iP (by Feb 18 2022 11.59pm)
3. ...
```

#

### **`todo (description)` - logs a new To-do**

_Records a new To-do task to the list of tasks._

#### **Example of usage:**

`todo Cut hair`

#### **Example outcome:**

A new To-do task to be added to the list of tasks.

This will be expected:

```
Got it. I've added this task:
Todo - Cut hair
Now you have 2 tasks in the list.

> list

These are the tasks currently stored in the list:
1. ...
2. Todo - Cut Hair
```

#

### **`deadline (description) /by (date) (time)` - logs a new Deadline**

_Records a new Deadline task to the list of tasks._

_Date input has to be in the format: yyyy-mm-dd e.g. 2022-04-18._

_Time input has to be in the 24-hour format: hh:mm e.g. 19:33._

#### **Example of usage:**

`deadline Homework Submission /by 2022-01-13 23:59`

#### **Example outcome:**

A new Deadline task to be added to the list of tasks.

This will be expected:

```
Got it. I've added this task:
Deadline - Homework Submission (by Jan 13 2022 11.59pm)
Now you have 1 tasks in the list.

> list

These are the tasks currently stored in the list:
1. Deadline - Homework Submission (by Jan 13 2022 11.59pm)
```

#

### **`event (description) /at (date) (time)` - logs a new Event**

_Records a new Event task to the list of tasks._

_Date input has to be in the format: yyyy-mm-dd e.g. 2022-04-18._

_Time input has to be in the 24-hour format: hh:mm e.g. 19:33._

#### **Example of usage:**

`event Module Briefing /at 2022-02-21 09:00`

#### **Example outcome:**

A new Event task to be added to the list of tasks.

This will be expected:

```
Got it. I've added this task:
Event - Module Briefing (at Feb 21 2022 09.00am)
Now you have 1 tasks in the list.

> list

These are the tasks currently stored in the list:
1. Event - Module Briefing (at Feb 21 2022 09.00am)
```

#

### **`delete (index)` - deletes the indexed task**

_Deletes the task at the given index._

#### **Example of usage:**

`delete 1`

#### **Example outcome:**

The task at index 1 will be deleted.

This will be expected:

```
Noted. I've removed this task:
Event - Module Briefing (at Feb 21 2022 09.00am)
Now you have 0 tasks in the list.

> list

There are currently no elements in the list!
```

#

### **`mark (index)` - marks the indexed task as done**

_Marks the task at the given index as Done._

#### **Example of usage:**

`mark 1`

#### **Example outcome:**

The task at index 1 will be marked as done.

If the task has been previously marked as done, this will be expected:

```
The task is already marked.

> list

These are the tasks currently stored in the list:
1. !!!DONE!!! Event - Module Briefing (at Feb 21 2022 09.00am)
```

else, this will be expected:

```
Nice! I've marked this task as done:
!!!DONE!!! Event - Module Briefing (at Feb 21 2022 09.00am)

> list

These are the tasks currently stored in the list:
1. !!!DONE!!! Event - Module Briefing (at Feb 21 2022 09.00am)
```

#

### **`unmark (index)` - marks the indexed task as not yet done**

_Marks the task at the given index as not yet done._

#### **Example of usage:**

`unmark 1`

#### **Example outcome:**

The task at index 1 will be marked as not yet done.

If the task has been previously marked as not yet done, this will be expected:

```
The task is already unmarked.

> list

These are the tasks currently stored in the list:
1. Event - Module Briefing (at Feb 21 2022 09.00am)
```

else, this will be expected:

```
Nice! I've marked this task as done:
Event - Module Briefing (at Feb 21 2022 09.00am)

> list

These are the tasks currently stored in the list:
1. Event - Module Briefing (at Feb 21 2022 09.00am)
```

#

### **`clone (index)` - clones the indexed task**

_Clones the task at the given index._

#### **Example of usage:**

`clone 1`

#### **Example outcome:**

The task at index 1 will be cloned.

This will be expected:

```
I have cloned the task and added it to the end of the task list! This is the cloned task:
Todo - Walk the dog

> list

These are the tasks currently stored in the list:
1. Todo - Walk the dog
2. Event - Module Briefing (at Feb 21 2022 09.00am)
3. Todo - Walk the dog
```

#

### **`update (what to update) (index) (new value)` - updates the indexed task**

_Updates the specified detail of the task at the given index to the new value._

_If date is to be updated, new value must follow the format: yyyy-mm-dd e.g. 2019-04-20._

_If time is to be updated, new value must follow the format: hh:mm e.g. 23:59._

#### **Example of usage:**

`update time 1 23:00`

#### **Example outcome:**

The time of the task at index 1 will be updated.

This will be expected:

```
I have updated the task as per your request! This is the updated task:
Event - Module Briefing (at Feb 21 2022 11.00pm)

> list

These are the tasks currently stored in the list:
1. Event - Module Briefing (at Feb 21 2022 11.00am)
```

#

### **`find (keyword)` - searches for keyword**

_Searches the list of tasks for tasks that contains the keyword._

#### **Example of usage:**

`find Bidding`

#### **Example outcome:**

The tasks that contains the keyword will be filtered out.

If there are no tasks that contains the keyword, this will be expected:

```
There's nothing that contains the keyword!

> list

These are the tasks currently stored in the list:
1. Event - Module Briefing (at Feb 21 2022 11.00am)
2. Deadline - Module Bidding (by August 20 2022, 12:00pm)
```

else, this will be expected:

```
These are the matching tasks:
1. Deadline - Module Bidding (by: Aug 20 2022 12:00pm)

> list

These are the tasks currently stored in the list:
1. Event - Module Briefing (at Feb 21 2022 11.00am)
2. Deadline - Module Bidding (by Aug 20 2022 12:00pm)
```

#

### **`bye` - exits program**

_Exits the program._

#### **Example of usage:**

`bye`

#### **Example outcome:**

A farewell message will be printed.

This will be expected:

```
Sayonara!! Hope to see you again soon heh! :-)
```

#
