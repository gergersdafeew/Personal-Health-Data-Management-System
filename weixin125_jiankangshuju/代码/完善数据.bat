@echo off
chcp 65001 >nul
cd /d "%~dp0ssmxynp9"
echo 正在完善数据库演示数据...
python run_data_init.py
if %errorlevel% neq 0 (
  echo.
  echo 若提示缺少 pymysql，请先执行: pip install pymysql
  echo 或确保 MySQL 已启动且密码为 Ma123456@
)
pause
