package com.wuxin.tank;


import com.wuxin.tank.obstacles.*;
import com.wuxin.tank.tank.EnemyTank;
import com.wuxin.tank.tank.OursTank;
import com.wuxin.tank.tank.Shot;
import com.wuxin.tank.tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:23
 * @Description: 绘制类
 */
public class Draw extends JPanel implements KeyListener, Runnable {
    private final Vector<Tank> tankVector = new Vector<>();
    private final Vector<ObstaclesRoot> obstaclesRoots = new Vector<>();
    private final Vector<Boom> boomVector = new Vector<>();
    private static final int enemyTankNum = 5;
    private OursTank p1;
    private Graphics graphics;
    private static Image image1;
    private static Image image2;
    private static Image image3;
    private EnemyTank enemyTankIcon;


    //    初始化信息
    // static {
    //     image1 = Toolkit.getDefaultToolkit().getImage("D:\\desktop\\Learn\\Demo\\src\\resource\\image\\boom1.png");
    //     image2 = Toolkit.getDefaultToolkit().getImage("D:\\desktop\\Learn\\Demo\\src\\resource\\image\\boom2.png");
    //     image3 = Toolkit.getDefaultToolkit().getImage("D:\\desktop\\Learn\\Demo\\src\\resource\\image\\boom3.png");
    // }




    public Draw() {
        initTank(); // 初始化坦克
        // initObstacles(); // 初始化障碍物
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        graphics = g;
        activity();
        init();
        // drawObstacles();
        // drawImage();
        readHitEnemyTank(); // 读取file信息
    }

    void activity() {
        graphics.fillRect(0, 0, TankConstant.WINDOW_WIDTH, TankConstant.WINDOW_HEIGHT);
    }

    void initTank() {
        // 玩家一
        p1 = new OursTank(100, TankConstant.WINDOW_HEIGHT - TankConstant.TANK_HEIGHT * 4, Color.RED, Direction.RIGHT, null);

        // 敌方坦克
        for (int i = 0; i < enemyTankNum; i++) {
            EnemyTank enemyTank = new EnemyTank((TankConstant.TANK_WIDTH + 100) * (i), 0, Valid.getColor(p1), Direction.DOWN, null);
            tankVector.add(enemyTank);
            // 启动坦克
            new Thread(enemyTank).start();
        }

        // 添加其他坦克信息 保证坦克之间不能碰撞
        // p1.setOtherTankVector(tankVector);
        // for (Tank tank : tankVector) {
        //     tank.setOtherTankVector(tankVector);
        // }


    }

    /**
     * 绘制爆炸效果图片
     */
    void drawImage() {
        for (int i = 0; i < boomVector.size(); i++) {
            Boom boom = boomVector.get(i);
            if (boom.getLife() > 6) {
                graphics.drawImage(image1, boom.getX(), boom.getY(), boom.getWidth(), getHeight(), this);
            } else if (boom.getLife() > 3) {
                graphics.drawImage(image2, boom.getX(), boom.getY(), boom.getWidth(), getHeight(), this);
            } else {
                graphics.drawImage(image3, boom.getX(), boom.getY(), boom.getWidth(), getHeight(), this);
            }
            boom.lifeDown();
            if (boom.getLife() <= 0) {
                boomVector.remove(boom);
            }
        }
    }

    /**
     * 读取击中敌人坦克数量，默认读取历史记录
     */
    void readHitEnemyTank() {
        Font font = new Font("楷体", Font.BOLD, 20);
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        int i = ReadRecord.hitNum;
        // int i = 0;
        graphics.drawString("击中敌人坦克个数", TankConstant.RECORDS_X, TankConstant.RECORDS_Y);
        // 绘制敌人坦克图标
        enemyTankIcon = new EnemyTank(TankConstant.RECORDS_X, TankConstant.RECORDS_ICON_Y, graphics);
        graphics.drawString(i + "辆", TankConstant.RECORDS_ICON_X, TankConstant.RECORDS_ICON_Y + TankConstant.TANK_HEIGHT / 2);
        enemyTankIcon.setDirection(Direction.UP);
        enemyTankIcon.drawTank();
    }

    /**
     * 绘制障碍物
     */
    void initObstacles() {
        drawObstacles(0, 300, Direction.RIGHT, Color.BLUE, 4);
        drawObstacles(6 * TankConstant.TANK_HEIGHT, 300, Direction.RIGHT, Color.YELLOW, 6);
        drawObstacles(60, 70, Direction.DOWN, Color.GREEN, 2);
        drawObstacles(TankConstant.WINDOW_WIDTH - 160, 70, Direction.DOWN, Color.RED, 2);
        drawObstacles(200, 150, Direction.RIGHT, Color.WHITE, 3);

        Water water = new Water(100, 100);
        drawObstacles(water, 4);
    }


