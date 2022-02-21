# User Guide

## Features 

1. List - Shows entire list of tasks.

2. Delete - Deletes a single specified task.

3. Add - Adds a task you wish to do.

4. deadline - Adds a task with a specific deadline.

5. event - Adds and event with a specific reminder for it.
6. mark - Marks the specified event with an 'X'.
7. unmark - Unmarks the specified marked event.
8. find - Shows all tasks that has that specified word.
9. help - Shows all commands & some examples.
10. bye - Ends the conversation with the program. 

## Usage

#### 1. `list` 

####Expected Outcome: 
```
Tasks to do:
1. [T][] run 2.4km
2. [D][] CS2103T Quiz (by: Friday 2pm)
```

#### 2. `delete 2`

####Expected Outcome:
```
Sure, I've removed this task from the list:
   [D][] CS2103T Quiz (by: Friday 2pm)
   
Number of task(s) in your list: 1 
```

#### 3. `todo run 10km`

####Expected Outcome:
```
This task has been added to your list:
   [T][] run 10km
   
Number of task(s) in your list: 2
```

#### 4. `deadline iP /by 2022-02-18`

####Expected Outcome:
```
This task has been added to your list:
   [D][] iP (by: Feb 18th 2022)
   
Number of task(s) in your list: 3
```

#### 5. `event Jane's Wedding /at Fullerton Hotel`

####Expected Outcome:
```
This task has been added to your list:
   [E][] Jane's Wedding (at: Fullerton Hotel)
   
Number of task(s) in your list: 4
```

#### 6. `mark 4`

####Expected Outcome:
```
This task has been marked:
   [E][X] Jane's Wedding (at: Fullerton Hotel)
```

#### 7. `unmark 4`

####Expected Outcome:
```
This task has been unmarked:
   [E][] Jane's Wedding (at: Fullerton Hotel)
```

#### 8. `find run`

####Expected Outcome:
```
Here are the matching tasks in your list:
1. [T][] run 2.4km
2. [T][] run 10km
```

#### 9. `help`

####Expected Outcome:
```
Commands:
1. list
2. delete
3. todo
4. deadline
5. event
6. mark
7. unmark
8. find
9. help
10. bye

You can add 3 types of tasks to the list:
- todo    
- deadline
- event

Specify the type of task then it's description to add to the list.
Deadlines and events can accept a date or location as reminders.

Examples:
- todo run a mile
- deadline return library book /by Sunday 2359
- event Jack's wedding /at Holiday Inn 1800
- delete 2
- mark 3
- find book
```

#### 10. `bye`

####Expected Outcome:
```
Goodbye. 
```




