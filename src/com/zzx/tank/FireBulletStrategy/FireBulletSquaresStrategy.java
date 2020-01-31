package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.AbstractFactory.BaseTank;
import com.zzx.tank.AbstractFactory.GameFactory;
import com.zzx.tank.Dir;
import com.zzx.tank.ResourceManage;

import java.util.ArrayList;

public class FireBulletSquaresStrategy implements FireBulletStrategy {
    @Override
    public void fire(BaseTank tank) {
        int bulletW = ResourceManage.getInstance().bullet[1].getUp().getWidth();
        int bulletH = ResourceManage.getInstance().bullet[1].getUp().getHeight();
        int bulletX = tank.getX() + tank.WIDTH/2 - bulletW/2;
        int bulletY = tank.getY() + tank.HEIGHT/2 - bulletH/2;
        GameFactory gameFactory = tank.getTF().gameFactory;
        //UP
        gameFactory.createBullet(bulletX, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX+bulletW*1, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX-bulletW*1, bulletY-bulletW*2, Dir.UP, tank.getTF(), tank.getGroup());
        //DOWN
        gameFactory.createBullet(bulletX, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX+bulletW*1, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX-bulletW*1, bulletY+bulletW*2, Dir.DOWN, tank.getTF(), tank.getGroup());
        //LEFT
        gameFactory.createBullet(bulletX-bulletW*2, bulletY, Dir.LEFT, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX-bulletW*2, bulletY+bulletW*1, Dir.LEFT, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX-bulletW*2, bulletY-bulletW*1, Dir.LEFT, tank.getTF(), tank.getGroup());
        //RIGHT
        gameFactory.createBullet(bulletX+bulletW*2, bulletY, Dir.RIGHT, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX+bulletW*2, bulletY+bulletW*1, Dir.RIGHT, tank.getTF(), tank.getGroup());
        gameFactory.createBullet(bulletX+bulletW*2, bulletY-bulletW*1, Dir.RIGHT, tank.getTF(), tank.getGroup());
        //UP_RIGHT
        gameFactory.createBullet(bulletX+bulletW*1, bulletY-bulletW*1, Dir.UP_RIGHT, tank.getTF(), tank.getGroup());
        //RIGHT_DOWN
        gameFactory.createBullet(bulletX+bulletW*1, bulletY+bulletW*1, Dir.RIGHT_DOWN, tank.getTF(), tank.getGroup());
        //DOWN_LEFT
        gameFactory.createBullet(bulletX-bulletW*1, bulletY+bulletW, Dir.DOWN_LEFT, tank.getTF(), tank.getGroup());
        //LEFT_UP
        gameFactory.createBullet(bulletX-bulletW*1, bulletY-bulletW*1, Dir.LEFT_UP, tank.getTF(), tank.getGroup());
    }
}
