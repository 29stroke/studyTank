package com.zzx.tank;


import com.zzx.tank.AbstractFactory.BaseTank;

import java.util.Random;

public class TankMain {

	public static void main(String[] args) throws Exception {
		// 背景音乐
		new Thread(()->new Audio("audio/LastSurprise.wav").loop()).start();

		// new游戏窗口
		TankFrame tf = new TankFrame();
		// 加入敌方坦克
		Random random = new Random();
		int badCount = Integer.valueOf((String)PropertyManage.getInstance().getValue("badInitCount"));
		for(int i=0; i<badCount; i++){
			int badX = random.nextInt(1020);
			int badY = random.nextInt(660);
			Dir badDir = Dir.values()[random.nextInt(8)];

			// 位置重叠问题待解决
			BaseTank badTank = tf.gameFactory.createTank(badX, badY, badDir, tf, Group.BAD);
			badTank.setMoveing(true);
			tf.getBadTankList().add(badTank);
		}

		// 刷新频率
		while(true){
			Thread.sleep(50);
			tf.repaint();
		}
	}

}
