### Bean Copy

复制JavaBean对象

可以使用common-beanutils（浅拷贝），序列化Serializable（完全拷贝），将对象object转为map，再由map转为object（浅拷贝）

测试结果：

```Java
Student [score=[Score [name=数学, score=90], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]
Student [score=[Score [name=数学, score=90], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]
Student [score=[Score [name=数学, score=90], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]
Student [score=[Score [name=数学, score=90], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]
objects' hashcode: 
314265080
232824863
1282788025
519569038
objects' field-object hashcode: 
924291546
924291546
-115024220
924291546
Student [score=[Score [name=地理, score=84], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]
Student [score=[Score [name=地理, score=84], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]
Student [score=[Score [name=地理, score=83], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]
Student [score=[Score [name=地理, score=84], Score [name=语文, score=86], Score [name=英语, score=78]]] Person [id=1, name=Jack, age=22]

```