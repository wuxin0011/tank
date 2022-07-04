package com.wuxin.tank;

/**
 * @Author: wuxin001
 * @Date: 2022/05/30/17:15
 * @Description: 坦克大战一些数据常量
 */
public class TankConstant {


    /**
     * 偏移量x
     */
    public static final Integer WINDOW_WIDTH_OFFSET = 100;

    /**
     * 偏移量y
     */
    public static final Integer WINDOW_HEIGHT_OFFSET = 10;


    /**
     * 界面宽
     */
    public static final Integer WINDOW_WIDTH = 700;

    /**
     * 界面高
     */
    public static final Integer WINDOW_HEIGHT = 500;


    /**
     * 文字描述x坐标
     */
    public static final Integer RECORDS_X = WINDOW_WIDTH + 30;

    /**
     * 文字描述纵坐标
     */
    public static final Integer RECORDS_Y = 100;


    /**
     * 全部界面框
     */
    public static final Integer GAME_WIDTH = WINDOW_WIDTH + 300;


    /**
     * 全部界面框
     */
    public static final Integer GAME_HEIGHT = WINDOW_HEIGHT + 200;


    /**
     * 坦克宽
     */
    public static final Integer TANK_WIDTH = 50;

    /**
     * 坦克高
     */
    public static final Integer TANK_HEIGHT = 60;


    /**
     * 敌人坦克图标位置x
     */
    public static final Integer RECORDS_ICON_X = RECORDS_X + TANK_WIDTH * 2;


    /**
     * 敌人坦克图标位置y
     */
    public static final Integer RECORDS_ICON_Y = RECORDS_Y + 20;

    /**
     * 坦克轮子宽
     */
    public static final Integer TANK_WHEEL_WIDTH = 10;


    /**
     * 轮子高
     */
    public static final Integer TANK_WHEEL_HEIGHT = TANK_HEIGHT;


    /**
     * 控制中心宽
     */
    public static final Integer TANK_OPERATION_WIDTH = TANK_WIDTH - 2 * TANK_WHEEL_WIDTH;

    /**
     * 控制中心高
     */
    public static final Integer TANK_OPERATION_HEIGHT = TANK_HEIGHT * 3 / 5;


    /**
     * 坦克轮盘半径
     */
    public static final Integer TANK_WHEEL_RADIUS = TANK_OPERATION_WIDTH * 3 / 5;


    /**
     * 炮筒宽
     */
    public static final Integer TANK_GUN_BARREL_WIDTH = 4;

    /**
     * 炮筒高
     */
    public static final Integer TANK_GUN_BARREL_HEIGHT = TANK_HEIGHT / 2;


    /**
     * 子弹宽
     */
    public static final Integer TANK_BULLET_WIDTH = TANK_GUN_BARREL_WIDTH;


    /**
     * 子弹高
     */
    public static final Integer TANK_BULLET_HEIGHT = TANK_BULLET_WIDTH;


    /**
     * 坦克移动速度
     */
    public static final Integer TANK_SPEED = 8;


    /**
     * 子弹移动速度
     */
    public static final Integer SHOT_SPEED = 10;


    /**
     * 图片宽
     */
    public static final Integer IMAGE_WIDTH = 60;

    /**
     * 图片高
     */
    public static final Integer IMAGE_HEIGHT = 60;


}
