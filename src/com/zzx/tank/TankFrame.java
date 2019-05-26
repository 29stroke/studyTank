package com.zzx.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame{
	private Tank myTank = new Tank(200, 200, Dir.UP);
	private Bullet bullet = new Bullet(220, 220, Dir.UP);

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
		myTank.paint(g);
		bullet.paint(g);
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
			if(!ku && !kr && !kd && !kl){
				myTank.setMoveing(false);
			} else {
				if(ku) myTank.setDir(Dir.UP);
				if(kr) myTank.setDir(Dir.RIGHT);
				if(kd) myTank.setDir(Dir.DOWN);
				if(kl) myTank.setDir(Dir.LEFT);
				if(ku && kr) myTank.setDir(Dir.UP_RIGHT);
				if(kr && kd) myTank.setDir(Dir.RIGHT_DOWN);
				if(kd && kl) myTank.setDir(Dir.DOWN_LEFT);
				if(kl && ku) myTank.setDir(Dir.LEFT_UP);
				myTank.setMoveing(true);
			}
		}
	}

}
