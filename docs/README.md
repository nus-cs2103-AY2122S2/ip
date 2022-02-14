# Doge Chatbot 

Its name is **_Doge_**, a person who occasionally ~~pisses~~ annoys you but at the end of the day, you still love them. You can download me [here](https://drive.google.com/file/d/1awN6tT8uNJPXamfNgMrCKOAhq0H9qMdz/view?usp=sharing)! Below are the instructions on how to use it.
>Doge: I swear I'm nice! 😉

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
   - [x] Able to track different kinds of tasks (Todo, Event, Deadline)
   - [x] Mark completed tasks
   - [x] Unmark completed tasks
   - [x] Delete unwanted tasks
   - [x] List the current tasks

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

### 5. Finding a task:
1. **find** `book`: search and filters out all task that contains the word "book" 

## Usage of features

### Adding a todo task: `todo`
- Adds a todo task to the task list. 
- Format: `todo TASK`

Example:
```
todo read book
```
Expected Outcome:
```
Stop troubling me, I've already added this task:
T | | read book
```

### Adding an event task: `event`
- Adds an event task to the task list.
- Format: `event TASK / YYYY-MM-DD HH:MM`
- *(Date/Time have to be valid)*

Example:
```
event read book / 2022-02-10 14:00
```
Expected Outcome:
```
Stop troubling me, I've already added this task:
E | | read book | AT: 10-Feb-2022 14:00
```

### Adding a deadline task: `deadline`
- Adds a deadline task to the task list.
- Format: `deadline TASK / YYYY-MM-DD HH:MM`

Example:
```
deadline read book / 2022-02-10 14:00
```
Expected Outcome:
```
Stop troubling me, I've already added this task:
D | | read book | DEADLINE: 10-Feb-2022 14:00
```

### Listing certain tasks based on occurrence: `list`
- Shows a list of certain tasks in the task list.
- Format: `list LIMITER YYYY-MM-DD HH:MM`
- *(LIMITERS: >, >=, =, <=, <)*

Example:
```
list < 2022-04-10 14:00
```
Expected Outcome:
```
Here are the tasks in your list:
1) T | | read book
2) E | | read book | AT: 10-Feb-2022 14:00
3) D | | read book | DEADLINE: 10-Feb-2022 14:00
```

### Finding certain tasks based on char sequence: `find`
- Filters out a list of certain tasks in the task list based on character sequence specified.
- Format: `find CHARSEQUENCE`

Example:
```
find book
```
Expected Outcome:
```
Here are the matching tasks in your list:
1) T | | read book
2) E | | read book | AT: 10-Feb-2022 14:00
3) D | | read book | DEADLINE: 10-Feb-2022 14:00
```

## Launcher class for Doge:
```java
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```
