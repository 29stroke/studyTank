package com.zzx.tank.AbstractFactory;

import com.zzx.tank.Audio;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.TankFrame;

import java.awt.*;

public abstract class BaseExblode {
    protected int x;
    protected int y;
    public int WIDTH = ResourceManage.getInstance().explodes[1][0].getWidth();
    public int HEIGHT = ResourceManage.getInstance().explodes[1][0].getHeight();
    protected TankFrame tf;
    protected int step = 0;


    public BaseExblode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        // 爆炸音效
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public abstract void paint(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TankFrame getTf() {
        return tf;
    }

}
