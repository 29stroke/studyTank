package com.zzx.tank;

import com.zzx.tank.dto.ImageDto;

import java.awt.*;

public class Bullet {
    public int WIDTH = ResourceManage.getInstance().bullet.getUp().getWidth();
    public int HEIGHT = ResourceManage.getInstance().bullet.getUp().getHeight();
    public Rectangle rectangle = new Rectangle();
    private static final int SPEED = Integer.valueOf((String)PropertyManage.getInstance().getValue("bulletSpeed"));
    private int x;
    private int y;
    private Dir dir;
    private boolean live = true;
    private TankFrame tf;
    private Group group;

    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = this.WIDTH;
        this.rectangle.height = this .HEIGHT;
    }

    /**
     * 画出子弹
     */
    public void paint(Graphics g){
        if (live){
            // 根据方向画出子弹
            ImageDto bullet = ResourceManage.getInstance().bullet;
            switch (dir) {
                case UP:
                    g.drawImage(bullet.getUp(), x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(bullet.getRight(), x, y, null);
                    break;
                case DOWN:
                    g.drawImage(bullet.getDown(), x, y, null);
                    break;
                case LEFT:
                    g.drawImage(bullet.getLeft(), x, y, null);
                    break;
                case UP_RIGHT:
                    g.drawImage(bullet.getRightUp(), x, y, null);
                    break;
                case RIGHT_DOWN:
                    g.drawImage(bullet.getRightDown(), x, y, null);
                    break;
                case DOWN_LEFT:
                    g.drawImage(bullet.getLeftDown(), x, y, null);
                    break;
                case LEFT_UP:
                    g.drawImage(bullet.getLeftUp(), x, y, null);
                    break;
                default:
                    break;
            }
            move();
        } else {
            // 删除失去生命的子弹
            tf.getBulletList().remove(this);
        }

    }

    /**
     * 移动子弹
     */
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
    public void collideWith(Tank tank, Graphics g){
        //非同组跳过
        if(this.group == tank.getGroup()) return;
        //碰撞后双方殒命
        if(this.rectangle.intersects(tank.rectangle)){
            int exblodeX = tank.getX() + tank.WIDTH/2 - ResourceManage.getInstance().explodes[0].getWidth()/2;
            int exblodeY = tank.getY() + tank.HEIGHT/2 - ResourceManage.getInstance().explodes[0].getHeight()/2;
            this.setLive(false);
            tank.setLive(false);
            tf.getExblodeList().add(new Exblode(exblodeX, exblodeY, tf));
        }
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }
}
