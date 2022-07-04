package com.wuxin.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:14
 * @Description: 坐标类继承了该类具有坐标属性
 */
public class Scale implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private Graphics graphics;
    private Direction direction;
    private boolean isAlive;

    public Scale(int x, int y, int width, int height, Color color, Graphics graphics, Direction direction, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.graphics = graphics;
        this.direction = direction;
        this.isAlive = isAlive;
    }

    public Scale() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "={" +
                "  x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", width=" + width + "px" +
                ", height=" + height + "px" +
                '}';
    }


}
