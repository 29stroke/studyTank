package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.Bullet;
import com.zzx.tank.FireBulletStrategy.FireBulletStrategy;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.Tank;

import java.util.ArrayList;
import java.util.List;

public class FireBulletDefaultStrategy implements FireBulletStrategy {
    @Override
    public void fire(Tank tank) {
        int bulletX = tank.getX() + tank.WIDTH/2 - ResourceManage.getInstance().bullet.getUp().getWidth()/2;
        int bulletY = tank.getY() + tank.HEIGHT/2 - ResourceManage.getInstance().bullet.getUp().getHeight()/2;
        new Bullet(bulletX, bulletY, tank.getDir(), tank.getTF(), tank.getGroup());
    }
}
