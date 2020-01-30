package com.zzx.tank.AbstractFactory;

import com.zzx.tank.Dir;
import com.zzx.tank.Group;
import com.zzx.tank.TankFrame;

public class DefaultGameFactory implements GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new DefaultTank(x, y, dir, tf, group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new DefaultBullet(x, y, dir, tf, group);
    }

    @Override
    public BaseExblode createExblode(int x, int y, TankFrame tf) {
        return new DefaultExblode(x, y, tf);
    }
}
