package com.zzx.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame{
	int x = 200;
	int y = 200;
	public TankFrame(){
		setSize(800, 600);
		setResizable(false);
		setTitle("tank war game");
		setVisible(true);
		
		addKeyListener(new myKeyListener());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	@Override
	public void paint(Graphics g){
		System.out.println("go");
		g.fillRect(x, y, 50, 50);
		//x += 10;
		//y += 10;
	}
	
	class myKeyListener extends KeyAdapter{
		
		private boolean ku = false;
		private boolean kd = false;
		private boolean kl = false;
		private boolean kr = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				ku = true;
				break;
			case KeyEvent.VK_DOWN:
				kd = true;
				break;
			case KeyEvent.VK_LEFT:
				kl = true;
				break;
			case KeyEvent.VK_RIGHT:
				kr = true;
				break;
			default:
				break;
			}
			
			if (ku){
				y -= 10;
			};
			
			if (kd){
				y += 10;
			};
			
			if (kl){
				x -= 10;
			};
			
			if (kr){
				x += 10;
			};
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				ku = false;
				break;
			case KeyEvent.VK_DOWN:
				kd = false;
				break;
			case KeyEvent.VK_LEFT:
				kl = false;
				break;
			case KeyEvent.VK_RIGHT:
				kr = false;
				break;
			default:
				break;
			}
		}
	}

}
