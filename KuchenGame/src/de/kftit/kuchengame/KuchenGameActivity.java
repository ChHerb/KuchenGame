package de.kftit.kuchengame;


import de.kftit.game.CakeOrStone;
import de.kftit.game.GameMain;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
//import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class KuchenGameActivity extends RoboActivity implements Runnable,
		OnClickListener, OnDismissListener {
	private GameMain game;
	private Handler handler;
	// Beispiel für CI einer Viewkomponente
	@InjectView(R.id.spielbereich) FrameLayout spielfeld;
	// Entfallener Code durch CI:
	//private FrameLayout spielfeld;
	private int spielfeldBreite;
	private int spielfeldLaenge;
	private ImageView[] iViews;
	private Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kuchen_game);
		handler = new Handler();
		// Durch CI entfallen:
		//spielfeld = (FrameLayout) findViewById(R.id.spielbereich);
		game = new GameMain();
		startButton = (Button) findViewById(R.id.button1);
		startButton.setOnClickListener(this);
		refreshView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kuchen_game, menu);
		return true;
	}

	private void refreshView() {
		TextView anzeigePunkte = (TextView) findViewById(R.id.savedKuchen);
		anzeigePunkte.setText(getText(R.string.isoKuchen) + ": "
				+ Integer.toString(game.getSavedCakes()));

		TextView anzeigeLevel = (TextView) findViewById(R.id.level);
		anzeigeLevel.setText(getText(R.string.level) + ": "
				+ Integer.toString(game.getLevel()));

		ProgressBar restZeit = (ProgressBar) findViewById(R.id.progressBar1);
		restZeit.setMax(game.getTimeInDeziSec());
		restZeit.setProgress(game.getUsedTime());
		if (null != iViews) {
			for (ImageView cosView : iViews) {
				CakeOrStone cosTag = (CakeOrStone) cosView.getTag();
				cosTag.positionMove();
				cosView.setTag(cosTag);
				FrameLayout.LayoutParams tmpLayout = (LayoutParams) cosView
						.getLayoutParams();
				tmpLayout.leftMargin = cosTag.getPosX();
				tmpLayout.topMargin = cosTag.getPosY();
				cosView.setLayoutParams(tmpLayout);
			}
		}
	}

	private void runLevel() {
		int imageViewpos = 0;
		spielfeldBreite = spielfeld.getWidth();
		spielfeldLaenge = spielfeld.getHeight();

		game.setFeldBreite(spielfeldBreite);
		game.setFeldLaenge(spielfeldLaenge);
		game.nextRound();
		iViews = new ImageView[game.getCakes().length + game.getStones().length];
		for (CakeOrStone cake : game.getCakes()) {
			ImageView tmpIV = new ImageView(this);
			tmpIV.setImageResource(cake.getImageID());
			tmpIV.setOnClickListener(this);
			tmpIV.setTag(cake);
			FrameLayout.LayoutParams tmpLayout = new FrameLayout.LayoutParams(
					50, 50);
			tmpLayout.leftMargin = cake.getPosX();
			tmpLayout.topMargin = cake.getPosY();
			tmpLayout.gravity = Gravity.TOP + Gravity.LEFT;
			spielfeld.addView(tmpIV, tmpLayout);
			iViews[imageViewpos] = tmpIV;
			imageViewpos++;
		}

		for (CakeOrStone stone : game.getStones()) {
			ImageView tmpIV = new ImageView(this);
			tmpIV.setImageResource(stone.getImageID());
			tmpIV.setTag(stone);
			tmpIV.setOnClickListener(this);
			FrameLayout.LayoutParams tmpLayout = new FrameLayout.LayoutParams(
					50, 50);
			tmpLayout.leftMargin = stone.getPosX();
			tmpLayout.topMargin = stone.getPosY();
			tmpLayout.gravity = Gravity.TOP + Gravity.LEFT;
			spielfeld.addView(tmpIV, tmpLayout);
			iViews[imageViewpos] = tmpIV;
			imageViewpos++;
		}
		refreshView();
		handler.postDelayed(this, 100);
	}

	@Override
	public void run() {

		countTime();
	}
	@Override
	public void onActionModeFinished(ActionMode mode) {
		super.onActionModeFinished(mode);
		game.setGameOver(true);
		finish();
	}
	private void countTime() {
		game.setUsedTime(game.getUsedTime() + 1);
		refreshView();

		game.checkRound();
		if ((!game.isGameOver() && !game.isLevelOver())) {
			handler.postDelayed(this, 100);
		}
		if (game.isGameOver()) {
			Dialog dialog = new Dialog(this,
					android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
			dialog.setContentView(R.layout.gameover);
			dialog.setOnDismissListener(this);
			dialog.show();
//			Button back = (Button) findViewById(R.id.zurueck);
//			back.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					finish();
//				}
//			});
		}
		if (game.isLevelOver()) {
			for (ImageView soc : iViews) {
				soc.setVisibility(View.GONE);
			}
			runLevel();
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			startButton.setActivated(false);
			startButton.setVisibility(View.GONE);
			runLevel();
		} else {
			CakeOrStone cos = (CakeOrStone) v.getTag();
			if (cos.isCake()) {
				game.setSavedCakes(game.getSavedCakes() + 1);
				game.setCakesSavedInActLvl(game.getCakesSavedInActLvl() + 1);
				v.setVisibility(View.GONE);
			} else {
				game.setGameOver(true);
			}
		}
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		finish();
	}

}
