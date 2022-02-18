@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist taskList.txt del taskList.txt

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM delete output from previous run
if exist ACTUAL2.TXT del ACTUAL2.TXT

REM compile the code into the bin folder
C:\Users\G.SARAVANAN\.jdks\azul-13.0.6\bin\javac  -cp ..\src\main\java -Xlint:none -d C:\Users\G.SARAVANAN\.jdks\corretto-11.0.13\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
C:\Users\G.SARAVANAN\.jdks\azul-13.0.6\bin\java -classpath C:\Users\G.SARAVANAN\.jdks\corretto-11.0.13\bin Main < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
C:\Users\G.SARAVANAN\.jdks\azul-13.0.6\bin\java -classpath C:\Users\G.SARAVANAN\.jdks\corretto-11.0.13\bin Main < input2.txt > ACTUAL2.TXT

REM compare the output to the expected output
FC ACTUAL2.TXT EXPECTED2.TXT
