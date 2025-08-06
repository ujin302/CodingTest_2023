import queue
import sys

num = int(input())
q = queue.Queue()

for n in range (0, num):
    s = sys.stdin.readline().replace("\n", "")
    
    if s.split(" ")[0] == "push":
        q.put(s.split(" ")[1])
    elif s == "size":
        print(q.qsize())
    elif s == "empty":
        if q.qsize() == 0: print(1)
        else: print(0)
    elif q.qsize() == 0:
        print(-1)
    elif s == "front":
        print(list(q.queue)[0])
    elif s == "back":
        print(list(q.queue)[q.qsize()-1])
    elif s == "pop":
        print(q.get())