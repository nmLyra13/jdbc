@echo off
REM ==========================================================
REM  update-jdbc.bat  -  Atualiza e envia jdbc1/2/3/4 ao GitHub
REM  Coloque este arquivo em:  C:\Projetos\java\jdbc
REM ==========================================================

REM 1) Garante que estamos na pasta do reposit¢rio
cd /d "C:\Projetos\java\jdbc"

echo.
echo === 1/4  Puxando o que h  no GitHub...
git pull --rebase

echo.
echo === 2/4  Checando se h  mudan‡as locais...
git diff-index --quiet HEAD --
IF %ERRORLEVEL% EQU 0 (
    echo Nenhuma alteracao detectada. Nada a fazer.
    goto :EOF
)

echo.
echo === 3/4  Adicionando alteracoes das pastas jdbc1..4
git add jdbc*

REM 4) Monta commit com timestamp YYYY-MM-DD_HHMMSS
for /f "tokens=1-4 delims=/ " %%a in ("%date% %time%") do (
    set "DIA=%%c-%%b-%%a"
    set "HORA=%%d"
)
set "MSG=Update jdbc1-4 em %DIA% %HORA%"
echo Commit: %MSG%
git commit -m "%MSG%"

echo.
echo === 4/4  Enviando para origin/main...
git push origin main
echo.
echo Concluido!

