package de.kftit.game;

import de.kftit.kuchengame.R;
import de.kftit.utils.Utils;



public class CakeOrStone {
	private boolean isCake;
	private int imageID;
	private int speedX;
	private int speedY;
	private int feldBreite;
	private int feldLaenge;
	private int posX;
	private int posY;
	
	
	public CakeOrStone(boolean isCake, int breite, int laenge){
		this.isCake = isCake;
		setFeldBreite(breite-50);
		setFeldLaenge(laenge-50);
		setImage();
	}
	
	public boolean isCake() {
		return isCake;
	}

	public void setCake(boolean isCake) {
		this.isCake = isCake;
	}

	private void setImage(){
		double zufall;
		zufall = Utils.getRandomNumber();
		if (isCake){
			zufall = (int)(zufall * 4);
			if(zufall < 1){
				setImageID(R.drawable.cake_1);
			}
			else if (zufall < 2){
				setImageID(R.drawable.cake_2);
			}
			else if (zufall < 3){
				setImageID(R.drawable.cake_3);
			}
			else{
				setImageID(R.drawable.cake_4);
			}
			setSpeedX(makeRichtung()*(int)Math.round(Utils.getRandomNumber()*10));
			setSpeedY(makeRichtung()*(int)Math.round(Utils.getRandomNumber()*10));
			setPosX((int)Math.round(Utils.getRandomNumber()*getFeldBreite()));
			setPosY((int)Math.round(Utils.getRandomNumber()*getFeldLaenge()));
		}
		else
		{
			zufall = (int)(zufall * 4);
			if(zufall < 1){
				setImageID(R.drawable.stone_1);
			}
			else if (zufall < 2){
				setImageID(R.drawable.stone_1);
			}
			else if (zufall < 3){
				setImageID(R.drawable.stone_1);
			}
			else{
				setImageID(R.drawable.stone_1);
			}
			
			setSpeedX(makeRichtung()*(int)Math.round(Utils.getRandomNumber()*12));
			setSpeedY(makeRichtung()*(int)Math.round(Utils.getRandomNumber()*12));
			setPosX((int)Math.round(Utils.getRandomNumber()*getFeldBreite()));
			setPosY((int)Math.round(Utils.getRandomNumber()*getFeldLaenge()));
		}
	}
	private int makeRichtung(){
		double zufall = Utils.getRandomNumber();
		if(zufall < 0.5){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	public void positionMove(){
		if(getPosX()+getSpeedX() > feldBreite){
			setPosX(2*feldBreite-(getPosX()+getSpeedX()));
			setSpeedX(-1*getSpeedX());
		}
		else if(getPosX()+getSpeedX() < 0){
			setPosX(Math.abs(getPosX()+getSpeedX()));
			setSpeedX(-1*getSpeedX());
		}
		else{
			setPosX(getPosX()+getSpeedX());
		}
		if(getPosY()+getSpeedY() > feldLaenge){
			setPosY(2*feldLaenge-(getPosY()+getSpeedY()));
			setSpeedY(-1*getSpeedY());
		}
		else if(getPosY()+getSpeedY() < 0){
			setPosY(Math.abs(getPosY()+getSpeedY()));
			setSpeedY(-1*getSpeedY());
		}
		else{
			setPosY(getPosY()+getSpeedY());
		}
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public int getFeldBreite() {
		return feldBreite;
	}

	public void setFeldBreite(int feldBreite) {
		this.feldBreite = feldBreite;
	}

	public int getFeldLaenge() {
		return feldLaenge;
	}

	public void setFeldLaenge(int feldLaenge) {
		this.feldLaenge = feldLaenge;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	
}
