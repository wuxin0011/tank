package com.wuxin.tank.obstacles;

import com.wuxin.tank.CustomException;
import com.wuxin.tank.Direction;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/12/10:09
 * @Description: 障碍物 wall
 */
public class Wall extends Obstacles {


    public Wall(int x, int y, Direction direction) {
        super(x, y, 20, 10, Color.RED, null, direction, true, true, false, false);
    }

    @Override
    public void drawObstacles() {
        Graphics graphics = getGraphics();
        if (graphics == null) {
            throw new CustomException("绘制wall失败！障碍物 画笔 为null");
        }
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
