import tensorflow as tf
import numpy as np
from sklearn import datasets

'''
Training Sample:
(X, Y) pair

X - matrix with shape (NUM_SAMPLE,NUM_FEATURE)
Y - scalar

W - weight matrix with shape (NUM_FEATURE, )
B - bias (scalar)

H(X) = 1/(1 + e^(- (X * W + B)))
'''
iris = datasets.load_iris()
X = iris.data
Y = iris.target
X = np.array(X)
Y = np.array(Y)
Y_one_hot = np.zeros((Y.size, Y.max()+1))
Y_one_hot[np.arange(Y.size),Y] = 1
#print(X)
#print(Y_one_hot)



NUM_FEATURE = X.shape[1]
NUM_SAMPLE = X.shape[0]
NUM_CLASS = Y_one_hot.shape[1]
TRAINING_RATE = 0.01


X = np.zeros((NUM_SAMPLE,NUM_FEATURE),dtype=np.float64)
Y = np.zeros((NUM_SAMPLE,NUM_CLASS),dtype=np.int8)


#Define placeholder
x = tf.placeholder(dtype = tf.float32, shape=[None,NUM_FEATURE])
y = tf.placeholder(dtype = tf.float32, shape=[None,NUM_CLASS])


#Declare Variables
with tf.variable_scope("weights"):
	w = tf.get_variable("w", shape=[NUM_FEATURE,NUM_CLASS])
	b = tf.get_variable("b", shape=[NUM_CLASS])

#Define network
logit = tf.add(tf.matmul(x,w),b) #[None,NUM_CLASS]
pred = tf.nn.softmax(logit) #[None,NUM_CLASS]

#cross entropy
cost = (-1)*tf.reduce_mean(tf.reduce_sum(y * tf.log(pred),axis=1))

#Optimizer
optimizer = tf.train.GradientDescentOptimizer(TRAINING_RATE)
train = optimizer.minimize(cost)

init = tf.global_variables_initializer()
#Start Training
with tf.Session() as sess:
	sess.run(init)
	for epoch in range(10000):
		_, c = sess.run([train,cost],feed_dict= {x:X, y:Y_one_hot})
		if epoch%5 == 0:
			print(c)
print("Optimization Finished")
