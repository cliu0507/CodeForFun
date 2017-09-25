API Reference

## Tensor
[1., 2., 3.] # a rank 1 tensor; this is a vector with shape [3]
[[1., 2., 3.], [4., 5., 6.]] # a rank 2 tensor; a matrix with shape [2, 3]
[[[1., 2., 3.]], [[7., 8., 9.]]] # a rank 3 tensor with shape [2, 1, 3]


## Computational Graph
A computational graph is a series of TensorFlow operations arranged into a graph of nodes.


## Constant
```
node1 = tf.constant(3.0, dtype=tf.float32)
node2 = tf.constant(4.0) # also tf.float32 implicitly
```


```
print(node1,node2)
Tensor("Const:0", shape=(), dtype=float32) Tensor("Const_1:0", shape=(), dtype=float32)
```
Notice that printing the nodes does not output the values 3.0 and 4.0 as you might expect. Instead, they are nodes that, when evaluated, would produce 3.0 and 4.0, respectively.


## Session
To actually evaluate the nodes, we must run the computational graph within a session. A session encapsulates the control and state of the TensorFlow runtime.

```
sess = tf.Session()
print(sess.run([node1, node2]))
```

## Initialize Data
```
input1 = tf.random_normal([1,10,10,32])
input2 = tf.random_normal([1,20,20,32])
```
```
random_normal(
    shape,
    mean=0.0,
    stddev=1.0,
    dtype=tf.float32,
    seed=None,
    name=None
)
```

## Placeholder
A graph can be parameterized to accept external inputs, known as placeholders. A placeholder is a promise to provide a value later.
```
a = tf.placeholder(tf.float32)
b = tf.placeholder(tf.float32)
adder_node = a + b  # + provides a shortcut for tf.add(a, b)
```


## Variable
Variables allow us to add trainable parameters to a graph. They are constructed with a type and initial value:
(Usually Weights, Bias in Neural Networks)

```
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
x = tf.placeholder(tf.float32)
linear_model = W * x + b
```
Constants are initialized when you call tf.constant, and their value can never change. By contrast, variables are not initialized when you call tf.Variable. To initialize all the variables in a TensorFlow program, you must explicitly call a special operation as follows:
```
init = tf.global_variables_initializer()
sess.run(init)
```

## Get Variable (Recommend)
The best way to create a variable is to call the tf.get_variable function. This function requires you to specify the Variable's name. This name will be used by other replicas to access the same variable, as well as to name this variable's value when checkpointing and exporting models. tf.get_variable also allows you to reuse a previously created variable of the same name, making it easy to define models which reuse layers.

```
get_variable(
    name,
    shape=None,
    dtype=None,
    initializer=None,
    regularizer=None,
    trainable=True,
    collections=None,
    caching_device=None,
    partitioner=None,
    validate_shape=True,
    use_resource=None,
    custom_getter=None
)
```



```
my_variable = tf.get_variable("my_variable", [1, 2, 3])
W = tf.get_variable("W", shape=[784, 256],
       initializer=tf.contrib.layers.xavier_initializer())
my_int_variable = tf.get_variable("my_int_variable", [1, 2, 3], dtype=tf.int32, 
  initializer=tf.zeros_initializer)
```


## Variable Scope
```
with tf.variable_scope("one"):
    a = tf.get_variable("v", [1])
```


## Variable Collection
Because disconnected parts of a TensorFlow program might want to create variables, it is sometimes useful to have a single way to access all of them. For this reason TensorFlow provides collections, which are named lists of tensors or other objects, such as tf.Variable instances.
By default every tf.Variable gets placed in the following two collections: * tf.GraphKeys.GLOBAL_VARIABLES --- variables that can be shared across multiple devices, * tf.GraphKeys.TRAINABLE_VARIABLES--- variables for which TensorFlow will calculate gradients.

If you don't want a variable to be trainable, add it to the tf.GraphKeys.LOCAL_VARIABLES
```
my_local = tf.get_variable("my_local", shape=(), 
collections=[tf.GraphKeys.LOCAL_VARIABLES])
```


## tf.add_to_collection
```
my_local = tf.get_variable("my_local", shape=(), 
collections=[tf.GraphKeys.LOCAL_VARIABLES])
tf.add_to_collection("my_collection_name", my_local)
sth = sum(tf.get_collection("my_collection_name"))
```

