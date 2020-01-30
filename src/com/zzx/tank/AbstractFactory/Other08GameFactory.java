package com.zzx.tank.AbstractFactory;

import com.zzx.tank.Dir;
import com.zzx.tank.Group;
import com.zzx.tank.TankFrame;

public class Other08GameFactory implements GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new OtherTank(x, y, dir, tf, group, 8);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new OtherBullet(x, y, dir, tf, group, 0);
    }

    @Override
    public BaseExblode createExblode(int x, int y, TankFrame tf) {
        return new OtherExblode(x, y, tf, 2);
    }
}
