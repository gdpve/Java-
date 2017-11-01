import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

//地面
public class Ground {
	BufferedImage image;
	int x,y;
	int width;
	int height;
	
	public Ground() throws Exception{
		image = ImageIO.read(getClass().getResource("ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = 500;
	}
	
	public void step(){//地面图片向左移动
		x--;
		
		if(x == -109){
			x = 0;
		}
	}
}