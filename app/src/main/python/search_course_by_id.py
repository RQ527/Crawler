import requests
import json

from lxml import etree


def get(studentid=None):
    if studentid != None:
        url = 'http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=' + studentid
        res = requests.get(url)
        html = etree.HTML(res.text)
        tbody = html.xpath(r'//*[@id="kbStuTabs-list"]/div/table//tr')  # 10个title
        kb = {}
        for i in range(len(tbody) - 1):
            temp = html.xpath(
                r'//*[@id="kbStuTabs-list"]/div/table//tbody//tr[' + str(i + 1) + ']//td/text()')  # 10个title
            if len(temp) < 4:
                templist = list(kb[str(i)])
                templist[5] = temp[0]
                templist[6] = temp[1]
                templist[7] = temp[2]
                kb[str(i + 1)] = templist
            else:
                kb[str(i + 1)] = temp
        kb["total"] = len(tbody) - 1
        message = ""
        if res.status_code == 200:
            message = "请求成功"
        else:
            message = "请求失败"
        data = {
            "code": res.status_code,
            "message": message,
            "data": json.dumps(kb, ensure_ascii=False)
        }
        return data
