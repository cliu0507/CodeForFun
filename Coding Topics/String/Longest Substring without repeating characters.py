Longest Substring Without Repeating Characters


abcabcbb

answer="abc"


bbbbb


class Solution(object):
	def lengthOfLongestSubstring(self, s):
		if len(s) == 0:
			return 0
		hash = dict()
		start = 0 
		end = 0
		max_res = 1
		while end < len(s):
			if not s[end] in hash:
				hash[s[end]] = "True"
				end+=1
				max_res = max(max_res,end-start)
			else:
				#max_res = max(max_res,end-start)
				while start < end and s[start] != s[end]:
					hash.pop(s[start])
					start +=1
				end +=1
				start +=1
				#max_res = max(max_res,end-start+1)
		return max_res







