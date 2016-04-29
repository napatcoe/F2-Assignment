package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Gun> guns = new ArrayList<Gun>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	private Timer timer;
	
	private int itemType = 0;
	private long score = 0;
	private double difficulty = 0.1;
	private double itemRate = 0.01;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateItem(){
		Item i = new Item((int)(Math.random()*390), 30);
		gp.sprites.add(i);
		items.add(i);
		i.setType();
	}

	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}

		if(Math.random() < itemRate){
			generateItem();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 1;
			}
		}

		Iterator<Item> i_iter = items.iterator();
		while(i_iter.hasNext()){
			Item i = i_iter.next();
			i.proceed();

			if(!i.isAlive()){
				i_iter.remove();
				gp.sprites.remove(i);
				score -= 100;
			}
		}

		Iterator<Gun> g_iter = guns.iterator();
		while(g_iter.hasNext()){
			Gun g = g_iter.next();
			g.proceed();
			}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double gr;
		Rectangle2D.Double ir;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}

			for(Gun g : guns){
				gr = g.getRectangle();
				if(gr.intersects(er)){
					gp.sprites.remove(e);
					e.isDie();
					return;
			}

		}

			for(Item i : items){
				ir = i.getRectangle();
				if(ir.intersects(vr)){	
					gp.sprites.remove(i);
					i.deleteItem();
					itemType = i.getType();
					return;
			}
		}
	}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			if(itemType == 0)
				shoot1();
			else if(itemType == 1)
				shoot3();
			else if(itemType == 2)
				shoot5();
			else if(itemType == 3)
				shoot7();
			else if(itemType == 4)
				shoot9();
			else if(itemType == 5)
				shoot11();
			break;
		case KeyEvent.VK_R:
			allDie();
			break;
		}
	}

	public void allDie(){
			for(Enemy e : enemies){			
				gp.sprites.remove(e);
				e.isDie();
				return;
			}
				
   		}
	public void shoot1(){
            Gun a = new Gun((v.x)+12, v.y-20);
            
            a.setType(itemType);
            gp.sprites.add(a);
            guns.add(a);
        }

    public void shoot3(){
            Gun a = new Gun((v.x)+22, v.y-20);
            Gun b = new Gun((v.x)+12, v.y-20);
            Gun c = new Gun((v.x), v.y-20);
            
            	a.setType(itemType);
            	b.setType(itemType);
            	c.setType(itemType);
            
            gp.sprites.add(a);
            guns.add(a);
            gp.sprites.add(b);
            guns.add(b);
            gp.sprites.add(c);
            guns.add(c);
        }

    public void shoot5(){
            Gun a = new Gun((v.x)+32, v.y-20);
            Gun b = new Gun((v.x)+22, v.y-20);
            Gun c = new Gun((v.x)+12, v.y-20);
            Gun d = new Gun((v.x)-0, v.y-20);
            Gun e = new Gun((v.x)-12, v.y-20);
 			a.setType(itemType);
            b.setType(itemType);
            c.setType(itemType);
            d.setType(itemType);
            e.setType(itemType);
            gp.sprites.add(a);
            guns.add(a);
            gp.sprites.add(b);
            guns.add(b);
            gp.sprites.add(c);
            guns.add(c);
            gp.sprites.add(d);
            guns.add(d);
            gp.sprites.add(e);
            guns.add(e);
        }

    public void shoot7(){
            Gun a = new Gun((v.x)+42, v.y-20);
            Gun b = new Gun((v.x)+32, v.y-20);
            Gun c = new Gun((v.x)+22, v.y-20);
            Gun d = new Gun((v.x)+12, v.y-20);
            Gun e = new Gun((v.x), v.y-20);
            Gun f = new Gun((v.x)-12, v.y-20);
            Gun g = new Gun((v.x)-22, v.y-20);
            a.setType(itemType);
            b.setType(itemType);
            c.setType(itemType);
            d.setType(itemType);
            e.setType(itemType);
            f.setType(itemType);
            g.setType(itemType);
            gp.sprites.add(a);
            guns.add(a);
            gp.sprites.add(b);
            guns.add(b);
            gp.sprites.add(c);
            guns.add(c);
            gp.sprites.add(d);
            guns.add(d);
            gp.sprites.add(e);
            guns.add(e);
            gp.sprites.add(f);
            guns.add(f);
            gp.sprites.add(g);
            guns.add(g);
        }

    public void shoot9(){
            Gun a = new Gun((v.x)+52, v.y-20);
            Gun b = new Gun((v.x)+42, v.y-20);
            Gun c = new Gun((v.x)+32, v.y-20);
            Gun d = new Gun((v.x)+22, v.y-20);
            Gun e = new Gun((v.x)+12, v.y-20);
            Gun f = new Gun((v.x), v.y-20);
            Gun g = new Gun((v.x)-12, v.y-20);
            Gun h = new Gun((v.x)-22, v.y-20);
            Gun i = new Gun((v.x)-32, v.y-20);
            a.setType(itemType);
            b.setType(itemType);
            c.setType(itemType);
            d.setType(itemType);
            e.setType(itemType);
            f.setType(itemType);
            g.setType(itemType);
            h.setType(itemType);
            i.setType(itemType);
            gp.sprites.add(a);
            guns.add(a);
            gp.sprites.add(b);
            guns.add(b);
            gp.sprites.add(c);
            guns.add(c);
            gp.sprites.add(d);
            guns.add(d);
            gp.sprites.add(e);
            guns.add(e);
            gp.sprites.add(f);
            guns.add(f);
            gp.sprites.add(g);
            guns.add(g);
            gp.sprites.add(h);
            guns.add(h);
            gp.sprites.add(i);
            guns.add(i);
        }

    public void shoot11(){
            Gun a = new Gun((v.x)+62, v.y-20);
            Gun b = new Gun((v.x)+52, v.y-20);
            Gun c = new Gun((v.x)+42, v.y-20);
            Gun d = new Gun((v.x)+32, v.y-20);
            Gun e = new Gun((v.x)+22, v.y-20);
            Gun f = new Gun((v.x)+12, v.y-20);
            Gun g = new Gun((v.x), v.y-20);
            Gun h = new Gun((v.x)-12, v.y-20);
            Gun i = new Gun((v.x)-22, v.y-20);
            Gun j = new Gun((v.x)-32, v.y-20);
            Gun k = new Gun((v.x)-42, v.y-20);
            a.setType(itemType);
            b.setType(itemType);
            c.setType(itemType);
            d.setType(itemType);
            e.setType(itemType);
            f.setType(itemType);
            g.setType(itemType);
            h.setType(itemType);
            i.setType(itemType);
            j.setType(itemType);
            k.setType(itemType);
            gp.sprites.add(a);
            guns.add(a);
            gp.sprites.add(b);
            guns.add(b);
            gp.sprites.add(c);
            guns.add(c);
            gp.sprites.add(d);
            guns.add(d);
            gp.sprites.add(e);
            guns.add(e);
            gp.sprites.add(f);
            guns.add(f);
            gp.sprites.add(g);
            guns.add(g);
            gp.sprites.add(h);
            guns.add(h);
            gp.sprites.add(i);
            guns.add(i);
            gp.sprites.add(j);
            guns.add(j);
            gp.sprites.add(k);
            guns.add(k);
        }

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}

