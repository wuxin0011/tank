package com.wuxin.tank.tank;


import com.wuxin.tank.AbstractMove;
import com.wuxin.tank.CustomException;
import com.wuxin.tank.Direction;
import com.wuxin.tank.TankConstant;

import java.awt.*;
import java.util.Vector;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:19
 * @Description: 坦克的父类，要求所有坦克继承该类
 */
public class Tank extends AbstractMove {

    private static final long serialVersionUID = 1L;
    private static final boolean IS_ALIVE = true;
    private final Vector<Shot> shotVector = new Vector<>();
    private Vector<Tank> otherTankVector = new Vector<>();


    public Tank(int x, int y, Graphics graphics, Color color, Direction direction) {
        this(x, y, color, graphics, direction, 0);
    }

    public Tank(int x, int y, Graphics graphics) {
        this(x, y, Color.CYAN, graphics, Direction.DOWN, 0);
    }

    public Tank(int x, int y, Color color, Direction direction, Graphics graphics) {
        this(x, y, color, graphics, direction, 0);
    }


    public Tank(int x, int y, Color color, Graphics graphics, Direction direction, int type) {
        super(x, y, TankConstant.TANK_WIDTH, TankConstant.TANK_HEIGHT, color, graphics, direction, IS_ALIVE, TankConstant.TANK_SPEED, type);
    }

    public Vector<Shot> getShotVector() {
        return shotVector;
    }

    public void setOtherTankVector(Vector<Tank> otherTankVector) {
        this.otherTankVector = otherTankVector;
    }

    public Vector<Tank> getOtherTankVector() {
        return otherTankVector;
    }

    /**
     * 绘制坦克
     */
    public void drawTank() {
        int x = getX();
        int y = getY();
        Graphics graphics = getGraphics();
        if (graphics == null) {
            throw new NullPointerException("画笔对象为空！");
        }
        graphics.setColor(getColor());
        int radiusX = 0, radiusY = 0, operationX = 0, operationY = 0, bx = 0, by = 0, leftWheelX = 0, leftWheelY = 0, rightWheelX = 0, rightWheelY = 0;
        switch (getDirection()) {
            case UP:
                // 左边轮子
                leftWheelX = x;
                leftWheelY = y;
                // 控制台
                operationX = x + TankConstant.TANK_WHEEL_WIDTH;
                operationY = y + (TankConstant.TANK_HEIGHT - TankConstant.TANK_OPERATION_HEIGHT) / 2;
                // 转向盘
                radiusX = x + (TankConstant.TANK_WIDTH - TankConstant.TANK_WHEEL_RADIUS) / 2;
                radiusY = y + (TankConstant.TANK_HEIGHT - TankConstant.TANK_WHEEL_RADIUS) / 2;
                // 炮筒
                bx = x + (TankConstant.TANK_WIDTH - TankConstant.TANK_GUN_BARREL_WIDTH) / 2;
                by = y;
                // 右边轮子
                rightWheelX = x + TankConstant.TANK_WHEEL_WIDTH + TankConstant.TANK_OPERATION_WIDTH;
                rightWheelY = y;
                break;
            case DOWN:
                // 左边轮子
                leftWheelX = x;
                leftWheelY = y;
                // 控制台
                operationX = x + TankConstant.TANK_WHEEL_WIDTH;
                operationY = y + (TankConstant.TANK_HEIGHT - TankConstant.TANK_OPERATION_HEIGHT) / 2;
                // 转向盘
                radiusX = x + (TankConstant.TANK_WIDTH - TankConstant.TANK_WHEEL_RADIUS) / 2;
                radiusY = y + (TankConstant.TANK_HEIGHT - TankConstant.TANK_WHEEL_RADIUS) / 2;
                // 炮筒
                bx = x + (TankConstant.TANK_WIDTH - TankConstant.TANK_GUN_BARREL_WIDTH) / 2;
                by = y + TankConstant.TANK_GUN_BARREL_HEIGHT;
                // 右边轮子
                rightWheelX = x + TankConstant.TANK_WHEEL_WIDTH + TankConstant.TANK_OPERATION_WIDTH;
                rightWheelY = y;
                break;
            case LEFT:
                // 左边轮子
                leftWheelX = x;
                leftWheelY = y;
                // 控制台
                operationX = x + (TankConstant.TANK_HEIGHT - TankConstant.TANK_OPERATION_HEIGHT) / 2;
                operationY = y + TankConstant.TANK_WHEEL_WIDTH;
                // 转向盘
                radiusX = x + (TankConstant.TANK_HEIGHT - TankConstant.TANK_WHEEL_RADIUS) / 2;
                radiusY = y + (TankConstant.TANK_WIDTH - TankConstant.TANK_WHEEL_RADIUS) / 2;
                // 炮筒
                bx = x;
                by = y + (TankConstant.TANK_WIDTH - TankConstant.TANK_GUN_BARREL_WIDTH) / 2;
                // 右边轮子
                rightWheelX = x;
                rightWheelY = y + (TankConstant.TANK_WIDTH - TankConstant.TANK_WHEEL_WIDTH);
                break;
            case RIGHT:
                // 左边轮子
                leftWheelX = x;
                leftWheelY = y;
                // 控制台
                operationX = x + (TankConstant.TANK_HEIGHT - TankConstant.TANK_OPERATION_HEIGHT) / 2;
                operationY = y + TankConstant.TANK_WHEEL_WIDTH;
                // 转向盘
                radiusX = x + (TankConstant.TANK_HEIGHT - TankConstant.TANK_WHEEL_RADIUS) / 2;
                radiusY = y + (TankConstant.TANK_WIDTH - TankConstant.TANK_WHEEL_RADIUS) / 2;
                // 炮筒
                bx = x + TankConstant.TANK_GUN_BARREL_HEIGHT;
                by = y + (TankConstant.TANK_WIDTH - TankConstant.TANK_GUN_BARREL_WIDTH) / 2;
                // 右边轮子
                rightWheelX = x;
                rightWheelY = y + (TankConstant.TANK_WIDTH - TankConstant.TANK_WHEEL_WIDTH);
                break;
            default:
                throw new CustomException("获取不到坦克炮筒方向！");
        }
        drawTank(leftWheelX, leftWheelY, operationX, operationY, radiusX, radiusY, bx, by, rightWheelX, rightWheelY, getDirection());
    }


