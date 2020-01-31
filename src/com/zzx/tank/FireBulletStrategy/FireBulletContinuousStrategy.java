package com.zzx.tank.FireBulletStrategy;

import com.zzx.tank.AbstractFactory.BaseTank;
import com.zzx.tank.Dir;
import com.zzx.tank.ResourceManage;

import java.util.ArrayList;

public class FireBulletContinuousStrategy implements FireBulletStrategy {
    @Override
    public void fire(BaseTank tank) {
        int bulletX = tank.getX() + tank.WIDTH/2 - ResourceManage.getInstance().bullet[1].getUp().getWidth()/2;
        int bulletY = tank.getY() + tank.HEIGHT/2 - ResourceManage.getInstance().bullet[1].getUp().getHeight()/2;
        for (int i=0; i<5; i++) {
            int tempBulletX = bulletX;
            int tempBulletY = bulletY;
            if(tank.getDir() == Dir.UP) {
                tempBulletY = bulletY - ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            } else if(tank.getDir() == Dir.DOWN) {
                tempBulletY = bulletY + ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            } else if(tank.getDir() == Dir.LEFT) {
                tempBulletX = bulletX - ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            }else if(tank.getDir() == Dir.RIGHT) {
                tempBulletX = bulletX + ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            }else if(tank.getDir() == Dir.UP_RIGHT) {
                tempBulletX = bulletX + ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
                tempBulletY = bulletY - ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            }else if(tank.getDir() == Dir.RIGHT_DOWN) {
                tempBulletX = bulletX + ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
                tempBulletY = bulletY + ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            }else if(tank.getDir() == Dir.DOWN_LEFT) {
                tempBulletX = bulletX - ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
                tempBulletY = bulletY + ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            }else if(tank.getDir() == Dir.LEFT_UP) {
                tempBulletX = bulletX - ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
                tempBulletY = bulletY - ResourceManage.getInstance().bullet[1].getUp().getHeight()*i;
            }

            tank.getTF().gameFactory.createBullet(tempBulletX, tempBulletY, tank.getDir(), tank.getTF(), tank.getGroup());
        }
    }
}
