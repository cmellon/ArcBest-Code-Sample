package com.space;
/**
 * 
 * Since all of the post wave upgrades screens are so similar, this class will be the only documented one.
 * The other upgrade screens have identical behavior. The only difference is the textures displayed
 * as well as the methods called from the player class.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class NewWaveShipScreen implements Screen {
	private Texture glowImg;
	private Texture smallGlowImg;
	private Texture shipScreenImg;
	private Texture upgradeSelectionImg;
	public Space main; 
	private boolean stillPressed = true;
	private int currentSelection = 0;
	private Rectangle selector;
	private Rectangle mainMenuRect;
	private Rectangle saveGameRect;
	private Rectangle nextWaveRect;
	private Rectangle shipRect;
	private Rectangle fireRect;
	private Rectangle lightningRect;
	private Rectangle windRect;
	private Rectangle upgrade1Rect;
	private Rectangle upgrade2Rect;
	private Rectangle upgrade3Rect;
	private Rectangle upgrade4Rect;
	private OrthographicCamera camera;
	private  SpriteBatch batch;
	
	  private Texture texture;
	    private Sprite sprite;
	    private BitmapFont font;
	    
	    

	public NewWaveShipScreen(Space game, Player playerSource) { 
		main = game;
		shipScreenImg = main.shipScreenImg;
		glowImg = main.mainMenuScreenGlowImg;
		smallGlowImg = main.smallGlowImg;
		upgradeSelectionImg = main.upgradeSelectionImg;
		
		selector = new Rectangle();
		selector.width = 2;
		selector.height = 2;
		selector.x = 400;
		selector.y = 240;
		
		mainMenuRect = new Rectangle();
		mainMenuRect.width = 186;
		mainMenuRect.height = 50;
		mainMenuRect.x = 581;
		mainMenuRect.y = 181;
		
		saveGameRect = new Rectangle();
		saveGameRect.width = 186;
		saveGameRect.height = 50;
		saveGameRect.x = 581;
		saveGameRect.y = 181-71;
		
		nextWaveRect = new Rectangle();
		nextWaveRect.width = 186;
		nextWaveRect.height = 50;
		nextWaveRect.x = 581;
		nextWaveRect.y = 181-140;
		
		shipRect = new Rectangle();
		shipRect.width = 175;
		shipRect.height = 50;
		shipRect.x = -9;
		shipRect.y = 320;
		
		fireRect = new Rectangle();
		fireRect.width = 175;
		fireRect.height = 50;
		fireRect.x = -9;
		fireRect.y = 320 - 56;
		
		lightningRect = new Rectangle();
		lightningRect.width = 175;
		lightningRect.height = 50;
		lightningRect.x = -9;
		lightningRect.y = 320 - 112;
		
		windRect = new Rectangle();
		windRect.width = 175;
		windRect.height = 50;
		windRect.x = -9;
		windRect.y = 320 - 168;
		
		upgrade1Rect = new Rectangle(); //Top left
		upgrade1Rect.width = 170;
		upgrade1Rect.height = 100;
		upgrade1Rect.x = 170;
		upgrade1Rect.y = 336;
		
		upgrade2Rect = new Rectangle(); //Top right
		upgrade2Rect.width = 170;
		upgrade2Rect.height = 100;
		upgrade2Rect.x = 170+190;
		upgrade2Rect.y = 336;
		
		upgrade3Rect = new Rectangle(); //Bottom Left
		upgrade3Rect.width = 170;
		upgrade3Rect.height = 100;
		upgrade3Rect.x = 170;
		upgrade3Rect.y = 336-138;	
		
		upgrade4Rect = new Rectangle();//Bottom Right
		upgrade4Rect.width = 170;
		upgrade4Rect.height = 100;
		upgrade4Rect.x = 170+190;
		upgrade4Rect.y = 336-138;
				
		
		camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      
	      batch = new SpriteBatch();
	      
	        texture = main.texture;
	        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

	        TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

	        sprite = new Sprite(region);
	        sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
	        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	        sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

	        font = main.font;
	}

	@Override
	public void render(float delta) {
		
		if(Gdx.input.isTouched()){
				currentSelection = -1;
				Vector3 touchPos = new Vector3();
			    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			    camera.unproject(touchPos);
			    
			    selector.x = touchPos.x;
			    selector.y = touchPos.y;
		}
		if(!Gdx.input.isTouched()){
			if (selector.overlaps(nextWaveRect))main.setScreen(main.gameScreen);
			if (selector.overlaps(fireRect))main.setScreen(main.fireScreen);
			if (selector.overlaps(lightningRect))main.setScreen(main.lightningScreen);
			if (selector.overlaps(windRect))main.setScreen(main.windScreen);
			if (selector.overlaps(saveGameRect))if(!Space.player.gameSaved)Space.player.saveGame();//main.setScreen(main.saveGameScreen);
			if (selector.overlaps(mainMenuRect))main.setScreen(main.mainMenu);
			if (selector.overlaps(upgrade1Rect)){
				//Upgrade Health
				Space.player.upgradeHealth();
				selector.x = -400;
				selector.y = -240;
			}
			if (selector.overlaps(upgrade2Rect)){
				//Upgrade Laser Damage with click
				Space.player.upgradeLaserDamage();
				selector.x = -400;
				selector.y = -240;
			}
			if (selector.overlaps(upgrade3Rect)){
				//Upgrade FireRate
				Space.player.upgradeLaserFireRate();
				selector.x = -400;
				selector.y = -240;
			}
			if (selector.overlaps(upgrade4Rect)){
				//Upgrade Health
				Space.player.upgradeVelocity();
				selector.x = -400;
				selector.y = -240;
			}
			
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			if(!stillPressed){

			if (currentSelection == 1)main.setScreen(main.fireScreen);
			if (currentSelection == 2)main.setScreen(main.lightningScreen);
			if (currentSelection == 3)main.setScreen(main.windScreen);
			if (currentSelection == 8)main.setScreen(main.mainMenu);
			if (currentSelection == 9)Space.player.saveGame();//main.setScreen(main.saveGameScreen);
			if (currentSelection == 10)main.setScreen(main.gameScreen);
			}
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			if(!stillPressed)currentSelection = (currentSelection-1);
			if (currentSelection <= -1)currentSelection = 10;
			stillPressed = true;
			
			selector.x = -400;
			selector.y = -240;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			if(!stillPressed)currentSelection = (currentSelection+1);
			if (currentSelection >= 11)currentSelection = 0;
			stillPressed = true;
			
			selector.x = -400;
			selector.y = -240;
		}
		if(!Gdx.input.isKeyPressed(Keys.DOWN)&&!Gdx.input.isKeyPressed(Keys.UP)&&!Gdx.input.isKeyPressed(Keys.ENTER)){stillPressed = false;}
		
		batch.begin();
		batch.draw(shipScreenImg,0,0);
		if (currentSelection == 0||selector.overlaps(shipRect)){
			batch.draw(smallGlowImg, 3, 302 );
		}
		if (currentSelection == 1||selector.overlaps(fireRect)){
			batch.draw(smallGlowImg, 3, 300-55);
		}
		if (currentSelection == 2||selector.overlaps(lightningRect)){
			batch.draw(smallGlowImg, 3, 300 - 111);
		}
		if (currentSelection == 3||selector.overlaps(windRect)){
			batch.draw(smallGlowImg, 3, 300 - 167);
		}
		if (currentSelection == 4||selector.overlaps(upgrade1Rect)){
			batch.draw(upgradeSelectionImg,  167, 336);
		}
		if (currentSelection == 5||selector.overlaps(upgrade2Rect)){
			batch.draw(upgradeSelectionImg,  167+190, 336);
		}
		if (currentSelection == 6||selector.overlaps(upgrade3Rect)){
			batch.draw(upgradeSelectionImg,  167, 336-138);
		}
		if (currentSelection == 7||selector.overlaps(upgrade4Rect)){
			batch.draw(upgradeSelectionImg,  167+190, 336-138);
		}
		if (currentSelection == 8||selector.overlaps(mainMenuRect)){
			batch.draw(glowImg, 570, 165);
		}
		if (currentSelection == 9||selector.overlaps(saveGameRect)){
			batch.draw(glowImg,  570, 165-71);
		}
		if (currentSelection == 10||selector.overlaps(nextWaveRect)){
			batch.draw(glowImg,  570, 165-140);
		}

		
		main.font.draw(batch, Integer.toString(Space.player.shipLvl), 690, 392);
		main.font.draw(batch, Integer.toString(Space.player.shipXp), 690, 392 - 32);
		main.font.draw(batch, Integer.toString((Space.player.shipLvl*250+Space.player.shipLvl*Space.player.shipLvl*Space.player.shipLvl)-Space.player.shipXp), 690, 392 - 64);

		//Adjust position of the orbs display based upon how many digits it has. Goes up to 999
		if(Space.player.genericOrbs<10)main.font.draw(batch, Integer.toString(Space.player.genericOrbs), 700, 422);
		else if(Space.player.genericOrbs<100)main.font.draw(batch, Integer.toString(Space.player.genericOrbs), 700-10, 422);
		else main.font.draw(batch, Integer.toString(Space.player.genericOrbs), 700-20, 422);

		main.font.draw(batch, Integer.toString(Space.player.healthCost), 220, 335); //Top Left
		main.font.draw(batch, Integer.toString(Space.player.laserDamageCost), 220+190, 335);//Top Right
		main.font.draw(batch, Integer.toString(Space.player.laserFireRateCost), 220, 335-138); //Bottom Left
		main.font.draw(batch, Integer.toString(Space.player.velocityCost), 220+190, 335-138);//Bottom Right


		//batch.draw(glowImg, 0, 240); 465 55
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

		selector.x = -400;
		selector.y = -240;
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		selector.x = -400;
		selector.y = -240;
		stillPressed = true;
		currentSelection = 0;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		selector.x = -400;
		selector.y = -240;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

}
