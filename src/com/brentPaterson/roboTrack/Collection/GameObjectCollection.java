package com.brentPaterson.roboTrack.Collection;

import java.util.Vector;

public class GameObjectCollection {
	private Vector theCollection;
	
	public GameObjectCollection() {
		theCollection = new Vector();
	}
	
	public void add(Object newObject) {
		theCollection.addElement(newObject);
	}
	
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator {
		private int curElementIndex;
		
		public GameObjectIterator() {
			curElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (theCollection.size() <= 0) return false;
			if (curElementIndex == theCollection.size() - 1) return false;
			return true;
		}
		
		public Object getNext() {
			curElementIndex++;
			return theCollection.elementAt(curElementIndex);
		}
	}
}
