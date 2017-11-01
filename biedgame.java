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
			
			Bird bird;//小鸟
			Column column1,column2;//柱子
			Ground ground;//地面
			BufferedImage backGround;//背景图
			
			//分数
			int score;
			
			//标示游戏是否结束
			//boolean gameOver;
			BufferedImage gameOverImage;
			
			BufferedImage startImage;
			//游戏状态
			int state;
			private boolean gameOver;
			public static final int START = 0;
			public static final int RUNNING = 1;
			public static final int GAME_OVER = 2;
			//游戏一般有三种状态
			/*构造方法 初始化各个变量*/
			
			public BirdGame() throws Exception{
				score = 0;
				bird = new Bird();
				column1 = new Column(1);
				
				column2 = new Column(2);
				ground = new Ground();
				backGround = ImageIO.read(getClass().getResource("bg.png"));
				//gameOver = false得到背景图
				state = START;
				gameOverImage = ImageIO.read(getClass().getResource("gameover.png"));
				startImage = ImageIO.read(getClass().getResource("start.png"));
			}
			
			//游戏的启动方法
			
			
			
			public static void main(String[] args) throws Exception {
				JFrame frame = new JFrame("我是一个小逗比，啦啦啦啦");//画出窗体
				BirdGame game = new BirdGame();
				frame.add(game);
				frame.setSize(432, 644);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体的关闭
				frame.setVisible(true);//调用paint方法
				game.action();
			}
			
			//画图
			
			public void paint(Graphics g) {
				g.drawImage(backGround, 0, 0, null);
				g.drawImage(column1.image, column1.x-column1.width/2, column1.y - column1.height/2, null);
				g.drawImage(column2.image, column2.x-column2.width/2, column2.y - column2.height/2, null);
				g.drawImage(ground.image, ground.x, ground.y, null);

				Graphics2D g2 = (Graphics2D)g;
		        g.drawImage(bird.image, bird.x-bird.width/2, bird.y - bird.height/2, null);
				//画笔再转回来
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
			
			
			//动  图片移动
			public void action() throws Exception{

				
				
				MouseListener l = new MouseAdapter(){//鼠标事件
				
					public void mousePressed(MouseEvent e){
						try {
							switch(state){
							case GAME_OVER:
								//游戏结束所有内容重置
								bird = new Bird();
								column1 = new Column(1);
								column2 = new Column(2);
								ground = new Ground();
								score = 0;
								//改变状态
								state = START;
								break;
							case START:
								//改变状态
								state = RUNNING;
							case RUNNING:
								//鸟向上飞
								bird.fly();					
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
					}
				};
				
				
				  
				addMouseListener(l);//死循环
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
						//鸟挥动翅膀
						bird.flappy();
						//地面移动
						ground.step();
						break;
					case RUNNING:
						ground.step();
						column1.step();
						column2.step();
						bird.step();
						bird.flappy();	
						//如果撞了 改变状态
						if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
							state = GAME_OVER;
						}
						//计分
						if(bird.x == column1.x || bird.x == column2.x){
							score++;
							 
								  
						}
						break;
					}
					repaint();//重绘方法,让最新画面显示出来
					Thread.sleep(1000/50);
				}
			
			
			
			
			
			
			
			
			
			
			
			}
		
		}