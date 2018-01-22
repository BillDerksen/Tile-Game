
import java.awt.Graphics;

public class SettingState extends State {

	
	public SettingState(Game game) {
		super(game);
		
	}
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.mud, 0, 0, null);
	}

}
