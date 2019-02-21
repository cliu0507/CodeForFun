Question: K Sum Questions Summary

做过leetcode的人都知道, 里面有2sum, 3sum(closest), 4sum等问题, 这些也是面试里面经典的问题, 考察是否能够合理利用排序这个性质, 一步一步得到高效的算法. 经过总结, 本人觉得这些问题都可以使用一个通用的K sum求和问题加以概括消化, 这里我们先直接给出K Sum的问题描述和算法(递归解法), 然后将这个一般性的方法套用到具体的K, 比如leetcode中的2Sum, 3Sum, 4Sum问题. 同时我们也给出另一种哈希算法的讨论.

leetcode求和问题描述(K sum problem)：

K sum的求和问题一般是这样子描述的：给你一组N个数字(比如 vector<int> num), 然后给你一个常数(比如 int target) ，我们的goal是在这一堆数里面找到K个数字，使得这K个数字的和等于target。

注意事项(constraints):

注意这一组数字可能有重复项：比如 1 1 2 3 , 求3sum, 然后 target  = 6, 你搜的时候可能会得到 两组1 2 3, 1 2 3，1 来自第一个1或者第二个1, 但是结果其实只有一组，所以最后结果要去重。

K Sum求解方法, 适用leetcode 2Sum, 3Sum, 4Sum：

方法一： 暴力，就是枚举所有的K-subset, 那么这样的复杂度就是 从N选出K个，复杂度是O(N^K)

方法二： 排序，这个算法可以考虑最简单的case, 2sum，这是个经典问题，方法就是先排序，然后利用头尾指针找到两个数使得他们的和等于target, 这个2sum算法网上一搜就有，这里不赘述了，给出2sum的核心代码：


[java] view plain copy
//2 sum  
int i = starting; //头指针  
int j = num.size() - 1; //尾指针  
while(i < j) {  
    int sum = num[i] + num[j];  
    if(sum == target) {  
        store num[i] and num[j] somewhere;  
        if(we need only one such pair of numbers)  
            break;  
        otherwise  
            do ++i, --j;  
    }  
    else if(sum < target)  
        ++i;  
    else  
        --j;  
}  


2sum的算法复杂度是O(N log N) 因为排序用了N log N以及头尾指针的搜索是线性的，所以总体是O(N log N)，好了现在考虑3sum, 有了2sum其实3sum就不难了，这样想：先取出一个数，那么我只要在剩下的数字里面找到两个数字使得他们的和等于(target – 那个取出的数)就可以了吧。所以3sum就退化成了2sum, 取出一个数字，这样的数字有N个，所以3sum的算法复杂度就是O(N^2 ), 注意这里复杂度是N平方，因为你排序只需要排一次，后面的工作都是取出一个数字，然后找剩下的两个数字，找两个数字是2sum用头尾指针线性扫，这里很容易错误的将复杂度算成O(N^2 log N)，这个是不对的。我们继续的话4sum也就可以退化成3sum问题，那么以此类推，K-sum一步一步退化，最后也就是解决一个2sum的问题，K sum的复杂度是O(n^(K-1))。 这个界好像是最好的界了，也就是K-sum问题最好也就能做到O(n^(K-1))复杂度，之前有看到过有人说可以严格数学证明，这里就不深入研究了。

K Sum (2Sum, 3Sum, 4Sum) 算法优化(Optimization)：

这里讲两点，第一，注意比如3sum的时候，先整体排一次序，然后枚举第三个数字的时候不需要重复， 比如排好序以后的数字是 a b c d e f, 那么第一次枚举a, 在剩下的b c d e f中进行2 sum, 完了以后第二次枚举b, 只需要在 c d e f中进行2sum好了，而不是在a c d e f中进行2sum, 这个大家可以自己体会一下，想通了还是挺有帮助的。第二，K Sum可以写一个递归程序很优雅的解决，具体大家可以自己试一试。写递归的时候注意不要重复排序就行了。

Hash解法(Other)：

其实比如2sum还是有线性解法的，就是用hashmap, 这样你check某个值存在不存在就是常数时间，那么给定一个sum, 只要线性扫描, 对每一个number判断sum – num存在不存在就可以了。注意这个算法对有重复元素的序列也是适用的。比如 2 3 3 4 那么hashtable可以使 hash(2) = 1; hash(3) = 1, hash(4) =1其他都是0,  那么check的时候，扫到两次3都是check sum – 3在不在hashtable中，注意最后返回所有符合的pair的时候也还是要去重。这样子推广的话 3sum 其实也有O(N^2)的类似hash算法，这点和之前是没有提高的，但是4sum就会有更快的一个算法。

4sum的hash算法：

O(N^2)把所有pair存入hash表，并且每个hash值下面可以跟一个list做成map， map[hashvalue] = list，每个list中的元素就是一个pair, 这个pair的和就是这个hash值，那么接下来求4sum就变成了在所有的pair value中求 2sum，这个就成了线性算法了，注意这里的线性又是针对pair数量(N^2)的线性，所以整体上这个算法是O(N^2)，而且因为我们挂了list, 所以只要符合4sum的我们都可以找到对应的是哪四个数字。
关于hash的解法我研究还不是很多，以后要是有更深入的研究再更新。

