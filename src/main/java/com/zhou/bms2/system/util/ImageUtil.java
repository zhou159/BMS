package com.zhou.bms2.system.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author zhouxiong
 * @version v1.0 2022/6/17 18:26：创建此类
 * @since 2022/6/17 18:26
 */
public class ImageUtil {
    private static final int WIDTH = 91;
    private static final int HEIGHT = 35;
    private static final int LINE_COUNT = 5;
    private static final int NOISE_COUNT = 100;
    private static final String[] FONT_NAMES = {"Arial", "Times New Roman", "Verdana", "Courier New", "宋体", "华文楷体",
            "黑体", "微软雅黑", "楷体_GB2312"};
    private static final Random RANDOM = new Random();
    
    /**
     * 生成图片(长，宽，高)
     *
     * @param checkCode 验证码
     * @param file      临时文件
     * @throws IOException io异常
     */
    public static void createImage(String checkCode, File file) throws IOException {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics(); setBackground(g);
        // 生成验证码字符
        setCode(g, checkCode);
        // 随机生成横线
        setLine(g);
        // 随机生成噪点
        setNoise(image);
        
        ImageIO.write(image, "jpg", file);
    }
    
    /**
     * 将随机码填充到图片上
     *
     * @param g    图片编辑对象
     * @param code 随机码
     */
    public static void setCode(Graphics g, String code) {
        // 将生成的随机字符串写在图片上
        for (int i = 0; i < code.length(); i++) {
            setFont(g); int x = 10 + i * 20; int y = 30; g.drawString(String.valueOf(code.charAt(i)), x, y);
        }
    }
    
    /**
     * 生成随机字体
     *
     * @param g 图片编辑对象
     */
    public static void setFont(Graphics g) {
        // 随机颜色
        int red = RANDOM.nextInt(256); int green = RANDOM.nextInt(256); int blue = RANDOM.nextInt(256);
        Color color = new Color(red, green, blue); g.setColor(color);
        
        // 随机字体名
        String fontName = FONT_NAMES[RANDOM.nextInt(FONT_NAMES.length)];
        
        // 随机字体样式
        int fontStyle = RANDOM.nextBoolean() ? Font.BOLD : Font.PLAIN;
        
        // 随机字体大小，在20至30之间
        int fontSize = 20 + RANDOM.nextInt(11);
        
        Font font = new Font(fontName, fontStyle, fontSize); g.setFont(font);
    }
    
    /**
     * 设置背景色
     *
     * @param g 图片编辑对象
     */
    public static void setBackground(Graphics g) {
        g.setColor(Color.white); g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    
    /**
     * 随机生成横线
     *
     * @param g 图片编辑对象
     */
    public static void setLine(Graphics g) {
        g.setColor(Color.BLACK); int randomLines = RANDOM.nextInt(LINE_COUNT); for (int i = 0; i < randomLines; i++) {
            int x1 = RANDOM.nextInt(WIDTH); int y1 = RANDOM.nextInt(HEIGHT); int x2 = RANDOM.nextInt(WIDTH);
            int y2 = RANDOM.nextInt(HEIGHT); g.drawLine(x1, y1, x2, y2);
        }
    }
    
    /**
     * 随机生成噪点
     *
     * @param image 图片
     */
    public static void setNoise(BufferedImage image) {
        int randomNoises = RANDOM.nextInt(NOISE_COUNT); for (int i = 0; i < randomNoises; i++) {
            int x = RANDOM.nextInt(WIDTH); int y = RANDOM.nextInt(HEIGHT);
            int rgb = new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)).getRGB();
            image.setRGB(x, y, rgb);
        }
    }
}
