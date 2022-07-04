package com.wuxin.tank.obstacles;

import com.wuxin.tank.CustomException;
import com.wuxin.tank.Direction;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/12/10:10
 * @Description: 障碍物钢板类
 */
public class SteelPlate extends Obstacles {

    public SteelPlate(int x, int y, Direction direction) {
        super(x, y, 100, 100, Color.WHITE, null, direction, true, false, false, true);
    }
    @Override
    public void drawObstacles() {
        Graphics graphics = getGraphics();
        if (graphics == null) {
            throw new CustomException("绘制钢板失败！障碍物 画笔 为null");
        }
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
