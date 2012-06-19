@echo off
echo "Classe ? " 
set /P classe=
rem"c:\Program Files\Java\jdk1.7.0_04\bin\"
java -Xmx128M -classpath build\classes %classe% 
pause
