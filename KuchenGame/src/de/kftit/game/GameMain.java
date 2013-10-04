package de.kftit.game;


public class GameMain {
	private CakeOrStone[] cakes;

	

	private CakeOrStone[] stones;
	private int level;
	private int timeInDeziSec;
	private int usedTime;
	private int savedCakes;
	private int cakesSavedInActLvl;
	private boolean isGameOver;
	private boolean isLevelOver;
	private int feldBreite;
	private int feldLaenge;
	

	public GameMain() {
		level = 0;
		isGameOver = false;
		savedCakes = 0;
	}

	public void nextRound() {

		setTimeInDeziSec((20 + level)*10);
		cakes = new CakeOrStone[4 + level * 2];
		stones = new CakeOrStone[2 + level];
		level++;
		setUsedTime(0);
		cakesSavedInActLvl = 0;
		createIcons(cakes , true);
		createIcons(stones, false);
	}
	private CakeOrStone[] createIcons(CakeOrStone[] fillUp, boolean isCake){
		for (int i = 0; i < fillUp.length;i++){
			fillUp[i] = new CakeOrStone(isCake, feldBreite, feldLaenge);
		}
		
		return fillUp;
	}
	
	
	public void checkRound(){
		setGameOver(checkGameOver());
		setLevelOver(levelOver());
		
	}
	private boolean checkGameOver(){
		if (isGameOver){
			return true;
		}
		if(usedTime >= timeInDeziSec){
			return true;
		}
		
		
		return false;
	}
	private boolean levelOver(){
		if(cakesSavedInActLvl == cakes.length){
			return true;
		}
		return false;
	}
	
	
	
	public CakeOrStone[] getCakes() {
		return cakes;
	}

	public void setCakes(CakeOrStone[] cakes) {
		this.cakes = cakes;
	}

	public CakeOrStone[] getStones() {
		return stones;
	}

	public void setStones(CakeOrStone[] stones) {
		this.stones = stones;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}

	public int getSavedCakes() {
		return savedCakes;
	}

	public void setSavedCakes(int savedCakes) {
		this.savedCakes = savedCakes;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public int getTimeInDeziSec() {
		return timeInDeziSec;
	}

	public void setTimeInDeziSec(int timeInDeziSec) {
		this.timeInDeziSec = timeInDeziSec;
	}

	public boolean isLevelOver() {
		return isLevelOver;
	}

	public void setLevelOver(boolean isLevelOver) {
		this.isLevelOver = isLevelOver;
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

	public int getCakesSavedInActLvl() {
		return cakesSavedInActLvl;
	}

	public void setCakesSavedInActLvl(int cakesSavedInActLvl) {
		this.cakesSavedInActLvl = cakesSavedInActLvl;
	}
}
