package com.findroid.interestingview.util;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * CopyOnWriteMap，线程安全
 * create by zcj
 * 770233070@qq.com
 */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
	private volatile Map<K, V> interalMap;

	public CopyOnWriteMap() {
		interalMap = new HashMap<K, V>();
	}

	@Override
	public int size() {
		return interalMap.size();
	}

	@Override
	public boolean isEmpty() {
		return interalMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return interalMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return interalMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return interalMap.get(key);
	}

	@Override
	public V put(K key, V value) {
		synchronized (this) {
			Map<K, V> map = new HashMap<>(interalMap);
			V val = map.put(key, value);
			interalMap = map;
			return val;
		}
	}

	@Override
	public V remove(Object key) {
		return null;
	}

	@Override
	public void putAll(@NonNull Map<? extends K, ? extends V> m) {
		synchronized (this) {
			Map<K, V> map = new HashMap<>(interalMap);
			map.putAll(m);
			interalMap = map;
		}
	}

	@Override
	public void clear() {
		interalMap.clear();
	}

	@NonNull
	@Override
	public Set<K> keySet() {
		return interalMap.keySet();
	}

	@NonNull
	@Override
	public Collection<V> values() {
		return interalMap.values();
	}

	@NonNull
	@Override
	public Set<Entry<K, V>> entrySet() {
		return interalMap.entrySet();
	}
}
