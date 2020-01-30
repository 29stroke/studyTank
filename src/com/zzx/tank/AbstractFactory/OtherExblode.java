package com.zzx.tank.AbstractFactory;

import com.zzx.tank.ResourceManage;
import com.zzx.tank.TankFrame;

import java.awt.*;

public class OtherExblode extends BaseExblode {

    public OtherExblode(int x, int y, TankFrame tf, int pictureNo) {
        super(x, y, tf);
        this.pictureNo = pictureNo;
    }

    private int pictureNo = 1;

    public void paint(Graphics g){
        if (step > 15){
            //step=0;
            tf.getExblodeList().remove(this);
        } else {

            g.drawImage(ResourceManage.getInstance().explodes[pictureNo][step], this.x, this.y, null);
            step++;
        }
    }
}
