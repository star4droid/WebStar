package com.star4droid.WebStar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import com.google.gson.Gson;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class CodeActActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String path = "";
	private String prefex = "";
	
	private LinearLayout linear4;
	private LinearLayout searchLin;
	private LinearLayout linear7;
	private WebView webview1;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private LinearLayout linear6;
	private EditText searchTxT;
	private ImageView imageview1;
	private EditText rep;
	private Button button1;
	private Button button2;
	private ImageView imageview2;
	private ImageView imageview3;
	private TextView textview2;
	private TextView textview1;
	
	private AlertDialog.Builder d;
	private TimerTask t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.code_act);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear4 = findViewById(R.id.linear4);
		searchLin = findViewById(R.id.searchLin);
		linear7 = findViewById(R.id.linear7);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear3 = findViewById(R.id.linear3);
		linear2 = findViewById(R.id.linear2);
		linear6 = findViewById(R.id.linear6);
		searchTxT = findViewById(R.id.searchTxT);
		imageview1 = findViewById(R.id.imageview1);
		rep = findViewById(R.id.rep);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		imageview2 = findViewById(R.id.imageview2);
		imageview3 = findViewById(R.id.imageview3);
		textview2 = findViewById(R.id.textview2);
		textview1 = findViewById(R.id.textview1);
		d = new AlertDialog.Builder(this);
		
		//webviewOnProgressChanged
		webview1.setWebChromeClient(new WebChromeClient() {
				@Override public void onProgressChanged(WebView view, int _newProgress) {
					
				}
		});
		
		//OnDownloadStarted
		webview1.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				DownloadManager.Request webview1a = new DownloadManager.Request(Uri.parse(url));
				String webview1b = CookieManager.getInstance().getCookie(url);
				webview1a.addRequestHeader("cookie", webview1b);
				webview1a.addRequestHeader("User-Agent", userAgent);
				webview1a.setDescription("Downloading file...");
				webview1a.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
				webview1a.allowScanningByMediaScanner(); webview1a.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); webview1a.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
				
				DownloadManager webview1c = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				webview1c.enqueue(webview1a);
				showMessage("Downloading File....");
				BroadcastReceiver onComplete = new BroadcastReceiver() {
					public void onReceive(Context ctxt, Intent intent) {
						showMessage("Download Complete!");
						unregisterReceiver(this);
						
					}};
				registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("var el = document.getElementById(\"editor\");\nel.env.editor.find(search);".replace("search", new Gson().toJson(searchTxT.getText().toString())), null);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("var el = document.getElementById(\"editor\");\nel.env.editor.replaceAll(search);".replace("search", new Gson().toJson(rep.getText().toString())), null);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("var el = document.getElementById(\"editor\");\nel.env.editor.replace(search);".replace("search", new Gson().toJson(rep.getText().toString())), null);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (searchLin.getVisibility() == View.VISIBLE) {
					searchLin.setVisibility(View.GONE);
					imageview2.setScaleY((float)(imageview2.getScaleY() * -1));
				}
				else {
					searchLin.setVisibility(View.VISIBLE);
					imageview2.setScaleY((float)(imageview2.getScaleY() * -1));
				}
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.setWebViewClient(new WebViewClient()); 
				webview1.getSettings().setLoadWithOverviewMode(true);
				//webview1.getSettings().setUseWideViewPort(true);
				webview1.getSettings().setLoadsImagesAutomatically(true);
				webview1.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
				webview1.getSettings().setAppCacheEnabled(true);
				webview1.getSettings().setJavaScriptEnabled(true);
				webview1.getSettings().setPluginState(WebSettings.PluginState.ON);
				webview1.getSettings().setAllowFileAccess(true);
				webview1.getSettings().setAllowContentAccess(true);
				webview1.getSettings().setAllowFileAccessFromFileURLs(true);
				webview1.getSettings().setAllowUniversalAccessFromFileURLs(true);
				webview1.getSettings().setJavaScriptEnabled(true);
				webview1.getSettings().setDomStorageEnabled(true);
				android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance(); 
				cookieManager.setAcceptThirdPartyCookies(webview1, true);
				webview1.setWebContentsDebuggingEnabled(true);
				webview1.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.NORMAL);
				webview1.setSoundEffectsEnabled(true);
				webview1.getSettings().setGeolocationEnabled(true);
				if (path.endsWith(".html")) {
					webview1.loadUrl("file:///android_asset/".concat("CodeEditor/index.html"));
					textview2.setText("(html) ");
				}
				else {
					if (path.endsWith(".css")) {
						webview1.loadUrl("file:///android_asset/".concat("CodeEditor/indexCss.html"));
						if ("null".equals(FileUtil.readFile(path))) {
							FileUtil.writeFile(path, "height: auto;");
						}
						else {
							
						}
						textview2.setText("(css) ");
					}
					else {
						webview1.loadUrl("file:///android_asset/".concat("CodeEditor/indexJs.html"));
						textview2.setText("(javascript) ");
					}
				}
				java.util.Timer t = new java.util.Timer();
				final String p = FileUtil.readFile(getIntent().getStringExtra("path"));
				final String id = getIntent().getStringExtra("id");
				java.util.TimerTask as = new TimerTask() {
						@Override
						public void run() {
								runOnUiThread(new Runnable() {
										@Override
										public void run() {
												String g = "";
								if (path.endsWith(".css")) {
									prefex = id.concat("{");
									g = id.concat("{").concat(p.concat("}"));
									webview1.evaluateJavascript(" var el = document.getElementById(\"editor\");\nel.env.editor.session.setValue(code);\nel.env.editor.find(\";\");\nel.env.editor.replaceAll(\";\n\");".replace("code", new Gson().toJson(p)), null);
								}
								else {
									g = p;
									webview1.evaluateJavascript(" var el = document.getElementById(\"editor\");\nel.env.editor.session.setValue(code);".replace("code", new Gson().toJson(p)), null);
								}
										}
								});
						}
				};
				t.schedule(as, (int)(100));
				if (path.endsWith(".css")) {
					textview1.setVisibility(View.GONE);
				}
				else {
					textview1.setText(path.replace(FileUtil.getExternalStorageDir(), ""));
				}
			}
		});
	}
	
	private void initializeLogic() {
		imageview2.setScaleY((float)(imageview2.getScaleY() * -1));
		searchLin.setVisibility(View.GONE);
		webview1.setWebViewClient(new WebViewClient()); 
		webview1.getSettings().setLoadWithOverviewMode(true);
		//webview1.getSettings().setUseWideViewPort(true);
		webview1.getSettings().setLoadsImagesAutomatically(true);
		webview1.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
		webview1.getSettings().setAppCacheEnabled(true);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setPluginState(WebSettings.PluginState.ON);
		webview1.getSettings().setAllowFileAccess(true);
		webview1.getSettings().setAllowContentAccess(true);
		webview1.getSettings().setAllowFileAccessFromFileURLs(true);
		webview1.getSettings().setAllowUniversalAccessFromFileURLs(true);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setDomStorageEnabled(true);
		android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance(); 
		cookieManager.setAcceptThirdPartyCookies(webview1, true);
		webview1.setWebContentsDebuggingEnabled(true);
		webview1.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.NORMAL);
		webview1.setSoundEffectsEnabled(true);
		webview1.getSettings().setGeolocationEnabled(true);
		path = getIntent().getStringExtra("path");
		if (path.endsWith(".html")) {
			webview1.loadUrl("file:///android_asset/".concat("CodeEditor/index.html"));
			textview2.setText("(html) ");
		}
		else {
			if (path.endsWith(".css")) {
				webview1.loadUrl("file:///android_asset/".concat("CodeEditor/indexCss.html"));
				if ("null".equals(FileUtil.readFile(getIntent().getStringExtra("path")))) {
					FileUtil.writeFile(getIntent().getStringExtra("path"), "height: auto;");
				}
				else {
					
				}
				textview2.setText("(css) ");
			}
			else {
				webview1.loadUrl("file:///android_asset/".concat("CodeEditor/indexJs.html"));
				textview2.setText("(javascript) ");
			}
		}
		java.util.Timer t = new java.util.Timer();
		final String p = FileUtil.readFile(getIntent().getStringExtra("path"));
		final String id = getIntent().getStringExtra("id");
		java.util.TimerTask as = new TimerTask() {
				@Override
				public void run() {
						runOnUiThread(new Runnable() {
								@Override
								public void run() {
										String g = "";
						if (path.endsWith(".css")) {
							prefex = id.concat("{");
							g = id.concat("{").concat(p.concat("}"));
							webview1.evaluateJavascript(" var el = document.getElementById(\"editor\");\nel.env.editor.session.setValue(code);\nel.env.editor.find(\";\");\nel.env.editor.replaceAll(\";\n\");".replace("code", new Gson().toJson(p)), null);
						}
						else {
							g = p;
							webview1.evaluateJavascript(" var el = document.getElementById(\"editor\");\nel.env.editor.session.setValue(code);".replace("code", new Gson().toJson(p)), null);
						}
								}
						});
				}
		};
		t.schedule(as, (int)(100));
		button1.getBackground().setColorFilter(0xFF0067EE, PorterDuff.Mode.MULTIPLY);
		button2.getBackground().setColorFilter(0xFF0067EE, PorterDuff.Mode.MULTIPLY);
		searchLin.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF454473));
		if (getIntent().getStringExtra("path").endsWith(".css")) {
			textview1.setVisibility(View.GONE);
		}
		else {
			textview1.setText(getIntent().getStringExtra("path").replace(FileUtil.getExternalStorageDir(), ""));
		}
	}
	
	@Override
	public void onBackPressed() {
		webview1.evaluateJavascript("document.getElementById(\"editor\").env.editor.getValue()", new ValueCallback<String>() {
			    @Override
			    public void onReceiveValue(final String s888) {
				            d.setTitle("Choose Action");
				d.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (String.valueOf(s888.charAt((int)(0))).equals("\"") && String.valueOf(s888.charAt((int)(s888.length() - 1))).equals("\"")) {
							FileUtil.writeFile(path, (new Gson().fromJson(s888,String.class)));
							finish();
						}
						else {
							if ("null".equals(s888.replace("\"", ""))) {
								d.setMessage("the editor returned null!");
								d.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										
									}
								});
								d.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										finish();
									}
								});
								d.setNeutralButton("save the value", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										FileUtil.writeFile(path, s888);
										finish();
									}
								});
								d.create().show();
							}
							else {
								FileUtil.writeFile(path, s888);
								finish();
							}
						}
					}
				});
				d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.setNeutralButton("Exit without save", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				d.create().show();
				        }
			    });
	}
	public String _removeCharAt(final double _n, final String _string) {
		
		    String front = _string.substring(0,(int) _n);
		    String back = _string.substring((int) _n +1,_string.length());
		    return front + back;
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}