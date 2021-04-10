package csdn.cn.dsa.hash;

import java.lang.annotation.Target;
import java.util.TreeMap;

public class HashTable<K,V> {

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTable(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for(int i=0;i<M;i++){
            hashtable[i] = new TreeMap<>();
        }
    }
    public HashTable(){
        this(16);
    };

    public int getSize(){
        return size;
    }

    public int hash(K key){
        return (key.hashCode()&0x7fffffff)%M;
    }

    /**
     * 当key已经存在那么就是修改操作
     * 当key不存在那么久是添加操作
     * @param key
     * @param value
     */
    public void add(K key,V value){
        int index = hash(key);
        TreeMap<K, V> treeMap = hashtable[index];
        if(treeMap.containsKey(key)){
            treeMap.put(key,value);
        }else{
            treeMap.put(key,value);
            size++;
        }
    }

    public V remove(K key){
        int index = hash(key);
        TreeMap<K, V> treeMap = hashtable[index];
        if(treeMap.containsKey(key)){
            V ret = treeMap.remove(key);
            size--;
            return ret;
        }
        return null;
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

}
