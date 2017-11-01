import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

//����
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
	
	public void step(){//����ͼƬ�����ƶ�
		x--;
		
		if(x == -109){
			x = 0;
		}
	}
}