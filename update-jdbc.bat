@echo off
REM ==========================================================
REM  update-jdbc.bat  -  Atualiza e envia jdbc1/2/3/4 ao GitHub
REM  Coloque este arquivo em:  C:\Projetos\java\jdbc
REM ==========================================================

REM 1) Garante que estamos na pasta do reposit¢rio
cd /d "C:\Projetos\java\jdbc"
git add .
git commit -m "update %date% %time%"
git push origin main
echo.
echo Concluido!

