from textblob import *
import sys

translated=open('translated.txt','w')
with open('translatortest.txt','r') as op:
    text=op.read()
sys.stdout=translated
blob=TextBlob(text)

print blob.translate(to='fr')