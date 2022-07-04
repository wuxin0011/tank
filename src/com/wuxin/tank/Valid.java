package com.wuxin.tank;

import com.wuxin.tank.tank.EnemyTank;
import com.wuxin.tank.tank.Shot;
import com.wuxin.tank.tank.Tank;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: wuxin001
 * @Date: 2022/06/04/11:11
 * @Description: 规范设计！
 */
@SuppressWarnings({"all"})
public class Valid {


    /**
     * 坦克方向常量
     */
    private static final List<Direction> DIRECTIONS = Stream.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT).collect(Collectors.toList());


    /**
     * 坦克颜色集合
     */
    private static final List<Color> COLORS = Stream.of(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.YELLOW, Color.PINK, Color.PINK, Color.MAGENTA).collect(Collectors.toList());

    /**
     * 检查是否越过边界
     */
    public static boolean checkScaleIsOver(Scale scale) {
        if (scale == null) {
            return false;
        }
        Direction direction = scale.getDirection();
        if (Direction.UP.equals(direction)) {
            if (scale.getY() > 0) {
                return false;
            }
        }
        if (Direction.DOWN.equals(direction)) {
            if (scale.getY() + scale.getHeight() < TankConstant.WINDOW_HEIGHT) {
                return false;
            }
        }
        if (Direction.LEFT.equals(direction)) {
            if (scale.getX() > 0) {
                return false;
            }
        }
        if (Direction.RIGHT.equals(direction)) {
            if (scale.getX() + scale.getHeight() < TankConstant.WINDOW_WIDTH) {
                return false;
            }
        }
        return true;
    }


    // 能否向上移
    public static boolean scaleIsMoveUP(Scale s) {
        if (Direction.UP.equals(s.getDirection())) {
            // 判断y坐标是否越界
            if (s.getY() > 0) {
                return true;
            }
        }
        return false;
    }

    // 能否向上移
    public static boolean scaleIsMoveDown(Scale s) {
        if (Direction.UP.equals(s.getDirection())) {
            // 判断y坐标是否越界
            if (s.getY() + s.getHeight() < TankConstant.WINDOW_HEIGHT) {
                return true;
            }
        }
        return false;
    }


    // 能否向上移
    public static boolean scaleIsMoveLeft(Scale s) {
        if (Direction.UP.equals(s.getDirection())) {
            return s.getX() > 0;
        }
        return false;
    }

    // 能否向上移
    public static boolean scaleIsMoveRight(Scale scale) {
        if (Direction.UP.equals(scale.getDirection())) {
            // 判断y坐标是否越界
            if (scale.getX() + scale.getHeight() < TankConstant.WINDOW_WIDTH) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检查子弹是否越界
     */
    public static boolean checkShot(Shot shot) {
        if (shot == null) {
            return false;
        }
        Direction direction = shot.getDirection();
        int x = shot.getX();
        int y = shot.getY();
        int height = shot.getHeight();
        int width = shot.getWidth();
        if (Direction.UP.equals(direction)) {
            // 判断y坐标是否越界
            if (y < 0) {
                return false;
            }
        }
        if (Direction.DOWN.equals(direction)) {
            // 判断y坐标是否越界
            if (y + height < TankConstant.WINDOW_HEIGHT) {
                return false;
            }
        }

        if (Direction.LEFT.equals(direction)) {
            // 判断x坐标是否越界
            if (x > 0) {
                return false;
            }
        }
        if (Direction.RIGHT.equals(direction)) {
            // 判断x坐标是否越界
            if (x + height < TankConstant.WINDOW_WIDTH) {
                return false;
            }
        }
        return true;
    }


    /**
     * 随机生成一个方向
     */
    public static Direction getDirection() {
        return DIRECTIONS.get(new Random().nextInt(DIRECTIONS.size()));
    }

    /**
     * 获取随机方向
     */
    public static void randomDirection(Tank tank) {
        Direction direction = tank.getDirection();
        while (true) {
            // 随机方向不能和原来方向相同！
            Direction windowDirection = getDirection();
            if (!windowDirection.equals(direction)) {
                tank.setDirection(windowDirection);
                break;
            }
        }
    }

    /**
     * 除了坦克与子弹之间发生碰撞会出现爆炸，其他继承了Scale类均需判断是否
     * 继承了scale之间物体是否发生碰撞
     *
     * @param s1 第一个物体
     * @param s2 第二个物体
     * @return true 发生碰撞 false 为碰撞
     */
    public static boolean scaleIsHit(Scale s1, Scale s2) {
        Tank tank = null;
        Shot shot = null;
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.equals(s2)) {
            return false;
        }
        // 是否为子弹和坦克
        if (s1 instanceof Tank && s2 instanceof Shot) {
            tank = (Tank) s1;
            shot = (Shot) s2;
            if (tank.getType() == shot.getType()) {
                return false;
            }
        }
        if (s2 instanceof Tank && s1 instanceof Shot) {
            tank = (Tank) s2;
            shot = (Shot) s1;
            if (tank.getType() == shot.getType()) {
                return false;
            }
        }
        // 第一个物体坐标信息
        int x0 = s1.getX();
        int y0 = s1.getY();
        int w0 = s1.getWidth();
        int h0 = s1.getHeight();
        // 第二个物体坐标信息
        int x1 = s2.getX();
        int y1 = s2.getY();
        int w1 = s2.getWidth();
        int h1 = s2.getHeight();
        if (Direction.RIGHT.equals(s1.getDirection())) {
            if (Direction.LEFT.equals(s2.getDirection()) || Direction.RIGHT.equals(s2.getDirection())) {
                return ((x0 + h0 > x1) && (x0 < x1 + h1)) && ((y0 + h0 > y1) && (y0 < y1 + w1));
            }
            if (Direction.DOWN.equals(s2.getDirection()) || Direction.UP.equals(s2.getDirection())) {
                return ((x0 + h0 > x1) && (x0 < x1 + w1)) && ((y0 + h0 > y1) && (y0 < y1 + h1));
            }
        }

        if (Direction.LEFT.equals(s1.getDirection())) {
            if (Direction.LEFT.equals(s2.getDirection()) || Direction.RIGHT.equals(s2.getDirection())) {
                return ((x0 < x1 + h1) && (x0 + h0 > x1)) && ((y0 + w0 > y1) && (y0 < y1 + w1));
            }
            if (Direction.DOWN.equals(s2.getDirection()) || Direction.UP.equals(s2.getDirection())) {
                return ((x0 < x1 + w1) && (x0 + h0 > x1)) && ((y0 + w0 > y1) && (y0 < y1 + h1));
            }
        }
        if (Direction.UP.equals(s1.getDirection())) {
            if (Direction.LEFT.equals(s2.getDirection()) || Direction.RIGHT.equals(s2.getDirection())) {
                return ((x0 + w0 > x1) && (x0 < x1 + h1)) && ((y0 < y1 + w1) && (y0 + h0 < y1));
            }
            if (Direction.DOWN.equals(s2.getDirection()) || Direction.UP.equals(s2.getDirection())) {
                return ((x0 + w0 > x1) && (x0 < x1 + w1)) && ((y0 < y1 + h1) && (y0 + h0 < y1));
            }
        }
        if (Direction.DOWN.equals(s1.getDirection())) {
            if (Direction.LEFT.equals(s2.getDirection()) || Direction.RIGHT.equals(s2.getDirection())) {
                return ((x0 + w0 > x1) && (x0 < x1 + h1)) && ((y0 + h0 > y1) && (y0 < y1 + w1));
            }
            if (Direction.DOWN.equals(s2.getDirection()) || Direction.UP.equals(s2.getDirection())) {
                return ((x0 + w0 > x1) && (x0 < x1 + w1)) && ((y0 + h0 > y1) && (y0 < y1 + h1));
            }
        }
        return false;
    }


    public static boolean scaleNotHit(Scale s1, Scale s2) {
        return !scaleIsHit(s1, s2);
    }


    public static Color getColor(Tank tank) {
        if (tank == null) {
            return Color.CYAN;
        }
        Color color = tank.getColor();
        // 获取颜色不能和玩家坦克颜色一致！
        while (true) {
            Color color1 = COLORS.get(new Random().nextInt(COLORS.size()));
            if (color1.getRGB() != color.getRGB()) {
                return color1;
            }

        }
    }

    /**
     * 随机生成敌人坦克和我方坦克颜色不能一致
     */
    public static EnemyTank randomEnemyTank(Tank tank) {
        Direction direction = getDirection();
        int x = (int) (Math.random() * (TankConstant.WINDOW_WIDTH - TankConstant.TANK_HEIGHT));
        int y = (int) (Math.random() * (TankConstant.WINDOW_HEIGHT - TankConstant.TANK_HEIGHT));
        return new EnemyTank(x, y, getColor(tank), direction, null);
    }


}
