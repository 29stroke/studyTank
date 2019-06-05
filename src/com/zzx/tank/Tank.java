package com.zzx.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x = 200;
    private int y = 200;
    private Dir dir = Dir.UP;
    private TankFrame tf = null;
    private boolean moveing = false;
    public int WIDTH;
    public int HEIGHT;
    public static final int SPEED = 5;
    private boolean live = true;
    private Random random = new Random();
    private Group group;

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public void paint(Graphics g){
        if (live){
            //Color bakColor = g.getColor();
            //g.setColor(Color.BLUE);
            //g.fillRect(x, y, WIDTH, HEIGHT);
            //g.setColor(bakColor);
            if (Dir.UP == dir || Dir.DOWN == dir || Dir.LEFT == dir || Dir.RIGHT == dir){
                WIDTH = ResourceManage.tankU.getWidth();
                HEIGHT = ResourceManage.tankU.getHeight();
            } else {
                WIDTH = ResourceManage.tankRU.getWidth();
                HEIGHT = ResourceManage.tankRU.getHeight();
            }
            switch (dir) {
                case UP:
                    g.drawImage(ResourceManage.tankU, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceManage.tankR, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceManage.tankD, x, y, null);
                    break;
                case LEFT:
                    g.drawImage(ResourceManage.tankL, x, y, null);
                    break;
                case UP_RIGHT:
                    g.drawImage(ResourceManage.tankRU, x, y, null);
                    break;
                case RIGHT_DOWN:
                    g.drawImage(ResourceManage.tankRD, x, y, null);
                    break;
                case DOWN_LEFT:
                    g.drawImage(ResourceManage.tankLD, x, y, null);
                    break;
                case LEFT_UP:
                    g.drawImage(ResourceManage.tankLU, x, y, null);
                    break;
                default:
                    break;
            }
            move();
        } else {
            tf.getBadTankList().remove(this);
        }
    }

    public void fire(){
        int bulletX = 0;
        int bulletY = 0;
        if (Dir.UP == dir || Dir.DOWN == dir || Dir.LEFT == dir || Dir.RIGHT == dir){
            bulletX = this.x + this.WIDTH/2 - ResourceManage.bulletU.getWidth()/2;
            bulletY = this.y + this.HEIGHT/2 - ResourceManage.bulletU.getHeight()/2;
        } else {
            bulletX = this.x + this.WIDTH/2 - ResourceManage.missileRU.getWidth()/2;
            bulletY = this.y + this.HEIGHT/2 - ResourceManage.missileRU.getHeight()/2;
        }

        tf.getBulletList().add(new Bullet(bulletX, bulletY, dir, tf, group));
    }

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

            if(this.getX()>tf.getGameWidth()-this.WIDTH || this.getY()>tf.getGameHeight()-this.HEIGHT||
                    this.getX()<this.WIDTH || this.getY()<this.HEIGHT){
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
            }

            if(random.nextInt(10)>8) this.fire();
        }
    }

    public void randomDir(){
        if(random.nextInt(100) > 98){
            int dirInt = random.nextInt(8);
            Dir newDir = this.getDir();
            switch (dirInt) {
                case 1:
                    newDir = Dir.UP;
                    break;
                case 3:
                    newDir = Dir.RIGHT;
                    break;
                case 5:
                    newDir = Dir.DOWN;
                    break;
                case 7:
                    newDir = Dir.LEFT;
                    break;
                case 2:
                    newDir = Dir.UP_RIGHT;
                    break;
                case 4:
                    newDir = Dir.RIGHT_DOWN;
                    break;
                case 6:
                    newDir = Dir.DOWN_LEFT;
                    break;
                case 8:
                    newDir = Dir.LEFT_UP;
                    break;
                default:
                    break;
            }
            this.setDir(newDir);
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoveing() {
        return moveing;
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
