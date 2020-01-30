package com.zzx.tank.AbstractFactory;

import com.zzx.tank.Dir;
import com.zzx.tank.Group;
import com.zzx.tank.TankFrame;

public interface GameFactory {
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group);
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group);
    public BaseExblode createExblode(int x, int y, TankFrame tf);
}
