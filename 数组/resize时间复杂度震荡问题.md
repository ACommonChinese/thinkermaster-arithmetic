# resize时间复杂度震荡问题

### 往最后添加元素可能触发reize操作和均摊时间复杂度

- 由于存在resize操作，所以即使向最后一个位置添加元素，那么添加的最坏的时间复杂度是O(n)
- 不可能每次向最后一个位置添加元素都触发resize，那么使用最坏时间复杂度分析添加算法不合理
  - 假设当前的capacity=8，并且每一次添加都是使用的addLast
  - 当第9次添加的时候触发了resize,那么需要将原来的8个元素copy并添加第9个新元素，总共执行了 17次
  - 对于添加9个元素来说每次addLast平均上大概进行了17/9=2次的操作
  - 结论：capacity=n,n+1次的addLast操作一共执行了2n+1次操作，平均每次addLast进行2次操作
  - 这样均摊计算，时间复杂度就是O(1)级别的，对于addLast操作均摊复杂度比最坏复杂度更有参考 意义
- 同理，removeLast的均摊时间复杂度依然是O(1)的

### 复杂度震荡

在addLast进入临界时, 比如size为8, 当同时添加第9个和删除第9个元素时, 都需要进行resize操作, 即同时调用addLast和removeLast
都会触发resize, 那么就会出现复杂度的震荡

出现这个问题的原因就是remove删除的时候过于eager了  
解决方案是可以适当lazy处理, 即不要在1/2的时候马上缩容:

```java
// 删除指定索引的元素
public E remove(int index) {
    if (index < 0 || index > size) {
        throw new RuntimeException("index is illegal");
    }
    E ret = data[index];
    // 前移index后面所有的元素
    for (int i = index + 1; i < size; i++) {
        data[i-1] = data[i];
    }
    size--;
    data[size] = null;

    // 判断是否需要缩容
    // 修改这里
    if (size == data.length/4 && data.length/2 != 0) {
        resize(data.length/4);
    }

    return ret;
}
```