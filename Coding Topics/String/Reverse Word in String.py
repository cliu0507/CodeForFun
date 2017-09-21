Reverse Word in String

2步翻转方法
类似于 rotate array

Given s = "the sky is blue",
return "blue is sky the".


"the sky is blue" => reverse all

=> then reverse each word



Follow up (不太懂？似乎不可以这么做)

two arrays


a1a2a3...an
b1b2b3...bn


how to get a1b1a2b2a3b3...anbn


a1a2a3...anb1b2b3...bn


assume n = 4


a1a2(a3a4a5b1b2)b3b4b5
a1a2b2b1||a5a4a3b4b5
