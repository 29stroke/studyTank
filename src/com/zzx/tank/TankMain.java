package com.zzx.tank;


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
		
		while(true){
			Thread.sleep(50);
			tf.repaint();
		}
	}

}