    /**
     * 根据坐标绘制一个坦克
     *
     * @param leftWheelX  第一个轮子x
     * @param leftWheelY  第一个轮子y
     * @param operationX  操作中心x
     * @param operationY  操作中心y
     * @param radiusX     控制中心x
     * @param radiusY     控制中心y
     * @param bx          炮筒x
     * @param by          炮筒y
     * @param rightWheelX 第二个轮子x
     * @param rightWheelY 第二个轮子y
     */
    private void drawTank(int leftWheelX, int leftWheelY, int operationX, int operationY, int radiusX, int radiusY, int bx, int by, int rightWheelX, int rightWheelY, Direction direction) {
        // 绘制左边轮子
        wheel(leftWheelX, leftWheelY, direction);
        // 操作室
        operation(operationX, operationY, direction);
        // 绘制右边轮子
        wheel(rightWheelX, rightWheelY, direction);
        // 绘制炮筒
        bareel(bx, by, direction);
        // 转向盘
        control(radiusX, radiusY);
    }


    /**
     * xy坐标
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    public void wheel(int x, int y, Direction direction) {
        if (Direction.UP.equals(direction) || Direction.DOWN.equals(direction)) {
            getGraphics().fill3DRect(x, y, TankConstant.TANK_WHEEL_WIDTH, TankConstant.TANK_WHEEL_HEIGHT, false);
        } else {
            getGraphics().fill3DRect(x, y, TankConstant.TANK_WHEEL_HEIGHT, TankConstant.TANK_WHEEL_WIDTH, false);
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
            getGraphics().fill3DRect(x, y, TankConstant.TANK_OPERATION_WIDTH, TankConstant.TANK_OPERATION_HEIGHT, false);
        } else {
            getGraphics().fill3DRect(x, y, TankConstant.TANK_OPERATION_HEIGHT, TankConstant.TANK_OPERATION_WIDTH, false);
        }
    }


    /**
     * 炮筒
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @direction y 方向
     */
    public void bareel(int x, int y, Direction direction) {
        if (Direction.UP.equals(direction) || Direction.DOWN.equals(direction)) {
            getGraphics().fill3DRect(x, y, TankConstant.TANK_GUN_BARREL_WIDTH, TankConstant.TANK_GUN_BARREL_HEIGHT, true);
        } else {
            getGraphics().fill3DRect(x, y, TankConstant.TANK_GUN_BARREL_HEIGHT, TankConstant.TANK_GUN_BARREL_WIDTH, true);
        }
    }


    /**
     * 控制中心
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    public void control(int x, int y) {
        getGraphics().fillOval(x, y, TankConstant.TANK_WHEEL_RADIUS, TankConstant.TANK_WHEEL_RADIUS);
    }


    /**
     * 遍历子弹
     */
    public void tankShot() {
        for (Shot s : shotVector) {
            if (s != null && s.isAlive()) {
                s.setGraphics(getGraphics());
                s.setColor(getColor());
                s.setType(getType());
                s.drawShot();
            }
        }
        // 执行删除操作
        // 如果遍历操作同时删除 会报错！ ConcurrentModificationException 异常
        shotVector.removeIf(shot -> !shot.isAlive());
    }


    /**
     * 创建子弹
     */
    public void createShot() {
        Shot shot = null;
        // 获取坦克基本属性
        Direction direction = this.getDirection();
        Graphics graphics = getGraphics();
        if (graphics == null) {
            throw new NullPointerException("graphics is null");
        }
        int x = getX();
        int y = getY();
        int height = getHeight();
        int width = getWidth();

        switch (direction) {
            case UP:
                shot = new Shot(x + width / 2, getY(), graphics, direction);
                break;
            case DOWN:
                shot = new Shot(x + width / 2, y + height, graphics, direction);
                break;
            case LEFT:
                shot = new Shot(x, y + width / 2, graphics, direction);
                break;
            case RIGHT:
                shot = new Shot(x + height, y + width / 2, graphics, direction);
                break;
            default:
                break;
        }

        if (shot == null) {
            return;
        }
        shotVector.add(shot);
        // 启动该线程
        new Thread(shot).start();
    }


    @Override
    public void run() {
        System.out.println("坦克多线程启动");
    }


    @Override
    public void move(Direction direction) {
        setDirection(direction);
        this.move();
    }

    @Override
    public void move() {
        Direction direction = getDirection();
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }
}
