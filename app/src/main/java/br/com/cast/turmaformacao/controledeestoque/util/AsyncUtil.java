package br.com.cast.turmaformacao.controledeestoque.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import br.com.cast.turmaformacao.controledeestoque.controllers.activities.InventoryListActivity;

public class AsyncUtil extends AsyncTask<Synchronizable, Activity, Void> {

	ProgressDialog progressDialog;
	Synchronizable synchro;

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected Void doInBackground(Synchronizable... params) {
		synchro = params[0];
		publishProgress((Activity) synchro);
		synchro.synchronize(1);
		return null;
	}

	@Override
	protected void onProgressUpdate(Activity... values) {
		progressDialog = new ProgressDialog(values[0]);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL) ;
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		synchro.synchronize(2);
		progressDialog.dismiss();
	}
}
