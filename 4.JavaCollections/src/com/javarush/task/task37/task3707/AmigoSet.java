package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
	private final static Object PRESENT = new Object();
	
	private transient HashMap<E, Object> map;
	
	public AmigoSet() {
		this.map = new HashMap<>();
	}
	
	public AmigoSet(Collection<? extends E> collection) {
		int capacity = (int) (Math.ceil(collection.size() / .75f));
		this.map = new HashMap<>(capacity < 16 ? 16 : capacity);
		addAll(collection);
	}
	
	@Override
	public boolean add(Object e) {
		if (map.containsKey(e))
		{
			return false;
		} else
		{
			map.put((E) e, PRESENT);
			return true;
		}
	}
	
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	@Override
	public boolean contains(Object o) {
		return super.contains(o);
	}
	
	@Override
	public boolean remove(Object o) {
		return super.remove(o);
	}
	
	@Override
	public void clear() {
		map.clear();
	}
	
	@Override
	public Iterator iterator() {
		return map.keySet().iterator();
	}
	
	@Override
	public int size() {
		return map.size();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		super.clone();
		try
		{
			AmigoSet<E> cloneAmigoSet = new AmigoSet<>();
			cloneAmigoSet.map = (HashMap<E, Object>) map.clone();
			return cloneAmigoSet;
		} catch (Exception e)
		{
			throw new InternalError();
		}
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
		float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
		
		out.writeInt(capacity);
		out.writeFloat(loadFactor);
		out.writeInt(size());
		
		out.defaultWriteObject();
		
		for (E e : map.keySet()) {
			out.writeObject(e);
		}
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		int capacity = in.readInt();
		float loadFactor = in.readFloat();
		int size = in.readInt();
		
		map = new HashMap<>(capacity, loadFactor);
		
		in.defaultReadObject();
		
		for (int i = 0; i < size; i++) {
			map.put((E) in.readObject(), PRESENT);
		}
	}
}
