import urllib.request
import re
import time
import os
import socket
import threadpool
import datetime
import json
import http.client,urllib.parse

def noticeInfoFacade(information):
    try:
        data={'sender':'system','content':information}  
        data = json.dumps(data)  
        headers = {"Content-type": "application/json"}
        conn = http.client.HTTPConnection("localhost",3000)
        conn.request('POST', '/InfoFacade/notice', data, headers)
        response = conn.getresponse()
        dataResponse = response.read().decode('utf-8')
        conn.close()
    except:
        print ("send notice error")


localIP = socket.gethostbyname(socket.gethostname())
noticeInfoFacade(localIP)