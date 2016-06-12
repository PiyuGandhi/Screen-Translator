from havenondemand.hodclient import *

client=HODClient("f329a654-8977-49cf-aad1-428710d1e284")

params={'file':'C:/Users/piyush/Desktop/sample.mp4'}

response=client.post_request(params,HODApps.RECOGNIZE_SPEECH,async=True)

jobID=response['jobID']

client.get_job_status(jobID)

res=client.get_job_result(jobID)

print res