    void init() {
        if (graphics == null) {
            throw new NullPointerException("画笔对象初始化失败！");
        }
        drawTank(p1);
        // 绘制坦克的子弹对象
        p1.tankShot();

        // 我方子弹是否击中敌人坦克
        Vector<Shot> p1ShotVector = p1.getShotVector();
        for (Shot shot : p1ShotVector) {
            // 判断子弹是否存活
            if (shot != null && shot.isAlive()) {
                // 判断子弹是否击中坦克
                Vector<Tank> tankVector = this.tankVector;
                for (Tank tank : tankVector) {
                    // 判断子弹和坦克是否击中
                    if ((tank instanceof EnemyTank) && tank.isAlive() && Valid.scaleIsHit(shot, tank)) {
                        // 击中坦克 坦克消失 子弹消失
                        tank.setAlive(false);
                        shot.setAlive(false);
                        // 当子弹击中敌人坦克时候加入一个boom对象到集合中
                        Boom boom = new Boom(tank.getX(), tank.getY());
                        boomVector.add(boom);
                        // 记录我方坦克击中敌人坦克数量
                        // Records.addHitEnemyTankNum();
                        ReadRecord.hit();
                    }
                }
            }
        }

        // 当敌人坦克数量为0重新绘制5个坦克
        if (tankVector.size() == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < enemyTankNum; i++) {
                EnemyTank enemyTank = Valid.randomEnemyTank(p1);
                enemyTank.setGraphics(graphics);
                new Thread(enemyTank).start();
                tankVector.add(enemyTank);
            }

        }

        // 移除敌人坦克
        tankVector.removeIf(tank -> !tank.isAlive());


        // 敌人坦克线程
        for (Tank tank : tankVector) {
            EnemyTank enemyTank = null;
            if (tank != null && tank.isAlive()) {
                drawTank(tank);
                // 创建子弹线程
                // if (tank instanceof EnemyTank) {
                //     enemyTank = (EnemyTank) tank;
                //     // 创建子弹线程
                //     enemyTank.launchShot();
                //     // 敌方坦克发射子弹
                //     enemyTank.tankShot();
                //     // 判断敌人坦克是否击中我方坦克
                //     Vector<Shot> shotVector = enemyTank.getShotVector();
                //     for (Shot shot : shotVector) {
                //         if (shot != null && shot.isAlive() && p1 != null && p1.isAlive() && Valid.scaleIsHit(shot, p1)) {
                //             p1.setAlive(false);
                //             shot.setAlive(false);
                //         }
                //     }
                // }
            }
        }
    }

    void drawObstacles() {
        for (ObstaclesRoot obstaclesRoot : obstaclesRoots) {
            obstaclesRoot.getRoot().setGraphics(graphics);
            obstaclesRoot.drawObstacles();
        }
    }


    /**
     * 绘制障碍物
     */
    void drawObstacles(int x, int y, Direction direction, Color color, int num) {
        Obstacles root = new Obstacles(x, y, direction, color);
        drawObstacles(root, num);
    }


    /**
     * 绘制障碍物
     */
    void drawObstacles(Obstacles root, int num) {
        ObstaclesRoot obstaclesRoot = new ObstaclesRoot(root, num);
        obstaclesRoots.add(obstaclesRoot);
    }


    /**
     * 绘制障碍物
     */
    void drawWater(int x, int y, Direction direction, int num) {
        Water root = new Water(x, y, direction);
        ObstaclesRoot obstaclesRoot = new ObstaclesRoot(root, num);
        obstaclesRoots.add(obstaclesRoot);
    }


    /**
     * 绘制坦克
     */
    void drawTank(Tank tank) {
        tank.setGraphics(graphics);
        tank.drawTank();
    }


    /**
     * 当一个键被类型化时调用。
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * 按下键盘触发时候使用
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // 玩家一
            case KeyEvent.VK_W:
                // 遍历所有障碍物
                // Vector<ObstaclesRoot> obstaclesRoots = this.obstaclesRoots;
                // for (ObstaclesRoot obstaclesRoot : obstaclesRoots) {
                //     // 获取所有障碍物
                //     Vector<Obstacles> vector = obstaclesRoot.getVector();
                //     // 判断障碍物是否可坦克碰撞
                //     for (Obstacles obstacles : vector) {
                //         if (Valid.scaleIsHit(obstacles,p1)) {
                //             return;
                //         }
                //     }
                // }
                // p1.move(Direction.UP);
                p1.moveUp();
                repaint();
                break;
            case KeyEvent.VK_D:
                // p1.move(Direction.RIGHT);
                p1.moveRight();
                repaint();
                break;
            case KeyEvent.VK_A:
                // p1.move(Direction.LEFT);
                p1.moveLeft();
                repaint();
                break;
            case KeyEvent.VK_S:
                // p1.move(Direction.DOWN);
                p1.moveDown();
                repaint();
                break;
            case KeyEvent.VK_J:
                System.out.println("发射子弹--------------");
                // 创建线程发射子弹
                p1.createShot();
                break;
            default:
                break;

        }

    }


    /**
     * 当一个键被释放时调用。
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("释放事件: e.code=" + e.getKeyCode() + ",e.char=" + e.getKeyChar());
    }

    /**
     * 绘制坦克活动范围
     */
    private void canvas() {
        graphics.fillRect(0, 0, TankConstant.WINDOW_WIDTH, TankConstant.WINDOW_HEIGHT);
    }


    /**
     * 线程启动入口
     */
    @Override
    public void run() {
        // tankLunchShot(this.p1);
        while (true) {
            try {

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }


    }


    void tankLunchShot(Tank tank) {
        int time = 0;
        while (true) {
            try {
                if (tank == null) {
                    break;
                }
                // 移除销毁的子弹
                Vector<Shot> shotVector = tank.getShotVector();
                shotVector.removeIf(s -> !s.isAlive());
                // 是否还有子弹
                if (tank.getShotVector().size() == 0) {
                    break;
                }
                // 重新绘制
                System.out.println("重新绘制中:" + Thread.currentThread().getName() + ",执行次数:" + (time++));
                this.repaint();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new CustomException(Thread.currentThread().getName() + "子弹绘制失败！" + e.getMessage());
            }
        }

    }
}
