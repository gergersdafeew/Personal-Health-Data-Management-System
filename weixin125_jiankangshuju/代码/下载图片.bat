@echo off
chcp 65001 >nul
cd /d "%~dp0ssmxynp9"
echo 正在从网上下载健康相关图片并替换 upload 目录...
python download_images.py
pause
