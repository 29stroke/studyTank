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
		TankFrame tf = new TankFrame();
		Random random = new Random();
		for(int i=0; i<10; i++){
			int badX = random.nextInt(700) + 50;
			int badY = random.nextInt(500) + 50;
			int dirInt = random.nextInt(8);
			Dir badDir = Dir.UP;
			switch (dirInt) {
				case 1:
					badDir = Dir.UP;
					break;
				case 3:
					badDir = Dir.RIGHT;
					break;
				case 5:
					badDir = Dir.DOWN;
					break;
				case 7:
					badDir = Dir.LEFT;
					break;
				case 2:
					badDir = Dir.UP_RIGHT;
					break;
				case 4:
					badDir = Dir.RIGHT_DOWN;
					break;
				case 6:
					badDir = Dir.DOWN_LEFT;
					break;
				case 8:
					badDir = Dir.LEFT_UP;
					break;
				default:
					break;
			}

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
