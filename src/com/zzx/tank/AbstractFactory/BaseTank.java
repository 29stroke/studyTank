package com.zzx.tank.AbstractFactory;

import com.zzx.tank.*;
import com.zzx.tank.FireBulletStrategy.FireBulletDefaultStrategy;
import com.zzx.tank.FireBulletStrategy.FireBulletStrategy;
import com.zzx.tank.dto.ImageDto;

import java.awt.*;
import java.util.Random;

public abstract class BaseTank {
    public int WIDTH = ResourceManage.getInstance().goodTank[0].getUp().getWidth();
    public int HEIGHT = ResourceManage.getInstance().goodTank[0].getUp().getHeight();
    public Rectangle rectangle = new Rectangle();
    private static final int SPEED = Integer.valueOf((String)PropertyManage.getInstance().getValue("tankSpeed"));
    protected int x = 200;
    protected int y = 200;
    protected Dir dir = Dir.UP;
    protected TankFrame tf = null;
    private boolean moveing = false;
    protected boolean live = true;
    private Random random = new Random();
    protected Group group;
    private FireBulletStrategy fireBulletStrategy = new FireBulletDefaultStrategy();

    public BaseTank(int x, int y, Dir dir, TankFrame tf, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.rectangle.x = x;
        this.rectangle.y = y;
        this.rectangle.width = this.WIDTH;
        this.rectangle.height = this .HEIGHT;
    }

    /**
     * 画出坦克
     */
    public abstract void paint(Graphics g);

    /**
     * 发射子弹
     */
    public void fire(){
        // 设置发射子弹
        fireBulletStrategy.fire(this);
        // 设置子弹音效
        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    /**
     * 移动坦克
     */
    protected void move(){
        if(moveing){
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

            if (!boundsCheck()){
                if (this.group == Group.BAD) {
                    if (random.nextInt(30) > 28) this.fire();
                    if (random.nextInt(100) > 98) this.randomDir();
                }
            }
        }
    }

    /**
     * 坦克移动边界检测
     */
    private boolean boundsCheck(){
        if(this.getX()>tf.GAME_WIDTH-this.WIDTH || this.getY()>tf.GAME_HEIGHT-this.HEIGHT||
                this.getX()<0 || this.getY()<0){
            switch (dir) {
                case UP:
                    this.setDir(Dir.DOWN);
                    break;
                case RIGHT:
                    this.setDir(Dir.LEFT);
                    break;
                case DOWN:
                    this.setDir(Dir.UP);
                    break;
                case LEFT:
                    this.setDir(Dir.RIGHT);
                    break;
                case UP_RIGHT:
                    this.setDir(Dir.DOWN_LEFT);
                    break;
                case RIGHT_DOWN:
                    this.setDir(Dir.LEFT_UP);
                    break;
                case DOWN_LEFT:
                    this.setDir(Dir.UP_RIGHT);
                    break;
                case LEFT_UP:
                    this.setDir(Dir.RIGHT_DOWN);
                    break;
                default:
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 随机设定坦克方向
     */
    public void randomDir(){
        this.setDir(Dir.values()[random.nextInt(8)]);
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean getLive() { return this.live; }

    public void setMoveing(boolean moveing) {
        this.moveing = moveing;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    public TankFrame getTF() {
        return tf;
    }

    public void setFireBulletStrategy(FireBulletStrategy strategy) {
        this.fireBulletStrategy = strategy;
    }

    public FireBulletStrategy getFireBulletStrategy() {
        return fireBulletStrategy;
    }
}
