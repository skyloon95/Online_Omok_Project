package kr.devdogs.omok.ingame;

import javax.swing.JPanel;

public class RepaintThread implements Runnable{
	private JPanel screen;
	
	public RepaintThread(JPanel screen) {
		this.screen = screen;
	}
	
	@Override
	public void run() {
		while(true) {
			screen.repaint();
			
			try {
				Thread.sleep(1000/60);
			}catch(Exception e) {
				
			}
		}
	}
}
