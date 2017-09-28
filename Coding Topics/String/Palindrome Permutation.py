Palindrome Permutation

https://leetcode.com/articles/palindrome-permutation/



Palindrome Permutation II

Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].


https://leetcode.com/articles/palindrome-permutation-ii/

Method 1:
Brute Force (TLE)
The simplest solution is generate every possible permutation of the given string 
s and check if the generated permutation is a palindrome. After this, the palindromic permuations can be added to a 
set in order to eliminate the duplicates. At the end, we can return an array comprised of the elements of this 
set as the resultant array.


Method 2:
BackTracking + DFS 
Use swap (check whether swapped items are equal, if equal then swap)

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

class Solution:
	def generatePalindromes(self,s):
		result = set()
		center = None
		freq=dict()
		for char in s:
			if char in freq:
				freq[char] += 1
			else:
				freq[char] = 1
		if not self.canPermutePalindrome(s,freq):
			return []
		else:
			for key in freq:
				if freq[key] % 2 == 1:
					center = key
					oddLength = True
				else:
					st += str(key)*int(freq(key)/2)
			oddLength = False
			permute(st,0,center,result)
			return result

	def permute(self,st,cur,center,result):
		if cur == len(st):
			result.add(st + (center == None ? center:"") + st[::-1])
		else:
			for i in range(cur,st.length()):
				if st[i] != st[cur] || cur == i:
					st = swap(st,cur,i)
					permute(s,cur+1,center,result)
					st = swap(st,i,cur)


	def swap(st, a, b):
		st=list(st)
		temp = st[a]
		st[a] = st[b]
		st[b] = temp
		return "".join(st)

		
	def canPermutePalindrome(self,s,freq):
		oddLength = False
		for key,value in freq.items():
			if value%2 == 1 and oddLength == True:
				return False
			elif value%2 == 1 and oddLength == False:
				oddLength = True
			else:
				continue
		return True
		


