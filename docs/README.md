# José

Hola soy José. Je suis chatbot.

## Installation

1. Download and install Java 11. Google it if you have to.
2. Click the download button above
3. You just downloaded a jar of ip
4. Double click it **right now**
5. *Enjoy*

## Usage

### `help` - The only instruction you really need

Shows you, esteemed user, a list of commands and their corresponding formats for good of humanity.

Example of usage:
`help`

Expected outcome:

```
Here's some commands hombre
list: shows un lista of tasks
mark/unmark [task no.]: marks/unmarks task
priority [task no.] [low/medium/high]: changes task prioridad
delete [task no.]: eliminado el task
find [phrase]: gets tasks containing wordido
todo [desc]: creates todo
deadline [desc] /by [yyyy-MM-dd HHmm]: creates deadline
event [desc] /at [yyyy-MM-dd HHmm]: creates event
bye: Jose says bye
```

---

### `list` - The second instruction that you kinda really only need

Shows you, esteemed user, your list of tasks.

Example of usage: `list`

Expected outcome:

```
Aqui estan las tareas en su lista:
1. [T][1][ ] Todo1
2. [D][1][ ] Deadline (by: Feb 18 2022, 9:00AM)
3. [E][1][X] Event (at: Feb 18 2022, 9:00AM)
```

---

### `mark / unmark` - Mark or unmark a task as done

Marks or unmarks a task as done

Example of usage: `mark 1`

Description of outcome: Marks task 1 as done

Expected outcome:

```
Aqui estan las tareas en su lista:
1. [T][1][X] Todo1
2. [D][1][ ] Deadline (by: Feb 18 2022, 9:00AM)
3. [E][1][X] Event (at: Feb 18 2022, 9:00AM)
```

---

### `priority` - Changes task priority

Changes the priority of the specified task

Example of usage: `priority 1 high`

Available priority values: `low, medium, high`

Description of outcome: Changes the priority of task 1 from [1] low to [3] high

Expected outcome:

```
Aqui estan las tareas en su lista:
1. [T][3][X] Todo1
2. [D][1][ ] Deadline (by: Feb 18 2022, 9:00AM)
3. [E][1][X] Event (at: Feb 18 2022, 9:00AM)
```

---

### `delete` - Deletes a task

Deletes the specified task

Example of usage: `delete 1`

Description of outcome: Deletes task 1

Expected outcome:

```
Aqui estan las tareas en su lista:
2. [D][1][ ] Deadline (by: Feb 18 2022, 9:00AM)
3. [E][1][X] Event (at: Feb 18 2022, 9:00AM)
```

---

### `find` - Finds tasks containing the specified query

Finds tasks containing the specified query

Example of usage: `find Deadline`

Description of outcome: Lists tasks containing the phrase 'Deadline' in its description

Expected outcome:

```
Aqui estan las tareas en su lista:
1. [D][1][ ] Deadline (by: Feb 18 2022, 9:00AM)
```

---

### `todo` - Creates a ToDo item

Creates a ToDo item

Example of usage: `todo find swiper`

Expected outcome:

```
Entendido. He anadido esta tarea:
[T][1][ ] find swiper
```

---

### `deadline` - Creates a task with a deadline

Creates a task with a deadline

Example of usage: `deadline find swiper /by 2022-02-18 0900`

Date and time format: `yyyy-MM-dd HHmm`

Expected outcome:

```
Entendido. He anadido esta tarea:
[D][1][ ] find swiper (by: Feb 18 2022, 9:00AM)
```

---

### `event` - Creates an event

Creates an event

Example of usage: `event find swiper /at 2022-02-18 0900`

Date and time format: `yyyy-MM-dd HHmm`

Expected outcome:

```
Entendido. He anadido esta tarea:
[E][1][ ] find swiper (at: Feb 18 2022, 9:00AM)
```

---

### `bye` - bye

bye. Feel free to close the program.

Example of usage: `bye`

Expected outcome:

```
Adios. Backpack backpack swiper no swiping!
```
