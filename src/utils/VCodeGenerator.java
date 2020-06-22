package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成器
 * 
 * @author wYp,chenw
 */
public class VCodeGenerator {

	private int vcodeLen = 4;// 验证码长度
	private int disturbline = 3;// 干扰线条数
	private int fontsize = 15;// 验证码图片字体大小
	private int width = 60;// 验证码图片宽度
	private int height = 20;// 验证码图片高度

	/**
	 * 生成验证码
	 * 
	 * @return 验证码
	 */
	public String generatorVCode() {
		// 创建一个随机数生成器
		Random ran = new Random();
		// 用于保存随机产生的验证码
		StringBuffer sb = new StringBuffer();
		// 随机产生4位数字的验证码
		for (int i = 0; i < vcodeLen; i++) {
			// 得到随机产生的验证码数字
			String strRand = String.valueOf(ran.nextInt(10));
			sb.append(strRand);
		}
		return sb.toString();
	}

	/**
	 * 生成验证码图片
	 * 
	 * @param vcode 要画的验证码
	 * @return
	 */
	public BufferedImage generatorVCodeImage(String vcode) {
		// 创建验证码图片
		BufferedImage vcodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = vcodeImage.getGraphics();
		// 填充背景色
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, width, height);

		// 为验证码图片画一些干扰线
		drawDisturbLine(g);

		// 在图片上画验证码
		for (int i = 0; i < vcode.length(); i++) {
			// 创建字体，字体的大小应该根据图片的高度来定
			Font font = new Font("Times New Roman", Font.PLAIN, fontsize);
			// 设置字体
			g.setFont(font);
			// 随机生成颜色
			g.setColor(getRandomColor());
			// 画验证码
			g.drawString(vcode.charAt(i) + "", i * fontsize + 5, fontsize);
		}
		// 释放此图形的上下文以及它使用的所有系统资源
		g.dispose();

		return vcodeImage;
	}

	/**
	 * 为验证码图片画一些干扰线
	 * 
	 * @param g
	 */
	private void drawDisturbLine(Graphics g) {
		Random ran = new Random();
		for (int i = 0; i < disturbline; i++) {
			int x1 = ran.nextInt(width);
			int y1 = ran.nextInt(height);
			int x2 = ran.nextInt(width);
			int y2 = ran.nextInt(height);
			g.setColor(getRandomColor());
			// 画干扰线
			g.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 返回一个随机颜色
	 * 
	 * @return
	 */
	private Color getRandomColor() {
		Random ran = new Random();
		return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220));
	}
}
