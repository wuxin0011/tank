package com.wuxin.tank.tank;

import com.wuxin.tank.Valid;
import com.wuxin.tank.Direction;

import java.awt.*;
import java.util.Vector;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:20
 * @Description:
 */
public class EnemyTank extends Tank {

    private static final long serialVersionUID = 1L;


    public EnemyTank(int x, int y) {
        this(x, y, Color.CYAN, Direction.DOWN, null);
    }

    public EnemyTank(int x, int y, Graphics graphics) {
        this(x, y, Color.CYAN, Direction.DOWN, graphics);
    }

    public EnemyTank(int x, int y, Color color, Direction direction, Graphics graphics) {
        super(x, y, color, graphics, direction, 0);
    }

    /**
     * 地方坦克随机移动
     */
    @Override
    public void run() {
        EnemyTank enemyTank = this;
        while (this.isAlive()) {
            for (int i = 0; i < 30; i++) {
                try {
                    // 获取其他坦克信息
                    Vector<Tank> otherTankVector = getOtherTankVector();
                    // otherTankVector.removeIf(tank -> tank == null || !tank.isAlive() || tank == enemyTank);
                    // for (Tank tank : otherTankVector) {
                    //     if (tank != null && tank.isAlive() && Valid.scaleNotHit(enemyTank, tank)) {
                    //         System.out.println("禁止移动 ...");
                    //         move();
                    //     } else {
                    //         // move();
                    //     }
                    // }
                    move();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 随机获取一个方向,其中方向不和当前方向一致！
            Valid.randomDirection(this);
        }
    }

    // 发射子弹
    public void launchShot() {
        Vector<Shot> shotVector = getShotVector();
        while (shotVector.size() < 2) {
            try {
                createShot();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 启动
        }
    }
}
