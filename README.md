# doge.Doge project template

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

## Features of doge.Doge
Some of the features that **_Doge_** has:
1. doge.task.Task tracker
   1. Able to track different kinds of tasks (doge.task.Todo, doge.task.Event, doge.task.Deadline)

   2. Mark completed tasks

   3. Unmark completed tasks

   4. Delete unwanted tasks

   5. List the current tasks

## Basic Commands for doge.Doge
1. Adding a task:
   1. **todo** `task`: adds a doge.task.Todo task
   
   2. **event** `task`/`yyyy-MM-dd HH:mm`: adds an event 
    
   3. **deadline** `task`/`yyyy-MM-dd HH:mm`: adds a task with a deadline
   

2. Listing all tasks:
   1. **list**: generates a list of all tasks
   2. **list** `limiter` `yyyy-MM-dd HH:mm`: generates a list of tasks based on occurrence stated
      1. `limiter`: =, >, <, >=, <=
      

3. Deleting a task:
   1. **delete** `task no.`: delete specified task


4. Marking/Unmarking a task:
   1. **mark** `task no.`: mark a task as completed
   2. **unmark** `task no.`: unmark a task as uncompleted
