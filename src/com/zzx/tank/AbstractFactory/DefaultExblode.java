package com.zzx.tank.AbstractFactory;

import com.zzx.tank.Audio;
import com.zzx.tank.ResourceManage;
import com.zzx.tank.TankFrame;

import java.awt.*;

public class DefaultExblode extends BaseExblode {

    public DefaultExblode(int x, int y, TankFrame tf) {
        super(x, y, tf);
    }

    public void paint(Graphics g){
        if (step > 15){
            //step=0;
            tf.getExblodeList().remove(this);
        } else {

            g.drawImage(ResourceManage.getInstance().explodes[1][step], this.x, this.y, null);
            step++;
        }
    }
}
