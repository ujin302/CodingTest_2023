class Node:
    def __init__(self, data):
        self.root = data.split()[0]
        self.left = data.split()[1]
        self.right= data.split()[2]    

N = int(input())
tree_dict = {}

for i in range(0, N):
    node = Node(input()) # 노드 생성
    tree_dict[node.root] = node # tree에 노드 저장

# 전위순회: 루트 -> 왼쪽 -> 오른쪽
def preorder(root):
    if root != '.': 
        print(root, end='') # 루트
        preorder(tree_dict[root].left) # 왼쪽
        preorder(tree_dict[root].right) # 오른쪽

# 중위순회: 왼쪽 -> 루트 -> 오른쪽
def inorder(root):
    if root != '.':
        inorder(tree_dict[root].left) # 왼쪽
        print(root, end='') # 루트
        inorder(tree_dict[root].right) # 오른쪽
        

# 후위순회: 자식 노드(왼 -> 오)를 모두 확인한 후에 루트 노드를 확인
def postorder(root):
    if root != '.':
        postorder(tree_dict[root].left) # 왼쪽
        postorder(tree_dict[root].right) # 오른쪽
        print(root, end='')

preorder('A')
print()
inorder('A')
print()
postorder('A')