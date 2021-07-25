# multilayer logistic regression
import numpy as np
import torch.nn as nn
import torch
import csv


X_raw = []
Y_raw = []

with open('dataset_classification.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=';')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            # ignore first line?
            pass
        else:
            X_raw.append([float(row[0]), float(row[1])])
            Y_raw.append(int(row[2]))
        line_count += 1
X = np.array(X_raw)
Y = np.array(Y_raw, dtype=np.int8)
'''
# random
X = np.random.rand(100, 2)
Y = np.random.randint(low=0, high=1, size=(100,))
'''

#print(X)
#print(Y)

X = torch.Tensor(X)
Y = torch.Tensor(Y).long()

print(X)
print(Y)


class Net(nn.Module):
    def __init__(self, n_feature, n_hidden, n_output):
        super(Net, self).__init__()
        self.hidden = nn.Linear(n_feature, n_hidden, bias=True)
        self.dropout = nn.Dropout(p=0.5)
        self.relu = nn.ReLU()
        self.out = nn.Linear(n_hidden, n_output)

    def forward(self, x):
        x = self.hidden(x)
        x = self.relu(x)
        x = self.dropout(x)
        x = self.out(x)
        return x


net = Net(n_feature=2, n_hidden=10, n_output=2)
optimizer = torch.optim.SGD(net.parameters(), lr=0.02)
loss_func = torch.nn.CrossEntropyLoss()

# regularization items
lambda1 = 0.0001
lambda2 = 0.0001

epochs = 500

for epoch in range(epochs):

    all_linear1_params = torch.cat([x.view(-1) for x in net.hidden.parameters()])
    l1_regularization = lambda1 * torch.norm(all_linear1_params, 1)
    l2_regularization = lambda2 * torch.norm(all_linear1_params, 2)

    out = net(X)
    cross_entropy_loss = loss_func(out, Y) # note target label is not one-hotted
    loss = cross_entropy_loss + l1_regularization + l2_regularization

    optimizer.zero_grad()
    loss.backward()
    optimizer.step()
    print("cur loss is ", loss)

## prediction
with torch.no_grad():

    input_x = torch.tensor([0.3, 0.4])
    res = net(input_x)
    print(res)

    input_x = torch.tensor([11, 14],dtype=torch.float32)
    res = net(input_x)
    print(res)