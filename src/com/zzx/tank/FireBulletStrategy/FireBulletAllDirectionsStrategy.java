package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.Bullet;
import com.zzx.tank.Dir;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.Tank;

import java.util.ArrayList;

public class FireBulletAllDirectionsStrategy implements FireBulletStrategy {
    @Override
    public void fire(Tank tank) {
        int bulletX = tank.getX() + tank.WIDTH/2 - ResourceManage.getInstance().bullet.getUp().getWidth()/2;
        int bulletY = tank.getY() + tank.HEIGHT/2 - ResourceManage.getInstance().bullet.getUp().getHeight()/2;
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        //UP
        bullets.add(new Bullet(bulletX, bulletY, Dir.UP, tank.getTF(), tank.getGroup()));
        //DOWN
        bullets.add(new Bullet(bulletX, bulletY, Dir.DOWN, tank.getTF(), tank.getGroup()));
        //LEFT
        bullets.add(new Bullet(bulletX, bulletY, Dir.LEFT, tank.getTF(), tank.getGroup()));
        //RIGHT
        bullets.add(new Bullet(bulletX, bulletY, Dir.RIGHT, tank.getTF(), tank.getGroup()));
        //UP_RIGHT
        bullets.add(new Bullet(bulletX, bulletY, Dir.UP_RIGHT, tank.getTF(), tank.getGroup()));
        //RIGHT_DOWN
        bullets.add(new Bullet(bulletX, bulletY, Dir.RIGHT_DOWN, tank.getTF(), tank.getGroup()));
        //DOWN_LEFT
        bullets.add(new Bullet(bulletX, bulletY, Dir.DOWN_LEFT, tank.getTF(), tank.getGroup()));
        //LEFT_UP
        bullets.add(new Bullet(bulletX, bulletY, Dir.LEFT_UP, tank.getTF(), tank.getGroup()));

        tank.getTF().getBulletList().addAll(bullets);
    }
}
