package com.zzx.tank;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private Dir dir;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SPEED = 10;
    private boolean live = true;
    private TankFrame tf = null;


    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g){
        if (live){
            Color bakColor = g.getColor();
            g.setColor(Color.red);
            g.fillRect(x, y, WIDTH, HEIGHT);
            g.setColor(bakColor);
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
}
