package br.com.cast.turmaformacao.controledeestoque;

import android.app.Application;

import br.com.cast.turmaformacao.controledeestoque.util.ApplicationUtil;

public class InventoryControlApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		ApplicationUtil.setContext(getApplicationContext());
	}
}
