package controller.listeners;

import java.util.EventListener;

import controller.HomeEvent;

public interface HomeEventListener extends EventListener
{
	public void homeEventListener(HomeEvent he);

}
