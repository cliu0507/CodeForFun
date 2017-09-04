Ransom Note

Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true



Assume ransom length:m   magazine length : n

O(1)space + O(m+n + mlogm+nlogn)

class Solution(object):
    def canConstruct(self, ransomNote, magazine):
        """
        :type ransomNote: str
        :type magazine: str
        :rtype: bool
        """
        r = sorted(ransomNote)
        m = sorted(magazine)
        
        i = 0
        j = 0
        
        while(i < len(r) and j< len(m)):
            if r[i] == m[j]:
                i+=1
                j+=1
            else:
                j+=1
        return i == len(r)
            
Also we can use Hashmap:

O(n+m) time + O(n) space


Note since we are using ascii => WE CAN USE INT[256] 
If we limit to english character = > WE CAN USE INT[26], which would be O(1) Space




Neat Python:
def canConstruct(self, ransomNote, magazine):
    return not collections.Counter(ransomNote) - collections.Counter(magazine)