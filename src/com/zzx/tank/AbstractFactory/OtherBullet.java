package com.zzx.tank.AbstractFactory;

import com.zzx.tank.Dir;
import com.zzx.tank.Group;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.TankFrame;
import com.zzx.tank.dto.ImageDto;

import java.awt.*;

public class OtherBullet extends BaseBullet {

    public OtherBullet(int x, int y, Dir dir, TankFrame tf, Group group, int pictureNo) {
        super(x, y, dir, tf, group);
        this.pictureNo = pictureNo;
    }

    public int pictureNo = 1;

    /**
     * 画出子弹
     */
    public void paint(Graphics g) {
        if (live){
            // 根据方向画出子弹
            ImageDto bullet = ResourceManage.getInstance().bullet[pictureNo];
            switch (dir) {
                case UP:
                    g.drawImage(bullet.getUp(), x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(bullet.getRight(), x, y, null);
                    break;
                case DOWN:
                    g.drawImage(bullet.getDown(), x, y, null);
                    break;
                case LEFT:
                    g.drawImage(bullet.getLeft(), x, y, null);
                    break;
                case UP_RIGHT:
                    g.drawImage(bullet.getRightUp(), x, y, null);
                    break;
                case RIGHT_DOWN:
                    g.drawImage(bullet.getRightDown(), x, y, null);
                    break;
                case DOWN_LEFT:
                    g.drawImage(bullet.getLeftDown(), x, y, null);
                    break;
                case LEFT_UP:
                    g.drawImage(bullet.getLeftUp(), x, y, null);
                    break;
                default:
                    break;
            }
            this.move();
        } else {
            // 删除失去生命的子弹
            this.tf.getBulletList().remove(this);
        }

    }
}
