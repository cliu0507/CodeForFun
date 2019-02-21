Question:


You are driving your girlfriend/boyfriend to a destination where is 100 miles away from your home

There are many stops along the road. You can take a rest at some stops if you want. However, your girlfriend hopes to drive exact 50 miles between stops.
Unhappiness score = |the distance you drive before stop  - 50|. 
You would like to make sure the total nnhappiness score is as low as possible when you arrive destination. Please write an algorithm to 
output all optimal with lowest path path along the road.

input [0,30,40,75,100] - means the stops on the way. 0 means starting point, 100 means destination.

Example:
path1: 0->30(cost = |50 - 20| = 30), 30->40(cost = |10-50| =40), 40->100(cost = |60-50| = 10), total cost = 80
path2: 0->40(cost = |40-50| =10), 40->100(cost = |60-50| = 10), total cost = 20


def pathFinder(inputs):
  #code here
