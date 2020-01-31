package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.AbstractFactory.BaseTank;
import com.zzx.tank.Dir;
import com.zzx.tank.ResourceManage;

import java.util.ArrayList;

public class FireBulletAllDirectionsStrategy implements FireBulletStrategy {
    @Override
    public void fire(BaseTank tank) {
        int bulletX = tank.getX() + tank.WIDTH / 2 - ResourceManage.getInstance().bullet[1].getUp().getWidth() / 2;
        int bulletY = tank.getY() + tank.HEIGHT / 2 - ResourceManage.getInstance().bullet[1].getUp().getHeight() / 2;
        for (Dir dir : Dir.values()) {
            tank.getTF().gameFactory.createBullet(bulletX, bulletY, dir, tank.getTF(), tank.getGroup());
        }
    }
}
