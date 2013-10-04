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

	/**
	 * Setzt die Bilder der Objekte und deren Flugrichtung.
	 */
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
	/**
	 * Startrichtung der Objekte. Bei Rückgabe von 1 gleiten
	 * die Objekte zum Anfang des Spiels nach rechts bzw. unten.
	 * Bei -1 gleiten die Objekte nach links bzw. oben.
	 * @return +-1
	 */
	private int makeRichtung(){
		double zufall = Utils.getRandomNumber();
		if(zufall < 0.5){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * Bewegung des Kuchen/Stein Objekts
	 */
	public void positionMove(){
		// Bei erreichen des seitlichen Außenbereichs des Spielfeldes ist
		// die Richtung so zu Ändern, dass das Objekt wieder ins Spielfeld
		// gleitet. Durch Multiplizieren der Flugrichtung mit -1 wird das
		// Objekt mit Einfallswinkel = Austrittswinkel zurück ins Spielfeld 
		// geführt.
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
		// Bei erreichen des odenen bzw. unteren Außenbereichs des Spielfeldes ist
		// die Richtung so zu Ändern, dass das Objekt wieder ins Spielfeld
		// gleitet. Durch Multiplizieren der Flugrichtung mit -1 wird das
		// Objekt mit Einfallswinkel = Austrittswinkel zurück ins Spielfeld 
		// geführt.
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
