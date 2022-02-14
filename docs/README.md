# User Guide
Cannot remember what tasks you need to do? Let **ENCIK** remind you!
**ENCIK** has been designed to perfectly recreate that familiar sense of dread talking to any Enciks you know!
Let that sense of dread propel your motivation to finish all tasks, or face **ENCIK'S** wrath! ~~(2x weekend extras)~~

Start talking to **ENCIK NOW!**

## Starting the App
1. Ensure you have Java 11 installed.
2. Download **ENCIK** from [HERE](https://github.com/arcornior/ip/releases).
3. Save **ENCIK** in a directory of your choosing.
4. Run **ENCIK** with  `java -jar duke.jar`.
5. Add your task!
6. Let **ENCIK** help you!

## Features 
**ENCIK** has many features, check these out!

### Viewing Schedule
Easily view all tasks scheduled for a certain day!
### Finding Tasks
Can't remember what you wanted to do though you know it roughly has something to do with a book? Well now you can!
Just search for keywords to view all tasks related to them!
### That familiar dread talking to enciks
Let your fear of enciks drive your motivation levels through the roof and use that energy to finish all tasks!


## Usage
Do note the following!
* Command is case sensitive!
* Syntax notes:
    * `UPPERCASE` words indicate necessary arguments to pass in with command.
    * Argument order **CANNOT** be changed!

### Add a task!

Add 3 different types of tasks!

Format: 

`todo NAME`

`deadline NAME /by DATE TIME`

`event NAME /at DATE START_TIME-END_TIME`

Note that `DATE` is in `DD/MM/YYYY` format and `TIME` is in `HHmm`(24H) format.

Sample Input:
```
todo smn1
deadline smn2 /by 12/12/1212 1212
event smn3 /at 12/12/1212 1212-1412
```

Expected outcome:
```
YOU WANT TO DO THIS AH:
  [T][] smn1
VERY GOOD! YOU STILL GOT 1 THINGS TO DO AH BETTER NOT FORGET!

YOU BETTER FINISH THIS AH:
  [D][] smn2 (by: 12 Dec 1212 1212)
YOU STILL GOT 2 THINGS TO DO AH BETTER NOT FORGET!

YOU BETTER REMEMBER THIS AH:
  [E][] smn3 (at: 12 Dec 1212 1212-1412)
YOU STILL GOT 3 THINGS TO DO AH BETTER NOT FORGET!
```
### List your tasks!

Easily view all tasks **ENCIK** is keeping track of!

Format:

`list`

Expected outcome:
```
NEED ME TO REMIND YOU AH?
  1. [T][] smn1
  2. [D][] smn2 (by: 12 Dec 1212 1212)
  3. [E][] smn3 (at: 12 Dec 1212 1212-1412)
```
### Mark/ Unmark tasks!

Mark or unmark completed tasks!

Format:

`mark INDEX`

`unmark INDEX`

Note that `INDEX` should be confirmed with `list` command first.

Sample Input:
```
mark 1
unmark 1
```

Expected outcome:
```
THIS ONE
  [T][X] smn1
FINISH ALREADY AH? SWEE CHAI BUTTERFLY RECRUIT!

I THOUGHT THIS ONE
  [T][] smn1
FINISH ALREADY? NEVER MIND THIS WEEKEND CONFINE!
```

### Delete a task!
No longer planning on doing a task? Just delete it!

Format:

`delete INDEX`

Note that `INDEX` should be confirmed with `list` command first.

Sample Input:

`delete 1`

Expected outcome:
```
YOU DON'T WANT DO THEN SAY DON'T DO AH?
  [T][] smn1
WAKE UP YOUR BLOODY IDEA CHAO RECRUIT!
2 MORE TASKS REMAINING! YOU BETTER ONE TIMES GOOD ONE!
```

### Find a task!
Can't remember what you wanted to do? Just search for a keyword!

Format:

`find KEYWORD`

Note that `KEYWORD` can be anything you wish to search.

Sample Input:
```
find smn
find dasf
```
Expected outcome:
```
NEED ME HELP YOU FIND AH? VERY GOOD!
THIS WEEKEND YOU WATCH OUT!
  1. [D][] smn2 (by: 12 Dec 1212 1212)
  2. [E][] smn2 (at: 12 Dec 1212 1212-1412)
  
I NEVER FIND ANYTHING! YOU DARE MAKE ME WASTE MY TIME AH?? VERY GOOD!
```
### View all tasks scheduled on a date!
Can't remember what you wanted to do a certain date?

Format:

`schedule DATE`

Note that `DATE` is in `DD/MM/YYYY` format.

Sample Input:

```
schedule 12/12/1212
schedule 04/04/1234
```
Expected outcome:
```
NEED ME HELP YOU FIND AH? VERY GOOD!
THIS WEEKEND YOU WATCH OUT!
  1. [D][] smn2 (by: 12 Dec 1212 1212)
  2. [E][] smn2 (at: 12 Dec 1212 1212-1412)
 
I NEVER FIND ANYTHING! YOU DARE MAKE ME WASTE MY TIME AH?? VERY GOOD!
```
### Quit **ENCIK**!
Finished adding/ checking tasks?

Format:

`bye`

Expected outcome:
```
BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!
```