```
trainable_var_key = tf.GraphKeys.TRAINABLE_VARIABLES
all_vars = tf.get_collection(key=trainable_var_key, scope="MLP")
fc1_vars = tf.get_collection(key=trainable_var_key, scope="MLP/fc1")
fc2_vars = tf.get_collection(key=trainable_var_key, scope="MLP/fc2")
fc1_weight_vars = tf.get_collection(key=trainable_var_key, scope="MLP/fc1/weights")
fc1_bias_vars = tf.get_collection(key=trainable_var_key, scope="MLP/fc1/biases")
```

Note that tf.get_collection() returns a copy of the collection or an empty list if the collection does not exist.


## Re-Assign Variable
A variable is initialized to the value provided to tf.Variable but can be changed using operations like tf.assign. For example, W=-1 and b=1 are the optimal parameters for our model. We can change W and b accordingly:
```
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
x = tf.placeholder(tf.float32)
linear_model = W * x + b
y = tf.placeholder(tf.float32)
squared_deltas = tf.square(linear_model - y)
loss = tf.reduce_sum(squared_deltas)
print(sess.run(loss, {x: [1, 2, 3, 4], y: [0, -1, -2, -3]}))

#Re Assign Variable
fixW = tf.assign(W, [-1.])
fixb = tf.assign(b, [1.])
sess.run([fixW, fixb])
print(sess.run(loss, {x: [1, 2, 3, 4], y: [0, -1, -2, -3]}))
```

## Initializing variables
Before you can use a variable, it must be initialized. If you are programming in the low-level TensorFlow API (that is, you are explicitly creating your own graphs and sessions), you must explicitly initialize the variables.
To initialize all trainable variables in one go, before training starts, call tf.global_variables_initializer().
```
session.run(tf.global_variables_initializer())
# Now all variables are initialized.
```



## Loss
A loss function measures how far apart the current model is from the provided data. We'll use a standard loss model for linear regression, which sums the squares of the deltas between the current model and the provided data. linear_model - y creates a vector where each element is the corresponding example's error delta. We call tf.square to square that error.
```
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
x = tf.placeholder(tf.float32)
linear_model = W * x + b
y = tf.placeholder(tf.float32)
squared_deltas = tf.square(linear_model - y)
loss = tf.reduce_sum(squared_deltas)
print(sess.run(loss, {x: [1, 2, 3, 4], y: [0, -1, -2, -3]}))
23.66
```


## tf.train
TensorFlow provides optimizers that slowly change each variable in order to minimize the loss function. The simplest optimizer is gradient descent. It modifies each variable according to the magnitude of the derivative of loss with respect to that variable.
```
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(loss)
sess.run(init) # reset values to incorrect defaults.
for i in range(1000):
  sess.run(train, {x: [1, 2, 3, 4], y: [0, -1, -2, -3]})

print(sess.run([W, b]))
```


## Complete Model
```
import tensorflow as tf

# Model parameters
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
# Model input and output
x = tf.placeholder(tf.float32)
linear_model = W * x + b
y = tf.placeholder(tf.float32)

# loss
loss = tf.reduce_sum(tf.square(linear_model - y)) # sum of the squares
# optimizer
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(loss)

# training data
x_train = [1, 2, 3, 4]
y_train = [0, -1, -2, -3]
# training loop
init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init) # reset values to wrong
for i in range(1000):
  sess.run(train, {x: x_train, y: y_train})

# evaluate training accuracy
curr_W, curr_b, curr_loss = sess.run([W, b, loss], {x: x_train, y: y_train})
print("W: %s b: %s loss: %s"%(curr_W, curr_b, curr_loss))
```


## Using variables
To use the value of a tf.Variable in a TensorFlow graph, simply treat it like a normal tf.Tensor:
```
v = tf.get_variable("v", shape=(), initializer=tf.zeros_initializer())
w = v + 1  # w is a tf.Tensor which is computed based on the value of v.
           # Any time a variable is used in an expression it gets automatically
           # converted to a tf.Tensor representing its value.
```


## Device placement
Just like any other TensorFlow operation, you can place variables on particular devices. For example, the following snippet creates a variable named v and places it on the second GPU device:

```
with tf.device("/gpu:1"):
  v = tf.get_variable("v", [1])
```
