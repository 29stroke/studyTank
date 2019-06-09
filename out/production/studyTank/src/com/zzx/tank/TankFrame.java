package com.zzx.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankFrame extends Frame{
	public static final int GAME_WIDTH = Integer.valueOf((String)PropertyManage.getInstance().getValue("gameWidth"));
	public static final int GAME_HEIGHT = Integer.valueOf((String)PropertyManage.getInstance().getValue("gameHeight"));
	private Tank myTank = new Tank(200, 200, Dir.UP, this, Group.GOOD);
	private List<Bullet> bulletList = new ArrayList<Bullet>(50);
	private List<Tank> badTankList = new ArrayList<Tank>(10);
	private List<Exblode> exblodeList = new ArrayList<Exblode>();
	private Random random = new Random();

	/**
	 * 游戏窗口
	 */
	public TankFrame(){
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war game");
		setVisible(true);

		// 加入键盘监听
		addKeyListener(new myKeyListener());
		// 加入exit操作
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	Image offScreenImage = null;
	/**
	 * 解决屏幕闪烁问题（全部画出加载的方式）
	 */
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

	/**
	 * 屏幕刷新时的处理
	 */
	@Override
	public void paint(Graphics g){
		Color bakColor = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹数量：" + bulletList.size(), 10, 40);
		g.drawString("敌军数量：" + badTankList.size(), 10, 55);
		g.drawString("爆炸数量：" + exblodeList.size(), 10, 70);
		g.setColor(bakColor);

		// 己方坦克画出
		myTank.paint(g);
		// 敌方坦克画出
		for (int i=0; i<badTankList.size(); i++){
			badTankList.get(i).paint(g);
		}

		// 画出子弹
		for(int i=0; i< bulletList.size(); i++){
			bulletList.get(i).paint(g);
		}

		// 敌方坦克和子弹碰撞检测
		for(int i=0; i<badTankList.size(); i++){
			for(int j=0; j<bulletList.size(); j++){
				bulletList.get(j).collideWith(badTankList.get(i), g);
			}
		}

		// 爆炸画出
		for(int i=0; i< exblodeList.size(); i++){
			exblodeList.get(i).paint(g);
		}

		// 敌方坦克消灭后坦克随机生成
		if(badTankList.size()==0){
			int badCount = Integer.valueOf((String)PropertyManage.getInstance().getValue("badRanCount"));
			int addBadTankCount = random.nextInt(badCount);
			for(int i=0; i< addBadTankCount; i++){
				int badX = random.nextInt(1020);
				int badY = random.nextInt(660);
				Dir badDir = Dir.values()[random.nextInt(8)];

				// 位置重叠问题待解决
				Tank badTank = new Tank(badX, badY, badDir, this, Group.BAD);
				badTank.setMoveing(true);
				badTankList.add(badTank);
			}
		}
	}

	public List<Bullet> getBulletList(){
		return this.bulletList;
	}

	public List<Tank> getBadTankList() {
		return this.badTankList;
	}

	public List<Exblode> getExblodeList() {
		return this.exblodeList;
	}

	/**
	 * 键盘处理
	 */
	class myKeyListener extends KeyAdapter{
		private boolean ku = false;
		private boolean kd = false;
		private boolean kl = false;
		private boolean kr = false;

		/**
		 * 键盘按下处理
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			// 方向设定
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
			// 好吵
			// new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		}

		/**
		 * 键盘松开处理
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			// 方向设定
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

			// 发射子弹
			if (KeyEvent.VK_J == key) {
				myTank.fire();
			}

			setMainTankDir();
		}

		/**
		 * 设定方向&移动
		 */
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
