package com.zzx.tank;

import com.zzx.tank.AbstractFactory.*;
import com.zzx.tank.FireBulletStrategy.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TankFrame extends Frame{
	public static final int GAME_WIDTH = Integer.valueOf((String)PropertyManage.getInstance().getValue("gameWidth"));
	public static final int GAME_HEIGHT = Integer.valueOf((String)PropertyManage.getInstance().getValue("gameHeight"));
	private List<BaseBullet> bulletList = new ArrayList<BaseBullet>(50);
	private List<BaseTank> badTankList = new ArrayList<BaseTank>(10);
	private List<BaseExblode> exblodeList = new ArrayList<BaseExblode>();
	private Random random = new Random();
	private int times = 1;
	public GameFactory gameFactory = new DefaultGameFactory();
	private HashMap<Integer, GameFactory> gameFactoryMap = new HashMap<Integer, GameFactory>();
	private int gameFactoryNo = 0;
	public BaseTank myTank = gameFactory.createTank(200, 200, Dir.UP, this, Group.GOOD);;

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

		// 加入一键换装
		gameFactoryMap.put(1, new Other01GameFactory());
		gameFactoryMap.put(2, new Other02GameFactory());
		gameFactoryMap.put(3, new Other03GameFactory());
		gameFactoryMap.put(4, new Other04GameFactory());
		gameFactoryMap.put(5, new Other05GameFactory());
		gameFactoryMap.put(6, new Other06GameFactory());
		gameFactoryMap.put(7, new Other07GameFactory());
		gameFactoryMap.put(8, new Other08GameFactory());
		gameFactoryMap.put(9, new Other09GameFactory());
		gameFactoryMap.put(10, new Other10GameFactory());
		gameFactoryMap.put(11, new Other00GameFactory());
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
		g.drawString("子弹数量：" + bulletList.size(), 10, 45);
		g.drawString("敌军数量：" + badTankList.size(), 10, 60);
		g.drawString("爆炸数量：" + exblodeList.size(), 10, 75);
		g.drawString("上左右下：WADS", 10, 90);
		g.drawString("射击：J", 10, 105);
		g.drawString("默认射击策略：Y", 10, 120);
		g.drawString("连续射击策略：U", 10, 135);
		g.drawString("八方射击策略：I", 10, 150);
		g.drawString("方形射击策略：O", 10, 165);
		g.drawString("清除射击策略：P", 10, 180);
		g.drawString("一键换装：H", 10, 195);
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
			if (times <= 10){
				times = times + 1;
			}
			int addBadTankCount = random.nextInt(badCount)*times;
			for(int i=0; i< addBadTankCount; i++){
				int badX = random.nextInt(1020);
				int badY = random.nextInt(660);
				Dir badDir = Dir.values()[random.nextInt(8)];

				// 位置重叠问题待解决
				BaseTank badTank = gameFactory.createTank(badX, badY, badDir, this, Group.BAD);
				badTank.setMoveing(true);
				badTankList.add(badTank);
			}
		}
	}

	public List<BaseBullet> getBulletList(){
		return this.bulletList;
	}

	public List<BaseTank> getBadTankList() {
		return this.badTankList;
	}

	public List<BaseExblode> getExblodeList() {
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


			// 设置发射策略
			if (KeyEvent.VK_U == key) {
				myTank.setFireBulletStrategy(new FireBulletContinuousStrategy());
			} else if (KeyEvent.VK_I == key) {
				myTank.setFireBulletStrategy(new FireBulletAllDirectionsStrategy());
			} else if (KeyEvent.VK_O == key) {
				myTank.setFireBulletStrategy(new FireBulletSquaresStrategy());
			} else if (KeyEvent.VK_P == key) {
				myTank.setFireBulletStrategy(new FireBulletClearStrategy());
			} else if (KeyEvent.VK_Y == key) {
				myTank.setFireBulletStrategy(new FireBulletDefaultStrategy());
			}

			// 一键换装
			if (KeyEvent.VK_H == key) {
				gameFactoryNo = gameFactoryNo + 1;
				gameFactory = gameFactoryMap.get(gameFactoryNo);

				// 己方坦克换装
				FireBulletStrategy fireBulletStrategy = myTank.getFireBulletStrategy();
				myTank = gameFactory.createTank(myTank.getX(), myTank.getY(), myTank.getDir(), myTank.getTF(), Group.GOOD);
				myTank.setFireBulletStrategy(fireBulletStrategy);

				// 敌方坦克换装
				List<BaseTank> newBadTankList = new ArrayList<BaseTank>(badTankList.size());
				for (int i=0; i<badTankList.size(); i++) {
					BaseTank oldBadTank = badTankList.get(i);
					BaseTank newBadTank = gameFactory.createTank(oldBadTank.getX(), oldBadTank.getY(), oldBadTank.getDir(), oldBadTank.getTF(), Group.BAD);
					newBadTank.setMoveing(true);
					newBadTankList.add(newBadTank);
				}
				badTankList.clear();
				badTankList.addAll(newBadTankList);

				// 一键换装标记清零
				if (gameFactoryNo == 11) {
					gameFactoryNo = 0;
				}
			}

			// 坦克移动音效
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
