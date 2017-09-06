Single Resource Reservations

Design a data structure to do reservations of future jobs on a single machine under following constraints.
1) Every job requires exactly k time units of the machine.
2) The machine can do only one job at a time. 
3) Time is part of the system. Future Jobs keep coming at different times. Reservation of a future job is done only if there is no existing reservation within k time frame (after and before)
4) Whenever a job finishes (or its reservation time plus k becomes equal to current time), it is removed from system.


分析：
一台机器，每个任务都需要K时间完成
任务的预约随时都可以进来，但是要保证只接受不重叠的任务（所以只能reserve only if 在K time之前和之后没有别的reservation）


假设 k=4 现在已经有了一些reservation
{2,12}
这个时候明显t=4的预约不被允许，因为2还没有完成，但是t=8的预约是可以的
{2,7,12}

	7
2		12


明显reservation就是一个search和insert的过程
假设需要预约的时间是TIME
每次在一个BST中search 是否有在 (TIME-4，TIME+4)在此范围的node,如果有 则不可以插入

k=4
time = 9
需要search 9-4 和 9+4
def insert(root, time):
	if root == None:
		return TreeNode(9)
	else:
		#Conflict, don't need to anything
		if time - k < root.key and time+k > root.key:
			return root
		else:
			#Go to left
			if time < root:
				root.left = insert(root.left, time)
			else:
				root.right = insert(root.right, time)
		return root

deletion of a job is a simple node deletion in BST


Time complexicity analysis: 
Insert O(logn)
Deletion O(logn)



如果使用array(sorted)的话，做checking time conflict的时候可以使用binary search，也是O(logn)
但是insert和deletion需要O(n)time
