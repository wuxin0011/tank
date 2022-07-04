package com.wuxin.tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: wuxin001
 * @Date: 2022/06/02/10:24
 * @Description:
 */
@SuppressWarnings({"all"})
public class TankJFream extends JFrame {

    private Draw draw = null;


    public TankJFream() throws HeadlessException {
        init();
    }

    void init() {
        draw = new Draw();
        setTitle("坦克大战");
        setVisible(true);
        // 添加面板
        add(draw);
        // 设置位置以及大小
        setBounds(TankConstant.WINDOW_WIDTH_OFFSET, TankConstant.WINDOW_HEIGHT_OFFSET, TankConstant.GAME_WIDTH, TankConstant.GAME_HEIGHT);
        // 添加键盘事件监听
        addKeyListener(draw);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置背景色
        getContentPane().setBackground(Color.BLACK);
        // 启动面板绘制线程
        new Thread(draw).start();
        // 添加窗口关闭事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("close ....");
                // Records.saveNum();
                ReadRecord.write();
            }
        });
    }

}
