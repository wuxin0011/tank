package com.wuxin.tank.obstacles;

import java.awt.*;
import com.wuxin.tank.Direction;
import com.wuxin.tank.TankConstant;
import com.wuxin.tank.CustomException;

/**
 * @Author: wuxin001
 * @Date: 2022/06/12/10:29
 * @Description: 默认障碍物类继承了该类需要实现
 */
public class DefaultObstacle extends Obstacles {
    private static final long serialVersionUID = 1L;
    private static final int OBSTACLES_WIDTH = TankConstant.TANK_WIDTH;
    private static final int OBSTACLES_HEIGHT = TankConstant.TANK_HEIGHT;
    private static final Color OBSTACLES_COLOR = Color.RED;
    private static final Direction OBSTACLES_DIRECTION = Direction.RIGHT;
    private static final boolean IS_ALIVE = true;


    public DefaultObstacle(int x, int y, Direction direction, Color color, Graphics graphics) {
        this(x, y, OBSTACLES_WIDTH, OBSTACLES_HEIGHT, color, graphics, direction, IS_ALIVE);
    }

    public DefaultObstacle(int x, int y, int width, int height, Color color, Graphics graphics, Direction direction, boolean isAlive) {
        super(x, y, width, height, color, graphics, direction, isAlive, false, false, false);
    }


    /**
     * 绘制障碍物
     */
    @Override
    public void drawObstacles() {
        Graphics graphics = getGraphics();
        if (graphics == null) {
            throw new CustomException("绘制障碍物失败！获取不到画笔信息！");
        }
        Direction direction = getDirection();
        int x = getX();
        int y = getY();
        graphics.setColor(getColor());
        int operationX = 0, operationY = 0, leftWheelX = 0, leftWheelY = 0, rightWheelX = 0, rightWheelY = 0;
        switch (direction) {
            case UP:
            case DOWN:
                // 左边轮子
                leftWheelX = x;
                leftWheelY = y;
                // 控制台
                operationX = x + TankConstant.TANK_WHEEL_WIDTH;
                operationY = y + (TankConstant.TANK_HEIGHT - TankConstant.TANK_OPERATION_HEIGHT) / 2;
                // 右边轮子
                rightWheelX = x + TankConstant.TANK_WHEEL_WIDTH + TankConstant.TANK_OPERATION_WIDTH;
                rightWheelY = y;
                break;
            case LEFT:
            case RIGHT:
                // 左边轮子
                leftWheelX = x;
                leftWheelY = y;
                // 控制台
                operationX = x + (TankConstant.TANK_HEIGHT - TankConstant.TANK_OPERATION_HEIGHT) / 2;
                operationY = y + TankConstant.TANK_WHEEL_WIDTH;
                // 右边轮子
                rightWheelX = x;
                rightWheelY = y + (TankConstant.TANK_WIDTH - TankConstant.TANK_WHEEL_WIDTH);
                break;
            default:
                System.out.println("障碍物绘制失败！");
        }
        drawObstacles(leftWheelX, leftWheelY, operationX, operationY, rightWheelX, rightWheelY, direction);
    }


    /**
     * 障碍物信息
     *
     * @param leftWheelX  第一个轮子x
     * @param leftWheelY  第一个轮子y
     * @param operationX  操作中心x
     * @param operationY  操作中心y
     * @param rightWheelX 第二个轮子x
     * @param rightWheelY 第二个轮子y
     */
    private void drawObstacles(int leftWheelX, int leftWheelY, int operationX, int operationY, int rightWheelX, int rightWheelY, Direction direction) {
        // 绘制左边轮子
        wheel(leftWheelX, leftWheelY, direction);
        // 操作室
        operation(operationX, operationY, direction);
        // 绘制右边轮子
        wheel(rightWheelX, rightWheelY, direction);

    }


    /**
     * xy坐标
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    private void wheel(int x, int y, Direction direction) {
        if (Direction.UP.equals(direction) || Direction.DOWN.equals(direction)) {
            fillRect(x, y, TankConstant.TANK_WHEEL_WIDTH, TankConstant.TANK_WHEEL_HEIGHT);
        } else {
            fillRect(x, y, TankConstant.TANK_WHEEL_HEIGHT, TankConstant.TANK_WHEEL_WIDTH);
        }
    }

    /**
     * 控制中心
     *
     * @param x         横坐标
     * @param y         纵坐标
     * @param direction 泡桐方向
     */
    private void operation(int x, int y, Direction direction) {
        if (Direction.UP.equals(direction) || Direction.DOWN.equals(direction)) {
            fillRect(x, y, TankConstant.TANK_OPERATION_WIDTH, TankConstant.TANK_OPERATION_HEIGHT);
        } else {
            fillRect(x, y, TankConstant.TANK_OPERATION_HEIGHT, TankConstant.TANK_OPERATION_WIDTH);
        }
    }

    /**
     * 绘制障碍物具体信息
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    void fillRect(int x, int y, int width, int height) {
        getGraphics().fill3DRect(x, y, width, height, false);
    }

}
