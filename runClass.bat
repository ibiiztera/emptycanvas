@echo off
echo "Classe ? " 
set /P classe=
set JAVA_HOME=""c:\Program Files\Java\jdk1.7.0_04""
java -Xmx128M -classpath build\classes %classe%
pause
