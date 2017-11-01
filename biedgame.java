import java.awt.Color;
		import java.awt.Font;
		import java.awt.Graphics;
		import java.awt.Graphics2D;
		import java.awt.event.MouseAdapter;
		import java.awt.event.MouseEvent;
		import java.awt.event.MouseListener;
		import java.awt.image.BufferedImage;

		import javax.imageio.ImageIO;
		import javax.swing.JFrame;
		import javax.swing.JPanel;


		public class BirdGame extends JPanel{
			
			Bird bird;//С��
			Column column1,column2;//����
			Ground ground;//����
			BufferedImage backGround;//����ͼ
			
			//����
			int score;
			
			//��ʾ��Ϸ�Ƿ����
			//boolean gameOver;
			BufferedImage gameOverImage;
			
			BufferedImage startImage;
			//��Ϸ״̬
			int state;
			private boolean gameOver;
			public static final int START = 0;
			public static final int RUNNING = 1;
			public static final int GAME_OVER = 2;
			//��Ϸһ��������״̬
			/*���췽�� ��ʼ����������*/
			
			public BirdGame() throws Exception{
				score = 0;
				bird = new Bird();
				column1 = new Column(1);
				
				column2 = new Column(2);
				ground = new Ground();
				backGround = ImageIO.read(getClass().getResource("bg.png"));
				//gameOver = false�õ�����ͼ
				state = START;
				gameOverImage = ImageIO.read(getClass().getResource("gameover.png"));
				startImage = ImageIO.read(getClass().getResource("start.png"));
			}
			
			//��Ϸ����������
			
			
			
			public static void main(String[] args) throws Exception {
				JFrame frame = new JFrame("����һ��С���ȣ���������");//��������
				BirdGame game = new BirdGame();
				frame.add(game);
				frame.setSize(432, 644);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����Ĺر�
				frame.setVisible(true);//����paint����
				game.action();
			}
			
			//��ͼ
			
			public void paint(Graphics g) {
				g.drawImage(backGround, 0, 0, null);
				g.drawImage(column1.image, column1.x-column1.width/2, column1.y - column1.height/2, null);
				g.drawImage(column2.image, column2.x-column2.width/2, column2.y - column2.height/2, null);
				g.drawImage(ground.image, ground.x, ground.y, null);

				Graphics2D g2 = (Graphics2D)g;
		        g.drawImage(bird.image, bird.x-bird.width/2, bird.y - bird.height/2, null);
				//������ת����
			   Font f = new Font(Font.SANS_SERIF, Font.BOLD, 80);
				g.setFont(f);
				g.drawString(""+score, 43, 63);
				g.setColor(new Color(255,255,0));
				g.drawString(""+score, 42, 60);
				
				
			
				
	//		if(gameOver){
	//			g.drawImage(gameOverImage, 0, 0, null);
	//		}
			
				switch(state){
				case GAME_OVER:
					g.drawImage(gameOverImage, 0, 0, null);
					break;
				case START:
					g.drawImage(startImage, 0, 0, null);
					break;
				}
				
			}
			
			
			//��  ͼƬ�ƶ�
			public void action() throws Exception{

				
				
				MouseListener l = new MouseAdapter(){//����¼�
				
					public void mousePressed(MouseEvent e){
						try {
							switch(state){
							case GAME_OVER:
								//��Ϸ����������������
								bird = new Bird();
								column1 = new Column(1);
								column2 = new Column(2);
								ground = new Ground();
								score = 0;
								//�ı�״̬
								state = START;
								break;
							case START:
								//�ı�״̬
								state = RUNNING;
							case RUNNING:
								//�����Ϸ�
								bird.fly();					
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
					}
				};
				
				
				  
				addMouseListener(l);//��ѭ��
			while(true){ 
//				if(!gameOver){
//				ground.step();
//					column1.step();
//				column2.step();
//					bird.step();					
//					bird.flappy();				
//			}
//			if(bird.hit(ground) || bird.hit(column1) || bird.hit(column2)){
//					gameOver = true;	
				
					switch(state){
					case START:
						//��Ӷ����
						bird.flappy();
						//�����ƶ�
						ground.step();
						break;
					case RUNNING:
						ground.step();
						column1.step();
						column2.step();
						bird.step();
						bird.flappy();	
						//���ײ�� �ı�״̬
						if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
							state = GAME_OVER;
						}
						//�Ʒ�
						if(bird.x == column1.x || bird.x == column2.x){
							score++;
							 
								  
						}
						break;
					}
					repaint();//�ػ淽��,�����»�����ʾ����
					Thread.sleep(1000/50);
				}
			
			
			
			
			
			
			
			
			
			
			
			}
		
		}