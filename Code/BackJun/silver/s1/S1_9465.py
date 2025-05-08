# 스티커
import queue

# 틀린 코드이자 나의 시도...
def myfindScore(x, y):
    global result
    q = queue.PriorityQueue()
    q.put((score[x][y], x, y))
    moveX = [1, -1, 0, 0] # 상하좌우
    moveY = [0, 0, -1, 1]
    
    for i in range(len(moveX)):
        mX = x + moveX[i]
        mY = y + moveY[i]
        # 인덱스 범위 확인
        if mX < 0 or mY < 0 or mX >= 2 or mY >= n:
            continue
        
        # 현재 노드보다 크고 방문 X
        if score[x][y] < score[mX][mY] and not check[mX][mY]:
            q.put((score[mX][mY], x, y))
            findScore(mX, mY)
            
    
    # 현재 노드가 가장 큰값인지 판단
    node = q.get()
    if node[1] == x and node[2] == y and not check[node[1]][node[2]]:
        result += node[0]
        check[x][y] = True
        
        # 면으로 닿아있는 노드 = True
        for i in range(len(moveX)):
            cX = x + moveX[i]
            cY = y + moveY[i]
            # 인덱스 범위 확인
            if cX < 0 or cY < 0 or cX >= 2 or cY >= n:
                continue
            
            check[cX][cY] = True
        
def myanswer():
    tc = int(input())
    result = 0

    for i in range(tc) :
        result = 0
        n = int(input())
        score = []
        check = [[False]*n for _ in range(2)]
        
        score.append(list(map(int, input().split(" "))))
        score.append(list(map(int, input().split(" "))))
        
        for x in range(2):
            for y in range(n):
                if not check[x][y]:
                    findScore(x, y)
        
        print(result)    
