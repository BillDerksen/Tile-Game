import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable { //Implements runnable maakt een aparte thread voor deze class
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	private State settingState;
	
	//Input
	private KeyManager keyManager;
	
		
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
	}
	
	private void init() { //Initializeert alle graphics zodat deze klaar staan voor de game.
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		menuState = new MenuState(this);
		settingState = new SettingState(this);
		gameState = new GameState(this); //Dit kan als GameState class gezet worden omdat deze de State class extend.
		
		State.setState(gameState);
	}
	
	
	
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy(); // BufferStrategy is de manier hoe de computer het scherm tekent/'drawt'. Bufferen gaat flickering tegen, het is een soort pre-draw voor het op het scherm verschijnt.
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics(); // Het graphics object staat toe dat je dingen kan tekenen op het canvas. Onder deze lijn kan er 'gedrawd' worden.
		g.clearRect(0, 0, width, height);//Scherm leeghalen
		//Begin draw
		
		if(State.getState() != null)
			State.getState().render(g);

		
		//Einde draw
		bs.show();
		g.dispose(); //Sluit het graphics object af.
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps; //Er zitten 1 miljard nanoseconden in 1 seconde. 
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1 ) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public synchronized void start() { //Synchronized wordt gebruikt als je een thread direct benaderd.
		if(running) //Zorgt ervoor dat er niet nog een thread opgestart wordt als running al true is.
			return;
		running = true;
		thread = new Thread(this); //Runt deze(this) class.
		thread.start(); //Start de run() methode.
	}
	
	public synchronized void stop() {
		if(!running) //Zorgt ervoor dat het niet stopt als het al gestopt is.
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
