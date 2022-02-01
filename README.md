# Doge project template

This is a project template for a greenfield Java project. Its name is **_Doge_**, a person who occasionally pisses you off but at the end of the day, you still love them. Given below are instructions on how to use it.

```
         ▄              ▄    
        ▌▒█           ▄▀▒▌   
        ▌▒▒█        ▄▀▒▒▒▐   
       ▐▄█▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐   
     ▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐   
   ▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌   
  ▐▒▒▒▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▌  
  ▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐  
 ▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌ 
 ▌░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌ 
▌▒▒▒▄██▄▒▒▒▒▒▒▒▒░░░░░░░░▒▒▒▐ 
▐▒▒▐▄█▄█▌▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▒▒▌
▐▒▒▐▀▐▀▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▐ 
 ▌▒▒▀▄▄▄▄▄▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▒▌ 
 ▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒▒▄▒▒▐  
  ▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒▄▒▒▒▒▌  
    ▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀   
      ▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀     
         ▀▀▀▀▀▀▀▀▀▀▀▀        
```

## Features of Doge
1. Task tracker
   1. Able to track different kinds of tasks (Todo, Event, Deadline)
   2. Mark completed tasks
   3. Unmark completed tasks
   4. Delete unwanted tasks
   5. List the current tasks

## Basic Commands for Doge
### 1. Adding a task:
1. **todo** `borrow book`: adds a todo task
2. **event** `read book`/`2022-02-10 20:15`: adds an event
3. **deadline** `return book`/`2022-02-12 19:10`: adds a task with a deadline


### 2. Listing all tasks:
1. **list**: generates a list of all tasks
2. **list** `<` `2022-05-20 15:20`: generates a list of tasks that falls before 20th May 2022 15:20

### 3. Deleting a task:
   1. **delete** `2`: deletes the 2nd task in the list


### 4. Marking/Unmarking a task:
   1. **mark** `1`: marks 1st task as completed
   2. **unmark** `3`: unmarks 3rd task as uncompleted


## Adding a todo task: `todo`
- Adds a todo task to the task list. 
- Format: `todo t/TASK`
```
Examples:
- todo t/read book
- todo t/eat lunch
```

## Adding an event task: `event`
- Adds an event task to the task list.
- Format: `event t/TASK d/YYYY-MM-DD o/HH:MM`
- *(Date/Time have to be valid)*
```
Examples:
- event t/read book d/2022-02-10 o/14:00
- todo t/eat lunch d/2023-05-10 o/16:00
```

## Adding a deadline task: `deadline`
- Adds an event task to the task list.
- Format: `deadline t/TASK d/YYYY-MM-DD o/HH:MM`
```
Examples:
- deadline t/read book d/2022-02-10 o/14:00
- todo t/eat lunch d/2023-05-10 o/16:00
```

## Listing certain tasks based on occurrence: `List`
- Shows a list of certain tasks in the task list.
- Format: `list l/LIMITER d/YYYY-MM-DD t/HH:MM`
- *(limiters: >, >=, =, <=, <)*
```
Examples:
- list l/< d/2022-02-10 o/14:00
- list l/>= d/2022-03-15 o/19:00
```
