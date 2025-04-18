import queue

def findDanji(x, y):
    # 상, 뒤, 하, 앞
    moveX = [0, -1, 0, 1]
    moveY = [1, 0, -1, 0]
    q = queue.Queue() # 연결된 단지 저장
    count = 1
    
    q.put([x, y])
    checkarr[x][y] = nowNum
    while(q.qsize() > 0):
        node = q.get()
        
        for i in range(4):
            mX = node[0] + moveX[i]
            mY = node[1] + moveY[i]
            
            # 범위 확인
            if mX < 0 or mY < 0 or mY > n-1 or mX > n-1:
                continue
            
            # 방문 기록 O || 집 X
            if checkarr[mX][mY] != 0 or arr[mX][mY] == 0:
                continue
            
            q.put([mX, mY])
            checkarr[mX][mY] = nowNum
            count+=1
    
    return count

n = int(input())
arr = [[0]*n for _ in range(n)]
nowNum = 0 # 현재 단지 번호
checkarr = [[0]*n for _ in range(n)]
result = list()

for i in range(n) :
    str = input()
    for s in range(n) :
        arr[i][s] = int(str[s])

for i in range(n):
    for j in range(n):
        # 방문 기록 X && 집 존재
        if checkarr[i][j] == 0 and arr[i][j] == 1:
            nowNum+=1
            result.append(findDanji(i, j))

print(len(result))
result.sort()
for i in result:
    print(i)