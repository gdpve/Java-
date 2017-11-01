package test;
/**
 *JAVAGUI��� 
 *����  ����·��
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//�̳�  ��չ����  ����Ů��

public class Bird extends JPanel {
	
	BufferedImage bg;//����ͼƬ
	BufferedImage birdImage;//���ͼƬ
	BufferedImage[] images;//һ�����ͼƬ
	int index;//���ڼ�����
	BufferedImage groundImage;//�����ͼƬ
	int groundX;
	int groundY;
	
	//������Ĺ�����  ���ڳ�ʼ�������������(��Ա����)
	//�쳣
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
		JFrame frame = new JFrame();//�������� ���
		Bird panel = new Bird();//�������
		frame.add(panel);//������������
		panel.setBackground(Color.BLUE);
		frame.setTitle("����û��ҩ,�о�������!");//���ñ���
		frame.setSize(432,644 + 30);//���ô���ĳߴ�
		//���ùرմ����ͬʱ�˳�����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô������
		frame.setLocationRelativeTo(null);
		//���ô���ɼ�
		frame.setVisible(true);
		panel.action();//����Ϸ�˶�����
	}
	
	//���Ʒ���
	public void paint(Graphics g){//����
		g.drawImage(bg,0,0,null);
		g.drawImage(birdImage,132,280,null);
		g.drawImage(groundImage,groundX,groundY,null);
	}
	
	//������Ķ���֡���л�
	public void fly(){
		birdImage = images[(index++/12)%images.length];
	}
	//�����Ϸ����˶��ķ���
	public void action() throws Exception{
		while(true){
			fly();//����Ķ���֡�л�
			step();//�õ��涯����
			Thread.sleep(1000/60);//�ó���ÿ��1/60��ִ���Դ�
			repaint();//�����ػ淽��,�����»�����ʾ����
		}
	}
	//����µķ���,���Ƶ�����˶�
	public void step(){
		groundX--;
		if(groundX == - 109){
			groundX = 0;
		}
	}
}















