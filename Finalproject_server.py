#!/usr/bin/python
import json
from bson import json_util
from bson.json_util import dumps
import bottle
import datetime
from bottle import Bottle
from bottle import route, run, request, abort, response
from pymongo import MongoClient

connection = MongoClient('localhost',27017) 
db = connection.market
collection = db.stocks


#Step 3 Create function
@route('/createStock/<ticker>', method = 'POST')
def create(ticker):
  newStock = request.json.get("newStock")
  collection.insert(newStock)
  
  #search for the newly inserted document to ensure it was inserted successfully
  insertedDocument = collection.find_one({"Ticker" : ticker}, {"_id": 0})

  #display the newly inserted document
  return json.dumps(insertedDocument)

#Step 4 Read function
@route('/readStock/<ticker>', method = 'GET')
def read(ticker):
  stock = collection.find_one({"Ticker" : ticker})
  
  #if the document can be found, display it
  if stock:
    return json.dumps(stock,default=json_util.default)
  #error message if the document cannot be found
  else:
    return "Unable to find the document you were looking for\n"

#Step 5 Update function
@route('/updateStock/<ticker>', method ='POST')
def update(ticker):
  volume = request.json.get("Volume")
  myquery = {"Ticker": ticker }  
  newvalues = { "$set": { "Volume" : volume } }
  doc = collection.find_one({"Ticker" :ticker}, {"_id" : 0})
  
  #if the document exists update and display updated document
  if doc:
    collection.update(myquery, newvalues)    
    newDoc = collection.find_one({"Ticker" : ticker}, {"_id" : 0, "Earnings Date": 0})
    return json.dumps(newDoc)
  #error message if the desired document does not exist
  else:
    return "Unable to update that Document\n"

#Step 6 Delete function
@route('/deleteStock/<ticker>', method ='GET')
def delete(ticker):
  doc = {"Ticker" : ticker}
  document = list(collection.find({"Ticker" : ticker}))
  
  #if the document to delete is found
  if document:
    collection.remove(doc)
    return "Successfully Removed Document \n"
  
  #if the document is not able to be found
  elif not document:
    return "That document does not exist \n"
  #if another issue arises
  else:
    return "Unable to remove the document\n"
  
#I honestly have no idea how to go about this  
#Stock Report Function
@route('/stockReport', method = 'PUT')
def stockReport():
  userList = request.json.get("userList")
  
  pipeline = [
    {"$match" :{"Ticker" : {"$in" : userList}}},
    {"$project" : {"Ticker": 1, 
                   "Company" : 1,
                   "Industry" : 1, 
                   "Country":1, 
                   "Price": 1,
                   "Profit Margin" : 1,
                   "Return on Assets" : 1,
                   "Return on Investment" : 1,
                   "Performance Year" : 1,
                   "Performance (YTD)" : 1,
                   "Analyst Recom" : 1,
                   "_id": 0}}
    ]
  cursor = collection.aggregate(pipeline) 
  result = list(cursor)
  return json.dumps(result)
  
  
 
  
#industryReport
@route('/industryReport/<industry>', method='GET')
def industryReport(industry):
  pipeline = [
    {"$match" : {"Industry" : industry}},
    {"$project" : {"Ticker" : 1, "Shares Outstanding" : 1, "Company" : 1, "Price" : 1, "Analyst Recom" : 1, "_id" : 0}},
    {"$sort" : {"Analyst Recom" : -1}},
    {"$limit" : 5}]
  #use the above pipeline to create an aggregate for the stocks collection
  cursor = collection.aggregate(pipeline)
  
  #create a list of objects that are returned from the aggregate search
  result = list(cursor)
  
  #return the result in json format
  return json.dumps(result)
    
    
      
if __name__ == '__main__':
  #app.run(debug=True)
  run(host='localhost',port=8080)