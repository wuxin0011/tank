package com.wuxin.tank.obstacles;

import com.wuxin.tank.CustomException;
import com.wuxin.tank.Direction;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/12/10:04
 * @Description: 障碍物水！
 */
public class Water extends Obstacles {

    public Water(int x, int y, Direction direction) {
        super(x, y, 100, 100, Color.BLUE, null, direction, true, false, false, true);
    }

    public Water(int x, int y) {
        this(x, y, Direction.RIGHT);
    }


    @Override
    public void drawObstacles() {
        Graphics graphics = getGraphics();
        if (graphics == null) {
            throw new CustomException("绘制水失败！障碍物 获取不到画笔对象！！！");
        }
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
