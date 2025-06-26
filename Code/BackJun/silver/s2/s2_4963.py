import queue

def findLand(x, y):
    global result
    nearLand = queue.Queue()
    nearLand.put([x, y])
    mapCheck[moveX][moveY] = True
    
    # 앞뒤위아래 / 대각선위앞 & 대각선위뒤 & 대각선아래앞 & 대각선아래뒤
    moveXArr = [0, 0, -1, 1, -1, -1, 1, 1]
    moveYArr = [1, -1, 0, 0, 1, -1, 1, -1]
    
    while nearLand.qsize() > 0:
        node = nearLand.get()
        for i in range(len(moveXArr)):
            moveX = node[0] + moveXArr[i]
            moveY = node[1] + moveYArr[i]
            
            if moveX >= h or moveY >= w or moveX < 0 or moveY < 0:
                continue
            
            if mapInfo[moveX][moveY] == 1 and not mapCheck[moveX][moveY]:
                nearLand.put([moveX, moveY])
                mapCheck[moveX][moveY] = True

    result +=1
            
while True:
    w, h = map(int, input().split())
    
    if w == 0 and h == 0: break
    
    result = 0
    mapInfo = [[0]*w for _ in range(h)]
    mapCheck = [[False]*w for _ in range(h)]
    for i in range(h):
        intList = list(map(int, input().split()))
        for j in range(w):
            mapInfo[i][j] = intList[j]
    
    for x in range(h):
        for y in range(w):
            if mapInfo[x][y] == 1 and not mapCheck[x][y]: findLand(x, y)

    print(result)