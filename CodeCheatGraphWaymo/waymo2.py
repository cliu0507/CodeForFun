from queue import Queue

def canReachtoRow(board):
    M = len(board)
    N = len(board[0])
    start_row = 0
    end_row = len(board)
    q = Queue()
    for i in range(M):
        if board[0][i] == 1:
            q.put((0,i))
    visited = set()
    while q.qsize() >0:
        i, j = q.get()
        visited.add((i, j))
        if i == end_row:
            return True
        drs = []
        for dr in drs:
            ii = i + dr[0]
            jj = j + dr[0]
            if 0<ii<M and 0<j<N and board[i][j] == 1 and (ii,jj) not in visited:
                q.put((ii,jj))
    return False

