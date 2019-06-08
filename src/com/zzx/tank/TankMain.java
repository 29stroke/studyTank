package com.zzx.tank;


import java.util.Random;

public class TankMain {

	public static void main(String[] args) throws Exception {
//		Frame frame = new Frame();
//		frame.setSize(800, 600);
//		frame.setResizable(false);
//		frame.setTitle("tank war game");
//		frame.setVisible(true);
//		
//		frame.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e){
//				System.exit(0);
//			}
//		});

		// 背景音乐 // 好吵
		//new Thread(()->new Audio("audio/war1.wav").loop()).start();

		TankFrame tf = new TankFrame();
		Random random = new Random();
		for(int i=0; i<10; i++){
			int badX = random.nextInt(700) + 50;
			int badY = random.nextInt(500) + 50;
			Dir badDir = Dir.values()[random.nextInt(8)];

			// 位置重叠问题待解决
			Tank badTank = new Tank(badX, badY, badDir, tf, Group.BAD);
			badTank.setMoveing(true);
			tf.addBadTankList(badTank);
		}
		while(true){
			Thread.sleep(50);
			tf.repaint();
		}
	}

}
