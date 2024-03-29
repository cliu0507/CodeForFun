随机抽样问题表示如下：
要求从N个元素中随机的抽取k个元素，其中N无法确定。
这种应用的场景一般是数据流的情况下，由于数据只能被读取一次，而且数据量很大，并不能全部保存，因此数据量N是无法在抽样开始时确定的；但又要保持随机性，于是有了这个问题。所以搜索网站有时候会问这样的问题。
这里的核心问题就是“随机”，怎么才能是随机的抽取元素呢？我们设想，买彩票的时候，由于所有彩票的中奖概率都是一样的，所以我们才是“随机的”买彩票。那么要使抽取数据也随机，必须使每一个数据被抽样出来的概率都一样。
【解决】
解决方案就是蓄水库抽样（reservoid sampling）。主要思想就是保持一个集合（这个集合中的每个数字出现），作为蓄水池，依次遍历所有数据的时候以一定概率替换这个蓄水池中的数字。

 Init : a reservoir with the size： k
        for    i= k+1 to N
            M=random(1, i);
            if( M < k)
                 SWAP the Mth value and ith value
       end for


解释一下：程序的开始就是把前k个元素都放到水库中，然后对之后的第i个元素，以k/i的概率替换掉这个水库中的某一个元素。
下面来具体证明一下：每个水库中的元素出现概率都是相等的。
【证明】
（1）初始情况。出现在水库中的k个元素的出现概率都是一致的，都是1。这个很显然。
（2）第一步。第一步就是指，处理第k+1个元素的情况。分两种情况：元素全部都没有被替换；其中某个元素被第k+1个元素替换掉。
我们先看情况2：第k+1个元素被选中的概率是k/(k+1)（根据公式k/i），所以这个新元素在水库中出现的概率就一定是k/(k+1)（不管它替换掉哪个元素，反正肯定它是以这个概率出现在水库中）。下面来看水库中剩余的元素出现的概率，也就是1-P(这个元素被替换掉的概率)。水库中任意一个元素被替换掉的概率是：(k/k+1)*(1/k)=1/(k+1)，意即首先要第k+1个元素被选中，然后自己在集合的k个元素中被选中。那它出现的概率就是1-1/(k+1)=k/(k+1)。可以看出来，旧元素和新元素出现的概率是相等的。
情况1：当元素全部都没有替换掉的时候，每个元素的出现概率肯定是一样的，这很显然。但具体是多少呢？就是1-P(第k+1个元素被选中)=1-k/(k+1)=1/(k+1)。
（3）归纳法：重复上面的过程，只要证明第i步到第i+1步，所有元素出现的概率是相等的即可。



看到一个问题：怎样随机从N个元素中选择一个元素，你依次遍历每个元素，但不知道N多大。
将N个元素用[1、2、...、N]编号。如果在知道N的大小，我们可以从[1、N]中随机选择一个数作为选择对象。
但是现在不知道N的大小，要使每一个元素被取的概率相等（随机）。这个概念叫蓄水池抽样。

Solution：以1/i的概率取第i个元素。
证明：数学归纳法。当i=1时：第1个元素以1/1=1的概率被取，符合条件。
设i=k时符合条件，即前k个元素都以1/k的概率被取。
则i=k+1时：对于第k+1个元素，被取概率为1/（k+1），符合条件。
对于前k个元素，每个元素被取的概率=被取并且没被第k+1个元素替换的概率=（1/k）*(1−1/（k+1）)=1/（k+1）符合条件。
综上所述：得证。

将问题扩展：给你一个长度为N的链表。N很大，但你不知道N有多大。你的任务是从这N个元素中随机取出k个元素。你只能遍历这个链表一次。你的算法必须保证取出的元素恰好有k个，且它们是完全随机的（出现概率均等）。
这次与上面唯一的不同是：总共需要取k个元素。仿照即可得出解决方案。
Solution：以1的概率取前k个元素，从i=k+1开始，以k/i的概率取第i个元素，若被取，以均等的概率替换先前被取的k个元素。
证明：同样数学归纳法。当i=k+1时：第k+1个元素以k/k+1概率被取，前k个元素被取的概率=1 - 被第k+1个元素替换的概率=1−k/(k+1)*1/k=k/(k+1) 符合条件。
设i=p时符合条件，即前p个元素都以k/p的概率被取。
则i=p+1时：对第p+1个元素，被取概率为k/(p+1)符合条件。
对于前p个元素，每个元素被取的概率=被取并且没有被第p+1个元素替换的概率=
k/p*((1−k/(p+1))+k/(p+1)*(1−1/k))=k/p+1同样符合条件。
综上所述：得证。

另外还有一种方法：给每个元素随机生成一个固定区间（如[0,1]）的权重。用一个大小为k的堆来选取权重较大的k个元素。仿照也可解决最开始的取1个的问题。




/* So this question can be interpreted as :
看到一个问题：怎样随机从N个元素中选择一个元素，你依次遍历每个元素，但不知道N多大。
*/
Best Solution: RESERVIOR SAMPLING

public class Solution {

    Random randomno = new Random();
    ListNode head = null;
    
    public Solution(ListNode head) {
        this.head = head;    
    }
    
    
    // RESERVIOR SAMPLING: K=1
    public int getRandom() {
        ListNode result = null;
        ListNode current = head;
        
        for(int n = 1; current!=null; n++) {
            if (randomno.nextInt(n) == 0) {
                result = current;
            }
            current = current.next;
        }
        
        return result.val;
        
    }
}




/*

Most intuitive way:
First traverse the linkedlist and get the length of list, supposed it to be l
then use Java Random Generater to generate "rnd = randomno.nextInt(length)+1"
Then find the rnd th element in the linkedlist, output result

Cons: If the list is super long, traverse whole list will be time consuming!


*/
SOLUTION 2
public class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    int length = 0;
    ListNode head = null;
    Random randomno = null;
    ListNode current = null;
    
    public Solution(ListNode head) {
        this.head = head;
        this.current = head;
        randomno = new Random();
        while(current!=null)
        {
            length++;
            current = current.next;
        }
        
        
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
       int rnd = randomno.nextInt(length)+1;
       current = head;
       int cur_pos = 1;
       while(cur_pos!=rnd)
       {
           current = current.next;
           cur_pos++;
       }
       return current.val;
    }
}