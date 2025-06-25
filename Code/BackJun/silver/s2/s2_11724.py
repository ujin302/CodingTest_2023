from sys import stdin

def bfs(index):
    connectedNodeStack = []
    connectedNodeStack.append(index)
    
    while len(connectedNodeStack) > 0:
        for n in nodeInfo[connectedNodeStack.pop()]:
            if not checkNode[n]:
                connectedNodeStack.append(n)
                checkNode[n] = True

                
n, m = map(int, stdin.readline().split())
nodeInfo = [[] for _ in range(n+1)]
checkNode = [False] * (n+1)
result = 0

for i in range(m):
    a, b = map(int, stdin.readline().split())
    nodeInfo[a].append(b)
    nodeInfo[b].append(a)

for i in range(1, n+1):
    if not checkNode[i]:
        checkNode[i] = True
        bfs(i)
        result +=1

print(result)