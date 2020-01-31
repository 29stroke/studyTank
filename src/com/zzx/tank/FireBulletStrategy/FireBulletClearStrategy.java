package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.AbstractFactory.BaseTank;
import com.zzx.tank.Dir;
import com.zzx.tank.ResourceManage;

import java.util.ArrayList;

public class FireBulletClearStrategy implements FireBulletStrategy {
    @Override
    public void fire(BaseTank tank) {
        int bulletW = ResourceManage.getInstance().bullet[1].getUp().getWidth();
        int bulletH = ResourceManage.getInstance().bullet[1].getUp().getHeight();

        for (BaseTank badTank:tank.getTF().getBadTankList()) {
            int bulletX = badTank.getX() + badTank.WIDTH/2 - bulletW/2;
            int bulletY = badTank.getY() + badTank.HEIGHT/2 - bulletH/2;
            tank.getTF().gameFactory.createBullet(bulletX, bulletY, badTank.getDir(), badTank.getTF(), tank.getGroup());
        }
    }
}
