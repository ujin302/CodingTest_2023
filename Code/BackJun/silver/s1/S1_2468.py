# 안전 영역
import queue

def findSafe(x, y):
    q = queue.Queue() # 한 덩어리 확인
    q.put([x, y])
    checkarr[x][y] = count
    
    while q.qsize() > 0:
        node = q.get()
        # 상하좌우
        moveX = [0, 0, -1, 1]
        moveY = [1, -1, 0, 0]
        
        for i in range(len(moveX)):
            x = node[0] + moveX[i]
            y = node[1] + moveY[i]
            
            # 범위 넘어간 경우
            if x < 0 or y < 0 or x > len(arr)-1 or y > len(arr)-1:
                continue
            
            # 이미 방문한 경우 || 침수 지역인 경우
            if checkarr[x][y] > 0 or not notrainarr[x][y]:
                continue
            
            # 침수 X
            q.put([x, y])
            checkarr[x][y] = count

n = int(input())
arr = [[0]*n for _ in range(n)]

result = 0
m = 0 # 최대 높이

for i in range(n):
    s = input()
    for j in range(n):
        arr[i][j] = int(s.split(" ")[j])
        if arr[i][j] > m : m = arr[i][j]

for h in range(m):
    count = 0 # 안전지역 개수
    notrainarr = [[False]*n for _ in range(n)] # 침수 지역
    checkarr = [[0]*n for _ in range(n)] # 방문 확인
    
    # 침수 지역 구분
    for i in range(n):
        for j in range(n):
            # 침수 지역 X : True
            if h < arr[i][j]: notrainarr[i][j] = True
            
    for xi in range(n):
        for yi in range(n):
            # 방문 전 && 침수 X
            if checkarr[xi][yi] == 0 and notrainarr[xi][yi]:
                count+=1
                findSafe(xi, yi)
                if count > result: result = count
                
print(result)