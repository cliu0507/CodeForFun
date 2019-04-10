


# 
# Your previous Python 3 content is preserved below:
# 
# def say_hello():
#     print('Hello, World')
# 
# for i in range(5):
#     say_hello()
# 
# 
# # 
# # Your previous Plain Text content is preserved below:
# # 
# # Hello Chang.
# 
#

Longest Repeated Character in a string
# aabbbcc => bbb


def longestRepeat(inputStr):
    if inputStr == "":
        return ""
    #assumption: only a - z, A-Z
    string = inputStr
    i = 0
    j = i
    maxLength = 0
    maxChar = ""
    while i< len(string) and j < len(string):
        curChar = string[i]
        curLength = j-i+1
        if curLength > maxLength:
            maxChar = curChar
            maxLength = curLength  
            
        if string[i] == string[j]:
            j+=1
        else:
            i = j
                       
    return maxLength * maxChar
            
            
#print(longestRepeat("aabbbcc"))
                
#print(longestRepeat("aabbbccccc"))
print(longestRepeat("aabbbccyhfcccccccc"))     
#print(longestRepeat("a"))            
            
