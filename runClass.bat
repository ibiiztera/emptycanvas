@echo off
echo "Classe ? " 
set /P classe=
"c:\Program Files\Java\jdk1.7.0_04\bin\java.exe" -Xmx128M -classpath build\classes %classe% 
pause
