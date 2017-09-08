Symmetric Tree

方法1： 递归

方法2： 迭代

BFS
    1
   / \
  2   2
 / \ / \
3  4 4  3


其实就是左右两个节点的子节点也需要对称



第1种方法
	直接把level拆成两个queue，一个queue q1对应左边子树，一个queue对应右边子树q2
	如果q1放左节点 ,q2就放右边节点，两个queue里面的元素应该完全一样

第2种方法
	只用一个queue也可以
	public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if(root == null) return true;
        q.add(root.left);
        q.add(root.right);
        while(q.size() > 1){
            TreeNode left = q.poll(),
                     right = q.poll();
            if(left== null&& right == null) continue;
            if(left == null ^ right == null) return false;
            if(left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);            
        }
        return true;
    }

第三种方法

类似于same Tree的问题
使用两个stack 分别模拟向左 和向右遍历 如果一样则说明是对称的
和same tree的区别就是一个是标准preorder 这个问题需要另一个栈push先右边的节点（从上往右下）

原则上会省空间