package com.zzx.tank;

import java.awt.*;

public class Bullet {
    public int WIDTH = ResourceManage.bulletU.getWidth();
    public int HEIGHT = ResourceManage.bulletU.getHeight();
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
        // 使用新子弹图片后不需要这个操作
//        if (Dir.UP == dir || Dir.DOWN == dir || Dir.LEFT == dir || Dir.RIGHT == dir){
//            WIDTH = ResourceManage.bulletU.getWidth();
//            HEIGHT = ResourceManage.bulletU.getHeight();
//        } else {
//            WIDTH = ResourceManage.missileRU.getWidth();
//            HEIGHT = ResourceManage.missileRU.getHeight();
//        }

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
            //Color bakColor = g.getColor();
            //g.setColor(Color.red);
            //g.fillRect(x, y, WIDTH, HEIGHT);
            //g.setColor(bakColor);
            // 根据方向画出子弹
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
            int exblodeX = tank.getX() + tank.WIDTH/2 - ResourceManage.explodes[0].getWidth()/2;
            int exblodeY = tank.getY() + tank.HEIGHT/2 - ResourceManage.explodes[0].getHeight()/2;
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
