
# Libraries Included:
# Numpy, Scipy, Scikit, Pandas

Given a json datafile of speech recognition, parse the json file and create a data structure to get phone label for each 0.01s

# [l, aa, b, s, t, er, z, ah, n]

#t1 = 0 - 0.01
#t2 = 0.01 - 0.02 
#...
#tn = 1.14 - 1.15

#sil:0-0.45
#l:0.45 - 0.

input = """
{
  "transcript": "LOBSTERS AND LOBSTERS",
  "words": [
    {
      "word": "LOBSTERS",
      "start": 0.45,
      "end": 1.15,
      "phones": [
        {
          "duration": 0.12,
          "phone": "l"
        },
        {
          "duration": 0.1,
          "phone": "aa"
        },
        {a
          "duration": 0.12,
          "phone": "b"
        },
        {
          "duration": 0.08,
          "phone": "s"
        },
        {
          "duration": 0.09,
          "phone": "t"
        },
        {
          "duration": 0.09,
          "phone": "er"
        },
        {
          "duration": 0.1,
          "phone": "z"
        }
      ]
    },
    {
      "word": "AND",
      "start": 1.15,
      "end": 1.3299999999999998,
      "phones": [
        {
          "duration": 0.07,
          "phone": "ah"
        },
        {
          "duration": 0.02,
          "phone": "n"
        },
        {
          "duration": 0.09,
          "phone": "d"
        }
      ]
    },
    {
      "word": "LOBSTERS",
      "start": 1.33,
      "end": 2.0700000000000003,
      "phones": [
        {
          "duration": 0.06,
          "phone": "l"
        },
        {
          "duration": 0.1,
          "phone": "aa"
        },
        {
          "duration": 0.1,
          "phone": "b"
        },
        {
          "duration": 0.05,
          "phone": "s"
        },
        {
          "duration": 0.1,
          "phone": "t"
        },
        {
          "duration": 0.09,
          "phone": "er"
        },
        {
          "duration": 0.24,
          "phone": "z"
        }
      ]
    }
  ]
}
"""

My answer:
Create a matrix, column is phone, row is time(per 0.01s)

import math
import numpy as np

def parser(input):
    transcript = input['transcript']
    words = input['word'] #list of dictionary
    global_start = 0
    global_start = words[0]["end"]
    phone_dict = {} #{'l':0, 'z':1}
    
    #get the phone set
    index = 0
    for word in words:
        phones_list = word["phones"]
        for phone in phone_list:
            if not phone["phone"] in phone_set:
                phone_dict[phone["phone"]] = index
                index+=1
    
    num_column = len(phone) + 1 #add one extra sil 
    num_row = int(float(global_end - global_start)/0.01))
    
    #create a matrix
    res = np.zeros((num_row,num_column),dtype=int)
    
    prev_phone_end = 0
    for word in words:
        phones_list = word["phones"]
        
        sil_duration = word["start"] - prev_phone_end
        if sil_duration >= 0.01:
            sil_start = prev_phone_end
            sil_end = word["start"]
            col_id_sil = len(phone_dict)
            row_id_sil_start = int(float(sil_start)/0.01)
            row_id_sil_end = int(float(sil_end)/0.01)
            for row_id in range(row_id_sil_start,row_id_sil_end+1):
                res[col_id,row_id] = 1
        prev_phone_end = word["start"]
        
        for phone in phone_list:
            col_id = phone_dict[phone["phone"]]
            phone_start = prev_phone_end + phone["duration"]
            phone_end = phone_start + phone["duration"]
            row_id_start = int(float(phone_start)/0.01)
            row_id_end = int(float(phone_end)/0.01)
            for row_id in range(row_id_start,row_id_end+1):
                res[col_id,row_id] = 1
            prev_phone_end = phone_end
    return res
        





# [sil, l, aa, b, s, t, er, z, ah, n]
#  1, 0, 0, 0, 0
#  ....
#  0, 1, 0, 0, 0


"""


