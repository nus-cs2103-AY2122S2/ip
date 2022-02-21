@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist task.txt del task.txt

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    pause
    exit /b 1
)
REM no error here, errorlevel == 0
REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin\ kidsnd274.duke.Main --cmd < input.txt > ACTUAL.TXT

REM compare the output to the expected output
@REM FC ACTUAL.TXT EXPECTED.TXT

REM testing saving capability
@REM java -classpath ..\bin\ kidsnd274.duke.Main --cmd < input.txt > ACTUAL.TXT
@REM FC ACTUAL.TXT EXPECTED2.TXT
pause
