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
	Dir dir = Dir.UP;
	private static final int speed = 10;

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
		//System.out.println("go");
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

			setMainTankDir();

			switch (dir) {
				case UP:
					y -= speed;
					break;
				case RIGHT:
					x += speed;
					break;
				case DOWN:
					y += speed;
					break;
				case LEFT:
					x -= speed;
					break;
				case UP_RIGHT:
					y -= speed;
					x += speed;
					break;
				case RIGHT_DOWN:
					x += speed;
					y += speed;
					break;
				case DOWN_LEFT:
					y += speed;
					x -= speed;
					break;
				case LEFT_UP:
					x -= speed;
					y -= speed;
					break;
				default:
					break;
			}
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

			setMainTankDir();
		}

		private void setMainTankDir(){
			if(ku) dir = Dir.UP;
			if(kr) dir = Dir.RIGHT;
			if(kd) dir = Dir.DOWN;
			if(kl) dir = Dir.LEFT;
			if(ku && kr) dir = Dir.UP_RIGHT;
			if(kr && kd) dir = Dir.RIGHT_DOWN;
			if(kd && kl) dir = Dir.DOWN_LEFT;
			if(kl && ku) dir = Dir.LEFT_UP;
		}
	}

}
