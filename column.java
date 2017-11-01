import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

//柱子类
public class Column {
	BufferedImage image;
	int x,y;
	int width,height;
	//柱子中间的间隙
	int gap;
	//两根柱子之间的距离
	int distance;
	
	//构造出柱子对象，初始化属性值
	public Column(int n) throws Exception{
		image = ImageIO.read(getClass().getResource("column.png"));
		width = image.getWidth();
		height = image.getHeight();
		gap = 160;
		distance = 270;
		x = 550 +(n-1) *distance;
		y = ran.nextInt(300) + 140;
	}
	      Random ran = new Random();
	public void step() {
		x--;
		if(x == -width/3){
			x = distance * 2 - width/3;
			y = ran.nextInt(300) + 140;//改变柱子的高度
		}
	}
	
	
}

