# 2178. 미로 탐색
import queue

# Node 클래스 선언
class Node:
    x = 0
    y = 0
    
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
# 미로 탐색
def find():
    moveX = [0, -1, 0, 1]
    moveY = [1, 0, -1, 0]
    
    q = queue.Queue()
    q.put(Node(0,0))
    
    while(q.qsize() > 0):
        n = q.get()
        
        for i in range(4):
            x = n.x + moveX[i]
            y = n.y + moveY[i]
            
            if x < 0 or y < 0 or x > len(miro)-1 or y > len(miro[0])-1: 
               continue
            
            if int(miro[x][y]) == 0 or check[x][y]:
                continue
            
            q.put(Node(x, y))
            check[x][y] = True
            miro[x][y] = int(miro[n.x][n.y]) + 1
    
    print(miro[len(miro)-1][len(miro[0])-1])   

ip = input()
n = int(ip.split(' ')[0])
m = int(ip.split(' ')[1])

miro = []
# check = [[False] * m] * n
check = [[False] * m for _ in range(n)]

for i in range(n):
    str = input()
    miro.append(list(str))
    check.append(list())

check[0][0] = True

find()