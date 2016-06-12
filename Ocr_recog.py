from havenondemand.hodclient import *
import sys
client=HODClient("f329a654-8977-49cf-aad1-428710d1e284")

params={'file':'C:\Users\piyush\Desktop\AngelHack\screencapture_entire.png'}

ocr_text=open('capture.txt','w')
ocr_text.truncate()
sys.stdout=ocr_text

response = client.post_request(params,HODApps.OCR_DOCUMENT,async=False)

print response