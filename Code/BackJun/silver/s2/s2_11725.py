import queue

def findParent():
    childQueue = queue.Queue()
    
    for c in treeList[1]:
        parent[c] = 1
        childQueue.put(c) 
    
    while childQueue.qsize() > 0:
        child = childQueue.get()
        for c in treeList[child]:
            if parent[c] == 0 and c != 1:
                parent[c] = child
                childQueue.put(c)
                
    print("\n".join(map(str, parent[2:])))

n = int(input())

treeList = [[] for _ in range(n+1)]
parent = [0] * (n+1)

for i in range(n-1):
    a, b = map(int, input().split())
    treeList[a].append(b)
    treeList[b].append(a)

findParent()