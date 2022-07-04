package com.wuxin.tank.tank;

import com.wuxin.tank.CustomException;
import com.wuxin.tank.AbstractMove;
import com.wuxin.tank.Direction;
import com.wuxin.tank.TankConstant;
import com.wuxin.tank.Valid;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:25
 * @Description:子弹类
 */
public class Shot extends AbstractMove {
    private static final long serialVersionUID = 1L;
    private static final int LOOP_TIME = 20;

    public Shot(int x, int y, Color color, Graphics graphics, Direction direction) {
        super(x, y, TankConstant.TANK_GUN_BARREL_WIDTH, TankConstant.TANK_GUN_BARREL_WIDTH, color, graphics, direction, true, TankConstant.SHOT_SPEED);
    }

    public Shot(int x, int y, Graphics graphics, Direction direction) {
        this(x, y, Color.cyan, graphics, direction);
    }


    /**
     * 绘制子弹
     */
    public synchronized void drawShot() {
        if (isAlive()) {
            Graphics graphics = getGraphics();
            graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }


    @Override
    public void run() {
        moveShot();
    }


    void moveShot() {
        while (true) {
            switch (getDirection()) {
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
                default:
                    throw new CustomException("子弹发射失败！获取不到子弹发射方向！");
            }
            // System.out.println("子弹:" + getX() + ",y=" + getY());
            if (Valid.checkScaleIsOver(this)) {
                this.setAlive(false);
                break;
            }
        }

    }


    @Override
    public void moveUp() {
        setDirection(Direction.UP);
        for (int i = 0; i < LOOP_TIME; i++) {
            try {
                if (Valid.checkScaleIsOver(this)) {
                    this.setAlive(false);
                    break;
                }
                super.moveUp();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new CustomException("子弹发射失败！" + e.getMessage());
            }
        }
    }

    @Override
    public void moveDown() {
        setDirection(Direction.DOWN);
        for (int i = 0; i < LOOP_TIME; i++) {
            try {
                if (Valid.checkScaleIsOver(this)) {
                    this.setAlive(false);
                    break;
                }
                if (getY() + getHeight() + getSpeed() > TankConstant.WINDOW_HEIGHT) {
                    setAlive(false);
                    break;
                }

                super.moveDown();
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new CustomException("子弹发射失败！" + e.getMessage());
            }
        }
    }

    @Override
    public void moveLeft() {
        setDirection(Direction.LEFT);
        for (int i = 0; i < LOOP_TIME; i++) {
            try {
                if (Valid.checkScaleIsOver(this)) {
                    this.setAlive(false);
                    break;
                }
                super.moveLeft();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new CustomException("子弹发射失败！" + e.getMessage());
            }
        }
    }

    @Override
    public void moveRight() {
        setDirection(Direction.RIGHT);
        for (int i = 0; i < LOOP_TIME; i++) {
            try {
                if (Valid.checkScaleIsOver(this)) {
                    this.setAlive(false);
                    break;
                }
                super.moveRight();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new CustomException("子弹发射失败！" + e.getMessage());
            }
        }
    }


}
