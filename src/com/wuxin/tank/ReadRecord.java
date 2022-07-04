package com.wuxin.tank;

import java.io.*;

/**
 * @Author: wuxin001
 * @Date: 2022/06/10/15:19
 * @Description:
 */
// @SuppressWarnings({"all"})
public class ReadRecord implements Serializable {

    private static final long serialVersionUID = -1L;
    private static final String suffix = "\\src\\resource\\records.txt";
    private static String path = "";
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static BufferedOutputStream bos;
    private static BufferedInputStream bis;
    public static Integer hitNum = 0;


    public static void hit() {
        hitNum++;
    }

    static {
        // path = new File(path).getAbsolutePath() + suffix;
        // fileIsExist(path);
        hitNum = read();
    }

    public static void fileIsExist(String sourcePath) {
        // 首次加载需要检查文件是不是存在 如果文件不存在 创建该文件，下次读写不加载该文件
        File file = new File(sourcePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomException("文件创建失败！请手动创建文件！ record.txt");
            }
        }
    }

    public static int read() {
        try {
            path = new File("").getAbsolutePath() + suffix;
            fileIsExist(path);
            ois = new ObjectInputStream(new FileInputStream(path));
            Object o = ois.readObject();
            if (o instanceof Integer) {
                hitNum = (Integer) o;
            }
            close(ois);
        } catch (Exception e) {
            e.printStackTrace();
            hitNum = 0;
        }
        return hitNum;
    }

    public static void write() {
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            if (hitNum != null) {
                oos.writeObject(hitNum);
            }
            flush(oos);
            close(oos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("写入失败...");
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomException("关闭失败...");
            }
        }
    }

    public static void flush(Flushable flushable) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomException("刷新失败...");
            }
        }
    }

    public static BufferedInputStream createBufferInputStream(InputStream inputStream) {
        bis = new BufferedInputStream(inputStream);
        return bis;
    }

    public static BufferedOutputStream createBufferedOutputStream(OutputStream outputStream) {
        bos = new BufferedOutputStream(outputStream);
        return bos;
    }


}
