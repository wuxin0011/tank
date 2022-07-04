package com.wuxin.tank.obstacles;


import com.wuxin.tank.Direction;
import com.wuxin.tank.CustomException;

import java.awt.*;
import java.util.Vector;

/**
 * @Author: wuxin001
 * @Date: 2022/06/12/10:37
 * @Description: 默认障碍物的根节点
 */
public class ObstaclesRoot implements DrawObstacle {
    private int num;
    private Obstacles root;
    private Vector<Obstacles> vector = new Vector<>();

    public ObstaclesRoot(Obstacles root, int num) {
        this.root = root;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Obstacles getRoot() {
        return root;
    }

    public void setRoot(Obstacles root) {
        this.root = root;
    }

    public Vector<Obstacles> getVector() {
        return vector;
    }

    public void setVector(Vector<Obstacles> vector) {
        this.vector = vector;
    }

    @Override
    public void drawObstacles() {
        Obstacles root = getRoot();
        Vector<Obstacles> vector = getVector();
        if (root == null) {
            throw new CustomException("绘制失败 获取不到root节点！");
        }
        if (root.getGraphics() == null) {
            throw new CustomException("绘制失败 Graphics null ！");
        }
        // 绘制root节点障碍物信息
        root.drawObstacles();
        // 添加到列表中，保存当前障碍物信息
        vector.add(root);

        Direction direction = root.getDirection();
        Color color = root.getColor();
        Graphics graphics = root.getGraphics();
        int x = root.getX();
        int y = root.getY();
        int height = root.getHeight();
        int width = root.getWidth();

        int nextX = x;
        int nextY = y;

        for (int i = 0; i < getNum(); i++) {
            switch (direction) {
                case UP:
                    nextY = y - height * (i + 1);
                    break;
                case DOWN:
                    nextY = y + height * (i + 1);
                    break;
                case LEFT:
                    nextX = x - height * (i + 1);
                    break;
                case RIGHT:
                    nextX = x + height * (i + 1);
                    break;
                default:
                    throw new CustomException("障碍物创建异常！获取不到障碍物初始方向！");
            }
            drawObstacles(nextX, nextY, direction, color, graphics);
        }
    }

    /**
     * @param x
     * @param y
     * @param direction
     * @param color
     * @param graphics
     */
    public void drawObstacles(int x, int y, Direction direction, Color color, Graphics graphics) {
        // 生成障碍物
        DefaultObstacle obstacles = new DefaultObstacle(x, y, direction, color, graphics);
        // 绘制障碍物
        obstacles.drawObstacles();
        // 保存生成的节点信息
        getVector().add(obstacles);
    }
}
