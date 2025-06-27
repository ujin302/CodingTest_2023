import queue

def findSheep(x, y):
    sheepQueue = queue.Queue()
    sheepCheck[x][y] = True
    sheepQueue.put([x, y])
    
    # 앞뒤위아래
    moveXArr = [1, -1, 0, 0]
    moveYArr = [0, 0, -1, 1]
    
    while sheepQueue.qsize() > 0:
        node = sheepQueue.get()
        
        for i in range(len(moveXArr)):
            moveX = node[0] + moveXArr[i]
            moveY = node[1] + moveYArr[i]
            
            if moveX >= h or moveY >= w:
                continue
            if moveX < 0 or moveY < 0:
                continue
            
            if sheepInfo[moveX][moveY] == '#' and not sheepCheck[moveX][moveY]:
                sheepCheck[moveX][moveY] = True
                sheepQueue.put([moveX, moveY])


tc = int(input())

for t in range(tc):
    h, w = map(int, input().split())
    sheepInfo = [[] * w for _ in range(h)]
    sheepCheck = [[False] * w for _ in range(h)]
    result = 0
    
    for x in range(h):
        for s in input():
            sheepInfo[x].append(s)
    
    for x in range(h):
        for y in range(w):
            if not sheepCheck[x][y] and sheepInfo[x][y] == '#':
                findSheep(x, y)
                result +=1
    
    print(result)