
# 其实也可以把search/insert 放到solution class之中

class Trie:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = {}
        self.endmark = '#'

    def insert(self, word: str) -> None:
        """
        Inserts a word into the trie.
        """
        curNode = self.root
        for char in word:
            if char in curNode:
                curNode = curNode[char]
            else:
                curNode[char] = {}
                curNode = curNode[char]
        curNode[self.endmark] = True

    def search(self, word: str) -> bool:
        """
        Returns if the word is in the trie.
        """
        curNode = self.root
        for char in word:
            if char in curNode:
                curNode = curNode[char]
            else:
                return False
        # print(self.root)
        return self.endmark in curNode

    def startsWith(self, prefix: str) -> bool:
        """
        Returns if there is any word in the trie that starts with the given prefix.
        """
        curNode = self.root
        for char in prefix:
            if char in curNode:
                curNode = curNode[char]
            else:
                return False
        return True

# Your Trie object will be instantiated and called as such:
obj = Trie()
obj.insert('abc')
obj.insert('abd')
obj.insert('leetc')
obj.insert('lovec')
obj.insert('leetcode')
obj.insert('lovec')

print(obj.search('ab'))
print(obj.search('leetcode'))
print(obj.startsWith('l'))