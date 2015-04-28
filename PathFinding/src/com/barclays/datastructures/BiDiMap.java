package com.barclays.datastructures;

import java.util.HashMap;

public class BiDiMap<T,V> 
{
	HashMap<T, V>	keysMap;
	HashMap<V, T>	valuesMap;

	public BiDiMap()
	{
		keysMap	=	new HashMap<T,V>();
		valuesMap	=	new HashMap<V,T>();
	}


	public void put(T key,V value)
	{
		keysMap.put(key, value);
		valuesMap.put(value, key);
	}


	public V getValueForKey(T key)
	{
		return keysMap.get(key);
	}

	public T getKeyForValue(V value)
	{
		return valuesMap.get(value);
	}

	public int size()
	{
		return keysMap.size();
	}

	public boolean containsKey(T key)
	{
		return keysMap.containsKey(key);
	}
}
