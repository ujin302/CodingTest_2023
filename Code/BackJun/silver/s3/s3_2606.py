import queue

def checkVirusBFS():
    result = 0
    networkQueue = queue.Queue()
    networkQueue.put(1)
    
    while networkQueue.qsize() > 0:
        computer = networkQueue.get()
        
        for node in networkInfo[computer]:
            if not networkCheck[node]:
                result+=1
                networkQueue.put(node)
                networkCheck[node] = True
    
    print(result)

def checkVirusDFS():
    result = 0
    networkStack = []
    networkStack.append(1)
    
    while len(networkStack) > 0:
        computer = networkStack.pop()
        
        for node in networkInfo[computer]:
            if not networkCheck[node]:
                result+=1
                networkStack.append(node)
                networkCheck[node] = True
    
    print(result)

num = int(input())
networkCount = int(input())

networkInfo = [[] for _ in range(num+1)]
networkCheck = [False] * (num+1)

for i in range(networkCount):
    a, b = map(int, input().split())
    networkInfo[a].append(b)
    networkInfo[b].append(a)

networkCheck[1] = True
checkVirusDFS()
checkVirusBFS()