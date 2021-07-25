'''
_y = ax + b

loss = square (_y - y) = square (ax + b - y)
derivative wrt a = 2x
derivative wrt b = 2b
'''


# Making the imports
import numpy as np

x = np.array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
y = np.array([1, 3, 2, 5, 7, 8, 8, 9, 10, 12])

epochs = 100

a = 0
b = 0

L = 0.001

sum_D_a = 0
sum_D_b = 0

for epoch in range(epochs):
    sum_D_a = 0
    sum_D_b = 0
    cur_loss = 0
    for x_i, y_i in zip(x, y):
        y_pred = a * x_i + b
        D_a = 2 * x_i * (y_pred - y_i)
        D_b = 2 * (y_pred - y_i)
        sum_D_a += D_a
        sum_D_b += D_b
        cur_loss += (y_pred - y_i) ** 2
    sum_D_a = sum_D_a / float(len(x))
    sum_D_b = sum_D_b / float(len(x))
    a = a - L * sum_D_a
    b = b - L * sum_D_b
    print(cur_loss, a, b)