package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.AbstractFactory.BaseTank;
import com.zzx.tank.FireBulletStrategy.FireBulletStrategy;
import com.zzx.tank.ResourceManage;

import java.util.ArrayList;
import java.util.List;

public class FireBulletDefaultStrategy implements FireBulletStrategy {
    @Override
    public void fire(BaseTank tank) {
        int bulletX = tank.getX() + tank.WIDTH/2 - ResourceManage.getInstance().bullet[1].getUp().getWidth()/2;
        int bulletY = tank.getY() + tank.HEIGHT/2 - ResourceManage.getInstance().bullet[1].getUp().getHeight()/2;
        tank.getTF().gameFactory.createBullet(bulletX, bulletY, tank.getDir(), tank.getTF(), tank.getGroup());
    }
}
