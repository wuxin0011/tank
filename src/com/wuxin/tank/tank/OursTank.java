package com.wuxin.tank.tank;

import com.wuxin.tank.Direction;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:19
 * @Description:
 */
public class OursTank extends Tank {
    private static final long serialVersionUID = 1L;
    public OursTank(int x, int y) {
        this(x, y, null);
    }

    public OursTank(int x, int y, Graphics graphics) {
        this(x, y, Color.YELLOW, Direction.UP, graphics);
    }

    public OursTank(int x, int y, Color color, Direction direction, Graphics graphics) {
        super(x, y, color, graphics, direction, 1);
    }



    //   for (Tank tank : getOtherTankVector()) {
    //     if (tank != null && tank.isAlive() && Valid.scaleNotHit(this, tank)) {
    //         super.moveRight();
    //     }
    // }


}
