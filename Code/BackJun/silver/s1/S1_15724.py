# 주지수

s = input()
n = int(s.split(" ")[0])
m = int(s.split(" ")[1])
arr = [[0] * (m+1)]
sumarr = [[0] * (m+1) for _ in range(n+1)]

for i in range(1, n+1):
    str = input()
    arr.append([0] + list(map(int, str.split(" "))))
    for j in range(1, m+1):
        # arr[i][j] = int(str.split(" ")[j])
        sumarr[i][j] = arr[i][j] + sumarr[i][j-1] + sumarr[i-1][j] - sumarr[i-1][j-1]

ct = int(input())

for c in range(ct):
    x1, y1, x2, y2 = map(int, input().split(" "))
    t = 0
    if x1 == 1 and y1 == 1:
        t = sumarr[x2][y2]
    else :
        t = sumarr[x2][y2] - sumarr[x2][y1-1] - sumarr[x1-1][y2] + sumarr[x1-1][y1-1]
    
    print(t)