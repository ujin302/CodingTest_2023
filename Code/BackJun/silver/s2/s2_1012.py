import sys;
sys.setrecursionlimit(10**5)

def findEarthworm(startY, startX):
    checkArr[startY][startX] = True
    
    # 앞뒤위아래
    moveXArr = [1, -1, 0, 0]
    moveYArr = [0, 0, -1, 1]
    
    for i in range(4):
        moveX = startX + moveXArr[i]
        moveY = startY + moveYArr[i]
        
        if moveX < 0 or moveY < 0:
            continue
        if moveX >= m or moveY >= n:
            continue
        
        if targetArr[moveY][moveX] == 1 and not checkArr[moveY][moveX]:
            findEarthworm(moveY, moveX)
            

tc = int(input())

for i in range(tc):
    # nearNode = []
    result = 0
    m, n, k = map(int, input().split())
    targetArr = [[0]*m for _ in range(n)] # 양배추 위치 저장
    checkArr = [[False]*m for _ in range(n)] # 방문 기록
    
    # 값 세팅
    minX, minY = 51, 51
    for c in range(k):
        x, y = map(int, input().split())
        targetArr[y][x] = 1
        
    for y in range(n):
        for x in range(m):
            if targetArr[y][x] == 1 and not checkArr[y][x]:
                findEarthworm(y, x)
                result+=1
    print(result)