from torch.utils.data import Dataset, DataLoader
import torch
import numpy as np


# very good Pytorch instruction : https://github.com/victoresque/pytorch-template#data-loader

'''

Data Loader
Writing your own data loader
Inherit BaseDataLoader

BaseDataLoader is a subclass of torch.utils.data.DataLoader, you can use either of them.

BaseDataLoader handles:

Generating next batch
Data shuffling
Generating validation data loader by calling BaseDataLoader.split_validation()
DataLoader Usage

BaseDataLoader is an iterator, to iterate through batches:

for batch_idx, (x_batch, y_batch) in data_loader:
    pass
'''

from torch.utils.data.dataset import Dataset


class MyCustomDataset(Dataset):
    def __init__(self, X_Y_train):
        '''


        :param X_Y_train: example
        [
        [1,2,0],
        [2,3,1],
        [2,4,5]
        ]
        maps to x1,x2, label
        '''
        # stuff
        self.data = X_Y_train

    def __getitem__(self, index):
        # preprocess stuff

        return self.data[index][:-1], self.data[index][-1]

    def __len__(self):
        return len(self.data)  # of how many examples(images?) you have


# How to use dataloader

train_dataset = MyCustomDataset(X_Y_train)
test_dataset = MyCustomDataset(X_Y_test)

train_dataloader = DataLoader(train_dataset, batch_size=64, shuffle=True)
test_dataloader = DataLoader(test_dataset, batch_size=64, shuffle=True)

optimizer = torch.optim.SGD(net.parameters(), lr=0.02)
loss_func = torch.nn.CrossEntropyLoss()

EPOCH = 1000
for epoch in range(EPOCH):
    for step, (x, y) in enumerate(train_dataloader):   # gives batch data
        # do stuff
        out = net(x)
        loss = loss_func(out, y)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()
        pass