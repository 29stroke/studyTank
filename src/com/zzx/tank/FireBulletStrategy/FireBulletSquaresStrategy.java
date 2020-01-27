package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.Bullet;
import com.zzx.tank.Dir;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.Tank;

import java.util.ArrayList;

public class FireBulletSquaresStrategy implements FireBulletStrategy {
    @Override
    public void fire(Tank tank) {
        int bulletW = ResourceManage.getInstance().bullet.getUp().getWidth();
        int bulletH = ResourceManage.getInstance().bullet.getUp().getHeight();
        int bulletX = tank.getX() + tank.WIDTH/2 - bulletW/2;
        int bulletY = tank.getY() + tank.HEIGHT/2 - bulletH/2;
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        //UP
        bullets.add(new Bullet(bulletX, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX+bulletW*1, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX-bulletW*1, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup()));
        //DOWN
        bullets.add(new Bullet(bulletX, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX+bulletW*1, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX-bulletW*1, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup()));
        //LEFT
        bullets.add(new Bullet(bulletX-bulletW*2, bulletY, Dir.LEFT, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX-bulletW*2, bulletY+bulletW*1, Dir.LEFT, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX-bulletW*2, bulletY-bulletW*1, Dir.LEFT, tank.getTF(), tank.getGroup()));
        //RIGHT
        bullets.add(new Bullet(bulletX+bulletW*2, bulletY, Dir.RIGHT, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX+bulletW*2, bulletY+bulletW*1, Dir.RIGHT, tank.getTF(), tank.getGroup()));
        bullets.add(new Bullet(bulletX+bulletW*2, bulletY-bulletW*1, Dir.RIGHT, tank.getTF(), tank.getGroup()));
        //UP_RIGHT
        bullets.add(new Bullet(bulletX+bulletW*1, bulletY-bulletW*1, Dir.UP_RIGHT, tank.getTF(), tank.getGroup()));
        //RIGHT_DOWN
        bullets.add(new Bullet(bulletX+bulletW*1, bulletY+bulletW*1, Dir.RIGHT_DOWN, tank.getTF(), tank.getGroup()));
        //DOWN_LEFT
        bullets.add(new Bullet(bulletX-bulletW*1, bulletY+bulletW, Dir.DOWN_LEFT, tank.getTF(), tank.getGroup()));
        //LEFT_UP
        bullets.add(new Bullet(bulletX-bulletW*1, bulletY-bulletW*1, Dir.LEFT_UP, tank.getTF(), tank.getGroup()));

        tank.getTF().getBulletList().addAll(bullets);
    }
}
