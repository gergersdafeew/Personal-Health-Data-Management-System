# -*- coding: utf-8 -*-
"""下载知识科普视频到 upload 目录。"""
import os
import ssl
import urllib.request

UPLOAD = os.path.join(os.path.dirname(__file__), "src", "main", "webapp", "upload")

VIDEOS = {
    "zhishikepu_shipin1.mp4": "https://videos.pexels.com/video-files/3195394/3195394-sd_640_360_25fps.mp4",
    "zhishikepu_shipin2.mp4": "https://videos.pexels.com/video-files/5377684/5377684-sd_640_360_25fps.mp4",
    "zhishikepu_shipin3.mp4": "https://videos.pexels.com/video-files/3254066/3254066-sd_640_360_25fps.mp4",
    "zhishikepu_shipin4.mp4": "https://videos.pexels.com/video-files/854024/854024-sd_640_360_25fps.mp4",
    "zhishikepu_shipin5.mp4": "https://videos.pexels.com/video-files/856973/856973-sd_640_360_25fps.mp4",
    "zhishikepu_shipin6.mp4": "https://videos.pexels.com/video-files/3189181/3189181-sd_640_360_25fps.mp4",
}

BASE = "http://localhost:8080/ssmxynp9/upload"


def download(url, dest):
    ctx = ssl.create_default_context()
    req = urllib.request.Request(url, headers={
        "User-Agent": "Mozilla/5.0",
        "Referer": "https://www.pexels.com/",
    })
    with urllib.request.urlopen(req, context=ctx, timeout=120) as resp:
        data = resp.read()
    if len(data) < 50000:
        raise ValueError("视频文件过小")
    with open(dest, "wb") as f:
        f.write(data)
    return len(data)


def main():
    os.makedirs(UPLOAD, exist_ok=True)
    urls = {}
    for name, url in VIDEOS.items():
        dest = os.path.join(UPLOAD, name)
        size = download(url, dest)
        urls[name] = "%s/%s" % (BASE, name)
        print("OK  %s (%d KB)" % (name, size // 1024))
    return urls


if __name__ == "__main__":
    main()
