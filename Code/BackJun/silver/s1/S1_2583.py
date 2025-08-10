def countPart(x, y): 
    pSize = 1
    arrBox[y][x] = 2 
    nodeArr = []
    nodeArr.append([x,y])
    
    # 상하좌우
    moveX = [0, 0, -1, 1]
    moveY = [1, -1, 0, 0]
    
    while len(nodeArr) > 0:
        node = nodeArr.pop()
        
        for i in range(0, 4):
            mX = node[0] + moveX[i]
            mY = node[1] + moveY[i]
            
            if mX < 0 or mX >= n or mY < 0 or mY >= m:
                continue
            if arrBox[mY][mX] == 1 or arrBox[mY][mX] == 2:
                continue
            
            arrBox[mY][mX] = 2
            pSize +=1
            nodeArr.append((mX, mY))
    arrResult.append(pSize)
    

m, n, k = map(int, input().split())
arrBox = [[0] * n for _ in range(0,m)] # 7 * 5행
# 0 > 영역 / 1 > 직사각형 내부 / 2 > 0이었는데 확인한 곳
arrResult = []

for i in range(0, k):
    ax, ay, bx, by = map(int, input().split())
    # (0,2) ~ (4,4) ax, ay - bx, by
    # >> (0,2) (0,4) (4,2) (4,4)
    # >> (ax, ay) (ax, by) (bx, ay), (bx, by)
    
    for x in range(ax, bx):
        for y in range(ay, by):
            arrBox[y][x] = 1
            

for x in range(0, n):
    for y in range(0,m):
        if arrBox[y][x] == 0: countPart(x, y)

arrResult.sort()
print(len(arrResult))
print(" ".join(map(str, arrResult)))