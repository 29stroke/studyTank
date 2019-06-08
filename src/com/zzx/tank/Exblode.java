package com.zzx.tank;

import java.awt.*;
import com.zzx.tank.Audio;

public class Exblode {
    private int x;
    private int y;
    public int WIDTH = ResourceManage.explodes[0].getWidth();
    public int HEIGHT = ResourceManage.explodes[0].getHeight();
    private TankFrame tf;
    private int step = 0;


    public Exblode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){
        if (step > 15){
            //step=0;
            tf.getExblodeList().remove(this);
        } else {

            g.drawImage(ResourceManage.explodes[step], this.x, this.y, null);
            step++;
        }
    }
}
