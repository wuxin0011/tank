package com.wuxin.tank;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:23
 * @Description: 移动方式接口
 */
public interface Move {

    /**
     * 向上移动
     */
    void moveUp();

    /**
     * 向下移动
     */
    void moveDown();

    /**
     * 向左移动
     */
    void moveLeft();

    /**
     * 向右移动
     */
    void moveRight();

}
