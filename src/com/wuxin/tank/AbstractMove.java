package com.wuxin.tank;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:43
 * @Description: 移动类，继承了该类表示物体可以移动
 */
public abstract class AbstractMove extends Scale implements Move, Runnable {
    private static final long serialVersionUID = 1L;

    private int speed;

    private int type;

    public AbstractMove(int speed) {
        this.speed = speed;
    }

    public AbstractMove(int x, int y, int width, int height, Color color, Graphics graphics, Direction direction, boolean isAlive, int speed) {
        this(x, y, width, height, color, graphics, direction, isAlive, speed, 0);
    }

    public AbstractMove(int x, int y, int width, int height, Color color, Graphics graphics, Direction direction, boolean isAlive, int speed, int type) {
        super(x, y, width, height, color, graphics, direction, isAlive);
        this.speed = speed;
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    public void moveUp() {
        if (getY() >= 0) {
            setDirection(Direction.UP);
            setY(getY() - speed);
        }
    }

    @Override
    public void moveDown() {
        if (getY() + getHeight() <= TankConstant.WINDOW_HEIGHT) {
            setDirection(Direction.DOWN);
            setY(getY() + speed);
        }

    }

    @Override
    public void moveLeft() {
        if (getX() >= 0) {
            setDirection(Direction.LEFT);
            setX(getX() - speed);
        }

    }

    @Override
    public void moveRight() {
        if (getX() + getHeight() <= TankConstant.WINDOW_WIDTH) {
            setDirection(Direction.RIGHT);
            setX(getX() + speed);
        }
    }


    public void move(Direction direction) {
        setDirection(direction);
        move();
    }

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
