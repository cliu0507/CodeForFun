# multilayer logistic regression
import numpy as np
import torch.nn as nn
import torch.nn.functional as F
import torch
import csv


X_raw = []
Y_raw = []

with open('dataset_regression.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=';')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            # ignore first line?
            pass
        else:
            X_raw.append([
                float(row[0]),
                float(row[1]),
                float(row[2]),
                float(row[3]),
                float(row[4]),
                float(row[5]),
                float(row[6]),
                float(row[7]),
                float(row[8]),
                float(row[9]),
                float(row[10])
            ])
            Y_raw.append(int(row[11]))
        line_count += 1
X = np.array(X_raw, dtype=np.float32)
Y = np.array(Y_raw, dtype=np.float32)
'''
# random
X = np.random.rand(100, 2)
Y = np.random.randint(low=0, high=1, size=(100,))
'''

#print(X)
#print(Y)

X = torch.Tensor(X)
Y = torch.Tensor(Y)

print(X)
print(Y)


class Net(nn.Module):
    def __init__(self, n_feature, n_hidden):
        super(Net, self).__init__()
        #self.hidden = nn.Linear(n_feature, n_hidden, bias=True)
        #self.dropout = nn.Dropout(p=0.5)
        #self.relu = nn.ReLU()
        self.out = nn.Linear(n_feature, 1, bias=True)

    def forward(self, x):
        #x = self.hidden(x)
        #x = self.relu(x)
        #x = self.dropout(x)
        x = self.out(x)
        return x


net = Net(n_feature=11, n_hidden=10)
optimizer = torch.optim.SGD(net.parameters(), lr=0.00004)
loss_func = torch.nn.MSELoss()

# regularization items
lambda1 = 0.0001
lambda2 = 0.0001

epochs = 100000

for epoch in range(epochs):

    #all_linear1_params = torch.cat([x.view(-1) for x in net.parameters()])
    #l1_regularization = lambda1 * torch.norm(all_linear1_params, 1)
    #l2_regularization = lambda2 * torch.norm(all_linear1_params, 2)

    out = net(X)
    out = out.squeeze()
    regression_loss = loss_func(out, Y) # note target label is not one-hotted
    #loss = regression_loss + l1_regularization + l2_regularization
    loss = regression_loss

    optimizer.zero_grad()
    loss.backward()
    optimizer.step()

    if epoch % 20 == 0:
        print("cur loss is ", loss)

## prediction
with torch.no_grad():

    # gt is 7
    to_be_predicted= [8.5, 0.28, 0.56 , 1.8, 0.092, 35, 103, 0.9969, 3.3, 0.75, 10.5]
    input_x = torch.tensor(to_be_predicted)
    res = net(input_x)
    print(res)

    # gt is 4
    to_be_predicted = [6.9 , 0.32, 0.16, 1.4, 0.051, 15, 96, 0.994, 3.22, 0.38,9.5]
    input_x = torch.tensor(to_be_predicted)
    res = net(input_x)
    print(res)

