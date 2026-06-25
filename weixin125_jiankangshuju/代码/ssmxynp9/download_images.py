# -*- coding: utf-8 -*-
"""按业务内容为每张图下载匹配的健康主题图片。"""
import os
import ssl
import urllib.request

UPLOAD = os.path.join(os.path.dirname(__file__), "src", "main", "webapp", "upload")


def unsplash(photo_id, w=800, h=None):
    url = "https://images.unsplash.com/photo-%s?w=%d&q=85&auto=format&fit=crop" % (photo_id, w)
    if h:
        url += "&h=%d" % h
    return url


def pexels(pid, w=800, h=None):
    url = "https://images.pexels.com/photos/%s/pexels-photo-%s.jpeg?auto=compress&cs=tinysrgb&w=%d" % (pid, pid, w)
    if h:
        url += "&h=%d&fit=crop" % h
    return url


# 健康计划 / 科普封面使用已验证的 Unsplash 直链（避免 Pexels ID 与主题不符）
IMAGE_URLS = {
    # 首页轮播
    "1618412361473.jpg": unsplash("1534438327276-14e5300c3a48", 1200),
    "picture1.jpg": unsplash("1571019613454-1cb2f99b2d8b", 1200),
    "picture2.jpg": unsplash("1490645935967-10de6ba17061", 1200),
    "picture3.jpg": unsplash("1476480862126-209bfaa8edc8", 1200),
    "1618412369578.jpg": unsplash("1544367567-0f2fcb009e0b", 1200),
    "1618412392377.jpg": unsplash("1506126613408-eca07ce68773", 1200),

    # 健康计划封面
    "jiankangjihua_fengmian1.jpg": unsplash("1476480862126-209bfaa8edc8", 800),   # 春季减重-跑步
    "jiankangjihua_fengmian2.jpg": unsplash("1556761175-5973dc0f32e7", 800),      # 办公室久坐
    "jiankangjihua_fengmian3.jpg": unsplash("1576091160550-2173dba999ef", 800),   # 中老年养护
    "jiankangjihua_fengmian4.jpg": unsplash("1574629810360-7efbbe195018", 800),  # 青少年运动
    "jiankangjihua_fengmian5.jpg": unsplash("1544367567-0f2fcb009e0b", 800),     # 产后瑜伽
    "jiankangjihua_fengmian6.jpg": unsplash("1512621776951-a57141f2eefd", 800), # 糖尿病-轻食

    # 知识科普封面
    "zhishikepu_fengmian1.jpg": unsplash("1490645935967-10de6ba17061", 800),
    "zhishikepu_fengmian2.jpg": unsplash("1534438327276-14e5300c3a48", 800),
    "zhishikepu_fengmian3.jpg": unsplash("1541781774459-bb2af2f05b55", 800),
    "zhishikepu_fengmian4.jpg": pexels(6551413, 800),
    "zhishikepu_fengmian5.jpg": unsplash("1612349317150-e413f6a5b16d", 800),
    "zhishikepu_fengmian6.jpg": unsplash("1506126613408-eca07ce68773", 800),

    # 用户头像
    "yonghu_gerentouxiang1.jpg": pexels(2379004, 400, 400),
    "yonghu_gerentouxiang2.jpg": pexels(774909, 400, 400),
    "yonghu_gerentouxiang3.jpg": pexels(1222271, 400, 400),
    "yonghu_gerentouxiang4.jpg": pexels(1239291, 400, 400),
    "yonghu_gerentouxiang5.jpg": pexels(1681010, 400, 400),
    "yonghu_gerentouxiang6.jpg": pexels(1130626, 400, 400),

    # 数据分析客观图（体检/化验/心脏/手环/血压/健康报告）
    "shujufenxi_keguantuxiang1.jpg": unsplash("1576091160399-112ba8d25d1d", 800),
    "shujufenxi_keguantuxiang2.jpg": unsplash("1576091160550-2173dba999ef", 800),
    "shujufenxi_keguantuxiang3.jpg": unsplash("1559757175-0eb30cd8c063", 800),
    "shujufenxi_keguantuxiang4.jpg": unsplash("1584515933487-779824d29309", 800),
    "shujufenxi_keguantuxiang5.jpg": unsplash("1612349317150-e413f6a5b16d", 800),
    "shujufenxi_keguantuxiang6.jpg": unsplash("1581595219315-a187dd40c322", 800),

    "1618412115746.jpg": unsplash("1490645935967-10de6ba17061", 800),
    "1618412126802.jpg": pexels(774909, 400, 400),
    "1618412163427.jpg": pexels(2379004, 400, 400),
    "1618412245666.jpg": pexels(1130626, 400, 400),
    "1618412258492.jpg": unsplash("1490645935967-10de6ba17061", 800),
    "1618412321709.jpg": unsplash("1571019613454-1cb2f99b2d8b", 800),
    "1618412342604.jpg": unsplash("1576091160399-112ba8d25d1d", 600),
}

for i in range(1, 7):
    key = "yonghu_gerentouxiang%d.jpg" % i
    IMAGE_URLS["jiankangshuju_gerentouxiang%d.jpg" % i] = IMAGE_URLS[key]
    IMAGE_URLS["jiankangxinxigongxiang_gerentouxiang%d.jpg" % i] = IMAGE_URLS[key]


def download(url, dest):
    ctx = ssl.create_default_context()
    req = urllib.request.Request(url, headers={"User-Agent": "Mozilla/5.0"})
    with urllib.request.urlopen(req, context=ctx, timeout=90) as resp:
        data = resp.read()
    if len(data) < 2000:
        raise ValueError("文件过小，可能下载失败")
    if data[:2] != b"\xff\xd8" and data[:4] != b"\x89PNG":
        raise ValueError("非有效图片格式")
    with open(dest, "wb") as f:
        f.write(data)
    return len(data)


def main():
    os.makedirs(UPLOAD, exist_ok=True)
    ok, fail = 0, []
    for name, url in IMAGE_URLS.items():
        dest = os.path.join(UPLOAD, name)
        try:
            size = download(url, dest)
            print("OK  %s (%d KB)" % (name, size // 1024))
            ok += 1
        except Exception as e:
            print("FAIL %s: %s" % (name, e))
            fail.append(name)
    print("\n完成: %d 成功, %d 失败" % (ok, len(fail)))
    return fail


if __name__ == "__main__":
    main()
