package com.dhr.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 验证码程序
 * @author Mr DU
 *
 */
public class CheckAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		//获取response对象
		HttpServletResponse res = ServletActionContext.getResponse();
		int width = 100;
		int height = 30;
		Random random = new Random();
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		// 设置图片
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// 设置文字颜色
		g.setFont(new Font(null, Font.BOLD, 20));
		//定义code为验证码值
		String code = "";
		for (int i = 1; i <= 4; i++) {
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			String c = randomNumber();
			g.drawString(c, 20 * i, 10 + new Random().nextInt(20));
			code += c;
		}
		//获取session
		ServletActionContext.getRequest().getSession().setAttribute("code", code);
		//绘制干扰线
		g.setColor(Color.gray);
		for (int i = 0; i < 6; i++) {
			int x1 = random.nextInt(width);
			int x2 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		g.dispose();
		// 设置浏览器不缓存
		res.setDateHeader("expries", -1);
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Pragma", "no-cache");
		// 设置响应类型
		res.setContentType("image/jpeg");
		ImageIO.write(bufferedImage, "jpg", res.getOutputStream());
		return NONE;
	}
	/**
	 * 产生随机数
	 * @return
	 */
	public String randomNumber() {
		Random random = new Random();
		String number = "1234567890qwertyuiopasdfghjklzxcvbnm";
		char c = number.charAt(random.nextInt(number.length()));
		String code = "";
		code += c;
		return code;
	}
}
