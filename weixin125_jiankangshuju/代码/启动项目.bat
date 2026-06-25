@echo off
set "ROOT=%~dp0"
set "BACKEND=%ROOT%ssmxynp9"
set "PROJECT=%ROOT%mp-weixin"
echo ========================================
echo   Personal Health Data System
echo ========================================
echo.
if not exist "%BACKEND%\pom.xml" ( echo [ERROR] Backend not found. & pause & exit /b 1 )
echo [1/3] Starting backend...
start "Health-Backend" cmd /k "cd /d %BACKEND% && mvn exec:java"
echo [2/3] Waiting 15 seconds...
timeout /t 15 /nobreak >nul
echo [3/3] Opening admin and mini program...
start "" "http://localhost:8080/ssmxynp9/admin/dist/index.html"
set "CLI="
if exist "%LOCALAPPDATA%\微信开发者工具\cli.bat" set "CLI=%LOCALAPPDATA%\微信开发者工具\cli.bat"
if not defined CLI if exist "%ProgramFiles(x86)%\Tencent\微信web开发者工具\cli.bat" set "CLI=%ProgramFiles(x86)%\Tencent\微信web开发者工具\cli.bat"
if not defined CLI if exist "%ProgramFiles%\Tencent\微信web开发者工具\cli.bat" set "CLI=%ProgramFiles%\Tencent\微信web开发者工具\cli.bat"
if defined CLI ( echo y^| call "%CLI%" open --project "%PROJECT%" ) else ( start "" explorer "%PROJECT%" )
echo Done. Admin: abo/abo  MP: 用户1/123456
pause
