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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.HorizontalScrollView;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ClipData;
import java.util.Timer;
import java.util.TimerTask;
import android.net.Uri;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.content.Context;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import android.content.ClipboardManager;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class LayoutEditorActivity extends AppCompatActivity {
	
	public final int REQ_CD_IMPORTFILES = 101;
	public final int REQ_CD_FP = 102;
	public final int REQ_CD_CAMERA = 103;
	
	private Timer _timer = new Timer();
	
	private String path = "";
	private String seletedElement = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String properties = "";
	private String properties_code = "";
	private HashMap<String, Object> RMap = new HashMap<>();
	private String current_page = "";
	private boolean play = false;
	private double indexOfPages = 0;
	private boolean del = false;
	private double ur_position = 0;
	private String additonEvents = "";
	private String pageTitle = "";
	private String charS = "";
	private HashMap<String, Object> EV_Map = new HashMap<>();
	private String type = "";
	private String property = "";
	
	private ArrayList<HashMap<String, Object>> FilesListMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> widgets = new ArrayList<>();
	private ArrayList<String> names = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> LM_add = new ArrayList<>();
	private ArrayList<String> EventsListStr = new ArrayList<>();
	private ArrayList<String> PCodesList = new ArrayList<>();
	private ArrayList<String> ECodesList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> EventsLM = new ArrayList<>();
	private ArrayList<String> EV_n = new ArrayList<>();
	private ArrayList<String> PagesList = new ArrayList<>();
	private ArrayList<String> u_r_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> customTag = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> variablesLm = new ArrayList<>();
	private ArrayList<String> css_properties = new ArrayList<>();
	private ArrayList<String> NotAbleToDropInIt = new ArrayList<>();
	private ArrayList<String> libs = new ArrayList<>();
	private ArrayList<String> Characters = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private ImageView imageview1;
	private LinearLayout hscroll1;
	private LinearLayout linear2;
	private ImageView imageview16;
	private ImageView imageview3;
	private LinearLayout page;
	private ImageView imageview12;
	private TextView p_title;
	private ImageView imageview14;
	private ImageView imageview13;
	private LinearLayout propertiesLin;
	private LinearLayout eventsLin;
	private LinearLayout filesLin;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private LinearLayout linear5;
	private ScrollView vscroll1;
	private ListView widgetList;
	private Spinner spinner1;
	private WebView webview2;
	private LinearLayout linear6;
	private ImageView imageview6;
	private TextView index;
	private ImageView imageview7;
	private TextView textview1;
	private ImageView imageview8;
	private ImageView imageview9;
	private WebView webview1;
	private HorizontalScrollView hscroll2;
	private LinearLayout linear8;
	private LinearLayout bottom;
	private ImageView imageview10;
	private ImageView undo;
	private ImageView redo;
	private ImageView imageview2;
	private ImageView imageview11;
	private ListView EventsList;
	private Button button1;
	private ListView FilesList;
	
	private AlertDialog.Builder d;
	private Intent importFiles = new Intent(Intent.ACTION_GET_CONTENT);
	private TimerTask tm;
	private AlertDialog c;
	private Intent i = new Intent();
	private ObjectAnimator obj = new ObjectAnimator();
	private Vibrator vib;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_camera;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.layout_editor);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		hscroll1 = findViewById(R.id.hscroll1);
		linear2 = findViewById(R.id.linear2);
		imageview16 = findViewById(R.id.imageview16);
		imageview3 = findViewById(R.id.imageview3);
		page = findViewById(R.id.page);
		imageview12 = findViewById(R.id.imageview12);
		p_title = findViewById(R.id.p_title);
		imageview14 = findViewById(R.id.imageview14);
		imageview13 = findViewById(R.id.imageview13);
		propertiesLin = findViewById(R.id.propertiesLin);
		eventsLin = findViewById(R.id.eventsLin);
		filesLin = findViewById(R.id.filesLin);
		linear4 = findViewById(R.id.linear4);
		linear7 = findViewById(R.id.linear7);
		linear5 = findViewById(R.id.linear5);
		vscroll1 = findViewById(R.id.vscroll1);
		widgetList = findViewById(R.id.widgetList);
		spinner1 = findViewById(R.id.spinner1);
		webview2 = findViewById(R.id.webview2);
		webview2.getSettings().setJavaScriptEnabled(true);
		webview2.getSettings().setSupportZoom(true);
		linear6 = findViewById(R.id.linear6);
		imageview6 = findViewById(R.id.imageview6);
		index = findViewById(R.id.index);
		imageview7 = findViewById(R.id.imageview7);
		textview1 = findViewById(R.id.textview1);
		imageview8 = findViewById(R.id.imageview8);
		imageview9 = findViewById(R.id.imageview9);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		hscroll2 = findViewById(R.id.hscroll2);
		linear8 = findViewById(R.id.linear8);
		bottom = findViewById(R.id.bottom);
		imageview10 = findViewById(R.id.imageview10);
		undo = findViewById(R.id.undo);
		redo = findViewById(R.id.redo);
		imageview2 = findViewById(R.id.imageview2);
		imageview11 = findViewById(R.id.imageview11);
		EventsList = findViewById(R.id.EventsList);
		button1 = findViewById(R.id.button1);
		FilesList = findViewById(R.id.FilesList);
		d = new AlertDialog.Builder(this);
		importFiles.setType("*/*");
		importFiles.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		fp.setType("*/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		_file_camera = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_camera;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_camera = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_camera);
		} else {
			_uri_camera = Uri.fromFile(_file_camera);
		}
		camera.putExtra(MediaStore.EXTRA_OUTPUT, _uri_camera);
		camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Exit?");
				d.setMessage(" ");
				d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				d.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
			}
		});
		
		imageview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog c = new AlertDialog.Builder(LayoutEditorActivity.this).create();
				LayoutInflater cLI = getLayoutInflater();
				View cCV = (View) cLI.inflate(R.layout.actions, null);
				c.setView(cCV);
				final LinearLayout var = (LinearLayout)
				cCV.findViewById(R.id.var);
				final LinearLayout source = (LinearLayout)
				cCV.findViewById(R.id.source);
				final LinearLayout drop = (LinearLayout)
				cCV.findViewById(R.id.drop);
				final LinearLayout remove = (LinearLayout)
				cCV.findViewById(R.id.remove);
				final LinearLayout lib = (LinearLayout)
				cCV.findViewById(R.id.lib);
				final LinearLayout title = (LinearLayout)
				cCV.findViewById(R.id.title);
				final LinearLayout firebase = (LinearLayout)
				cCV.findViewById(R.id.firebase);
				c.show();
				source.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						i.setClass(getApplicationContext(), CodeActActivity.class);
						i.putExtra("path", path.concat("/".concat(current_page.replace(path, ""))));
						startActivity(i);
					}
				});
				var.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						final AlertDialog dialog = new AlertDialog.Builder(LayoutEditorActivity.this,android.R.style.Theme_Material_Light_NoActionBar).create();
						LayoutInflater dialogLI = getLayoutInflater();
						View dialogCV = (View) dialogLI.inflate(R.layout.dialog_csv, null);
						dialog.setView(dialogCV);
						final LinearLayout linear = (LinearLayout)
						dialogCV.findViewById(R.id.linear);
						final EditText tag = (EditText)
						dialogCV.findViewById(R.id.tag);
						final Button add = (Button)
						dialogCV.findViewById(R.id.add);
						final EditText code = (EditText)
						dialogCV.findViewById(R.id.code);
						dialog.show();
						add.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF0067EE));
						add.setText("add variable ");
						final 
						starlistview ui = new starlistview(LayoutEditorActivity.this);
						ui.custom_view = R.layout.events_csv;
						ui.setInterface(
						new starlistview.OnBlindCustomView() {
							@Override 
							public void OnBlind(int id,final int _position,final starlistview t8888){
								LayoutInflater uLI = getLayoutInflater();
								View uCV = (View) uLI.inflate(t8888.custom_view, null);
								final TextView text = (TextView)
								uCV.findViewById(R.id.text);
								final ImageView edit = (ImageView)
								uCV.findViewById(R.id.edit);
								final ImageView delete = (ImageView)
								uCV.findViewById(R.id.delete);
								text.setText((t8888.list).get((int)_position).get("full").toString());
								delete.setOnClickListener(new View.OnClickListener(){
									@Override
									public void onClick(View _view){
										variablesLm.remove((int)(_position));
										FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/variables.json"))), new Gson().toJson(variablesLm));
										ui.setListMap(variablesLm);
									}
								});
								edit.setOnClickListener(new View.OnClickListener(){
									@Override
									public void onClick(View _view){
										tag.setText((t8888.list).get((int)_position).get("name").toString());
										code.setText((t8888.list).get((int)_position).get("init").toString());
									}
								});
								t8888.cv = uCV;
							}
						}
						);
						linear.addView(ui);
						ui.setListMap(variablesLm);
						add.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								if (!_strEquals(tag.getText().toString(), "db\nfirebase\nfirebaseApp\nauth\n")) {
									map = new HashMap<>();
									map.put("name", tag.getText().toString());
									map.put("init", code.getText().toString());
									map.put("full", "var ".concat(tag.getText().toString()).concat("=").concat(code.getText().toString().concat(";")));
									variablesLm.add(map);
									FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/variables.json"))), new Gson().toJson(variablesLm));
									ui.setListMap(variablesLm);
								}
								else {
									SketchwareUtil.showMessage(getApplicationContext(), "invalid name!");
								}
							}
						});
						code.setHint("Initializer | example: new Map()");
						tag.setHint("name");
					}
				});
				drop.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						if (NotAbleToDropInIt.contains(seletedElement)) {
							NotAbleToDropInIt.remove((int)(_getPosByName(seletedElement, NotAbleToDropInIt)));
						}
						webview1.evaluateJavascript("new Sortable(document.getElementById(\"btn\"), {\n    group: 'shared',\n    animation: 150,\n    delay:300,\nonStart: function(event){\n      console.log(\"started\");\nWI.vib(\"300\");\n    }\n});".replace("btn", seletedElement), null);
						c.dismiss();
					}
				});
				remove.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						if (NotAbleToDropInIt.contains(seletedElement)) {
							
						}
						else {
							NotAbleToDropInIt.add(seletedElement);
						}
						webview1.evaluateJavascript("var el = document.getElementById('btn');\nvar sortable = Sortable.create(el, {\n   sort:false,\ndelay:10000\n});".replace("btn", seletedElement), null);
						c.dismiss();
					}
				});
				lib.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						if (FilesListMap.size() > 0) {
							final AlertDialog dialog = new AlertDialog.Builder(LayoutEditorActivity.this,android.R.style.Theme_Material_Light_NoActionBar).create();
							LayoutInflater dialogLI = getLayoutInflater();
							View dialogCV = (View) dialogLI.inflate(R.layout.dialog_csv, null);
							dialog.setView(dialogCV);
							final LinearLayout linear = (LinearLayout)
							dialogCV.findViewById(R.id.linear);
							final EditText tag = (EditText)
							dialogCV.findViewById(R.id.tag);
							final Button add = (Button)
							dialogCV.findViewById(R.id.add);
							final EditText code = (EditText)
							dialogCV.findViewById(R.id.code);
							dialog.show();
							add.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF0067EE));
							add.setText("add file ");
							((ViewGroup)add.getParent()).setVisibility(View.GONE);
							final 
							starlistview ui = new starlistview(LayoutEditorActivity.this);
							ui.custom_view = R.layout.events_csv;
							ui.setInterface(
							new starlistview.OnBlindCustomView() {
								@Override 
								public void OnBlind(int id,final int _position,final starlistview t8888){
									LayoutInflater uLI = getLayoutInflater();
									View uCV = (View) uLI.inflate(t8888.custom_view, null);
									final TextView text = (TextView)
									uCV.findViewById(R.id.text);
									final ImageView edit = (ImageView)
									uCV.findViewById(R.id.edit);
									final ImageView delete = (ImageView)
									uCV.findViewById(R.id.delete);
									text.setText(Uri.parse((t8888.list).get((int)_position).get("file").toString()).getLastPathSegment());
									delete.setOnClickListener(new View.OnClickListener(){
										@Override
										public void onClick(View _view){
											libs.remove((int)(_getPosByName(Uri.parse((t8888.list).get((int)_position).get("file").toString()).getLastPathSegment(), libs)));
											FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/libs.json"))), new Gson().toJson(libs));
											ui.setListMap(FilesListMap);
										}
									});
									edit.setImageResource(R.drawable.ic_add_white);
									if (!libs.contains(Uri.parse((t8888.list).get((int)_position).get("file").toString()).getLastPathSegment())) {
										delete.setVisibility(View.GONE);
									}
									else {
										edit.setVisibility(View.GONE);
									}
									edit.setOnClickListener(new View.OnClickListener(){
										@Override
										public void onClick(View _view){
											if (Uri.parse((t8888.list).get((int)_position).get("file").toString()).getLastPathSegment().endsWith(".css") || Uri.parse((t8888.list).get((int)_position).get("file").toString()).getLastPathSegment().endsWith(".js")) {
												libs.add(Uri.parse((t8888.list).get((int)_position).get("file").toString()).getLastPathSegment());
												FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/libs.json"))), new Gson().toJson(libs));
												ui.setListMap(FilesListMap);
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "this is not javascript or css file!!");
											}
										}
									});
									if (!Uri.parse((t8888.list).get((int)_position).get("file").toString()).getLastPathSegment().endsWith(".js")) {
										((ViewGroup)text.getParent()).setVisibility(View.GONE);
									}
									t8888.cv = uCV;
								}
							}
							);
							linear.addView(ui);
							ui.setListMap(FilesListMap);
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "add the files in files manager!");
						}
					}
				});
				obj.setTarget(cCV);
				obj.setPropertyName("translationY");
				obj.setFloatValues((float)(800), (float)(0));
				obj.setDuration((int)(300));
				obj.start();
				title.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						c.dismiss();
						final AlertDialog dialog = new AlertDialog.Builder(LayoutEditorActivity.this).create();
						LayoutInflater dialogLI = getLayoutInflater();
						View dialogCV = (View) dialogLI.inflate(R.layout.create_project, null);
						dialog.setView(dialogCV);
						final Button create = (Button)
						dialogCV.findViewById(R.id.create);
						final EditText name = (EditText)
						dialogCV.findViewById(R.id.name);
						final LinearLayout linear = (LinearLayout)
						dialogCV.findViewById(R.id.linear);
						final ImageView image = (ImageView)
						dialogCV.findViewById(R.id.image);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
						dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
						dialog.show();
						create.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF0067EE));
						linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF29354B));
						create.setText("edit");
						create.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								webview1.evaluateJavascript("document.body.parentNode.children[0].children[0].innerText=\"value\";".replace("value", name.getText().toString()), null);
								tm = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												webview1.evaluateJavascript("document.body.parentNode.children[0].children[0].innerText", new ValueCallback<String>() {
													    @Override
													    public void onReceiveValue(final String s888) {
														            pageTitle = s888.replace("\"", "");
														        }
													    });
												dialog.dismiss();
											}
										});
									}
								};
								_timer.schedule(tm, (int)(100));
							}
						});
						image.setImageResource(R.drawable.icon);
						webview1.evaluateJavascript("document.body.parentNode.children[0].children[0].innerText", new ValueCallback<String>() {
							    @Override
							    public void onReceiveValue(final String s888) {
								            name.setText(s888.replace("\"", ""));
								        }
							    });
					}
				});
				firebase.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						_FirebaseSetup();
						c.dismiss();
					}
				});
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play) {
					if (propertiesLin.getVisibility() == View.VISIBLE) {
						propertiesLin.setVisibility(View.GONE);
						eventsLin.setVisibility(View.VISIBLE);
						imageview3.setImageResource(R.drawable.ic_insert_drive_file_black);
						EventsLM.clear();
						map = new HashMap<>();
						map.put("name", "script : ".concat(current_page.replace("/", "")));
						map.put("code", "%script");
						map.put("path", path.concat("/codes/").concat(current_page).concat("/").concat("script.js"));
						map.put("bp", path.concat("/blocks/").concat(current_page).concat("/").concat("script.js"));
						map.put("cp", path.concat("/codes/").concat(current_page).concat("/").concat("script.js"));
						EventsLM.add(map);
						if (!"".equals(FileUtil.readFile(path.concat("/firebase/".concat("configurations.json"))))) {
							map = new HashMap<>();
							map = new Gson().fromJson(FileUtil.readFile(path.concat("/firebase/".concat("configurations.json"))), new TypeToken<HashMap<String, Object>>(){}.getType());
							if ("true".equals(map.get("enable").toString())) {
								map = new HashMap<>();
								map.put("name", "Firebase: OnAuthenticationChanged");
								map.put("code", "firebase.auth().onAuthStateChanged(function(user) {\n%script\n}); ");
								map.put("path", path.concat("/codes/").concat(current_page).concat("/").concat("Onchange.js"));
								map.put("bp", path.concat("/blocks/").concat(current_page).concat("/").concat("Onchange.js"));
								map.put("cp", path.concat("/codes/").concat(current_page).concat("/").concat("Onchange.js"));
								EventsLM.add(map);
							}
						}
						double n=0;
						for(String i:EV_n){
							for(String nm:names){
								if (FileUtil.isExistFile(path.concat("/codes/").concat(current_page).concat("/").concat(nm.concat("/".concat(i))))) {
									map = new HashMap<>();
									map.put("name", nm+" : ".concat(i));
									map.put("code", ECodesList.get((int)(n)));
									map.put("path", path.concat("/codes/").concat(current_page).concat("/").concat(nm.concat("/".concat(i))));
									map.put("bp", path.concat("/blocks/").concat(current_page).concat("/").concat(nm.concat("/".concat(i))));
									map.put("cp", path.concat("/codes/").concat(current_page).concat("/").concat(nm.concat("/".concat(i))));
									EventsLM.add(map);
								}
							}
							n++;
						} 
						EventsList.setAdapter(new EventsListAdapter(EventsLM));
					}
					else {
						if (eventsLin.getVisibility() == View.VISIBLE) {
							eventsLin.setVisibility(View.GONE);
							filesLin.setVisibility(View.VISIBLE);
							imageview3.setImageResource(R.drawable.layout);
							_refreshFiles();
						}
						else {
							propertiesLin.setVisibility(View.VISIBLE);
							filesLin.setVisibility(View.GONE);
							imageview3.setImageResource(R.drawable.icons8_click_64);
						}
					}
				}
				i.setClass(getApplicationContext(), WaitActivity.class);
				startActivity(i);
			}
		});
		
		imageview12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play) {
					indexOfPages--;
					try {
						String s = PagesList.get((int)(indexOfPages));
						_loadFrom(PagesList.get((int)(indexOfPages)));
					} catch (Exception e) {
						indexOfPages++;
					}
				}
			}
		});
		
		p_title.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play) {
					final AlertDialog c = new AlertDialog.Builder(LayoutEditorActivity.this).create();
					LayoutInflater cLI = getLayoutInflater();
					View cCV = (View) cLI.inflate(R.layout.dialog_csv, null);
					c.setView(cCV);
					final LinearLayout linearp = (LinearLayout)
					cCV.findViewById(R.id.linearp);
					final LinearLayout linear = (LinearLayout)
					cCV.findViewById(R.id.linear);
					c.show();
					final ArrayList<HashMap<String, Object>> nj = new ArrayList<>();
					final
					starlistview lms = new starlistview(LayoutEditorActivity.this);
					lms.custom_view = R.layout.events_csv;
					lms.setInterface(
					new starlistview.OnBlindCustomView() {
						@Override 
						public void OnBlind(int id,final int _position,final starlistview t8888){
							LayoutInflater uLI = getLayoutInflater();
							View uCV = (View) uLI.inflate(t8888.custom_view, null);
							final ImageView img = (ImageView)
							uCV.findViewById(R.id.imageview1);
							final TextView text = (TextView)
							uCV.findViewById(R.id.text);
							final ImageView delete = (ImageView)
							uCV.findViewById(R.id.delete);
							final ImageView edit = (ImageView)
							uCV.findViewById(R.id.edit);
							img.setImageResource(R.drawable.html);
							text.setText((t8888.list).get((int)_position).get("file").toString());
							delete.setOnClickListener(new View.OnClickListener(){
								@Override
								public void onClick(View _view){
									d.setTitle("are you sure?");
									d.setPositiveButton("delete", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											FileUtil.deleteFile(path.concat("/".concat(PagesList.get((int)(_position)).replace(path, "").concat("Save/"))));
											FileUtil.deleteFile(path.concat("/".concat(PagesList.get((int)(_position)).replace(path, ""))));
											_loadFrom(path.concat("/".concat("index.html")));
											nj.remove((int)(_position));
											PagesList.remove((int)(_position));
											lms.setListMap(nj);
										}
									});
									d.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											
										}
									});
									d.create().show();
								}
							});
							if (_position == 0) {
								delete.setVisibility(View.GONE);
							}
							edit.setVisibility(View.GONE);
							text.setOnClickListener(new View.OnClickListener(){
								@Override
								public void onClick(View _view){
									_loadFrom(PagesList.get((int)(_position)));
									c.dismiss();
								}
							});
							t8888.cv = uCV;
						}
					}
					);
					linear.addView(lms);
					for(String s:PagesList) {
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("file", Uri.parse(s).getLastPathSegment());
							nj.add(_item);
						}
						
					}
					lms.setListMap(nj);
					linearp.setVisibility(View.GONE);
				}
			}
		});
		
		imageview14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play) {
					indexOfPages++;
					try {
						String s = PagesList.get((int)(indexOfPages));
						_loadFrom(PagesList.get((int)(indexOfPages)));
					} catch (Exception e) {
						indexOfPages--;
					}
				}
			}
		});
		
		imageview13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play) {
					final AlertDialog c = new AlertDialog.Builder(LayoutEditorActivity.this).create();
					LayoutInflater cLI = getLayoutInflater();
					View cCV = (View) cLI.inflate(R.layout.create_project, null);
					c.setView(cCV);
					final Button create = (Button)
					cCV.findViewById(R.id.create);
					final EditText name = (EditText)
					cCV.findViewById(R.id.name);
					final LinearLayout linear = (LinearLayout)
					cCV.findViewById(R.id.linear);
					c.requestWindowFeature(Window.FEATURE_NO_TITLE); 
					c.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
					c.show();
					create.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF0067EE));
					linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF29354B));
					create.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View _view){
							if (FileUtil.isExistFile(path.concat("/".concat(name.getText().toString())))) {
								SketchwareUtil.showMessage(getApplicationContext(), "there is file with the same name");
							}
							else {
								if ((name.getText().toString().contains("/") || name.getText().toString().equals("")) || name.getText().toString().replace(".html", "").contains(".")) {
									SketchwareUtil.showMessage(getApplicationContext(), "Invalid input!! ");
								}
								else {
									if (name.getText().toString().endsWith(".html")) {
										
									}
									else {
										name.setText(name.getText().toString().concat(".html"));
									}
									FileUtil.writeFile(path.concat("/".concat(name.getText().toString())), "<html><head><title>main</title></head><body></body></html>");
									FileUtil.writeFile(path.concat("/".concat(name.getText().toString())).concat(".editor.html"), "<html><head><title>main</title></head><body onselectstart=\"return false\">\n<script id=\"scr\">\n//script here19373783\n</script>\n<select id=\"widgets\" onchange=\"WI.change(document.getElementById('widgets').value);\" style=\"width:100%;\">\n<option value=\"main\">main</option>\n</select><div id=\"main\" style=\"height: 100%; min-height:550px;\">\n<script src=\"drag.js\"></script>\n</div></body></html>");
									_loadFrom(path.concat("/".concat(name.getText().toString())));
									PagesList.add(path.concat("/".concat(name.getText().toString())));
									indexOfPages = PagesList.size();
									c.dismiss();
									indexOfPages--;
								}
							}
						}
					});
				}
			}
		});
		
		widgetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (!play) {
					double n=0;
					String type=""; 
					String log=""; 
					if (seletedElement.equals("document.body")) {
						seletedElement = "main";
					}
					for(int _repeat46 = 0; _repeat46 < (int)(LM_add.size()); _repeat46++) {
						if (LM_add.get((int)n).get("name").toString().equals(seletedElement)) {
							type = LM_add.get((int)n).get("type").toString();
							break;
						}
						else {
							log = log.concat("\n").concat(LM_add.get((int)n).get("name").toString().concat(" != ".concat(seletedElement)));
						}
						n++;
					}
					if (type.equals("layout")) {
						double btn = 1;
						while(names.contains(widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))))) {
							btn++;
						}
						webview1.loadUrl("javascript:\nvar newOption = document.createElement('option');\nvar optionText = document.createTextNode('btn');\nnewOption.appendChild(optionText);\nnewOption.setAttribute('value','btn');\ndocument.getElementById(\"widgets\").appendChild(newOption);".concat(widgets.get((int)_position).get("before").toString().concat(seletedElement.concat(widgets.get((int)_position).get("after").toString())).concat("")).replace("btn", widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn)))));
						names.add(widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))));
						map = new HashMap<>();
						map.put("name", widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))));
						map.put("type", widgets.get((int)_position).get("type").toString());
						map.put("childs", "");
						LM_add.add(map);
						ArrayList<String> arr = new ArrayList<>();
						if (!seletedElement.equals("main")) {
							try {
								if (!"".equals(LM_add.get((int)_getPosByName(seletedElement, names)).get("childs").toString())) {
									arr = new Gson().fromJson(LM_add.get((int)_getPosByName(seletedElement, names)).get("childs").toString(), new TypeToken<ArrayList<String>>(){}.getType());
								}
								arr.add(widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))));
							} catch (Exception e) {
								 
							}
						}
						LM_add.get((int)_getPosByName(seletedElement, names)).put("childs", new Gson().toJson(arr));
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
											    @Override
											    public void onReceiveValue(final String s888) {
												            if (!s888.equals(u_r_list.get((int)(ur_position)))) {
													double sz=u_r_list.size()-1;
													for(int _repeat140 = 0; _repeat140 < (int)(u_r_list.size()); _repeat140++) {
														if (sz > ur_position) {
															u_r_list.remove((int)(sz));
														}
														sz--;
													}
													u_r_list.add(s888);
													ur_position++;
												}
												_refreshUndoRedo();
												        }
											    });
									}
								});
							}
						};
						_timer.schedule(tm, (int)(100));
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "this is not Layout!!");
					}
				}
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				charS = Characters.get((int)(_position));
				_setup_properties();
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		//webviewOnProgressChanged
		webview2.setWebChromeClient(new WebChromeClient() {
				@Override public void onProgressChanged(WebView view, int _newProgress) {
					
				}
		});
		
		//OnDownloadStarted
		webview2.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				DownloadManager.Request webview2a = new DownloadManager.Request(Uri.parse(url));
				String webview2b = CookieManager.getInstance().getCookie(url);
				webview2a.addRequestHeader("cookie", webview2b);
				webview2a.addRequestHeader("User-Agent", userAgent);
				webview2a.setDescription("Downloading file...");
				webview2a.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
				webview2a.allowScanningByMediaScanner(); webview2a.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); webview2a.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
				
				DownloadManager webview2c = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				webview2c.enqueue(webview2a);
				showMessage("Downloading File....");
				BroadcastReceiver onComplete = new BroadcastReceiver() {
					public void onReceive(Context ctxt, Intent intent) {
						showMessage("Download Complete!");
						unregisterReceiver(this);
						
					}};
				registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
			}
		});
		
		webview2.setWebViewClient(new WebViewClient() {
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
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!"main".equals(seletedElement) && !play) {
					webview1.evaluateJavascript("try{\nvar btn =document.getElementById(\"btn\");\nvar nodes = Array.prototype.slice.call(btn.parentNode.children);\nvar index= nodes.indexOf(btn);\nbtn.parentNode.insertBefore(btn, \nbtn.parentNode.children[index-1]);\nvar nodes2 = Array.prototype.slice.call(btn.parentNode.children);\nbtn.addEventListener( 'click', ( event ) => {\nWI.change(event.target.id);\n});\nWI.change(btn.id);\n} catch(e){\n	if (e==\"Error: Java exception was raised during method invocation\"){} else {\nalert(e);\n}\n}".replace("btn", seletedElement), null);
				}
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!"main".equals(seletedElement) && !play) {
					webview1.evaluateJavascript("try{\nvar btn =document.getElementById(\"btn\");\nvar parent = btn.parentNode;\nvar nodes = Array.prototype.slice.call(parent.children);\nvar index= nodes.indexOf(btn);\ndocument.body.appendChild(btn);\nif ((index+1)>=parent.children.length){ parent.appendChild(btn);\n}  else {\nparent.insertBefore(btn, \nparent.children[index+1]);\n}\nvar nodes2 = Array.prototype.slice.call(btn.parentNode.children);\nbtn.addEventListener( 'click', ( event ) => {\nWI.change(event.target.id);\n});\nWI.change(btn.id);\n} catch(e){\n	if(e==\"Error: Java exception was raised during method invocation\"){} else {\nalert(e);\n}\n}".replace("btn", seletedElement), null);
				}
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Log");
				d.setMessage(textview1.getText().toString());
				d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.setPositiveButton("reset", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						textview1.setText("Log: ");
					}
				});
				d.create().show();
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview2.evaluateJavascript("document.getElementById(\"properties\").style.height='0px';\ndocument.getElementById(\"events\").style.height='100%';", null);
				imageview8.setBackgroundColor(0xFF006064);
				imageview9.setBackgroundColor(Color.TRANSPARENT);
			}
		});
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview2.evaluateJavascript("document.getElementById(\"events\").style.height='0px';\ndocument.getElementById(\"properties\").style.height='100%';", null);
				imageview9.setBackgroundColor(0xFF006064);
				imageview8.setBackgroundColor(Color.TRANSPARENT);
			}
		});
		
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
		
		imageview10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(play || seletedElement.equals("main"))) {
					try {
						del = true;
						if ("layout".equals(LM_add.get((int)_getPosByName(seletedElement, names)).get("type").toString())) {
							
						}
						FileUtil.deleteFile(path.concat("/blocks/").concat(current_page).concat("/").concat(seletedElement));
						FileUtil.deleteFile(path.concat("/codes/").concat(current_page).concat("/").concat(seletedElement));
						LM_add.remove((int)(_getPosByName(seletedElement, names)));
						names.remove((int)(_getPosByName(seletedElement, names)));
						webview1.evaluateJavascript("document.getElementById(\"".concat(seletedElement).concat("\").remove();\ndocument.getElementById(\"widgets\"). options[document.getElementById(\"widgets\").selectedIndex]= null;\ndocument.getElementById(\"widgets\").value = document.getElementById(\"widgets\").options[0].value;\nfunction check(){\nvar childs= document.getElementById(\"widgets\").getElementsByTagName('option');\nfor( i=0; i< childs.length; i++){\n var child = childs.options[i];\n  if(document.getElementById(child.value)){\n	  \n  } else {\n	  document.getElementById(\"select\").options[i]=null;\n	  check();\n	  break;\n  }\n  }\n  }\nWI.change(\"main\");"), null);
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
											    @Override
											    public void onReceiveValue(final String s888) {
												            if (!s888.equals(u_r_list.get((int)(ur_position)))) {
													double sz=u_r_list.size()-1;
													for(int _repeat235 = 0; _repeat235 < (int)(u_r_list.size()); _repeat235++) {
														if (sz > ur_position) {
															u_r_list.remove((int)(sz));
														}
														sz--;
													}
													u_r_list.add(s888);
													ur_position++;
												}
												_refreshUndoRedo();
												        }
											    });
									}
								});
							}
						};
						_timer.schedule(tm, (int)(50));
					} catch (Exception e) {
						try {
							if ("layout".equals(LM_add.get((int)_getPosByName(seletedElement, names) - 1).get("type").toString())) {
								
							}
							FileUtil.deleteFile(path.concat("/blocks/").concat(current_page).concat("/").concat(seletedElement));
							FileUtil.deleteFile(path.concat("/codes/").concat(current_page).concat("/").concat(seletedElement));
							LM_add.remove((int)(_getPosByName(seletedElement, names) - 1));
							names.remove((int)(_getPosByName(seletedElement, names) - 1));
							webview1.evaluateJavascript("document.getElementById(\"".concat(seletedElement).concat("\").remove();\ndocument.getElementById(\"widgets\").options[document.getElementById(\"widgets\").selectedIndex]= null;\nvar s=document.getElementById(\"widgets\");\nvar childs=s.getElementsByTagName('option');\nfor( i=childs.length-1; i>-1; i--){\nif (document.getElementById(s.options[i].value)){\n  \n}else{\n  s.options[i]=null;\n}\n}\nvar w= document.getElementById(\"widgets\");\nw.innerHTML=\"\";\nvar childs=document.getElementById(\"main\").getElementsByTagName(\"*\");\nfor(var i=0; i<childs.length; i++){\n	try {\n	var newOption1 = document.createElement('option');\nvar optionText1 = document.createTextNode(childs[i].id);\nnewOption1.appendChild(optionText);\nnewOption1.setAttribute('value',childs[i].id);\nw.appendChild(newOption);} catch(e){}\n	}\nWI.change(\"main\");"), null);
							tm = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
												    @Override
												    public void onReceiveValue(final String s888) {
													            if (!s888.equals(u_r_list.get((int)(ur_position)))) {
														double sz=u_r_list.size()-1;
														for(int _repeat213 = 0; _repeat213 < (int)(u_r_list.size()); _repeat213++) {
															if (sz > ur_position) {
																u_r_list.remove((int)(sz));
															}
															sz--;
														}
														u_r_list.add(s888);
														ur_position++;
													}
													_refreshUndoRedo();
													        }
												    });
										}
									});
								}
							};
							_timer.schedule(tm, (int)(50));
						} catch(final Exception ex) {
							 
						}
					}
				}
			}
		});
		
		undo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play) {
					ur_position--;
					try {
						webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML=html55;\ndocument.getElementById(\"widgets\").value = \"item\";\nWI.change(document.getElementById(\"widgets\").value);".replace("item", seletedElement).replace("html55", u_r_list.get((int)(ur_position))), null);
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										if (!play) {
											String cd=""; 
											for(String nm:names){
												cd = cd.concat("\n".concat("var element = document.getElementById(\"element\");\nelement.addEventListener(\"click\",function(event){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n});".replace("element", nm)));
											}
											webview1.evaluateJavascript("try {\n".concat(cd.concat("} catch(e){ }")), null);
										}
									}
								});
							}
						};
						_timer.schedule(tm, (int)(200));
					} catch (Exception e) {
						ur_position++;
					}
					_refreshUndoRedo();
				}
			}
		});
		
		redo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play) {
					ur_position++;
					try {
						webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML=html55;\ndocument.getElementById(\"widgets\").value = \"item\";\nWI.change(document.getElementById(\"widgets\").value);".replace("item", seletedElement).replace("html55", u_r_list.get((int)(ur_position))), null);
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										if (!play) {
											String cd=""; 
											for(String nm:names){
												cd = cd.concat("\n".concat("var element = document.getElementById(\"element\");\nelement.addEventListener(\"click\",function(event){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n});".replace("element", nm)));
											}
											webview1.evaluateJavascript("try {\n".concat(cd.concat("} catch(e){ }")), null);
										}
									}
								});
							}
						};
						_timer.schedule(tm, (int)(200));
					} catch (Exception e) {
						ur_position--;
					}
					_refreshUndoRedo();
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!play && propertiesLin.getVisibility() == View.VISIBLE) {
					webview1.evaluateJavascript("document.body. appendChild(document.getElementById(\"scr\"));\nvar scr=document.getElementById(\"scr\");\nscr.innerHTML ='//script here19373783';", null);
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
										    @Override
										    public void onReceiveValue(final String s888) {
											            String cd = "";
											/**/
											/*
for(String nm:names){
cd = cd.concat("\n".concat("var element = document.getElementById(\"element\");\nelement.addEventListener(\"click\",function(event){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n});".replace("element", nm)));
}
*/
											if (s888.contains("<body onselectstart=\"return false\">")) {
												FileUtil.writeFile(path.concat("/".concat(current_page.concat(".editor.html"))), "<html>".concat(_removeCharAt(0, _removeCharAt(s888.length() - 1, s888)).replace("//script here19373783", "//script here19373783".concat(cd)).replace("\\\"", "\"").replace("\\".concat("n"), "\n").concat("</html>")).replace("", "").replace("\\u003C", "<"));
											}
											else {
												String bvv = s888.replaceFirst("<body>", "<body onselectstart=\"return false\">");
												FileUtil.writeFile(path.concat("/".concat(current_page.concat(".editor.html"))), "<html>".concat(_removeCharAt(0, _removeCharAt(bvv.length() - 1, bvv)).replace("//script here19373783", "//script here19373783".concat(cd)).replace("\\\"", "\"").replace("\\".concat("n"), "\n").concat("</html>")).replace("", "").replace("\\u003C", "<"));
											}
											FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/save.json"))), new Gson().toJson(LM_add));
											FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/ur.json"))), new Gson().toJson(u_r_list));
											FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/urp"))), String.valueOf((long)(ur_position)));
											FileUtil.writeFile(path.concat("/".concat(current_page.concat("Save/drop.json"))), new Gson().toJson(NotAbleToDropInIt));
											tm = new TimerTask() {
												@Override
												public void run() {
													runOnUiThread(new Runnable() {
														@Override
														public void run() {
															String rev="";
															for(String scrpt:libs){
																if (scrpt.endsWith(".css")) {
																	rev = rev.concat("\n".concat("<link rel=\"stylesheet\" href=\"files/bbggbb\">".replace("bbggbb", scrpt)));
																}
																else {
																	rev = rev.concat("\n".concat("<script src=\"files/bbggbb\"></script>".replace("bbggbb", scrpt)));
																}
															}
															final String scpt=rev; 
															webview1.evaluateJavascript("document.getElementById(\"main\").appendChild(document.getElementById(\"scr\"));", null);
															tm = new TimerTask() {
																@Override
																public void run() {
																	runOnUiThread(new Runnable() {
																		@Override
																		public void run() {
																			webview1.evaluateJavascript("document.getElementById(\"main\").innerHTML", new ValueCallback<String>() {
																				    @Override
																				    public void onReceiveValue(final String s888) {
																					            String code = "";
																					String bv="";
																					double db=0;
																					for(int re5547 = 0; re5547 < (int)(variablesLm.size()); re5547++) {
																						bv = bv+"\n".concat(variablesLm.get((int)db).get("full").toString());
																							db++; 
																					}
																					code=bv;
																					for(String nm:names){
																						if ("main".equals(nm)) {
																							code = code.concat("\n".concat("var element = document.body;".replace("element", nm)));
																						}
																						else {
																							code = code.concat("\n".concat("var element = document.getElementById(\"element\");".replace("element", nm)));
																						}
																					}
																					code = code.concat("\n".concat(FileUtil.readFile(path.concat("/codes/").concat(current_page).concat("/").concat("script.js"))));
																					double n=0;
																					for(String i:EV_n){
																						for(String nm:names){
																							if (FileUtil.isExistFile(path.concat("/codes/").concat(current_page).concat("/").concat(nm.concat("/".concat(i))))) {
																								map = new HashMap<>();
																								map = new Gson().fromJson(EV_Map.get(i).toString(), new TypeToken<HashMap<String, Object>>(){}.getType());
																								String resl = FileUtil.readFile(path.concat("/codes/").concat(current_page).concat("/").concat(nm.concat("/".concat(i))));
																								code = code.concat("\n".concat(resl.replace("%element", nm)));
																							}
																						}
																						n++;
																					} 
																					String first = "<html><head><title>main</title></head><body>".replace("main", pageTitle).concat(scpt);
																					if (!"".contains(FileUtil.readFile(path.concat("/firebase/".concat("configurations.json"))))) {
																						map = new HashMap<>();
																						map = new Gson().fromJson(FileUtil.readFile(path.concat("/firebase/".concat("configurations.json"))), new TypeToken<HashMap<String, Object>>(){}.getType());
																						if ("true".equals(map.get("enable").toString())) {
																							first = first.concat("\n".concat("<script src=\"firebase/libraries/firebase-app-compat.js\"></script>\n<script src=\"firebase/libraries/firebase-firestore-compat.js\"></script>\n<script src=\"firebase/libraries/firebase-auth-compat.js\"></script>\n<script src=\"firebase/firebase.js\"></script>"));
																							if (!"".equals(FileUtil.readFile(path.concat("/codes/").concat(current_page).concat("/").concat("Onchange.js")))) {
																								first = first.concat("\n<script>//on authentication changed\n".concat(FileUtil.readFile(path.concat("/codes/").concat(current_page).concat("/").concat("Onchange.js")).concat("</script>")));
																							}
																						}
																					}
																					FileUtil.writeFile(path.concat("/".concat(current_page)), first.concat(_removeCharAt(0, _removeCharAt(s888.length() - 1, s888)).replace("min-width: 50.1235px;", "").replace("min-height: 50.1235px;", "").replace("border: 1.45322px solid black;", "").replace("\\\"", "\"").replace("\\".concat("n"), "\n").concat("</body></html>")).replace("script here19373783", "script\n"+code).replace("\\u003C", "<"));
																					tm = new TimerTask() {
																						@Override
																						public void run() {
																							runOnUiThread(new Runnable() {
																								@Override
																								public void run() {
																									imageview2.setVisibility(View.VISIBLE);
																									imageview11.setVisibility(View.VISIBLE);
																								}
																							});
																						}
																					};
																					_timer.schedule(tm, (int)(500));
																					        }
																				    });
																		}
																	});
																}
															};
															_timer.schedule(tm, (int)(30));
														}
													});
												}
											};
											_timer.schedule(tm, (int)(20));
											        }
										    });
								}
							});
						}
					};
					_timer.schedule(tm, (int)(10));
					imageview2.setVisibility(View.INVISIBLE);
					imageview11.setVisibility(View.INVISIBLE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "switch to edit mode to save!");
				}
			}
		});
		
		imageview11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				eventsLin.setVisibility(View.GONE);
				filesLin.setVisibility(View.GONE);
				propertiesLin.setVisibility(View.VISIBLE);
				if (play) {
					imageview11.setImageResource(R.drawable.ic_play_arrow_black);
					vscroll1.setVisibility(View.GONE);
					_loadFrom(path.concat("/".concat(current_page)));
				}
				else {
					imageview11.setImageResource(R.drawable.ic_pause_black);
					vscroll1.setVisibility(View.VISIBLE);
					webview1.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
					vscroll1.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
					webview1.loadUrl("file://".concat(path.concat("/".concat(current_page.replace(path, "")))));
					webview1.setWebChromeClient(new WebChromeClient() {
						// For 3.0+ Devices
						protected void openFileChooser(ValueCallback uploadMsg, String acceptType) { 
							mUploadMessage = uploadMsg; Intent i = new Intent(Intent.ACTION_GET_CONTENT); i.addCategory(Intent.CATEGORY_OPENABLE);
							i.setType("image/*"); startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
						}
						
						// For Lollipop 5.0+ Devices
						public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
							if (uploadMessage != null) {
								uploadMessage.onReceiveValue(null);
								uploadMessage = null; }
							 uploadMessage = filePathCallback; 
							Intent intent = fileChooserParams.createIntent(); try {
								startActivityForResult(intent, REQUEST_SELECT_FILE);
							} catch (ActivityNotFoundException e) {
								uploadMessage = null; Toast.makeText(getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show(); return false; }
							return true; }
						
						//For Android 4.1 only
						protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
							mUploadMessage = uploadMsg; Intent intent = new Intent(Intent.ACTION_GET_CONTENT); intent.addCategory(Intent.CATEGORY_OPENABLE); intent.setType("image/*"); startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
						}
						
						protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
							mUploadMessage = uploadMsg; 
							Intent i = new Intent(Intent.ACTION_GET_CONTENT); i.addCategory(Intent.CATEGORY_OPENABLE);
							i.setType("image/*"); startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
						}
						
						
					});
					textview1.setText("Log:");
					webview1.setWebChromeClient(new WebChromeClient() {
						    @Override
						    public boolean onConsoleMessage(android.webkit.ConsoleMessage consoleMessage) {
							        textview1.setText(textview1.getText().toString().concat("\n".concat(String.valueOf((long)(((double)(consoleMessage.lineNumber())))).concat(" : ".concat(consoleMessage.message())))));
							        return true;
							    }
						public void onReceivedError(WebView webview1, int errorCode, String description, String failingUrl) {
									textview1.setText(textview1.getText().toString().concat("\n".concat(description)));
								}
					});
					i.setClass(getApplicationContext(), WaitActivity.class);
					startActivity(i);
				}
				play = !play;
			}
		});
		
		EventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try {
					try {
						android.content.pm.PackageManager pm = getPackageManager();
						pm.getPackageInfo("com.star4droid.WebStarBlocks", android.content.pm.PackageManager.GET_ACTIVITIES);
						RMap = new HashMap<>();
						String el = EventsLM.get((int)_position).get("name").toString();
						RMap.put("code", EventsLM.get((int)_position).get("code").toString());
						RMap.put("path", EventsLM.get((int)_position).get("path").toString());
						RMap.put("element", el.split(" ")[0]);
						i.putExtra("pallets_path",FileUtil.getExternalStorageDir().concat("/.WebStar/blocks.json"));
						i.putExtra("blocks_save_path",EventsLM.get((int)_position).get("bp").toString());
						i.putExtra("code_save_path",EventsLM.get((int)_position).get("cp").toString());
						i.putExtra("collections_path",FileUtil.getExternalStorageDir().concat("/.WebStar/collections.json"));
						i.putExtra("titleB",EventsLM.get((int)_position).get("name").toString());
						map = new HashMap<>();
						map.put("element", new Gson().toJson(names));
						map.put("property", new Gson().toJson(css_properties));
						ArrayList<String> pg = new ArrayList<>();
						for(String h:PagesList){
							pg.add(Uri.parse(h).getLastPathSegment());
						}
						map.put("page", new Gson().toJson(pg));
						_refreshFiles();
						ArrayList<String> an = new ArrayList<>();
						double db=0;
						for(int re5547 = 0; re5547 < (int)(FilesListMap.size()); re5547++) {
							an.add(Uri.parse(FilesListMap.get((int)db).get("file").toString()).getLastPathSegment());
								db++; 
						}
						map.put("path", new Gson().toJson(an));
						ArrayList<String> ann = new ArrayList<>();
						double dbk=0;
						for(int re5547 = 0; re5547 < (int)(variablesLm.size()); re5547++) {
							ann.add(variablesLm.get((int)dbk).get("name").toString());
								dbk++; 
						}
						map.put("variable", new Gson().toJson(ann));
						i.putExtra("list_keys", new com.google.gson.Gson().toJson(map));
						i.setClassName("com.star4droid.WebStarBlocks","com.star4droid.WebStarBlocks.MainActivity");
						startActivity(i);
					} catch (android.content.pm.PackageManager.NameNotFoundException xegggg) { 
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/WebStar/install.apk"), "");
						try{
							int count;
							java.io.InputStream input= getApplicationContext().getAssets().open("install.apk");
							java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getExternalStorageDir().concat("/WebStar/install.apk")+"");
							byte data[] = new byte[1024];
							while ((count = input.read(data))>0) {
								output.write(data, 0, count);
							}
							output.flush();
							output.close();
							input.close();
							d.setTitle("plugin alert ");
							d.setMessage("click ok to install the plugin from the path: \n/storage/emulated/0/WebStar/install.apk");
							d.setCancelable(false);
							d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									Intent intent = new Intent(Intent.ACTION_VIEW);
									intent.setDataAndType(uriFromFile(getApplicationContext(), new File(FileUtil.getExternalStorageDir().concat("/WebStar/install.apk"))), "application/vnd.android.package-archive");
									intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
									try {
											startActivity(intent);
									} catch (ActivityNotFoundException xe){
											showMessage (xe.toString());
											 }
								}
							});
							d.create().show();
							}catch(Exception e){
									d.setTitle("eror!");
							d.setMessage("eror occurred while extracting the plugin  .. \nplease install it manually... ");
							d.setCancelable(false);
							d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									i = new Intent();
									i.setAction(Intent.ACTION_VIEW);
									i.setData(Uri.parse("https://www.mediafire.com/file/k5bk3zjj37k3ptc/WebStar+Blocks+plugin_0.4.apk/file"));
									startActivity(i);
								}
							});
							d.create().show();
							}
					}
				} catch(final Exception ee) {
					d.setTitle("eror!");
					d.setMessage("eror occurred while opening the plugin  .. \nplease install it manually... ");
					d.setCancelable(false);
					d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							i = new Intent();
							i.setAction(Intent.ACTION_VIEW);
							i.setData(Uri.parse("https://www.mediafire.com/file/k5bk3zjj37k3ptc/WebStar+Blocks+plugin_0.4.apk/file"));
							startActivity(i);
						}
					});
					d.setNegativeButton("Already installed", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							d.setTitle("info");
							d.setMessage("contact");
							d.setPositiveButton("messenger", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									i = new Intent();
									i.setAction(Intent.ACTION_VIEW);
									i.setData(Uri.parse("https://m.me/Star4Droid"));
									((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", (ee.toString())));
									SketchwareUtil.CustomToast(getApplicationContext(), "error copied To Clipboard ", 0xFFFFFFFF, 16, 0xFF000000, 8, SketchwareUtil.BOTTOM);
									startActivity(i);
								}
							});
							d.setNegativeButton("telegram", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									i = new Intent();
									i.setAction(Intent.ACTION_VIEW);
									i.setData(Uri.parse("https://t.me/star2droid"));
									((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", (ee.toString())));
									SketchwareUtil.CustomToast(getApplicationContext(), "error copied To Clipboard ", 0xFFFFFFFF, 16, 0xFF000000, 8, SketchwareUtil.BOTTOM);
									startActivity(i);
								}
							});
							d.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							d.create().show();
						}
					});
					d.create().show();
				}
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(importFiles, REQ_CD_IMPORTFILES);
			}
		});
	}
	
	private void initializeLogic() {
		button1.getBackground().setColorFilter(0xFF0067EE, PorterDuff.Mode.MULTIPLY);
		path = getIntent().getStringExtra("path");
		_loadFrom(getIntent().getStringExtra("path").concat("/index.html"));
		Characters.add("All");
		Characters.add("A");
		Characters.add("B");
		Characters.add("C");
		Characters.add("D");
		Characters.add("E");
		Characters.add("F");
		Characters.add("G");
		Characters.add("H");
		Characters.add("I");
		Characters.add("J");
		Characters.add("K");
		Characters.add("L");
		Characters.add("M");
		Characters.add("N");
		Characters.add("O");
		Characters.add("P");
		Characters.add("Q");
		Characters.add("R");
		Characters.add("S");
		Characters.add("T");
		Characters.add("U");
		Characters.add("V");
		Characters.add("W");
		Characters.add("X");
		Characters.add("Y");
		Characters.add("Z");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Characters));
		properties = "name code choice1 choice2..........etc\nparent parentNode.id\ncss%C getAttribute('style')\ninnerHTML innerHTML\nheight style.height auto value initial inherit 50% 100%\nwidth style.width auto value initial inherit 50% 100%\nmax-height style[\"max-height\"]\nmax-width style[\"max-width\"]\nmin-width style[\"min-width\"]\nmin-height style[\"min-height\"]\nfollow className\ntext innerText\nbackground-color%C style[\"background-color\"] transparent red blue green white black\ncolor%C style[\"color\"] red blue green white black\nid id\nsrc%f src files/\ntext-align style[\"text-align\"] center left right top\nvertical-align style[\"vertical-align\"] middle left right\npadding style[\"padding\"]\noverflow-x style[\"overflow-x\"] scroll hidden\noverflow-y style[\"overflow-y\"] scroll hidden\nopacity style[\"opacity\"]\nmargin style[\"margin\"]\nimage%f style.backgroundImage url('files/')\ndisplay style.display flex inline-block block none grid\ntype type week url time text tel submit search reset range radio password number month image hidden file email datetime-local date color checkbox button\npattern pattern\nvalue value\nmin min 2000-01-01\nmax max 2000-01-01\nchecked checked true false\nmaxlength maxlength\nfont-family style[\"font-family\"]\nfontSize style.fontSize\nborder style.border\njustify-content style[\"justify-content\"] center\nalignItems style.alignItems stretch center flex-start flex-end baseline initial inherit".replace("name code choice1 choice2..........etc\n", "");
		if (getIntent().hasExtra("tags")) {
			if (!getIntent().getStringExtra("properties").equals("")) {
				customTag = new Gson().fromJson(getIntent().getStringExtra("properties"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				double n=0;
				for(int re5547 = 0; re5547 < (int)(customTag.size()); re5547++) {
					properties = properties.concat("\n".concat(customTag.get((int)n).get("name").toString().concat(" ").concat(customTag.get((int)n).get("code").toString())));
						n++; 
				}
			}
		}
		customTag.clear();
		if (getIntent().hasExtra("tags")) {
			if (!getIntent().getStringExtra("tags").equals("")) {
				customTag = new Gson().fromJson(getIntent().getStringExtra("tags"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			}
		}
		if (getIntent().hasExtra("events")) {
			additonEvents = getIntent().getStringExtra("events");
		}
		_setupWidgetsLMP();
		webview1.setWebChromeClient(new WebChromeClient() {
			    @Override
			    public boolean onConsoleMessage(android.webkit.ConsoleMessage consoleMessage) {
				         
				        return true;
				    }
			public void onReceivedError(WebView webview1, int errorCode, String description, String failingUrl) {
						SketchwareUtil.showMessage(getApplicationContext(), description);
					}
		});
		current_page = "index.html";
		vscroll1.setVisibility(View.GONE);
		webview2.setWebViewClient(new WebViewClient()); 
		webview2.getSettings().setLoadWithOverviewMode(true);
		//webview2.getSettings().setUseWideViewPort(true);
		webview2.getSettings().setLoadsImagesAutomatically(true);
		webview2.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
		webview2.getSettings().setAppCacheEnabled(true);
		webview2.getSettings().setJavaScriptEnabled(true);
		webview2.getSettings().setPluginState(WebSettings.PluginState.ON);
		webview2.getSettings().setAllowFileAccess(true);
		webview2.getSettings().setAllowContentAccess(true);
		webview2.getSettings().setAllowFileAccessFromFileURLs(true);
		webview2.getSettings().setAllowUniversalAccessFromFileURLs(true);
		webview2.getSettings().setJavaScriptEnabled(true);
		webview2.getSettings().setDomStorageEnabled(true);
		android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance(); 
		cookieManager.setAcceptThirdPartyCookies(webview2, true);
		webview2.setWebContentsDebuggingEnabled(true);
		webview2.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.NORMAL);
		webview2.setSoundEffectsEnabled(true);
		webview2.getSettings().setGeolocationEnabled(true);
		webview2.loadUrl("data:text/html ,<html>".concat("<html><head><title>t</title></head><body>hi</body></html>".concat("<html>")));
		webview2.addJavascriptInterface(new WI(LayoutEditorActivity.this), "WI");
		redo.setScaleX((float)(-1));
		_addEffectTo(linear1);
		_addEffectTo(linear3);
		if ("".equals(FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/css.json")))) {
			FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/css.json"), "");
			try{
				int count;
				java.io.InputStream input= getApplicationContext().getAssets().open("css.json");
				java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getExternalStorageDir().concat("/.WebStar/save/css.json")+"");
				byte data[] = new byte[1024];
				while ((count = input.read(data))>0) {
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				
				}catch(Exception e){
						
				}
		}
		if (!"".equals(FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/css.json")))) {
			 ArrayList<HashMap<String, Object>> mk = new ArrayList<>();
			mk = new Gson().fromJson(FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/css.json")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			double bb=0;
			for(int re5547 = 0; re5547 < (int)(mk.size()); re5547++) {
				if (!mk.contains(mk.get((int)bb).get("property").toString().replace(" ", ""))) {
					css_properties.add(mk.get((int)bb).get("property").toString());
				}
					bb++; 
			}
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMPORTFILES:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				final ArrayList<String> arr = new ArrayList<>();
				arr.addAll(_filePath);
				AsyncTask.execute(new Runnable() {
					   @Override
					   public void run() {
						for(final String s:arr){
							tm = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											SketchwareUtil.showMessage(getApplicationContext(), "copying: ".concat(s));
										}
									});
								}
							};
							_timer.schedule(tm, (int)(10));
							_copyfile(s, path.concat("/files/").concat(Uri.parse(s).getLastPathSegment()));
						}
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										_refreshFiles();
									}
								});
							}
						};
						_timer.schedule(tm, (int)(100));
						   }
				});
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
			}
			break;
			
			case REQUEST_SELECT_FILE:
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				if (uploadMessage == null) return; uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(_resultCode, _data)); uploadMessage = null; }
			break;
			
			case FILECHOOSER_RESULTCODE:
			if (null == mUploadMessage){
				return; }
			Uri result = _data == null || _resultCode != RESULT_OK ? null : _data.getData(); mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;
			
			if (true){
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (RMap.containsKey("path")) {
			String r = FileUtil.readFile(RMap.get("path").toString());
			FileUtil.writeFile(RMap.get("path").toString(), RMap.get("code").toString().replace("%script", r));
			RMap.clear();
		}
		if (filesLin.getVisibility() == View.VISIBLE) {
			_refreshFiles();
		}
		PagesList.clear();
		ArrayList<String> arr = new ArrayList<>();
		FileUtil.listDir(path, arr);
		for(String i:arr){
			if ((i.endsWith(".html") && !i.endsWith(".editor.html")) && FileUtil.isFile(i)) {
				PagesList.add(i);
			}
		}
		if ((this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT)) {
			bottom.setVisibility(View.GONE);
			webview1.evaluateJavascript("document.getElementById(\"main\").style[\"min-height\"]=\"225px\";", null);
		}
		else {
			webview1.evaluateJavascript("document.getElementById(\"main\").style[\"min-height\"]=\"550px\";", null);
			bottom.setVisibility(View.VISIBLE);
			widgetList.setVisibility(View.GONE);
		}
		webview1.getSettings().setBuiltInZoomControls(true);
		webview1.getSettings().setDisplayZoomControls(true);
	}
	
	@Override
	public void onBackPressed() {
		imageview1.performClick(); 
	}
	public void _refreshFiles() {
		FilesListMap.clear();
		ArrayList<String> arr = new ArrayList<>();
		FileUtil.listDir(path.concat("/files/"), arr);
		for(String s:arr){
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("file", s);
				FilesListMap.add(_item);
			}
			
		}
		FilesList.setAdapter(new FilesListAdapter(FilesListMap));
	}
	public static class starGridview extends LinearLayout {
		public int custom_view;
		public View cv;
		public int cn=1;
		private Context ctx;
		public double cw = ((double)ViewGroup.LayoutParams.WRAP_CONTENT);
		public ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		public LinearLayout childs;
		public OnBlindCustomView ViewInterface;
		public starGridview(Context ctx) {
			super(ctx);
			this.ctx=ctx;
			this.setOrientation(LinearLayout.HORIZONTAL);
		}
		public interface OnBlindCustomView {
			public void OnBlind(int id,int _position,starGridview t);
		}
		public void setInterface(OnBlindCustomView i){
				this.ViewInterface = i;
			}
		public void setListMap(final ArrayList<HashMap<String, Object>> l){
				int x=0;
				this.removeAllViews();
				this.list.clear();
			LinearLayout lin= new LinearLayout(this.ctx);
			int cln=0;
			for(int _repeat11 = 0; _repeat11 < (int)(l.size()); _repeat11++) {
				if (cln==this.cn) {
					lin = new LinearLayout(this.ctx);
					lin.setLayoutParams(new LinearLayout.LayoutParams((int)(cw),(int) (ViewGroup.LayoutParams.WRAP_CONTENT)));
					cln=0;
				} 
				this.list.add(l.get((int)(x)));
				this.ViewInterface.OnBlind(this.custom_view,x,this);
				lin.addView(this.cv);
				try {
					this.addView(lin);} catch(Exception ex564){}
				x++;
				cln++;
			}
		}
	}
	@Override public void onConfigurationChanged(Configuration _configuration) { 
				super.onConfigurationChanged(_configuration);
		if(_configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			bottom.setVisibility(View.VISIBLE);
			widgetList.setVisibility(View.GONE);
			webview1.evaluateJavascript("document.getElementById(\"main\").style[\"min-height\"]=\"225px\";", null);
		} else {
			widgetList.setVisibility(View.VISIBLE);
			bottom.setVisibility(View.GONE);
			webview1.evaluateJavascript("document.getElementById(\"main\").style[\"min-height\"]=\"550px\";", null);
		}
	}
	
	
	public void _copyfile(final String _file_d, final String _path) {
		
		 ArrayList<String> listxx = new ArrayList<>();
		if (FileUtil.isDirectory(_file_d)) {
				FileUtil.listDir(_file_d, listxx);
				FileUtil.makeDir(_path);
				for (String sxx:listxx)   {
							if (FileUtil.isDirectory(sxx)) {
										_copyfile(sxx, _path.concat("/".concat(Uri.parse(sxx).getLastPathSegment())));
							}
							else {
										_copyfile(sxx, _path.concat("/".concat(Uri.parse(sxx).getLastPathSegment())));
							}
				}
		}
		else {
				try {
						FileUtil f = new FileUtil();
						f.copyFile(_file_d,_path); } catch (Exception ex837) {}
		}
	}
	public static class starlistview extends LinearLayout {
		public int custom_view;
		public View cv;
		public ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		public LinearLayout childs;
		public OnBlindCustomView ViewInterface;
		public starlistview(Context ctx) {
			super(ctx);
			this.setOrientation(LinearLayout.VERTICAL);
		}
		public interface OnBlindCustomView {
			public void OnBlind(int id,int _position,starlistview t8888);
		}
		public void setInterface(OnBlindCustomView i){
				this.ViewInterface = i;
			}
		public void setListMap(final ArrayList<HashMap<String, Object>> l){
				int x=0;
				this.removeAllViews();
				this.list.clear();
			for(int _repeat11 = 0; _repeat11 < (int)(l.size()); _repeat11++) {
				this.list.add(l.get((int)(x)));
				this.ViewInterface.OnBlind(this.custom_view,x,this);
				this.addView(this.cv);
				x++; 
			}
		}
	}
	
	private ValueCallback<Uri> mUploadMessage;
	public ValueCallback<Uri[]> uploadMessage;
	public static final int REQUEST_SELECT_FILE = 100;
	
	private final static int FILECHOOSER_RESULTCODE = 1;
	{
	}
	
	
	public void _loadFrom(final String _path) {
		LM_add.clear();
		names.clear();
		u_r_list.clear();
		variablesLm.clear();
		libs.clear();
		NotAbleToDropInIt.clear();
		current_page = _path.replace(path, "");
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
		webview1.addJavascriptInterface(new WI(LayoutEditorActivity.this), "WI");
		seletedElement = "main";
		ur_position = 0;
		if (!"".equals(FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/ur.json")))))) {
			u_r_list = new Gson().fromJson(FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/ur.json")))), new TypeToken<ArrayList<String>>(){}.getType());
			try {
				ur_position = Double.parseDouble(FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/urp")))));
			} catch (Exception e) {
				 
			}
			_refreshUndoRedo();
		}
		if (!FileUtil.readFile(path.concat("/").concat(current_page.concat("Save/variables.json"))).equals("")) {
			variablesLm = new Gson().fromJson(FileUtil.readFile(path.concat("/").concat(current_page.concat("Save/variables.json"))), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		webview1.loadUrl("file://".concat(_path.concat(".editor.html")));
		tm = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_setup_properties();
					}
				});
			}
		};
		_timer.schedule(tm, (int)(50));
		map = new HashMap<>();
		map.put("name", "main");
		map.put("type", "layout");
		LM_add.add(map);
		names.add("main");
		if (!FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/save.json")))).equals("")) {
			names.clear();
			LM_add.clear();
			LM_add = new Gson().fromJson(FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/save.json")))), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		if (!FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/drop.json")))).equals("")) {
			NotAbleToDropInIt = new Gson().fromJson(FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/drop.json")))), new TypeToken<ArrayList<String>>(){}.getType());
		}
		while(current_page.contains("//")) {
			current_page = current_page.replace("//", "/");
		}
		tm = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						double n=0;
						String loader = "";
						for(int _repeat101 = 0; _repeat101 < (int)(LM_add.size()); _repeat101++) {
							if (!names.contains(LM_add.get((int)n).get("name").toString())) {
								names.add(LM_add.get((int)n).get("name").toString());
							}
							loader = loader.concat("\n".concat("document.getElementById(\"element\").addEventListener(\"click\",function(event){\ntry {\nWI.change(event.target.id);\ndocument.getElementById(\"widgets\").value=event.target.id;\n} catch(e){ alert(e); }\n});".replace("element", LM_add.get((int)n).get("name").toString())));
							n++; 
						}
						/**/
						/*
webview1.evaluateJavascript("try {\n".concat(lo.concat("} catch(e){ alert(\"error \"+e);} ")), null);
d.setMessage(lo);
d.setPositiveButton("copy", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {
((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", lo));
}
});
d.setNegativeButton("ok", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {

}
});
d.create().show();
*/
						final String lo=loader;
						final String cr=current_page;
						for(int _repeat146 = 0; _repeat146 < (int)(10); _repeat146++) {
							tm = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (current_page.equals(cr)) {
												if (!play) {
													String cd=""; 
													double n=0;
													for(String nm:names){
														if ("layout".equals(LM_add.get((int)n).get("type").toString())) {
															cd = cd.concat("\n".concat("var element = document.getElementById(\"element\");\nnew Sortable(element, {\n    group: 'shared',\n   delay:300,\n    animation: 150\n});\nelement.addEventListener(\"touchend\",function(event){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n});".replace("element", nm)));
														}
														else {
															cd = cd.concat("\n".concat("var element = document.getElementById(\"element\");\nelement.addEventListener(\"touchend\",function(event){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n});".replace("element", nm)));
														}
														n++;
													}
													webview1.evaluateJavascript("try {\nnew Sortable(document.getElementById(\"main\"), {\n    group: 'shared',\n    animation: 150,\n    delay:300,\nonStart: function(event){\n      console.log(\"started\");\nWI.vib(\"300\");\n    }\n});\n".concat(cd.concat("} catch(e){ }")), null);
												}
											}
										}
									});
								}
							};
							_timer.schedule(tm, (int)(_repeat146 * 100));
						}
					}
				});
			}
		};
		_timer.schedule(tm, (int)(50));
		imageview9.setBackgroundColor(0xFF006064);
		imageview8.setBackgroundColor(Color.TRANSPARENT);
		p_title.setText(current_page);
		undo.setImageResource(R.drawable.ic_undo_grey);
		redo.setImageResource(R.drawable.ic_undo_grey);
		tm = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
							    @Override
							    public void onReceiveValue(final String s888) {
								            u_r_list.add(s888);
								        }
							    });
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										webview1.evaluateJavascript("document.body.parentNode.children[0].children[0].innerText", new ValueCallback<String>() {
											    @Override
											    public void onReceiveValue(final String s888) {
												            pageTitle = s888.replace("\"", "");
												        }
											    });
									}
								});
							}
						};
						_timer.schedule(tm, (int)(50));
					}
				});
			}
		};
		_timer.schedule(tm, (int)(200));
		if (!"".equals(FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/libs.json")))))) {
			libs = new Gson().fromJson(FileUtil.readFile(path.concat("/".concat(current_page.concat("Save/libs.json")))), new TypeToken<ArrayList<String>>(){}.getType());
		}
	}
	
	
	public void _JVI() {
	}
	class WI {
		Activity mContext;
		public WI(Activity c) { mContext = c; }
		
		@JavascriptInterface
		public void edit(String ik) {
			if (PCodesList.get((int)(Double.parseDouble(ik))).contains("innerHTML") || !"main".equals(seletedElement)) {
				if (PCodesList.get((int)(Double.parseDouble(ik))).contains("css")) {
					property = "";
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_edit_property();
								}
							});
						}
					};
					_timer.schedule(tm, (int)(100));
				}
				else {
					if (PCodesList.get((int)(Double.parseDouble(ik))).contains("innerHTML")) {
						property = "innerHTML";
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										_edit_property();
									}
								});
							}
						};
						_timer.schedule(tm, (int)(100));
					}
					else {
						_showDialogForProperty(PCodesList.get((int)(Double.parseDouble(ik))));
					}
				}
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "you can't edit this!");
			}
		}
		@JavascriptInterface
		public void reload(final String i) {
			d.setTitle("eror!");
			d.setMessage(i);
			d.setPositiveButton("reload", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									if (i.startsWith("event")) {
										_setup_events();
									}
									else {
										_setup_properties();
									}
								}
							});
						}
					};
					_timer.schedule(tm, (int)(20));
				}
			});
			d.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			d.create().show();
		}
		@JavascriptInterface
		public void change(final String i) {
			seletedElement = i.replace("\"", "");
			tm = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							webview1.evaluateJavascript("if(document.getElementById(\"btn\")){\nconsole.log(\"founded\");\n} else {\ndocument.getElementById(\"widgets\").options[selectedIndex]=null; \n}\nvar btn =document.getElementById(\"btn\");\nvar nodes = Array.prototype.slice.call(btn.parentNode.children);\nnodes.indexOf(btn)".replace("btn", seletedElement), new ValueCallback<String>() {
								    @Override
								    public void onReceiveValue(final String s888) {
									            if (s888.replace("\"", "").equals("null")) {
										tm = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														webview1.evaluateJavascript("var b=document.getElementById(\"widgets\");\nb.options[b.selectedIndex]=null;\nWI.change(\"main\");".replace("btn", seletedElement), null);
													}
												});
											}
										};
										_timer.schedule(tm, (int)(50));
									}
									else {
										index.setText(s888.replace("\"", ""));
										if (del) {
											tm = new TimerTask() {
												@Override
												public void run() {
													runOnUiThread(new Runnable() {
														@Override
														public void run() {
															webview1.evaluateJavascript("var re=\"\";\nvar s=document.getElementById(\"widgets\");\nvar childs=s.getElementsByTagName('option');\nfor(var i=childs.length-1; i>-1; i--){\nif (document.getElementById(s.options[i].value)){\n	var v=document.getElementById(s.options[i].value);\n	if (document.getElementById(v.className)){\n		var attr = document.getElementById(v.className).getAttribute('style');\n		v.style= attr;\n	}\n	  re+=\" \"+v.nodeName+\" \"+v.id;\n}else{\n  s.options[i]=null;\n}\n}\nre", new ValueCallback<String>() {
																    @Override
																    public void onReceiveValue(final String s888) {
																	            try {
																		if (!s888.replace("\"", "").equals("null")) {
																			_refreshIdsBy(s888.replace("\"", ""));
																			tm = new TimerTask() {
																				@Override
																				public void run() {
																					runOnUiThread(new Runnable() {
																						@Override
																						public void run() {
																							webview1.evaluateJavascript("element.nodeName".replace("element", seletedElement), new ValueCallback<String>() {
																								    @Override
																								    public void onReceiveValue(final String s888) {
																									            type = s888.replace("\"", "");
																									        }
																								    });
																						}
																					});
																				}
																			};
																			_timer.schedule(tm, (int)(30));
																		}
																	} catch (Exception e) {
																		SketchwareUtil.showMessage(getApplicationContext(), ("refresh "+e.toString()));
																	}
																	del = false;
																	        }
																    });
														}
													});
												}
											};
											_timer.schedule(tm, (int)(30));
										}
									}
									        }
								    });
						}
					});
				}
			};
			_timer.schedule(tm, (int)(50));
			tm = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
								    @Override
								    public void onReceiveValue(final String s888) {
									            if (!s888.equals(u_r_list.get((int)(ur_position)))) {
										double sz=u_r_list.size()-1;
										for(int _repeat319 = 0; _repeat319 < (int)(u_r_list.size()); _repeat319++) {
											if (sz > ur_position) {
												u_r_list.remove((int)(sz));
											}
											sz--;
										}
										u_r_list.add(s888);
										ur_position++;
									}
									_refreshUndoRedo();
									        }
								    });
						}
					});
				}
			};
			_timer.schedule(tm, (int)(100));
		}
		@JavascriptInterface
		public void id(final String id) {
			double n=0;
			String log=""; 
			for(int _repeat75 = 0; _repeat75 < (int)(LM_add.size()); _repeat75++) {
				if (LM_add.get((int)n).get("name").toString().equals(seletedElement)) {
					names.set((int)n, id);
					LM_add.get((int)n).put("name", id);
					break;
				}
				else {
					log = log.concat("\n").concat(LM_add.get((int)n).get("name").toString().concat(" != ".concat(seletedElement)));
				}
				n++;
			}
			{
				java.io.File dYx4Y = new java.io.File(path.concat("/blocks/").concat(current_page).concat("/").concat(seletedElement));
				java.io.File e5Cyk = new java.io.File(path.concat("/blocks/").concat(current_page).concat("/").concat(id));
				dYx4Y.renameTo(e5Cyk);
			}
			{
				java.io.File dYx4Y = new java.io.File(path.concat("/codes/").concat(current_page).concat("/").concat(seletedElement));
				java.io.File e5Cyk = new java.io.File(path.concat("/codes/").concat(current_page).concat("/").concat(id));
				dYx4Y.renameTo(e5Cyk);
			}
			tm = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							webview1.evaluateJavascript("document.getElementById(\"widgets\").value = \"c_id\";\nWI.change(\"c_id\");".replace("c_id", id), null);
						}
					});
				}
			};
			_timer.schedule(tm, (int)(30));
		}
		@JavascriptInterface
		public void event(final String code,final String name) {
			tm = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							try {
								try {
									android.content.pm.PackageManager pm = getPackageManager();
									pm.getPackageInfo("com.star4droid.WebStarBlocks", android.content.pm.PackageManager.GET_ACTIVITIES);
									RMap = new HashMap<>();
									RMap.put("code", ECodesList.get((int)(Double.parseDouble(code))));
									RMap.put("path", path.concat("/codes/").concat(current_page).concat("/").concat(seletedElement.concat("/".concat(name))));
									RMap.put("element", seletedElement);
									_refreshFiles();
									i.putExtra("pallets_path",FileUtil.getExternalStorageDir().concat("/.WebStar/blocks.json"));
									i.putExtra("blocks_save_path",path.concat("/blocks/").concat(current_page).concat("/").concat(seletedElement.concat("/".concat(name))));
									i.putExtra("code_save_path",path.concat("/codes/").concat(current_page).concat("/").concat(seletedElement.concat("/".concat(name))));
									i.putExtra("collections_path",FileUtil.getExternalStorageDir().concat("/.WebStar/collections.json"));
									i.putExtra("titleB",seletedElement.concat(" : ".concat(name)));
									ArrayList<String> pg = new ArrayList<>();
									for(String h:PagesList){
										pg.add(Uri.parse(h).getLastPathSegment());
									}
									map = new HashMap<>();
									map.put("page", new Gson().toJson(pg));
									map.put("property", new Gson().toJson(css_properties));
									map.put("element", new Gson().toJson(names));
									ArrayList<String> ann = new ArrayList<>();
									double dbk=0;
									for(int re5547 = 0; re5547 < (int)(variablesLm.size()); re5547++) {
										ann.add(variablesLm.get((int)dbk).get("name").toString());
											dbk++; 
									}
									map.put("variable", new Gson().toJson(ann));
									ann.clear();
									double db=0;
									for(int re5547 = 0; re5547 < (int)(FilesListMap.size()); re5547++) {
										ann.add(Uri.parse(FilesListMap.get((int)db).get("file").toString()).getLastPathSegment());
											db++; 
									}
									map.put("path", new Gson().toJson(ann));
									i.putExtra("list_keys", new com.google.gson.Gson().toJson(map));
									i.setClassName("com.star4droid.WebStarBlocks","com.star4droid.WebStarBlocks.MainActivity");
									startActivity(i);
								} catch (android.content.pm.PackageManager.NameNotFoundException xegggg) { 
									FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/WebStar/install.apk"), "");
									try{
										int count;
										java.io.InputStream input= getApplicationContext().getAssets().open("install.apk");
										java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getExternalStorageDir().concat("/WebStar/install.apk")+"");
										byte data[] = new byte[1024];
										while ((count = input.read(data))>0) {
											output.write(data, 0, count);
										}
										output.flush();
										output.close();
										input.close();
										d.setTitle("plugin alert ");
										d.setMessage("click ok to install the plugin from the path: \n/storage/emulated/0/WebStar/install.apk");
										d.setCancelable(false);
										d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface _dialog, int _which) {
												Intent intent = new Intent(Intent.ACTION_VIEW);
												intent.setDataAndType(uriFromFile(getApplicationContext(), new File(FileUtil.getExternalStorageDir().concat("/WebStar/install.apk"))), "application/vnd.android.package-archive");
												intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
												intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
												try {
														startActivity(intent);
												} catch (ActivityNotFoundException xe){
														showMessage (xe.toString());
														 }
											}
										});
										d.create().show();
										}catch(Exception e){
												d.setTitle("eror!");
										d.setMessage("eror occurred while extracting the plugin  .. \nplease install it manually... ");
										d.setCancelable(false);
										d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface _dialog, int _which) {
												i = new Intent();
												i.setAction(Intent.ACTION_VIEW);
												i.setData(Uri.parse("https://www.mediafire.com/file/k5bk3zjj37k3ptc/WebStar+Blocks+plugin_0.4.apk/file"));
												startActivity(i);
											}
										});
										d.create().show();
										}
								}
							} catch(final Exception ee) {
								d.setTitle("eror!");
								d.setMessage("eror occurred while opening the plugin  .. \nplease install it manually... ");
								d.setCancelable(false);
								d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										i = new Intent();
										i.setAction(Intent.ACTION_VIEW);
										i.setData(Uri.parse("https://www.mediafire.com/file/k5bk3zjj37k3ptc/WebStar+Blocks+plugin_0.4.apk/file"));
										startActivity(i);
									}
								});
								d.setNegativeButton("Already installed", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										d.setTitle("info");
										d.setMessage("contact");
										d.setPositiveButton("messenger", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface _dialog, int _which) {
												i = new Intent();
												i.setAction(Intent.ACTION_VIEW);
												i.setData(Uri.parse("https://m.me/Star4Droid"));
												((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", (ee.toString())));
												startActivity(i);
											}
										});
										d.setNegativeButton("telegram", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface _dialog, int _which) {
												i = new Intent();
												i.setAction(Intent.ACTION_VIEW);
												i.setData(Uri.parse("https://t.me/star2droid"));
												((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", (ee.toString())));
												startActivity(i);
											}
										});
										d.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface _dialog, int _which) {
												
											}
										});
										d.create().show();
									}
								});
								d.create().show();
							}
						}
					});
				}
			};
			_timer.schedule(tm, (int)(10));
		}
		@JavascriptInterface
		public void parent(final String id,final String np,final String op) {
			/**/
			/*
if ("layout".equals(LM_add.get((int)_getPosByName(np.replace("\"", ""), names)).get("type").toString())) {
ArrayList<String> arr = new ArrayList<>();
if (!LM_add.get((int)_getPosByName(np.replace("\"", ""), names)).get("childs").toString().equals("")) {
arr = new Gson().fromJson(LM_add.get((int)_getPosByName(np.replace("\"", ""), names)).get("childs").toString(), new TypeToken<ArrayList<String>>(){}.getType());
}
arr.add(id);
LM_add.get((int)_getPosByName(np.replace("\"", ""), names)).put("childs", new Gson().toJson(arr));
}
else {
tm = new TimerTask() {
@Override
public void run() {
runOnUiThread(new Runnable() {
@Override
public void run() {
webview1.evaluateJavascript("document.getElementById(\"op\").appendChild(document.getElementById(\"Eid\"));".replace("Eid", id).replace("op", op), null);
SketchwareUtil.showMessage(getApplicationContext(), "selected item is not layout!");
}
});
}
};
_timer.schedule(tm, (int)(50));
}
*/
		}
		@JavascriptInterface
		public void vib(String sc) {
			tm = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							vib.vibrate((long)(100));
						}
					});
				}
			};
			_timer.schedule(tm, (int)(10));
		}
	}
	
	
	public void _setupWidgetsLMP() {
		double n=0;
		map = new HashMap<>();
		_putKeysBySplit("name\n1button\n1before\n1var btn = document.createElement(\"button\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.innerText=\"btn\";\nbtn.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<button id=\"btn\">button</button>\n1type\n1button\n1img\n1widget_button", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1layout\n1before\n1var btn = document.createElement(\"div\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.style[\"min-height\"]=\"50.123456789px\";\nbtn.style[\"min-width\"]=\"50.123456789px\";\nbtn.style[\"border\"]=\"1.45321764px solid black\";\nnew Sortable(document.getElementById(\"btn\"), {\n    group: 'shared',\n	delay:300,\n    animation: 150,\nonStart: function(event){\n      console.log(\"started\");\nWI.vib(\"300\");\n    }\n});\nbtn.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<div id=\"btn\"></div>\n1type\n1layout\n1img\n1layout", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1textarea\n1before\n1var btn = document.createElement(\"textarea\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.innerText=\"btn\";\nbtn.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<textarea id=\"btn\">button</textarea>\n1type\n1button\n1img\n1edittext", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1text\n1before\n1var btn = document.createElement(\"div\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.innerText=\"btn\";\nbtn.addEventListener( 'touchend', ( event ) => {\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n});\n1html\n1<div id=\"btn\">button</div>\n1type\n1text\n1img\n1text", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1progressbar\n1before\n1var btn = document.createElement(\"progress\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<div id=\"btn\"></div>\n1type\n1progress\n1img\n1progress", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1form\n1before\n1var btn = document.createElement(\"form\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.style[\"min-height\"]=\"50.123456789px\";\nbtn.style[\"min-width\"]=\"50.123456789px\";\nbtn.style[\"border\"]=\"1.45321764px solid black\";\nnew Sortable(document.getElementById(\"btn\"), {\n    group: 'shared',\n    animation: 150,\n	delay:300,\nonStart: function(event){\n      console.log(\"started\");\nWI.vib(\"300\");\n    }\n});\nbtn.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<div id=\"btn\"></div>\n1type\n1layout\n1img\n1layout", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1input\n1before\n1var btn = document.createElement(\"input\");\nbtn.type=\"button\";\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\ntry {\nbtn.innerText=\"btn\"; \n} catch(e){}\nbtn.addEventListener( 'click', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<button id=\"btn\">button</button>\n1type\n1input\n1img\n1widget_button", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1ScollDiv_H\n1before\n1var btn = document.createElement(\"div\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.style[\"min-height\"]=\"50.123456789px\";\nbtn.style[\"min-width\"]=\"50.123456789px\";\nbtn.style[\"border\"]=\"1.45321764px solid black\";\nbtn.style.display=\"flex\";\nbtn.style[\"overflow-x\"]=\"scroll\";\nnew Sortable(document.getElementById(\"btn\"), {\n    group: 'shared',\n    animation: 150,\n	delay:300,\nonStart: function(event){\n      console.log(\"started\");\nWI.vib(\"300\");\n    }\n});\nbtn.style.height=\"50px\";\nbtn.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<div id=\"btn\"></div>\n1type\n1layout\n1img\n1scrollh", map);
		widgets.add(map);
		map = new HashMap<>();
		_putKeysBySplit("name\n1ScollDiv_V\n1before\n1var btn = document.createElement(\"div\");\n\n1after\n1.appendChild(btn);\nbtn.id=\"btn\";\nbtn.style[\"min-height\"]=\"50.123456789px\";\nbtn.style[\"min-width\"]=\"50.123456789px\";\nbtn.style[\"border\"]=\"1.45321764px solid black\";\nbtn.style.width=\"50px\";\nbtn.style[\"overflow-y\"]=\"scroll\";\nbtn.style.height=\"100px\";\nnew Sortable(document.getElementById(\"btn\"), {\n    group: 'shared',\n    animation: 150,\n	delay:300,\nonStart: function(event){\n      console.log(\"started\");\n	  WI.vib(\"300\");\n    }\n});\nbtn.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<div id=\"btn\"></div>\n1type\n1layout\n1img\n1scrollv", map);
		widgets.add(map);
		for(int _repeat58 = 0; _repeat58 < (int)(customTag.size()); _repeat58++) {
			map = new HashMap<>();
			map = new Gson().fromJson(new Gson().toJson(customTag.get((int)(n))).replace("click", "touchend"), new TypeToken<HashMap<String, Object>>(){}.getType());
			widgets.add(map);
			n++;
		}
		widgetList.setAdapter(new WidgetListAdapter(widgets));
		bottom.removeAllViews();
		starGridview lst = new starGridview(getApplicationContext());
		lst.custom_view = R.layout.widgets_list;
		lst.setInterface(
		new starGridview.OnBlindCustomView() {
			@Override 
			public void OnBlind(int id,final int _position,final starGridview t){
				LayoutInflater uLI = getLayoutInflater();
				View uCV = (View) uLI.inflate(t.custom_view, null);
				final TextView txxx = (TextView)
				uCV.findViewById(R.id.textview1);
				final ImageView ixxx = (ImageView)
				uCV.findViewById(R.id.imageview1);
				final LinearLayout lin = (LinearLayout)
				uCV.findViewById(R.id.linear);
				txxx.setText((t.list).get((int)_position).get("name").toString());
				try {
					ixxx.setImageResource(getResources().getIdentifier((t.list).get((int)_position).get("img").toString(), "drawable", getPackageName()));
				} catch (Exception e) {
					 
				}
				lin.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						if (!play) {
							double n=0;
							String type=""; 
							String log=""; 
							if (seletedElement.equals("document.body")) {
								seletedElement = "main";
							}
							for(int _repeat91 = 0; _repeat91 < (int)(LM_add.size()); _repeat91++) {
								if (LM_add.get((int)n).get("name").toString().equals(seletedElement)) {
									type = LM_add.get((int)n).get("type").toString();
									break;
								}
								else {
									log = log.concat("\n").concat(LM_add.get((int)n).get("name").toString().concat(" != ".concat(seletedElement)));
								}
								n++;
							}
							if (type.equals("layout")) {
								double btn = 1;
								while(names.contains(widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))))) {
									btn++;
								}
								webview1.loadUrl("javascript:\nvar newOption = document.createElement('option');\nvar optionText = document.createTextNode('btn');\nnewOption.appendChild(optionText);\nnewOption.setAttribute('value','btn');\ndocument.getElementById(\"widgets\").appendChild(newOption);".concat(widgets.get((int)_position).get("before").toString().concat(seletedElement.concat(widgets.get((int)_position).get("after").toString())).concat("")).replace("btn", widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn)))));
								names.add(widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))));
								map = new HashMap<>();
								map.put("name", widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))));
								map.put("type", widgets.get((int)_position).get("type").toString());
								map.put("childs", "");
								LM_add.add(map);
								ArrayList<String> arr = new ArrayList<>();
								if (!seletedElement.equals("main")) {
									try {
										if (!"".equals(LM_add.get((int)_getPosByName(seletedElement, names)).get("childs").toString())) {
											arr = new Gson().fromJson(LM_add.get((int)_getPosByName(seletedElement, names)).get("childs").toString(), new TypeToken<ArrayList<String>>(){}.getType());
										}
										arr.add(widgets.get((int)_position).get("name").toString().concat(String.valueOf((long)(btn))));
									} catch (Exception e) {
										 
									}
								}
								LM_add.get((int)_getPosByName(seletedElement, names)).put("childs", new Gson().toJson(arr));
								tm = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
													    @Override
													    public void onReceiveValue(final String s888) {
														            if (!s888.equals(u_r_list.get((int)(ur_position)))) {
															double sz=u_r_list.size()-1;
															for(int _repeat193 = 0; _repeat193 < (int)(u_r_list.size()); _repeat193++) {
																if (sz > ur_position) {
																	u_r_list.remove((int)(sz));
																}
																sz--;
															}
															u_r_list.add(s888);
															ur_position++;
														}
														_refreshUndoRedo();
														        }
													    });
											}
										});
									}
								};
								_timer.schedule(tm, (int)(100));
							}
							else {
								SketchwareUtil.showMessage(getApplicationContext(), "this is not Layout!!");
							}
						}
					}
				});
				try {
					boolean bv = false;
					if (widgets.get((int)(_position)).containsValue("type")) {
						if (!"".equals(widgets.get((int)(_position)).get("type").toString())) {
							String st = widgets.get((int)(_position)).get("type").toString();
							for(String sc:st.split(" ")){
								if (type.equals(sc)) {
									bv = true;
								}
							}
							if (!bv) {
								((ViewGroup)((ViewGroup)lin.getParent()).getParent()).removeView(((ViewGroup)lin.getParent()));
							}
						}
					}
				} catch (Exception e) {
					 
				}
				t.cv = uCV;
			}
		}
		);
		bottom.addView(lst);
		lst.cn=((int)10000);
		lst.cw=((double)100);
		lst.setListMap(widgets);
	}
	
	
	public void _putKeysBySplit(final String _string, final HashMap<String, Object> _map) {
		boolean b = false;
		String set="";
		for(String s:_string.split("\n1")){
				if (b) {
						map.put(set, s);
				}
				else {
						set=s; 
				}
				b = !b;
		}
	}
	
	
	public void _setup_properties() {
		PCodesList.clear();
		webview2.evaluateJavascript("((document.body)&&(WI))", new ValueCallback<String>() {
			    @Override
			    public void onReceiveValue(final String s888) {
				            if (!s888.replace("\"", "").equals("null")) {
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									properties_code = "if(document.getElementById(\"properties\")){\ndocument.getElementById(\"properties\").remove();\n}\ndocument.body.innerHTML=\"\";\ndocument.body.innerHTML+=\"<div id='properties' style='overflow-y: scroll; height:100%; width: 85px; border: 0.5px solid black;'></div>\";\nvar log=\"\"; ";
									for(String i:properties.split("\n")){
										if (String.valueOf(i.charAt((int)(0))).toUpperCase().equals(Characters.get((int)(spinner1.getSelectedItemPosition()))) || Characters.get((int)(spinner1.getSelectedItemPosition())).equals("All")) {
											PCodesList.add(i);
											properties_code = properties_code.concat("\ntry {\nvar k = document.createElement(\"button\");\nk.innerText = \"name\";\nk.style=\"height:40px;  width: 75px; padding: 2px; border: 0.5px solid black; margin: 4px; background-color: white; border-radius: 8px;\";\ndocument.getElementById(\"properties\").appendChild(k);\n} catch(r){\n	log+=\" \"+r;\n}".replace("codeNN", String.valueOf((long)(PCodesList.size() - 1))).replace("name", i.split(" ")[0].replace("%f", "").replace("%C", "")));
										}
									}
									_setup_events();
									/**/
									/*
tm = new TimerTask() {
@Override
public void run() {
runOnUiThread(new Runnable() {
@Override
public void run() {
webview1.evaluateJavascript("try {".concat(properties_code.concat("\n} catch(exp){\nWI.reload(\"eror: \"+exp);\n} ")), null);
d.setMessage(properties_code);
d.setPositiveButton("copy", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {
((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", properties_code));
}
});
d.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {

}
});
d.create().show();
}
});
}
};
_timer.schedule(tm, (int)(50));
*/
								}
							});
						}
					};
					_timer.schedule(tm, (int)(50));
				}
				else {
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_setup_properties();
								}
							});
						}
					};
					_timer.schedule(tm, (int)(100));
				}
				        }
			    });
	}
	
	
	public void _showDialogForProperty(final String _str) {
		tm = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						final AlertDialog c = new AlertDialog.Builder(LayoutEditorActivity.this).create();
						LayoutInflater cLI = getLayoutInflater();
						View cCV = (View) cLI.inflate(R.layout.edit_csv, null);
						c.setView(cCV);
						final TextView save = (TextView)
						cCV.findViewById(R.id.save);
						final TextView cancel = (TextView)
						cCV.findViewById(R.id.cancel);
						final EditText value = (EditText)
						cCV.findViewById(R.id.value);
						final ImageView pick = (ImageView)
						cCV.findViewById(R.id.pick);
						final LinearLayout choices = (LinearLayout)
						cCV.findViewById(R.id.choices);
						final WebView web = (WebView)
						cCV.findViewById(R.id.web);
						final TextView pn = (TextView)
						cCV.findViewById(R.id.pn);
						c.requestWindowFeature(Window.FEATURE_NO_TITLE); 
						c.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
						final CheckBox check = (CheckBox)
						cCV.findViewById(R.id.check);
						c.show();
						save.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF0067EE));
						cancel.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF0067EE));
						choices.setBackgroundColor(0xFFFFFFFF);
						pn.setText("");
						double n=1;
						final String str =_str.split(" ")[1];
						for(String g:_str.split(" ")){
							if(n>2){
								final CheckBox ch= new CheckBox(LayoutEditorActivity.this);
								choices.addView(ch);
								ch.setText(g);
								ch.setOnClickListener(new View.OnClickListener(){
									@Override
									public void onClick(View _view){
										if (((CheckBox) ch).isChecked()) {
											for (int i = 0; i < ((ViewGroup) choices).getChildCount(); i++) {
												View v = ((ViewGroup)choices).getChildAt(i);
												if (((CheckBox) v).getText().toString().equals(((CheckBox) ch).getText().toString())) {
													
												}
												else {
													((CheckBox) v).setChecked(false);
												}
											}
											value.setText(((CheckBox) ch).getText().toString());
										}
										else {
											
										}
									}
								});
								((ViewGroup)choices.getParent()).setLayoutParams(new LinearLayout.LayoutParams((int) (ViewGroup.LayoutParams.MATCH_PARENT),(int) (200)));
							}
							n++;
						}
						class WIH {
							Activity mContext;
							public WIH(Activity c) { mContext = c; }
							@JavascriptInterface
							public void set(String s) {
								if (check.isChecked()) {
									value.getText().insert(value.getSelectionStart(),s);
								}
								else {
									value.setText(s);
								}
							}
						}
						web.setWebViewClient(new WebViewClient()); 
						web.getSettings().setLoadWithOverviewMode(true);
						//web.getSettings().setUseWideViewPort(true);
						web.getSettings().setLoadsImagesAutomatically(true);
						web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
						web.getSettings().setAppCacheEnabled(true);
						web.getSettings().setJavaScriptEnabled(true);
						web.getSettings().setPluginState(WebSettings.PluginState.ON);
						web.getSettings().setAllowFileAccess(true);
						web.getSettings().setAllowContentAccess(true);
						web.getSettings().setAllowFileAccessFromFileURLs(true);
						web.getSettings().setAllowUniversalAccessFromFileURLs(true);
						web.getSettings().setJavaScriptEnabled(true);
						web.getSettings().setDomStorageEnabled(true);
						android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance(); 
						cookieManager.setAcceptThirdPartyCookies(web, true);
						web.setWebContentsDebuggingEnabled(true);
						web.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.NORMAL);
						web.setSoundEffectsEnabled(true);
						web.getSettings().setGeolocationEnabled(true);
						web.addJavascriptInterface(new WIH(LayoutEditorActivity.this), "WIH");
						web.loadUrl("data:text/html ,<html>".concat("<html>\n<body><input type=\"color\" id=\"p\"/><label id=\"txt\" height=\"50%\" width=\"50%\"></label>\n<script>\ndocument.getElementById(\"p\").addEventListener(\"change\",function(event){\nWIH.set(event.target.value);\n});\n</script>\n</body></html>".concat("<html>")));
						webview1.evaluateJavascript("document.getElementById(\"".concat(seletedElement).concat("\").property").replace("property", str).replace("Obj", seletedElement), new ValueCallback<String>() {
							    @Override
							    public void onReceiveValue(final String s888) {
								            if ("\"".equals(String.valueOf(s888.charAt((int)(0)))) && "\"".equals(String.valueOf(s888.charAt((int)(s888.length() - 1))))) {
									value.setText((new Gson().fromJson(s888,String.class)));
								}
								else {
									value.setText((new Gson().fromJson("\"".concat(s888.concat("\"")),String.class)));
								}
								/**/
								/*
SketchwareUtil.showMessage(getApplicationContext(), "document.getElementById(\"".concat(seletedElement).concat("\").property").replace("property", str).replace("Obj", seletedElement));
*/
								        }
							    });
						cancel.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								c.dismiss();
							}
						});
						save.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								if (str.contains("\"")) {
									if (str.contains("getAttribute")) {
										webview1.evaluateJavascript("var btn=\ndocument.getElementById(\"btn\");\nbtn.style=value;".replace("value", new Gson().toJson(value.getText().toString())).replace("btn", seletedElement).replace("property", str), null);
									}
									else {
										webview1.evaluateJavascript("var btn=\ndocument.getElementById(\"btn\");\nbtn.property=value;".replace("value", new Gson().toJson(value.getText().toString())).replace("btn", seletedElement).replace("property", str), null);
									}
								}
								else {
									if (str.split(" ")[0].equals("id")) {
										if (!"main".equals(seletedElement)) {
											webview1.evaluateJavascript("document.getElementById(element44)".replace("element44", new Gson().toJson(value.getText().toString())), new ValueCallback<String>() {
												    @Override
												    public void onReceiveValue(final String s888) {
													            if ("null".equals(s888.replace("\"", ""))) {
														tm = new TimerTask() {
															@Override
															public void run() {
																runOnUiThread(new Runnable() {
																	@Override
																	public void run() {
																		webview1.evaluateJavascript("var btn=\ndocument.getElementById(\"btn\");\nbtn.property=\"valueN\";\nvar w=document.getElementById(\"widgets\");\nw.options[w.selectedIndex]=null;\nvar newOption = document.createElement('option');\nvar optionText = document.createTextNode(btn.id);\nnewOption.appendChild(optionText);\nnewOption.setAttribute('value',btn.id);\nw.appendChild(newOption);\nw.value= btn.id;\nWI.id(btn.id);".replace("\"valueN\"", new Gson().toJson(value.getText().toString())).replace("btn", seletedElement).replace("property", str), null);
																	}
																});
															}
														};
														_timer.schedule(tm, (int)(50));
													}
													else {
														SketchwareUtil.showMessage(getApplicationContext(), "there is element with the same id!!");
														c.show();
													}
													        }
												    });
										}
									}
									else {
										webview1.evaluateJavascript("var btn=\ndocument.getElementById(\"btn\");\nvar prev=btn.parentNode;\nif (\"property\"==\"parentNode.id\"){\nif (document.getElementById(\"valueN\")){\ndocument.getElementById(\"valueN\").appendChild(btn);\nWI.parent(btn.id,btn.parentNode.id,prev.id);\n}\n} else {\nbtn.property=\"valueN\";\n}".replace("\"valueN\"", new Gson().toJson(value.getText().toString())).replace("btn", seletedElement).replace("property", str), null);
									}
								}
								tm = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												webview1.evaluateJavascript("var re=\"\";\nvar s=document.getElementById(\"widgets\");\nvar childs=s.getElementsByTagName('option');\nfor(var i=childs.length-1; i>-1; i--){\nif (document.getElementById(s.options[i].value)){\n	var v=document.getElementById(s.options[i].value);\n	if (document.getElementById(v.className)){\n		var attr = document.getElementById(v.className).getAttribute('style');\n		v.style= attr;\n		console.log(\"done\");\n	}\n	  re+=\" \"+v.nodeName+\" \"+v.id;\n}else{\n  s.options[i]=null;\n}\n}", null);
												tm = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																try {
																	if ("layout".equals(LM_add.get((int)_getPosByName(seletedElement, names)).get("type").toString())) {
																		webview1.evaluateJavascript("if(document.getElementById(\"item\").style.border==\"\"){\n	document.getElementById(\"item\").style.border=\"1.45322px solid black\";\n}".replace("item", seletedElement), null);
																	}
																} catch (Exception e) {
																	 
																}
															}
														});
													}
												};
												_timer.schedule(tm, (int)(50));
											}
										});
									}
								};
								_timer.schedule(tm, (int)(200));
								c.dismiss();
								tm = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
													    @Override
													    public void onReceiveValue(final String s888) {
														            if (!s888.equals(u_r_list.get((int)(ur_position)))) {
															double sz=u_r_list.size()-1;
															for(int _repeat170 = 0; _repeat170 < (int)(u_r_list.size()); _repeat170++) {
																if (sz > ur_position) {
																	u_r_list.remove((int)(sz));
																}
																sz--;
															}
															u_r_list.add(s888);
															ur_position++;
														}
														_refreshUndoRedo();
														        }
													    });
											}
										});
									}
								};
								_timer.schedule(tm, (int)(100));
							}
						});
						if ("follow".equals(_str.split(" ")[0]) || "parent".equals(_str.split(" ")[0])) {
							for(String g:names){
								final CheckBox ch= new CheckBox(LayoutEditorActivity.this);
								choices.addView(ch);
								ch.setText(g);
								ch.setOnClickListener(new View.OnClickListener(){
									@Override
									public void onClick(View _view){
										if (((CheckBox) ch).isChecked()) {
											for (int i = 0; i < ((ViewGroup) choices).getChildCount(); i++) {
												View v = ((ViewGroup)choices).getChildAt(i);
												if (((CheckBox) v).getText().toString().equals(((CheckBox) ch).getText().toString())) {
													
												}
												else {
													((CheckBox) v).setChecked(false);
												}
											}
											value.setText(((CheckBox) ch).getText().toString());
										}
										else {
											
										}
									}
								});
								((ViewGroup)choices.getParent()).setLayoutParams(new LinearLayout.LayoutParams((int) (ViewGroup.LayoutParams.MATCH_PARENT),(int) (200)));
							}
						}
						if (_str.split(" ")[0].contains("%f")) {
							final ArrayList<String> fnames = new ArrayList<>();
							double fn=0;
							for(int re5547 = 0; re5547 < (int)(FilesListMap.size()); re5547++) {
								if ("image".equals(_str.split(" ")[0])) {
									fnames.add("url('files/".concat(Uri.parse(FilesListMap.get((int)fn).get("file").toString()).getLastPathSegment().concat("')")));
								}
								else {
									fnames.add("files/".concat(Uri.parse(FilesListMap.get((int)fn).get("file").toString()).getLastPathSegment()));
								}
									fn++; 
							}
							for(String g:fnames){
								final CheckBox ch= new CheckBox(LayoutEditorActivity.this);
								choices.addView(ch);
								ch.setText(g);
								ch.setOnClickListener(new View.OnClickListener(){
									@Override
									public void onClick(View _view){
										if (((CheckBox) ch).isChecked()) {
											for (int i = 0; i < ((ViewGroup) choices).getChildCount(); i++) {
												View v = ((ViewGroup)choices).getChildAt(i);
												if (((CheckBox) v).getText().toString().equals(((CheckBox) ch).getText().toString())) {
													
												}
												else {
													((CheckBox) v).setChecked(false);
												}
											}
											value.setText(((CheckBox) ch).getText().toString());
										}
										else {
											
										}
									}
								});
								((ViewGroup)choices.getParent()).setLayoutParams(new LinearLayout.LayoutParams((int) (ViewGroup.LayoutParams.MATCH_PARENT),(int) (210)));
							}
						}
						if (!_str.contains("%C")) {
							((ViewGroup)web.getParent()).setVisibility(View.GONE);
						}
					}
				});
			}
		};
		_timer.schedule(tm, (int)(50));
	}
	
	
	public double _getPosByName(final String _name, final ArrayList<String> _list) {
		double n=0;
		for(int _repeat10 = 0; _repeat10 < (int)(_list.size()); _repeat10++) {
			if (_name.equals(_list.get((int)(n)))) {
				return (n);
			}
			n++;
		}
		return (-1);
	}
	
	
	public void _setup_events() {
		ECodesList.clear();
		EV_n.clear();
		EV_Map = new HashMap<>();
		webview2.evaluateJavascript("((document.body)&&(WI))", new ValueCallback<String>() {
			    @Override
			    public void onReceiveValue(final String s888) {
				            if (!s888.replace("\"", "").equals("null")) {
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									EventsListStr.clear();
									webview1.setTag("if(document.getElementById(\"events\")){\ndocument.getElementById(\"events\").remove();\n}\ndocument.body.innerHTML+=\"<div id='events' style='overflow-y: scroll; height:0px; width: 85px; border: 0.5px solid black;'></div>\";\nvar log=\"\"; ");
									if (!"".equals(additonEvents)) {
										EventsListStr = new Gson().fromJson(additonEvents, new TypeToken<ArrayList<String>>(){}.getType());
										for(String bvc:EventsListStr){
											map = new HashMap<>();
											_putKeysBySplit(bvc, map);
											_addEventFrom(map);
										}
									}
									map = new HashMap<>();
									_putKeysBySplit("name\n1OnClick\n1data\n1%element.addEventListener( 'click', ( event ) => {%script\n});\n1parameters\n1target event.target", map);
									EventsListStr.add(map.get("name").toString());
									_addEventFrom(map);
									map = new HashMap<>();
									_putKeysBySplit("name\n1touchmove\n1data\n1%element.addEventListener( 'touchmove', ( event ) => {%script\n});\n1parameters\n1target event.target", map);
									EventsListStr.add(map.get("name").toString());
									_addEventFrom(map);
									map = new HashMap<>();
									_putKeysBySplit("name\n1onload\n1data\n1%element.onload = function(){%script\n};", map);
									EventsListStr.add(map.get("name").toString());
									_addEventFrom(map);
									map = new HashMap<>();
									_putKeysBySplit("name\n1touchend\n1data\n1%element.addEventListener( 'touchend', ( event ) => {%script\n});\n1parameters\n1target event.target", map);
									EventsListStr.add(map.get("name").toString());
									_addEventFrom(map);
									map = new HashMap<>();
									_putKeysBySplit("name\n1touchStart\n1data\n1%element.addEventListener( 'touchstart', ( event ) => {%script\n});\n1parameters\n1target event.target", map);
									EventsListStr.add(map.get("name").toString());
									_addEventFrom(map);
									tm = new TimerTask() {
										@Override
										public void run() {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													properties_code = properties_code.concat(webview1.getTag().toString());
													webview2.evaluateJavascript("try {\n".concat(properties_code.concat("\nvar childDivs= document.getElementById(\"properties\").getElementsByTagName('button');\nfor( i=0; i< childDivs.length; i++){\n var childDiv = childDivs[i];\n  childDiv.addEventListener(\"click\",function(event){\n	  var nodes = Array.prototype.slice.call(event.target.parentNode.children);\n	  var index= nodes.indexOf(event.target);\n    WI.edit(index);\n  });\n}\n} catch(ex){\n	WI.reload(\"error \"+ex)\n}")), null);
													/**/
													/*
d.setMessage("try {\ndocument.body.style[\"background-color\"]=\"#471d47\";".concat(properties_code.concat("\nvar childDivs= document.getElementById(\"properties\").getElementsByTagName('button');\nfor( i=0; i< childDivs.length; i++){\n var childDiv = childDivs[i];\n  childDiv.addEventListener(\"click\",function(event){\n	  var nodes = Array.prototype.slice.call(event.target.parentNode.children);\n	  var index= nodes.indexOf(event.target);\n    WI.edit(index);\n  });\n}\nalert(\"run\");\n} catch(ex){\n	WI.reload(\"error \"+ex)\n}")));
d.setPositiveButton("copy", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {
((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "try {".concat(properties_code.concat("\nvar childDivs= document.getElementById(\"properties\").getElementsByTagName('button');\nfor( i=0; i< childDivs.length; i++){\n var childDiv = childDivs[i];\n  childDiv.addEventListener(\"click\",function(event){\n	  var nodes = Array.prototype.slice.call(event.target.parentNode.children);\n	  var index= nodes.indexOf(event.target);\n    WI.edit(index);\n  });\n}\nalert(\"run\");\n} catch(ex){\n	WI.reload(\"error \"+ex)\n}"))));
}
});
d.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {

}
});
d.create().show();
*/
												}
											});
										}
									};
									_timer.schedule(tm, (int)(50));
								}
							});
						}
					};
					_timer.schedule(tm, (int)(50));
				}
				else {
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_setup_events();
								}
							});
						}
					};
					_timer.schedule(tm, (int)(100));
				}
				        }
			    });
	}
	
	
	public void _addEventFrom(final HashMap<String, Object> _map) {
		ECodesList.add(_map.get("data").toString());
		EV_n.add(_map.get("name").toString());
		String code = "\ntry {\nvar b = document.createElement(\"button\");\nb.innerText = \"name\";\nb.addEventListener(\"click\",function(event){\ntry {\nWI.event(code,'name');\n} catch(e){ }\n});\nb.style=\"height:40px;  width: 75px; padding: 5px; border: 0.5px solid black; margin: 4px; background-color: white; border-radius: 8px;\";\ndocument.getElementById(\"events\").appendChild(b);\n} catch(r){\n	log+=\" \"+r;\n}".replace("code", String.valueOf((long)(ECodesList.size() - 1))).replace("name", _map.get("name").toString());
		webview1.setTag(webview1.getTag().toString().concat(code));
		EV_Map.put(_map.get("name").toString(), new Gson().toJson(_map));
	}
	
	
	public String _removeCharAt(final double _n, final String _string) {
		
		    String front = _string.substring(0,(int) _n);
		    String back = _string.substring((int) _n +1,_string.length());
		    return front + back;
	}
	
	
	public String _remove_last_from_path(final String _path) {
		String st=""; 
		ArrayList<String> lsts = new ArrayList<>();
		try {
				String lines[] = _path.concat("/").split("/");
				for(String line: lines) {
							lsts.add(line);
				}
		} catch (Exception e8727) {}
		lsts.remove((int)(lsts.size() - 1));
		for (String hh:lsts)   {
					if (FileUtil.isDirectory(hh)) {
								if (st=="")  {
								st = hh;
						} else {
								st = st +"/"+hh;
						} 
					}
					else {
								if (st=="")  {
								st = hh;
						} else {
								st = st +"/"+hh;
						} 
					}
		}
		return (st);
	}
	
	
	public void _refreshIdsBy(final String _str) {
		LM_add.clear();
		names.clear();
		String str = _removeCharAt(0, _str);
		double n=0;
		ArrayList<String> arr = new ArrayList<>();
		for(int _repeat46 = 0; _repeat46 < (int)(customTag.size()); _repeat46++) {
			arr.add(customTag.get((int)n).get("name").toString().toLowerCase());
			n++;
		}
		boolean b=false;
		for(String i:str.split(" ")){
			if (!i.equals("")) {
				if (b) {
					try {
						map.put("name", i);
						names.add(i);
						if (NotAbleToDropInIt.contains(i)) {
							map.put("type", i);
						}
						LM_add.add(map);
					} catch (Exception e) {
						 
					}
				}
				else {
					map = new HashMap<>();
					if ((i.equals("FORM") || arr.contains(i.toLowerCase())) || i.equals("DIV")) {
						map.put("type", "layout");
					}
					else {
						map.put("type", i);
					}
					map.put("childs", "");
				}
				b = !b;
			}
		}
	}
	
	
	public void _refreshUndoRedo() {
		try {
			String sf = u_r_list.get((int)(ur_position + 1));
			redo.setImageResource(R.drawable.ic_undo_white);
		} catch (Exception e) {
			redo.setImageResource(R.drawable.ic_undo_grey);
		}
		try {
			String sf = u_r_list.get((int)(ur_position - 1));
			undo.setImageResource(R.drawable.ic_undo_white);
		} catch (Exception e) {
			undo.setImageResource(R.drawable.ic_undo_grey);
		}
	}
	
	
	public void _addEffectTo(final View _view) {
		if (_view instanceof ImageView) {
			try {
				TypedValue typedValue = new TypedValue();
				getApplicationContext().getTheme().resolveAttribute(16843868, typedValue, true);
				_view.setBackgroundResource(typedValue.resourceId);
				_view.setClickable(true);
			} catch(Exception ze) {
				 
			}
		}
		else {
			if (_view instanceof ViewGroup) {
				for (int i = 0; i < ((ViewGroup) _view).getChildCount(); i++) {
					View v = ((ViewGroup)_view).getChildAt(i);
					_addEffectTo(v);
				}
			}
		}
	}
	
	
	public void _openFileFromPath(final String _path, final String _msg, final String _type) {
		//made by Sta4Droid (Annas Osman)
		/*MimeTypeMap myMime = MimeTypeMap.getSingleton();
String mimeType = myMime.getMimeTypeFromExtension(_type.substring(1));
lazy to fix it now 
*/
		Intent i888777 = new Intent();
		StrictMode.setVmPolicy(new
			StrictMode.VmPolicy.Builder().build());
			if(Build. VERSION.SDK_INT>=24){
							try{
											java.lang.reflect.Method
											m=StrictMode.class.getMethod(
											"disableDeathOnFileUriExposure");
											m.invoke(null);
							}
							catch(Exception e) {
											showMessage(e.toString());
							}
			}
			Uri ur888 = (Uri.fromFile(new java.io.File(_path)));
			i888777.setAction(Intent.ACTION_VIEW);
			i888777.setDataAndType(ur888,_type);
		startActivity(Intent.createChooser(i888777,_msg));
		//made by Star4Droid
		//t.me/star2droid
	}
	
	
	public void _edit_property() {
		final AlertDialog c = new AlertDialog.Builder(LayoutEditorActivity.this,android.R.style.Theme_Material_Light_NoActionBar).create();
		LayoutInflater cLI = getLayoutInflater();
		View cCV = (View) cLI.inflate(R.layout.edit_property, null);
		c.setView(cCV);
		final WebView web = (WebView)
		cCV.findViewById(R.id.web);
		final Button save = (Button)
		cCV.findViewById(R.id.save);
		final ImageView show = (ImageView)
		cCV.findViewById(R.id.show);
		final ImageView refresh = (ImageView)
		cCV.findViewById(R.id.refresh);
		final WebView W2 = (WebView)
		cCV.findViewById(R.id.W2);
		c.show();
		save.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF0067EE));
		refresh.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				if (!"".equals(property)) {
					web.setWebViewClient(new WebViewClient()); 
					web.getSettings().setLoadWithOverviewMode(true);
					//web.getSettings().setUseWideViewPort(true);
					web.getSettings().setLoadsImagesAutomatically(true);
					web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
					web.getSettings().setAppCacheEnabled(true);
					web.getSettings().setJavaScriptEnabled(true);
					web.getSettings().setPluginState(WebSettings.PluginState.ON);
					web.getSettings().setAllowFileAccess(true);
					web.getSettings().setAllowContentAccess(true);
					web.getSettings().setAllowFileAccessFromFileURLs(true);
					web.getSettings().setAllowUniversalAccessFromFileURLs(true);
					web.getSettings().setJavaScriptEnabled(true);
					web.getSettings().setDomStorageEnabled(true);
					android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance(); 
					cookieManager.setAcceptThirdPartyCookies(web, true);
					web.setWebContentsDebuggingEnabled(true);
					web.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.NORMAL);
					web.setSoundEffectsEnabled(true);
					web.getSettings().setGeolocationEnabled(true);
					web.loadUrl("file:///android_asset/".concat("CodeEditor/index.html"));
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									webview1.evaluateJavascript("document.getElementById(\"element\").innerHTML".replace("element", seletedElement), new ValueCallback<String>() {
										    @Override
										    public void onReceiveValue(final String s888) {
											            tm = new TimerTask() {
												@Override
												public void run() {
													runOnUiThread(new Runnable() {
														@Override
														public void run() {
															String f = s888;
															web.evaluateJavascript("document.getElementById(\"editor\").env.editor.setValue(code);".replace("code", f), null);
														}
													});
												}
											};
											_timer.schedule(tm, (int)(100));
											        }
										    });
								}
							});
						}
					};
					_timer.schedule(tm, (int)(100));
				}
				else {
					web.setWebViewClient(new WebViewClient()); 
					web.getSettings().setLoadWithOverviewMode(true);
					//web.getSettings().setUseWideViewPort(true);
					web.getSettings().setLoadsImagesAutomatically(true);
					web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
					web.getSettings().setAppCacheEnabled(true);
					web.getSettings().setJavaScriptEnabled(true);
					web.getSettings().setPluginState(WebSettings.PluginState.ON);
					web.getSettings().setAllowFileAccess(true);
					web.getSettings().setAllowContentAccess(true);
					web.getSettings().setAllowFileAccessFromFileURLs(true);
					web.getSettings().setAllowUniversalAccessFromFileURLs(true);
					web.getSettings().setJavaScriptEnabled(true);
					web.getSettings().setDomStorageEnabled(true);
					android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance(); 
					cookieManager.setAcceptThirdPartyCookies(web, true);
					web.setWebContentsDebuggingEnabled(true);
					web.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.NORMAL);
					web.setSoundEffectsEnabled(true);
					web.getSettings().setGeolocationEnabled(true);
					web.loadUrl("file:///android_asset/".concat("CodeEditor/indexCss.html"));
					tm = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									webview1.evaluateJavascript("document.getElementById(\"element\").getAttribute('style')".replace("element", seletedElement), new ValueCallback<String>() {
										    @Override
										    public void onReceiveValue(final String s888) {
											            tm = new TimerTask() {
												@Override
												public void run() {
													runOnUiThread(new Runnable() {
														@Override
														public void run() {
															String f = s888;
															while(f.contains("\\".concat("n"))) {
																f = f.replace("\\".concat("n"), " ");
															}
															web.evaluateJavascript("document.getElementById(\"editor\").env.editor.setValue(\"element {\\kkk987654321\"+code+\"\\kkk987654321}\");\ndocument.getElementById(\"editor\").env.editor.find(\";\");\ndocument.getElementById(\"editor\").env.editor.replaceAll(\";\\kkk987654321\");".replace("code", f).replace("kkk987654321", "n"), null);
														}
													});
												}
											};
											_timer.schedule(tm, (int)(100));
											        }
										    });
								}
							});
						}
					};
					_timer.schedule(tm, (int)(100));
				}
			}
		});
		class WIH {
			Activity mContext;
			public WIH(Activity c) { mContext = c; }
			@JavascriptInterface
			public void pick(final String clr) {
				tm = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								web.evaluateJavascript("document.getElementById(\"editor\").env.editor.insert(\"color\");".replace("color", clr.replace("\"", "")), null);
							}
						});
					}
				};
				_timer.schedule(tm, (int)(50));
			}
		}
		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				web.evaluateJavascript(" document.getElementById(\"editor\").env.editor.getValue()", new ValueCallback<String>() {
					    @Override
					    public void onReceiveValue(final String s888) {
						            java.util.Timer t = new java.util.Timer();
						java.util.TimerTask ts = new TimerTask() {
								@Override
								public void run() {
										runOnUiThread(new Runnable() {
												@Override
												public void run() {
														if ("".equals(property)) {
											String cv = _getTextFromCss(s888);
											webview1.evaluateJavascript("document.getElementById(\"element\").setAttribute(\"style\",\"valueNNNN\");\nconsole.log(\"done\");".replace("element", seletedElement).replace("\n", " ").replace("valueNNNN", cv), null);
											/**/
											/*
SketchwareUtil.showMessage(getApplicationContext(), "document.getElementById(\"element\").setAttribute(\"style\",\"valueNNNN\");".replace("element", seletedElement).replace("valueNNNN", cv));
*/
										}
										else {
											String cv = s888;
											webview1.evaluateJavascript("document.getElementById(\"element\").innerHTML =\"valueNNNN\";\nconsole.log(\"done\");".replace("element", seletedElement).replace("\n", " ").replace("valueNNNN", _removeCharAt(0, _removeCharAt(cv.length() - 1, cv))), null);
										}
												}
										});
								}
						};
						t.schedule(ts, (int)(100));
						c.dismiss();
						tm = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										webview1.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
											    @Override
											    public void onReceiveValue(final String s888) {
												            if (!s888.equals(u_r_list.get((int)(ur_position)))) {
													double sz=u_r_list.size()-1;
													for(int _repeat261 = 0; _repeat261 < (int)(u_r_list.size()); _repeat261++) {
														if (sz > ur_position) {
															u_r_list.remove((int)(sz));
														}
														sz--;
													}
													u_r_list.add(s888);
													ur_position++;
												}
												_refreshUndoRedo();
												        }
											    });
									}
								});
							}
						};
						_timer.schedule(tm, (int)(100));
						        }
					    });
			}
		});
		web.setLayoutParams(new LinearLayout.LayoutParams((int) (SketchwareUtil.getDisplayWidthPixels(getApplicationContext())),(int) (SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) - 200)));
		show.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				SketchwareUtil.showKeyboard(getApplicationContext());
			}
		});
		W2.setWebViewClient(new WebViewClient()); 
		W2.getSettings().setLoadWithOverviewMode(true);
		//W2.getSettings().setUseWideViewPort(true);
		W2.getSettings().setLoadsImagesAutomatically(true);
		W2.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
		W2.getSettings().setAppCacheEnabled(true);
		W2.getSettings().setJavaScriptEnabled(true);
		W2.getSettings().setPluginState(WebSettings.PluginState.ON);
		W2.getSettings().setAllowFileAccess(true);
		W2.getSettings().setAllowContentAccess(true);
		W2.getSettings().setAllowFileAccessFromFileURLs(true);
		W2.getSettings().setAllowUniversalAccessFromFileURLs(true);
		W2.getSettings().setJavaScriptEnabled(true);
		W2.getSettings().setDomStorageEnabled(true);
		android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance(); 
		cookieManager.setAcceptThirdPartyCookies(W2, true);
		W2.setWebContentsDebuggingEnabled(true);
		W2.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.NORMAL);
		W2.setSoundEffectsEnabled(true);
		W2.getSettings().setGeolocationEnabled(true);
		W2.addJavascriptInterface(new WIH(LayoutEditorActivity.this), "WIH");
		W2.loadUrl("data:text/html ,<html>".concat("<html><head><title>ttt</title></head><body>\n<input type='color' onchange='WIH.pick(event.target.value)'></input> color picker</body></html>".concat("<html>")));
		refresh.performClick(); 
	}
	
	
	public String _getTextFromCss(final String _str) {
		String res = "";
		double n=0;
		boolean start=false;
		double last=0;
		for(int _repeat34 = 0; _repeat34 < (int)(_str.length()); _repeat34++) {
			if ("}".equals(String.valueOf(_str.charAt((int)(last))))) {
				break; 
			}
			last++; 
		}
		n=0;
		for(int _repeat12 = 0; _repeat12 < (int)(_str.length()); _repeat12++) {
			if (start) {
				if (!(n > last-1)) {
					res = res.concat(String.valueOf(_str.charAt((int)(n))));
				}
			}
			else {
				if (String.valueOf(_str.charAt((int)(n))).equals("{")) {
					start=true;
				}
			}
			n++; 
		}
		return (res);
	}
	
	
	public String _replace(final String _txt, final String _string, final double _index) {
		double n=0;
		double d=0;
		boolean b=false; 
		for(int _repeat14 = 0; _repeat14 < (int)(_string.length()); _repeat14++) {
			if (_string.substring((int)(n), (int)(n + _txt.length())).equals(_txt)) {
				if (d == _index) {
					b = true; 
				}
				else {
					d++; 
					n++; 
				}
			}
			else {
				n++; 
			}
		}
		if (b) {
			return (_string.substring((int)(0), (int)(n)).concat(_string.substring((int)(n + _txt.length()), (int)(_string.length()))));
		}
		else {
			return (_string.substring((int)(10), (int)(n + _string.length())));
		}
	}
	
	
	public void _FirebaseSetup() {
		final AlertDialog c = new AlertDialog.Builder(LayoutEditorActivity.this).create();
		LayoutInflater cLI = getLayoutInflater();
		View cCV = (View) cLI.inflate(R.layout.firebase, null);
		c.setView(cCV);
		final EditText apiKey = (EditText)
		cCV.findViewById(R.id.apiKey);
		final EditText authDomain = (EditText)
		cCV.findViewById(R.id.authDomain);
		final EditText projectId = (EditText)
		cCV.findViewById(R.id.projectId);
		final EditText storageBucket = (EditText)
		cCV.findViewById(R.id.storageBucket);
		final EditText messagingSenderId = (EditText)
		cCV.findViewById(R.id.messagingSenderId);
		final EditText appId = (EditText)
		cCV.findViewById(R.id.appId);
		final EditText measurementId = (EditText)
		cCV.findViewById(R.id.measurementId);
		final LinearLayout linear = (LinearLayout)
		cCV.findViewById(R.id.linear);
		final CheckBox check = (CheckBox)
		cCV.findViewById(R.id.check);
		final Button save = (Button)
		cCV.findViewById(R.id.save);
		final Button cancel = (Button)
		cCV.findViewById(R.id.cancel);
		c.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		c.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		c.setCancelable(false);
		c.show();
		((ViewGroup)check.getParent()).setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)2, 0xFF263238, 0xFFFDE2E2));
		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				map = new HashMap<>();
				map.put("apiKey", apiKey.getText().toString());
				map.put("authDomain", authDomain.getText().toString());
				map.put("projectId", projectId.getText().toString());
				map.put("storageBucket", storageBucket.getText().toString());
				map.put("messagingSenderId", messagingSenderId.getText().toString());
				map.put("appId", appId.getText().toString());
				map.put("measurementId", measurementId.getText().toString());
				if (check.isChecked()) {
					map.put("enable", "true");
					if (FileUtil.isDirectory(path.concat("/firebase/".concat("libraries")))) {
						
					}
					else {
						FileUtil.writeFile(path.concat("/firebase/".concat("libraries/firebase-app-compat.js")), "");
						FileUtil.writeFile(path.concat("/firebase/".concat("libraries/firebase-firestore-compat.js")), "");
						FileUtil.writeFile(path.concat("/firebase/".concat("libraries/firebase-auth-compat.js")), "");
						try{
							int count;
							java.io.InputStream input= LayoutEditorActivity.this.getAssets().open("firebase/firebase-app-compat.js");
							java.io.OutputStream output = new  java.io.FileOutputStream(path.concat("/firebase/".concat("libraries/firebase-app-compat.js"))+"");
							byte data[] = new byte[1024];
							while ((count = input.read(data))>0) {
								output.write(data, 0, count);
							}
							output.flush();
							output.close();
							input.close();
							 
							}catch(Exception xe){
									 
							}
						try{
							int count;
							java.io.InputStream input= LayoutEditorActivity.this.getAssets().open("firebase/firebase-firestore-compat.js");
							java.io.OutputStream output = new  java.io.FileOutputStream(path.concat("/firebase/".concat("libraries/firebase-firestore-compat.js"))+"");
							byte data[] = new byte[1024];
							while ((count = input.read(data))>0) {
								output.write(data, 0, count);
							}
							output.flush();
							output.close();
							input.close();
							 
							}catch(Exception xe){
									SketchwareUtil.showMessage(getApplicationContext(), ("eror "+xe.toString()));
							}
						try{
							int count;
							java.io.InputStream input= LayoutEditorActivity.this.getAssets().open("firebase/firebase-auth-compat.js");
							java.io.OutputStream output = new  java.io.FileOutputStream(path.concat("/firebase/".concat("libraries/firebase-auth-compat.js"))+"");
							byte data[] = new byte[1024];
							while ((count = input.read(data))>0) {
								output.write(data, 0, count);
							}
							output.flush();
							output.close();
							input.close();
							 
							}catch(Exception xe){
									 
							}
						String s = "const firebaseApp = firebase.initializeApp({\n   apiKey: \"".concat(apiKey.getText().toString().concat("\",\n    authDomain: \"".concat(authDomain.getText().toString().concat("\",\n    projectId: \"".concat(projectId.getText().toString().concat("\",\n    storageBucket: \"".concat(storageBucket.getText().toString()).concat("\",\n    messagingSenderId: \"")))))));
						s = s.concat(appId.getText().toString().concat("\",\n    measurementId: \"".concat(measurementId.getText().toString().concat("\"\n   });"))));
						FileUtil.writeFile(path.concat("/firebase/firebase".concat(".js")), s.concat("\nconst db = firebaseApp.firestore();\n   const auth = firebaseApp.auth(); "));
					}
				}
				else {
					FileUtil.deleteFile(path.concat("/firebase/".concat("libraries")));
					map.put("enable", "false");
				}
				FileUtil.writeFile(path.concat("/firebase/".concat("configurations.json")), new Gson().toJson(map));
				c.dismiss();
			}
		});
		boolean vvv = false;
		if (!"".equals(FileUtil.readFile(path.concat("/firebase/".concat("configurations.json"))))) {
			map = new Gson().fromJson(FileUtil.readFile(path.concat("/firebase/".concat("configurations.json"))), new TypeToken<HashMap<String, Object>>(){}.getType());
			measurementId.setText(map.get("measurementId").toString());
			appId.setText(map.get("appId").toString());
			messagingSenderId.setText(map.get("messagingSenderId").toString());
			storageBucket.setText(map.get("storageBucket").toString());
			projectId.setText(map.get("projectId").toString());
			authDomain.setText(map.get("authDomain").toString());
			apiKey.setText(map.get("apiKey").toString());
			check.setChecked("true".equals(map.get("enable").toString()));
			vvv = "true".equals(map.get("enable").toString());
		}
		if (!vvv) {
			for (int i = 0; i < ((ViewGroup) linear).getChildCount(); i++) {
				View v = ((ViewGroup)linear).getChildAt(i);
				if (v instanceof ViewGroup) {
					if (!(v instanceof LinearLayout)) {
						v.setVisibility(View.GONE);
					}
				}
			}
		}
		check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
								final boolean checknnbbmm = _param2;
								if (check.isChecked()) {
					for (int i = 0; i < ((ViewGroup) linear).getChildCount(); i++) {
						View v = ((ViewGroup)linear).getChildAt(i);
						if (v instanceof ViewGroup) {
							if (!(v instanceof LinearLayout)) {
								v.setVisibility(View.VISIBLE);
							}
						}
					}
				}
				else {
					for (int i = 0; i < ((ViewGroup) linear).getChildCount(); i++) {
						View v = ((ViewGroup)linear).getChildAt(i);
						if (v instanceof ViewGroup) {
							if (!(v instanceof LinearLayout)) {
								v.setVisibility(View.GONE);
							}
						}
					}
				}
						}
				});
		linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFFFFF));
		save.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF0067EE));
		cancel.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF0067EE));
		cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				c.dismiss();
			}
		});
	}
	
	
	public boolean _strEquals(final String _str, final String _lst) {
		boolean b=false;
		for(String sx:_lst.split("\n")){
			if (sx==_str){
				b = true;
				break;
			}} 
		return (b);
	}
	
	
	public void _extra() {
	}
	private static Uri uriFromFile(Context context, File file) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
		} else {
			return Uri.fromFile(file);
		}
	}
	
	public class WidgetListAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public WidgetListAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.widgets_list, null);
			}
			
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear = _view.findViewById(R.id.linear);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			textview1.setText(_data.get((int)_position).get("name").toString());
			try {
				imageview1.setImageResource(getResources().getIdentifier(_data.get((int)_position).get("img").toString(), "drawable", getPackageName()));
			} catch (Exception e) {
				 
			}
			try {
				boolean bv = false;
				if (_data.get((int)(_position)).containsValue("type")) {
					if (!"".equals(_data.get((int)_position).get("type").toString())) {
						String st = _data.get((int)(_position)).get("type").toString();
						for(String sc:st.split(" ")){
							if (type.equals(sc)) {
								bv = true;
							}
						}
						if (!bv) {
							((ViewGroup)linear2.getParent()).removeView(linear2);
						}
					}
				}
			} catch (Exception e) {
				 
			}
			
			return _view;
		}
	}
	
	public class EventsListAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public EventsListAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.events_csv, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView text = _view.findViewById(R.id.text);
			final ImageView edit = _view.findViewById(R.id.edit);
			final ImageView delete = _view.findViewById(R.id.delete);
			
			text.setText(_data.get((int)_position).get("name").toString());
			delete.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					FileUtil.deleteFile(_data.get((int)_position).get("bp").toString());
					FileUtil.deleteFile(_data.get((int)_position).get("cp").toString());
					if (!(_data.get((int)_position).get("name").toString().equals("Firebase: OnAuthenticationChanged") || (0 == _position))) {
						_data.remove((int)(_position));
						notifyDataSetChanged();
					}
				}
			});
			edit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getApplicationContext(), CodeActActivity.class);
					i.putExtra("path", EventsLM.get((int)_position).get("cp").toString());
					startActivity(i);
				}
			});
			if (_data.get((int)_position).get("name").toString().equals("Firebase: OnAuthenticationChanged") || (0 == _position)) {
				delete.setImageResource(R.drawable.ic_rotate_right_white);
			}
			
			return _view;
		}
	}
	
	public class FilesListAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public FilesListAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.events_csv, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView text = _view.findViewById(R.id.text);
			final ImageView edit = _view.findViewById(R.id.edit);
			final ImageView delete = _view.findViewById(R.id.delete);
			
			imageview1.setImageResource(R.drawable.ic_insert_drive_file_black);
			text.setText(Uri.parse(_data.get((int)_position).get("file").toString()).getLastPathSegment());
			delete.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					FileUtil.deleteFile(_data.get((int)_position).get("file").toString());
					_data.remove((int)(_position));
					_refreshFiles();
				}
			});
			edit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					final AlertDialog c = new AlertDialog.Builder(LayoutEditorActivity.this).create();
					LayoutInflater cLI = getLayoutInflater();
					View cCV = (View) cLI.inflate(R.layout.create_project, null);
					c.setView(cCV);
					final Button create = (Button)
					cCV.findViewById(R.id.create);
					final EditText name = (EditText)
					cCV.findViewById(R.id.name);
					final LinearLayout linear = (LinearLayout)
					cCV.findViewById(R.id.linear);
					c.requestWindowFeature(Window.FEATURE_NO_TITLE); 
					c.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
					c.show();
					name.setText(Uri.parse(_data.get((int)_position).get("file").toString()).getLastPathSegment());
					create.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF0067EE));
					linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF29354B));
					create.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View _view){
							if (name.getText().toString().equals("")) {
								SketchwareUtil.showMessage(getApplicationContext(), "Invalid input!! ");
							}
							else {
								{
									java.io.File dYx4Y = new java.io.File(_data.get((int)_position).get("file").toString());
									java.io.File e5Cyk = new java.io.File(_remove_last_from_path(_data.get((int)_position).get("file").toString()).concat("/").concat(name.getText().toString()));
									dYx4Y.renameTo(e5Cyk);
								}
								_refreshFiles();
								c.dismiss();
							}
						}
					});
					create.setText("edit");
				}
			});
			
			return _view;
		}
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