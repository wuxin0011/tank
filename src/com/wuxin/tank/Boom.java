package com.wuxin.tank;

import java.awt.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/09/10:58
 * @Description: 爆炸类
 */
public class Boom extends Scale {

    private int life = 9;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    public Boom(int x, int y) {
        this(x, y, null, Direction.DOWN);
    }

    public Boom(int x, int y, Graphics graphics, Direction direction) {
        super(x, y, TankConstant.IMAGE_WIDTH, TankConstant.IMAGE_HEIGHT, Color.YELLOW, graphics, direction, true);
    }

    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            setAlive(false);
        }
    }


}
