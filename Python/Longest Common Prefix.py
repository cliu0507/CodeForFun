Longest Common Prefix


Write a function to find the longest common prefix string amongst an array of strings.


"""
Idea1: Vertical Scanning
"""
class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        ind = 0
        prefix = ""
        curchar = ""
        while (True and len(strs)!=0 ):
            for str in strs:
                #If reaching end of string
                if ind >= len(str):
                    return prefix
                else:
                    #Initial curchar
                    if curchar == "":
                        curchar =  str[ind]
                    else:
                        if curchar != str[ind]:
                            return prefix
            prefix += curchar        
            ind += 1
            curchar = ""
        return prefix


