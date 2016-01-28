
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JFrame;


public class Bounce extends Component implements MouseListener{
	
	static boolean end = false;
	static JFrame f;
	static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static final Color color = Color.magenta;
	static ArrayList<JFrame> frames = new ArrayList<JFrame>();
	static final int maxX = (int)screenSize.width;
	static final int maxY = (int)screenSize.height;
	static JFrame toAdd = null;
	static JFrame toRemove = null;
	
	public static void main(String[] args){
		f = new JFrame();
		f.setSize(100, 100);
		f.add(new Bounce(100,100,0,0));
		f.setLocation((int)(screenSize.getWidth() - 100),25);
		f.setUndecorated(true);
		f.addMouseListener(new Bounce.MainMouse());
		f.setVisible(true);
		
		
		run();
	}
	
	public static void run(){
		
		while(!end){
			try{
				Thread.sleep(10);
			}catch(Exception e){
		
			}
			for(JFrame temp: frames){
				Component[] c = temp.getContentPane().getComponents();
				if(((Bounce)c[0]).isClicked()){
					toRemove = temp;
					continue;
				}
				Point p = ((Bounce)c[0]).calcSpeeds();
				temp.setLocation(p.x,p.y);
			}
			if(toAdd != null){
				frames.add(toAdd);
				toAdd = null;
			}
			if(toRemove != null){
				frames.remove(toRemove);
				toRemove.setVisible(false);
				if(frames.size() == 0){
					end = true;
				}
			}
		}
		System.exit(1);
	}
	
	private int width,height;
	private int x,y;
	private int X_SPEED = 5;
	private int Y_SPEED = 5;
	private boolean clicked;
	
	public Bounce(int x_size, int y_size, int startX, int startY){
		width = x_size;
		height = y_size;
		int state = (int)(Math.random() * 5) + 1;
		System.out.println(state);
		if(state == 1 || state == 6 || state == 4){
			
		}
		if(state == 2){
			X_SPEED *= -1;
		}
		if(state == 3){
			Y_SPEED *= -1;
		}
		if(state == 5){
			X_SPEED *= -1;
			Y_SPEED *= -1;
		}
		x = startX;
		y = startY;
		setBackground(new Color(0, 255, 0, 0));
		clicked = false;
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(color);
		g.fillOval(0, 0, width, height);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() > 0 && e.getX() < 200 && e.getY() > 0 && e.getY() < 200)
			clicked = true;
	}
	
	public int getXSpeed(){
		return X_SPEED;
	}
	
	public int getYSpeed(){
		return Y_SPEED;
	}
	
	public Point calcSpeeds(){
		if ((x < 0 && X_SPEED < 0) || (x > maxX-200 && X_SPEED > 0)) {
			if(X_SPEED < 0){
				X_SPEED = y%4 + 3 + ((int)Math.random() * 2 + 1);
			}else{
				X_SPEED = - y%4 - 3 - ((int)Math.random() * 2 + 1);
			}
		}
		if ((y < 0 && Y_SPEED < 0)|| (y > maxY-200 && Y_SPEED > 0)) {
			if(Y_SPEED < 0){
				Y_SPEED = x%4 + 3 + ((int)Math.random() * 2 + 1);
			}else{
				Y_SPEED = - x%4 - 3 - ((int)Math.random() * 2 + 1);
			}
		}
		x+= X_SPEED;
		y+= Y_SPEED;
		Point p = new Point(x, y);
		return p;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	private static class MainMouse implements MouseListener{
		//private int x,y; //the mouse's x and y coords (relative to the JFrame)
						 //screenSize.getWidth() - 100 ,25
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			JFrame temp = new JFrame();
			temp.setSize(200, 200);
			temp.setLocation(x + (int)screenSize.getWidth() - 100, y + 25);
			temp.setUndecorated(true);
			temp.setBackground(new Color(0, 255, 0, 0));
			temp.setAlwaysOnTop(true);
			Bounce b = new Bounce(200,200,x + (int)screenSize.getWidth() - 100, y + 25);
			temp.add(b);
			temp.addMouseListener(b);
			toAdd = temp;
			temp.setVisible(true);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
						
		}

		@Override
		public void mouseExited(MouseEvent e) {
						
		}
		
	}
	
}
