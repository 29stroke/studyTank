package com.zzx.tank;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private Dir dir;
    public int WIDTH;
    public int HEIGHT;
    private static final int SPEED = 10;
    private boolean live = true;
    private TankFrame tf;
    private Group group;


    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        if (Dir.UP == dir || Dir.DOWN == dir || Dir.LEFT == dir || Dir.RIGHT == dir){
            WIDTH = ResourceManage.bulletU.getWidth();
            HEIGHT = ResourceManage.bulletU.getHeight();
        } else {
            WIDTH = ResourceManage.missileRU.getWidth();
            HEIGHT = ResourceManage.missileRU.getHeight();
        }
    }

    public void paint(Graphics g){
        if (live){
            //Color bakColor = g.getColor();
            //g.setColor(Color.red);
            //g.fillRect(x, y, WIDTH, HEIGHT);
            //g.setColor(bakColor);
            switch (dir) {
                case UP:
                    g.drawImage(ResourceManage.bulletU, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceManage.bulletR, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceManage.bulletD, x, y, null);
                    break;
                case LEFT:
                    g.drawImage(ResourceManage.bulletL, x, y, null);
                    break;
                case UP_RIGHT:
                    g.drawImage(ResourceManage.missileRU, x, y, null);
                    break;
                case RIGHT_DOWN:
                    g.drawImage(ResourceManage.missileRD, x, y, null);
                    break;
                case DOWN_LEFT:
                    g.drawImage(ResourceManage.missileLD, x, y, null);
                    break;
                case LEFT_UP:
                    g.drawImage(ResourceManage.missileLU, x, y, null);
                    break;
                default:
                    break;
            }
            move();
        } else {
            tf.getBulletList().remove(this);
        }

    }
    private void move(){
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

        if(x<0 || y<0 || x>tf.getWidth() || y>tf.getHeight()){
            this.live = false;
        }
    }

    public void collideWith(Tank tank, Graphics g){
        if(this.group == tank.getGroup()) return;

        Rectangle rectBullet = new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGHT);
        if(rectBullet.intersects(rectTank)){
            int exblodeX = tank.getX() + tank.WIDTH/2 - ResourceManage.explodes[0].getWidth()/2;
            int exblodeY = tank.getY() + tank.HEIGHT/2 - ResourceManage.explodes[0].getHeight()/2;
            this.setLive(false);
            tank.setLive(false);
            tf.addExblodeList(new Exblode(exblodeX, exblodeY, tf));
        }
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }
}
