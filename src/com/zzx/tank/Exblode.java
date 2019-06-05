package com.zzx.tank;

import java.awt.*;

public class Exblode {
    private int x;
    private int y;
    public int WIDTH = ResourceManage.explodes[0].getWidth();
    public int HEIGHT = ResourceManage.explodes[0].getHeight();
    private boolean live = true;
    private TankFrame tf;
    private int step = 0;


    public Exblode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g){
        if (step > 15){
            step=0;
        } else {
            g.drawImage(ResourceManage.explodes[step], 200, 400, null);
            step++;
        }
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
