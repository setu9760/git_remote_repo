package com.setu.eO_GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;

import com.setu.EasyOrder.R;
import com.setu.eO_Logic.JSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Splash_activity extends Activity {

	private final static int TIME_OUT = 10000;
	public static List<Integer> sel_id = new ArrayList<Integer>();
	public static List<String> sel_list = new ArrayList<String>();
	private final String link = "http://androidphpmysql.comlu.com/easyorder/";
	private int status;
	private JSONParser jsonparse;

	public static List<Integer> p_id = new ArrayList<Integer>();
	public static List<Integer> p_cat_id = new ArrayList<Integer>();
	public static List<String> p_name = new ArrayList<String>();
	public static List<Double> p_price = new ArrayList<Double>();
	public static List<Integer> p_veg = new ArrayList<Integer>();
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		newthread newthread = new newthread();
		newthread.execute();

	}

	public class newthread extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			progress = new ProgressDialog(Splash_activity.this);
			progress.setTitle("Please wait");
			progress.setMessage("fetching data");
			progress.setCancelable(false);
			progress.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpParams httpparams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpparams, TIME_OUT);
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setParams(httpparams);
				request.setURI(new URI(link));
				HttpResponse response = client.execute(request);
				HttpEntity httpentity = response.getEntity();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(httpentity.getContent()));
				StringBuilder sb = new StringBuilder("");
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append(System.getProperty("line.separator"));
				}
				jsonparse = new JSONParser(sb.toString());
				jsonparse.parse_selection();
				jsonparse.parse_all_products();
				status = 1;

			} catch (URISyntaxException e) {
				status = 0;
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				status = 0;
				e.printStackTrace();
			} catch (IOException e) {
				status = 0;
				e.printStackTrace();
			} catch (JSONException e) {
				status = 0;
				e.printStackTrace();
			} catch (Exception e) {
				status = 0;
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			progress.dismiss();
			if (status == 1) {
				Intent intent = new Intent(Splash_activity.this,
						MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			} else {
				
				Toast.makeText(Splash_activity.this,
						"Internet Connection is inactive.", Toast.LENGTH_LONG)
						.show();
				finish();
			}
			super.onPostExecute(result);
		}

	}

}
