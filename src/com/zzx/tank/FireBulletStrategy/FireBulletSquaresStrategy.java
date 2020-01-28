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
        //UP
        new Bullet(bulletX, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup());
        new Bullet(bulletX+bulletW*1, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup());
        new Bullet(bulletX-bulletW*1, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup());
        //DOWN
        new Bullet(bulletX, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup());
        new Bullet(bulletX+bulletW*1, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup());
        new Bullet(bulletX-bulletW*1, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup());
        //LEFT
        new Bullet(bulletX-bulletW*2, bulletY, Dir.LEFT, tank.getTF(), tank.getGroup());
        new Bullet(bulletX-bulletW*2, bulletY+bulletW*1, Dir.LEFT, tank.getTF(), tank.getGroup());
        new Bullet(bulletX-bulletW*2, bulletY-bulletW*1, Dir.LEFT, tank.getTF(), tank.getGroup());
        //RIGHT
        new Bullet(bulletX+bulletW*2, bulletY, Dir.RIGHT, tank.getTF(), tank.getGroup());
        new Bullet(bulletX+bulletW*2, bulletY+bulletW*1, Dir.RIGHT, tank.getTF(), tank.getGroup());
        new Bullet(bulletX+bulletW*2, bulletY-bulletW*1, Dir.RIGHT, tank.getTF(), tank.getGroup());
        //UP_RIGHT
        new Bullet(bulletX+bulletW*1, bulletY-bulletW*1, Dir.UP_RIGHT, tank.getTF(), tank.getGroup());
        //RIGHT_DOWN
        new Bullet(bulletX+bulletW*1, bulletY+bulletW*1, Dir.RIGHT_DOWN, tank.getTF(), tank.getGroup());
        //DOWN_LEFT
        new Bullet(bulletX-bulletW*1, bulletY+bulletW, Dir.DOWN_LEFT, tank.getTF(), tank.getGroup());
        //LEFT_UP
        new Bullet(bulletX-bulletW*1, bulletY-bulletW*1, Dir.LEFT_UP, tank.getTF(), tank.getGroup());
    }
}
