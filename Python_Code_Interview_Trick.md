1. 如何用int作为binary表示是否index被used:
```
int used
for i in range(0,n):
	if (used & 1 << i):
		continue //代表第i个index没有剩余的，已经被使用了
	else:
		dfs(used,path,result)
```
