package com.wuxin.tank.obstacles;


import com.wuxin.tank.Direction;
import com.wuxin.tank.Scale;
import com.wuxin.tank.TankConstant;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/01/16:17
 * @Description: 障碍物 类，继承了了该类需要实现绘制障碍物的方法！
 */
public class Obstacles extends Scale implements DrawObstacle {
    private static final long serialVersionUID = 1L;
    private static final int OBSTACLES_WIDTH = TankConstant.TANK_WIDTH;
    private static final int OBSTACLES_HEIGHT = TankConstant.TANK_HEIGHT;
    private static final Color OBSTACLES_COLOR = Color.RED;
    private static final Direction OBSTACLES_DIRECTION = Direction.RIGHT;
    private static final boolean IS_ALIVE = true;

    /**
     * 障碍物是否允许通过
     */
    private boolean isTankPass;

    /**
     * 障碍物是否允许被销毁！
     */
    private boolean isDestroy;


    /**
     * 是否允许子弹通过！
     */
    private boolean isShotPass;

    public Obstacles(int x, int y) {
        this(x, y, OBSTACLES_WIDTH, OBSTACLES_HEIGHT, OBSTACLES_COLOR, null, OBSTACLES_DIRECTION, IS_ALIVE, false, false, false);
    }


    public Obstacles(int x, int y, Direction direction, Color color) {
        this(x, y, OBSTACLES_WIDTH, OBSTACLES_HEIGHT, color, null, direction, IS_ALIVE, false, false, false);
    }

    public Obstacles(int x, int y, Direction direction) {
        this(x, y, OBSTACLES_WIDTH, OBSTACLES_HEIGHT, Color.WHITE, null, direction, IS_ALIVE, false, false, false);
    }


    public Obstacles(int x, int y, int width, int height, Color color, Graphics graphics, Direction direction, boolean isAlive, boolean isDestroy, boolean isTankPass, boolean isShotPass) {
        super(x, y, width, height, color, graphics, direction, isAlive);
        this.isDestroy = isDestroy;
        this.isShotPass = isShotPass;
        this.isTankPass = isTankPass;
    }

    public boolean isTankPass() {
        return isTankPass;
    }

    public void setTankPass(boolean tankPass) {
        isTankPass = tankPass;
    }

    public boolean isDestroy() {
        return isDestroy;
    }

    public void setDestroy(boolean destroy) {
        isDestroy = destroy;
    }

    public boolean isShotPass() {
        return isShotPass;
    }

    public void setShotPass(boolean shotPass) {
        isShotPass = shotPass;
    }


    /**
     * 绘制障碍物
     */
    @Override
    public void drawObstacles() {
        System.out.println("请实现该方法！" + this.getClass().getSimpleName());
    }


}
