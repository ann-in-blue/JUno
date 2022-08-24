package controller;

import java.util.EventObject;

import javax.swing.JPanel;

public class HomeEvent extends EventObject
{	
	private JPanel pannello;

	public HomeEvent(Object source, JPanel pannello) {
		super(source);
		this.pannello = pannello;
	}
	
	public JPanel getPannello() {
		return pannello;
	}

	public void setPannello(JPanel pannello) {
		this.pannello = pannello;
	}

	


}
