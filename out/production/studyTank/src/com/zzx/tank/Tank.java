package com.zzx.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    public int WIDTH = ResourceManage.goodTankU.getWidth();
    public int HEIGHT = ResourceManage.goodTankU.getHeight();
    public Rectangle rectangle = new Rectangle();
    private static final int SPEED = Integer.valueOf((String)PropertyManage.getInstance().getValue("tankSpeed"));
    private int x = 200;
    private int y = 200;
    private Dir dir = Dir.UP;
    private TankFrame tf = null;
    private boolean moveing = false;
    private boolean live = true;
    private Random random = new Random();
    private Group group;

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group){
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
    public void paint(Graphics g){
        if (live){
            // 使用新坦克图片后不需要这个操作
//            if (Dir.UP == dir || Dir.DOWN == dir || Dir.LEFT == dir || Dir.RIGHT == dir){
//                WIDTH = ResourceManage.tankU.getWidth();
//                HEIGHT = ResourceManage.tankU.getHeight();
//            } else {
//                WIDTH = ResourceManage.tankRU.getWidth();
//                HEIGHT = ResourceManage.tankRU.getHeight();
//            }
            // 根据方向画出坦克图片
            switch (dir) {
                case UP:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankU : ResourceManage.badTankU, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankR : ResourceManage.badTankR, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankD : ResourceManage.badTankD, x, y, null);
                    break;
                case LEFT:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankL : ResourceManage.badTankL, x, y, null);
                    break;
                case UP_RIGHT:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankRU : ResourceManage.badTankRU, x, y, null);
                    break;
                case RIGHT_DOWN:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankRD : ResourceManage.badTankRD, x, y, null);
                    break;
                case DOWN_LEFT:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankLD : ResourceManage.badTankLD, x, y, null);
                    break;
                case LEFT_UP:
                    g.drawImage(this.group==Group.GOOD ? ResourceManage.goodTankLU : ResourceManage.badTankLU, x, y, null);
                    break;
                default:
                    break;
            }
            move();
        } else {
            // 删除失去生命的坦克
            tf.getBadTankList().remove(this);
        }
    }

    /**
     * 发射子弹
     */
    public void fire(){
        int bulletX = this.x + this.WIDTH/2 - ResourceManage.bulletU.getWidth()/2;
        int bulletY = this.y + this.HEIGHT/2 - ResourceManage.bulletU.getHeight()/2;
        // 使用新坦克图片后不需要这个操作
//        if (Dir.UP == dir || Dir.DOWN == dir || Dir.LEFT == dir || Dir.RIGHT == dir){
//            bulletX = this.x + this.WIDTH/2 - ResourceManage.bulletU.getWidth()/2;
//            bulletY = this.y + this.HEIGHT/2 - ResourceManage.bulletU.getHeight()/2;
//        } else {
//            bulletX = this.x + this.WIDTH/2 - ResourceManage.missileRU.getWidth()/2;
//            bulletY = this.y + this.HEIGHT/2 - ResourceManage.missileRU.getHeight()/2;
//        }

        tf.getBulletList().add(new Bullet(bulletX, bulletY, dir, tf, group));
        // 发射子弹音效 好吵
        // if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    /**
     * 移动坦克
     */
    private void move(){
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
}
