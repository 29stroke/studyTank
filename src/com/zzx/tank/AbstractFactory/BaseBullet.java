package com.zzx.tank.AbstractFactory;

import com.zzx.tank.*;
import com.zzx.tank.dto.ImageDto;

import java.awt.*;

public abstract class BaseBullet  {
    public int WIDTH = ResourceManage.getInstance().bullet[0].getUp().getWidth();
    public int HEIGHT = ResourceManage.getInstance().bullet[0].getUp().getHeight();
    public Rectangle rectangle = new Rectangle();
    private static final int SPEED = Integer.valueOf((String)PropertyManage.getInstance().getValue("bulletSpeed"));
    protected int x;
    protected int y;
    protected Dir dir;
    protected boolean live = true;
    protected TankFrame tf;
    protected Group group;

    public BaseBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = this.WIDTH;
        this.rectangle.height = this .HEIGHT;
        tf.getBulletList().add(this);
    }

    /**
     * 画出子弹
     */
    public abstract void paint(Graphics g);

    /**
     * 移动子弹
     */
    protected void move(){
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case UP_RIGHT:
                y -= SPEED;
                x += SPEED;
                break;
            case RIGHT_DOWN:
                x += SPEED;
                y += SPEED;
                break;
            case DOWN_LEFT:
                y += SPEED;
                x -= SPEED;
                break;
            case LEFT_UP:
                x -= SPEED;
                y -= SPEED;
                break;
            default:
                break;
        }

        this.rectangle.x = x;
        this.rectangle.y = y;

        // 子弹飞出边界殒命
        if(x<0 || y<0 || x>tf.getWidth() || y>tf.getHeight()){
            this.live = false;
        }
    }

    /**
     * 子弹于坦克的碰撞检测
     */
    public void collideWith(BaseTank tank, Graphics g){
        //非同组跳过
        if(this.group == tank.getGroup()) return;
        //碰撞后双方殒命
        if(this.rectangle.intersects(tank.rectangle)){
            int exblodeX = tank.getX() + tank.WIDTH/2 - ResourceManage.getInstance().explodes[1][0].getWidth()/2;
            int exblodeY = tank.getY() + tank.HEIGHT/2 - ResourceManage.getInstance().explodes[1][0].getHeight()/2;
            this.setLive(false);
            tank.setLive(false);
            tf.getExblodeList().add(tf.gameFactory.createExblode(exblodeX, exblodeY, tf));
        }
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public TankFrame getTf() {
        return tf;
    }
}
