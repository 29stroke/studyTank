package com.zzx.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame{
	private Tank myTank = new Tank(200, 200, Dir.UP, this, Group.GOOD);
	private List<Bullet> bulletList = new ArrayList<Bullet>(50);
	private List<Tank> badTankList = new ArrayList<Tank>(10);
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;

	public TankFrame(){
		setSize(GAME_WIDTH, GAME_HEIGHT);
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

	Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g){
		Color bakColor = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹数量：" + bulletList.size(), 10, 40);
		g.drawString("敌军数量：" + badTankList.size(), 10, 55);
		g.setColor(bakColor);

		myTank.paint(g);
		for (int i=0; i<badTankList.size(); i++){
			badTankList.get(i).paint(g);

		}
//		for(Bullet bullet:bulletList){
//			bullet.paint(g);
//		}
		for(int i=0; i< bulletList.size(); i++){
			bulletList.get(i).paint(g);
		}

		//碰撞检测
		for(int i=0; i<badTankList.size(); i++){
			for(int j=0; j<bulletList.size(); j++){
				bulletList.get(j).collideWith(badTankList.get(i), g);
			}
		}
	}

	public List getBulletList(){
		return this.bulletList;
	}

	public static int getGameWidth() {
		return GAME_WIDTH;
	}

	public static int getGameHeight() {
		return GAME_HEIGHT;
	}


	public void addBadTankList(Tank badTank) {
		this.badTankList.add(badTank);
	}

	public List<Tank> getBadTankList() {
		return badTankList;
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
				case KeyEvent.VK_W:
					ku = true;
					break;
				case KeyEvent.VK_S:
					kd = true;
					break;
				case KeyEvent.VK_A:
					kl = true;
					break;
				case KeyEvent.VK_D:
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
			case KeyEvent.VK_W:
				ku = false;
				break;
			case KeyEvent.VK_S:
				kd = false;
				break;
			case KeyEvent.VK_A:
				kl = false;
				break;
			case KeyEvent.VK_D:
				kr = false;
				break;
			default:
				break;
			}

			if (KeyEvent.VK_J == key) {
				myTank.fire();
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
