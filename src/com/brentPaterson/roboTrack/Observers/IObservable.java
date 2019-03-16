package com.brentPaterson.roboTrack.Observers;

import java.util.Observer;

public interface IObservable {
	public void addObserver(Observer obs);
	public void notifyObservers();
}
