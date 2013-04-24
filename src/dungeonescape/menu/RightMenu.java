package dungeonescape.menu;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.character.Player;
import dungeonescape.components.JProgressBarColoredExperience;
import dungeonescape.components.JProgressBarColoredPlayer;
import dungeonescape.helper.Window;
import dungeonescape.map.Map;
import dungeonescape.map.Map.MapChangeListener;
import dungeonescape.map.MiniMap;
import dungeonescape.player.CharacterFunctions;
import dungeonescape.player.CharacterFunctions.PlayerStatsChangedListener;

public class RightMenu {

	private Map map;
	private Player player;

	JProgressBarColoredExperience exp;
	GLabel xp_label;
	
	JProgressBarColoredPlayer health;
	
	public GCanvas gcanvas;

	// MINI MAP
	MiniMap miniMap;
	double measure;

	public RightMenu(final Map map) {
		this.gcanvas = new GCanvas();
		this.gcanvas.setSize(Window.GAME_X, Window.WINDOW_Y);
		this.gcanvas.setLocation(896, 0);
		this.gcanvas.setBackground(Color.BLACK);
		this.map = map;
		this.player = map.player;
		this.miniMap = new MiniMap(map, this);
		this.map.setMapChangeListener(new MapChangeListener() {

			@Override
			public void onChanged(int state) {
				switch (state) {
				case Map.INIT_MAP:
					miniMap.updateMap(map, RightMenu.this);
					break;
				case Map.PRINT_RIGHT_SIDE:
					printMiniMap();
					break;
				}
			}
		});
		this.player.setPlayerStatsChangedListener( new PlayerStatsChangedListener() {
			
			@Override
			public void change(int change) {
				switch (change) {
				case CharacterFunctions.CHANGE_EXPERIENCE:
					updateExperience();
					break;
				}
			}
		});
		gcanvas.add(miniMap.gcanvas);
		initializePlayerStats();
	}

	public void add(GObject gobject) {
		gcanvas.add(gobject);
	}
	
	public void printMiniMap() {
		if (miniMap != null)
			miniMap.printMiniMap();
	}

	private void initializePlayerStats() {
		exp = new JProgressBarColoredExperience(100, 100);
		exp.setValue(player.getLevelProgress()[0]);
		exp.setSize(360, 15);
		exp.setLocation(20, 310);
		gcanvas.add(exp);

		health = new JProgressBarColoredPlayer(100, 100);
		health.setValue(player.getHealth());
		health.setSize(360, 15);
		health.setLocation(20, 340);
		gcanvas.add(health);
		
		xp_label = new GLabel("XP: "+ player.getExperience());
		xp_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		xp_label.setColor(Color.white);
		xp_label.setLocation(20, 300);
		gcanvas.add(xp_label);
	}

	private void updateExperience() {
		xp_label.setLabel("XP: "+ player.getExperience());
		exp.setValue(player.getLevelProgress()[0]);
	}
	
	private void printPlayerInventory() {
		
	}

}
