package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.Bullet;
import com.zzx.tank.Dir;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.Tank;

import java.util.ArrayList;

public class FireBulletClearStrategy implements FireBulletStrategy {
    @Override
    public void fire(Tank tank) {
        int bulletW = ResourceManage.getInstance().bullet.getUp().getWidth();
        int bulletH = ResourceManage.getInstance().bullet.getUp().getHeight();

        for (Tank badTank:tank.getTF().getBadTankList()) {
            int bulletX = badTank.getX() + badTank.WIDTH/2 - bulletW/2;
            int bulletY = badTank.getY() + badTank.HEIGHT/2 - bulletH/2;
            new Bullet(bulletX, bulletY, badTank.getDir(), badTank.getTF(), tank.getGroup());
        }
    }
}
