package com.inbm.constructuremanagement.inbm;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


abstract public class Downloader {


	private WeakReferenceHandler handler;
	public abstract <T> T postFileDownloaded(File[] files) throws JSONException;



	FileCache fileCache;
	File file;

	File[] files;
	public interface OnDownloaded {
		<T> void onDowndloaded(T t);
	}

	static class WeakReferenceHandler extends Handler
	{
		WeakReference<Object> reference;

		WeakReferenceHandler(Object o)
		{
			reference = new WeakReference<Object>(o);
		}
	}

	public Downloader(Context context, final OnDownloaded listener, final String url)  {


		fileCache = new FileCache(context);
		file = null;

		handler = new WeakReferenceHandler(this){
			public void handleMessage(Message msg){

				try{
					listener.onDowndloaded( postFileDownloaded(files));
					super.handleMessage(msg);
				} catch(Exception e){
					_log.inCatch("----> " + e.getMessage());
				}


			}
		};

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					fileCache.clear();
					file = fileCache.getFile(url);
					//if(file == null)
					download(url);


					//unzip(file.getAbsolutePath(), fileCache.getDir().getAbsolutePath());


					String uniqe_name = url.substring(url.lastIndexOf('/')+1, url.lastIndexOf('.'));
					_file.doUnzip(file.getAbsolutePath(), fileCache.getDir().getAbsolutePath(), uniqe_name);

					String f = fileCache.getDir().getAbsolutePath() + File.separator + uniqe_name;
					File dir = new File(f);

					//files = fileCache.getDir().listFiles();
					files = dir.listFiles();

					Looper.prepare();
					handler.sendEmptyMessage(1);
					Looper.loop();

				} catch (Exception e) {
					_log.inCatch(e.getMessage());
				}

			}
		}).start();
	}






	


	public void download(String downloadUrl) throws Exception {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(downloadUrl).build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Failed to download file: " + response);
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(response.body().bytes());

		fos.close();



	}



}
