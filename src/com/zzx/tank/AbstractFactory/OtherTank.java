package com.zzx.tank.AbstractFactory;

import com.zzx.tank.Dir;
import com.zzx.tank.Group;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.TankFrame;
import com.zzx.tank.dto.ImageDto;

import java.awt.*;

public class OtherTank extends BaseTank {

    public OtherTank(int x, int y, Dir dir, TankFrame tf, Group group, int pictureNo) {
        super(x, y, dir, tf, group);
        this.pictureNo = pictureNo-1;
    }

    private int pictureNo = 1;

    /**
     * 画出坦克
     */
    @Override
    public void paint(Graphics g){
        if (live){
            // 根据方向画出坦克图片
            ImageDto goodTank = ResourceManage.getInstance().goodTank[pictureNo];
            ImageDto badTank = ResourceManage.getInstance().badTank[pictureNo];
            switch (dir) {
                case UP:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getUp() : badTank.getUp(), x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getRight() : badTank.getRight(), x, y, null);
                    break;
                case DOWN:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getDown() : badTank.getDown(), x, y, null);
                    break;
                case LEFT:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getLeft() : badTank.getLeft(), x, y, null);
                    break;
                case UP_RIGHT:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getRightUp() : badTank.getRightUp(), x, y, null);
                    break;
                case RIGHT_DOWN:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getRightDown() : badTank.getRightDown(), x, y, null);
                    break;
                case DOWN_LEFT:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getLeftDown() : badTank.getLeftDown(), x, y, null);
                    break;
                case LEFT_UP:
                    g.drawImage(this.group==Group.GOOD ? goodTank.getLeftUp() : badTank.getLeftUp(), x, y, null);
                    break;
                default:
                    break;
            }
            this.move();
        } else {
            // 删除失去生命的坦克
            this.tf.getBadTankList().remove(this);
        }
    }
}
