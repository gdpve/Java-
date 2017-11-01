package test;
/**
 *JAVAGUI编程 
 *导报  导入路径
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//继承  拓展功能  风姐和女人

public class Bird extends JPanel {
	
	BufferedImage bg;//背景图片
	BufferedImage birdImage;//鸟的图片
	BufferedImage[] images;//一组鸟的图片
	int index;//用于计数的
	BufferedImage groundImage;//地面的图片
	int groundX;
	int groundY;
	
	//创建类的构造器  用于初始化所以类的属性(成员变量)
	//异常
	public Bird() throws Exception{
		bg = ImageIO.read(getClass().getResource("bg.png"));
		birdImage = ImageIO.read(getClass().getResource("0.png"));
		index = 0;
		images = new BufferedImage[8];
	
		groundImage =  ImageIO.read(getClass().getResource
				("ground.png"));
		
		groundX = 0;
		groundY = 500;
		
		for(int i = 0;i<images.length;i++){
			images[i] = ImageIO.read(getClass().getResource(
					i + ".png"));
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		JFrame frame = new JFrame();//创建窗体 相框
		Bird panel = new Bird();//创建面板
		frame.add(panel);//给窗体添加面板
		panel.setBackground(Color.BLUE);
		frame.setTitle("今天没吃药,感觉萌萌哒!");//设置标题
		frame.setSize(432,644 + 30);//设置窗体的尺寸
		//设置关闭窗体的同时退出程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体居中
		frame.setLocationRelativeTo(null);
		//设置窗体可见
		frame.setVisible(true);
		panel.action();//让游戏运动起来
	}
	
	//绘制方法
	public void paint(Graphics g){//画笔
		g.drawImage(bg,0,0,null);
		g.drawImage(birdImage,132,280,null);
		g.drawImage(groundImage,groundX,groundY,null);
	}
	
	//计算鸟的动画帧的切换
	public void fly(){
		birdImage = images[(index++/12)%images.length];
	}
	//添加游戏组件运动的方法
	public void action() throws Exception{
		while(true){
			fly();//让鸟的动画帧切换
			step();//让地面动起来
			Thread.sleep(1000/60);//让程序每隔1/60秒执行以次
			repaint();//调用重绘方法,让最新画面显示出来
		}
	}
	//添加新的方法,控制地面的运动
	public void step(){
		groundX--;
		if(groundX == - 109){
			groundX = 0;
		}
	}
}















