# Description: Given a list of unique words, write a function to find all pairs of distinct indices (I, j) in the given list, so that the concatenation of the two words is a palindrome.
# Example:
# Given words = [“dog”, “go”, “o”, "do"]
# Returns [[1, 0], [2, 1], [2, 3]]


def findPair(words):
    results = []
    numElements = len(words)
    
    for i in range(numElements):
        for j in range(i+1,numElements):
            if isPalindrome(i,j):
                results.append([i,j])
            
            if isPalindrome(candidateStr1):
                results.append([i,j])
            if isPalindrome(candidateStr2):
                results.append([j,i])
    return results

n * n * m 
      
   
def isPalindrome(ind1,ind2,words):
    string = words[ind1] + words[ind2]
    if string == "":
        return True
    else:
        length = len(string)
        i = 0
        j = length-1
        while(i < j):
            if string[i] != string[j]:
                return False
            else:
                i+=1
                j-=1
        return True

    
words = ["dog","go","o","do"]
print(findPair(words))
