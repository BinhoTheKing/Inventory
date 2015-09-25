package br.com.cast.turmaformacao.controledeestoque.util;

import android.content.Context;

public class ApplicationUtil {
	public static Context applicationContext;
	private ApplicationUtil(){
		super();
	}
	public static void setContext(Context $Context){
		applicationContext = $Context;
	}
	public static Context getApplicationContext(){
		return applicationContext;
	}
}
