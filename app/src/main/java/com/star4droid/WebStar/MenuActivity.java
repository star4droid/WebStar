package com.star4droid.WebStar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;
import android.view.View;
import java.text.DecimalFormat;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class MenuActivity extends AppCompatActivity {
	
	public final int REQ_CD_CAMERA = 101;
	
	private FloatingActionButton _fab;
	private HashMap<String, Object> map = new HashMap<>();
	private  TextView txtv;
	
	private ArrayList<String> path_files = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> customl = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private ListView listview1;
	private LinearLayout linear2;
	private LinearLayout linear15;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear16;
	private LinearLayout linear18;
	private ImageView imageview7;
	private ImageView imageview2;
	private LinearLayout linear10;
	private TextView textview2;
	private TextView textview7;
	private ImageView imageview3;
	private LinearLayout linear11;
	private TextView textview8;
	private TextView textview9;
	private ImageView imageview4;
	private LinearLayout linear12;
	private TextView textview10;
	private TextView textview11;
	private ImageView imageview5;
	private LinearLayout linear13;
	private TextView textview12;
	private TextView textview13;
	private ImageView imageview6;
	private LinearLayout linear14;
	private TextView textview14;
	private TextView textview15;
	private ImageView imageview8;
	private LinearLayout linear17;
	private TextView textview16;
	private TextView textview17;
	private ImageView imageview9;
	private LinearLayout linear19;
	private TextView textview18;
	private TextView textview19;
	private ImageView imageview1;
	private TextView textview1;
	
	private AlertDialog dialog;
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	private SharedPreferences sh;
	private Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_camera;
	private Intent urI = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.menu);
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
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		listview1 = findViewById(R.id.listview1);
		linear2 = findViewById(R.id.linear2);
		linear15 = findViewById(R.id.linear15);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear16 = findViewById(R.id.linear16);
		linear18 = findViewById(R.id.linear18);
		imageview7 = findViewById(R.id.imageview7);
		imageview2 = findViewById(R.id.imageview2);
		linear10 = findViewById(R.id.linear10);
		textview2 = findViewById(R.id.textview2);
		textview7 = findViewById(R.id.textview7);
		imageview3 = findViewById(R.id.imageview3);
		linear11 = findViewById(R.id.linear11);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		imageview4 = findViewById(R.id.imageview4);
		linear12 = findViewById(R.id.linear12);
		textview10 = findViewById(R.id.textview10);
		textview11 = findViewById(R.id.textview11);
		imageview5 = findViewById(R.id.imageview5);
		linear13 = findViewById(R.id.linear13);
		textview12 = findViewById(R.id.textview12);
		textview13 = findViewById(R.id.textview13);
		imageview6 = findViewById(R.id.imageview6);
		linear14 = findViewById(R.id.linear14);
		textview14 = findViewById(R.id.textview14);
		textview15 = findViewById(R.id.textview15);
		imageview8 = findViewById(R.id.imageview8);
		linear17 = findViewById(R.id.linear17);
		textview16 = findViewById(R.id.textview16);
		textview17 = findViewById(R.id.textview17);
		imageview9 = findViewById(R.id.imageview9);
		linear19 = findViewById(R.id.linear19);
		textview18 = findViewById(R.id.textview18);
		textview19 = findViewById(R.id.textview19);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		d = new AlertDialog.Builder(this);
		sh = getSharedPreferences("sh", Activity.MODE_PRIVATE);
		_file_camera = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_camera;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_camera = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_camera);
		} else {
			_uri_camera = Uri.fromFile(_file_camera);
		}
		camera.putExtra(MediaStore.EXTRA_OUTPUT, _uri_camera);
		camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_create_project();
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (linear3.getVisibility() == View.VISIBLE) {
					i.putExtra("pallets_path",FileUtil.getExternalStorageDir().concat("/.WebStar/blocks.json"));
					i.setClassName("com.star4droid.WebStarBlocks","com.star4droid.WebStarBlocks.ManagerActivity");
					startActivity(i);
				}
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setMessage("are sure?");
				d.setPositiveButton("install", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/blocks.json"), "");
						try{
							int count;
							java.io.InputStream input= MenuActivity.this.getAssets().open("blocks.json");
							java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getExternalStorageDir().concat("/.WebStar/blocks.json")+"");
							byte data[] = new byte[1024];
							while ((count = input.read(data))>0) {
								output.write(data, 0, count);
							}
							output.flush();
							output.close();
							input.close();
							SketchwareUtil.showMessage(getApplicationContext(), "done");
							}catch(Exception xe){
									d.setTitle("failed to import built-in blocks");
							d.setMessage("eror occurred while extracting the built-in blocks  .. ");
							d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							d.setCancelable(false);
							d.create().show();
							}
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
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog = new AlertDialog.Builder(MenuActivity.this,android.R.style.Theme_Material_Light_NoActionBar).create();
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
				add.setText("add property");
				customl.clear();
				if (!FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/properties.json")).equals("")) {
					customl = new Gson().fromJson(FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/properties.json")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				}
				final 
				starlistview ui = new starlistview(MenuActivity.this);
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
						text.setText((t8888.list).get((int)_position).get("name").toString());
						delete.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								customl.remove((int)(_position));
								ui.setListMap(customl);
								FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/properties.json"), new Gson().toJson(customl));
							}
						});
						edit.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								code.setText((t8888.list).get((int)_position).get("code").toString());
								tag.setText((t8888.list).get((int)_position).get("name").toString());
							}
						});
						t8888.cv = uCV;
					}
				}
				);
				linear.addView(ui);
				ui.setListMap(customl);
				add.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						map = new HashMap<>();
						_putKeysSplitBy("\n1", map, "name\n1Vname\n1code\n1Vcode".replace("Vname", tag.getText().toString()).replace("Vcode", code.getText().toString()));
						customl.add(map);
						ui.setListMap(customl);
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/properties.json"), new Gson().toJson(customl));
						code.setText("");
						tag.setText("");
					}
				});
				tag.setHint("property name");
				code.setHint("example: style.fontSize");
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog = new AlertDialog.Builder(MenuActivity.this,android.R.style.Theme_Material_Light_NoActionBar).create();
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
				add.setText("add Event");
				customl.clear();
				code.setSingleLine(false);
				final ArrayList<String> arr = new ArrayList<>();
				if (!FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/events.json")).equals("")) {
					ArrayList<String> stt = new ArrayList<>();
					stt = new Gson().fromJson(FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/events.json")), new TypeToken<ArrayList<String>>(){}.getType());
					for(String i:stt){
						map = new HashMap<>();
						arr.add(i);
						_putKeysSplitBy("\n1", map, i);
						customl.add(map);
					}
				}
				final 
				starlistview ui = new starlistview(MenuActivity.this);
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
						text.setText((t8888.list).get((int)_position).get("name").toString());
						delete.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								customl.remove((int)(_position));
								arr.remove((int)(_position));
								ui.setListMap(customl);
								FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/events.json"), new Gson().toJson(arr));
							}
						});
						edit.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								try {
									tag.setText((t8888.list).get((int)_position).get("name").toString());
								} catch (Exception e) {
									 
								}
								try {
									code.setText((t8888.list).get((int)_position).get("data").toString());
								} catch (Exception e) {
									 
								}
							}
						});
						t8888.cv = uCV;
					}
				}
				);
				linear.addView(ui);
				ui.setListMap(customl);
				add.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						map = new HashMap<>();
						_putKeysSplitBy("\n1", map, "name\n1OnClick\n1data\n1dataN".replace("OnClick", tag.getText().toString()).replace("dataN", code.getText().toString()));
						customl.add(map);
						arr.add("name\n1OnClick\n1data\n1dataN".replace("OnClick", tag.getText().toString()).replace("dataN", code.getText().toString()));
						ui.setListMap(customl);
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/events.json"), new Gson().toJson(arr));
						tag.setText("");
						code.setText("");
					}
				});
				tag.setHint("event name");
				code.setHint("example:  %element.addEventListener( 'click', ( event ) => {\n%script\n});");
			}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog = new AlertDialog.Builder(MenuActivity.this,android.R.style.Theme_Material_Light_NoActionBar).create();
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
				customl.clear();
				if (!FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/tags.json")).equals("")) {
					customl = new Gson().fromJson(FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/tags.json")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				}
				final 
				starlistview ui = new starlistview(MenuActivity.this);
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
						text.setText((t8888.list).get((int)_position).get("name").toString());
						delete.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
								customl.remove((int)(_position));
								FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/tags.json"), new Gson().toJson(customl));
								ui.setListMap(customl);
							}
						});
						edit.setVisibility(View.GONE);
						t8888.cv = uCV;
					}
				}
				);
				linear.addView(ui);
				ui.setListMap(customl);
				add.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						map = new HashMap<>();
						_putKeysSplitBy("\n1", map, "name\n1div\n1before\n1var div = document.createElement(\"div\");\n\n1after\n1.appendChild(div);\ndiv.id=\"btn\";\ndiv.style[\"min-height\"]=\"50.123456789px\";\nnew Sortable(document.getElementById(\"btn\"), {\n    group: 'shared',\n    animation: 150\n});\ndiv.style[\"min-width\"]=\"50.123456789px\";\ndiv.style[\"border\"]=\"1.45321764px solid black\";\ndiv.addEventListener( 'touchend', ( event ) => {\n	if(document.getElementById(\"widgets\")){\ndocument.getElementById(\"widgets\").value=event.target.id;\nWI.change(event.target.id);\n}\n});\n1html\n1<div id=\"btn\"></div>\n1type\n1layout\n1img\n1html \n1type \n1typeNN".replace("div", tag.getText().toString()).replace("typeNN", code.getText().toString()));
						customl.add(map);
						ui.setListMap(customl);
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/tags.json"), new Gson().toJson(customl));
						tag.setText("");
						code.setText("");
					}
				});
				code.setHint("custom parentNode, ex: div video select");
				tag.setHint("example <img> write ==> img");
			}
		});
		
		linear16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
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
				/**/
				/*
SketchwareUtil.showMessage(getApplicationContext(), "installed version is: ".concat(String.valueOf(p.getLongVersionCode())));
*/
			}
		});
		
		linear18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog dialog = new AlertDialog.Builder(MenuActivity.this).create();
				LayoutInflater dialogLI = getLayoutInflater();
				View dialogCV = (View) dialogLI.inflate(R.layout.support_csv, null);
				dialog.setView(dialogCV);
				final Button yt = (Button)
				dialogCV.findViewById(R.id.yt);
				final Button fb = (Button)
				dialogCV.findViewById(R.id.fb);
				final Button pa = (Button)
				dialogCV.findViewById(R.id.pa);
				final Button pt = (Button)
				dialogCV.findViewById(R.id.pt);
				dialog.show();
				yt.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						urI.setAction(Intent.ACTION_VIEW);
						urI.setData(Uri.parse("https://youtube.com/channel/UCgnoNpqgejMZbqDLtr8NNUw"));
						startActivity(urI);
					}
				});
				fb.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						urI.setAction(Intent.ACTION_VIEW);
						urI.setData(Uri.parse("https://www.facebook.com/groups/1350448918716864/?ref=share_group_link"));
						startActivity(urI);
					}
				});
				pa.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						d.setTitle("informations");
						d.setMessage("P1028381440");
						d.setPositiveButton("Copy", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "P1028381440"));
								SketchwareUtil.CustomToast(getApplicationContext(), "Copied To Clipboard", 0xFFFFFFFF, 16, 0xFF000000, 8, SketchwareUtil.BOTTOM);
							}
						});
						d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						d.create().show();
					}
				});
				pt.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View _view){
						urI.setAction(Intent.ACTION_VIEW);
						urI.setData(Uri.parse("https://www.patreon.com/Star4Droid"));
						startActivity(urI);
					}
				});
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear3.setVisibility(View.GONE);
				try {
					txtv.setText("manager");
				} catch (Exception e) {
					 
				}
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (_fab.getRotation()==0) {
					_showCustom(true);
				} else {
					_showCustom(false);
				};
			}
		});
	}
	
	private void initializeLogic() {
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)1, 0xFF000000, 0xFF0067EE));
		linear3.setVisibility(View.GONE);
		View cv = getLayoutInflater().inflate(R.layout.custom_fabs_view, null);
		
		linFab1 = (LinearLayout)cv.findViewById(R.id.lin1);
		linFab2 = (LinearLayout)cv.findViewById(R.id.lin2);
		linFab3 = (LinearLayout)cv.findViewById(R.id.lin3);
		
		textFab1 = (TextView)cv.findViewById(R.id.textview1);
		textFab2 = (TextView)cv.findViewById(R.id.textview2);
		textFab3 = (TextView)cv.findViewById(R.id.textview3);
		
		imgFab1 = (ImageView)cv.findViewById(R.id.imageview1);
		imgFab2 = (ImageView)cv.findViewById(R.id.imageview2);
		imgFab3 = (ImageView)cv.findViewById(R.id.imageview3);
		
		final LinearLayout l1 = (LinearLayout)cv.findViewById(R.id.linear1);
		
		_removeView(l1);
		
		((ViewGroup)_fab.getParent()).addView(l1);
		_setup(textFab1, "#2196F3");
		_setup(textFab2, "#00BCD4");
		_setup(textFab3, "#4CAF50");
		
		_setup(imgFab1, "#2196F3");
		_setup(imgFab2, "#00BCD4");
		_setup(imgFab3, "#4CAF50");
		textFab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imgFab1.performClick();
			}
		});
		
		imgFab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//community
				i = new Intent();
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://www.facebook.com/groups/1350448918716864/?ref=share_group_link"));
				startActivity(i);
			}
		});
		txtv = textFab2;
		textFab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imgFab2.performClick();
			}
		});
		
		imgFab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (linear3.getVisibility() == View.GONE) {
					linear3.setVisibility(View.VISIBLE);
					linear3.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
					textFab2.setText("Show Projects");
				}
				else {
					linear3.setVisibility(View.GONE);
					textFab2.setText("Manager");
				}
			}
		});
		textFab3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imgFab3.performClick();
			}
		});
		
		imgFab3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_create_project();
			}
		});
		linFab1.setTranslationY(getDip(50));
		linFab1.setAlpha(0);
		linFab2.setTranslationY(getDip(50));
		linFab2.setAlpha(0);
		linFab3.setTranslationY(getDip(50));
		linFab3.setAlpha(0);
		try {
			TypedValue typedValue = new TypedValue();
			getApplicationContext().getTheme().resolveAttribute(16843868, typedValue, true);
			_fab.setBackgroundResource(typedValue.resourceId);
			_fab.setClickable(true);
		} catch(Exception ze) {
			 
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_refresh();
		for (int i = 0; i < ((ViewGroup) linear3).getChildCount(); i++) {
			View v = ((ViewGroup)linear3).getChildAt(i);
			if (i > 0) {
				v.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, 0xFF000000, 0xFFE0F7FA));
				v.setElevation((float)5);
			}
		}
		if ("0.5".equals(FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/blocksV.txt")))) {
			try {
				if (sh.getString("ggg", "").equals("")) {
					sh.edit().putString("ggg", "done").commit();
					linear18.performClick(); 
				}
			} catch (Exception e) {
				 
			}
		}
		else {
			FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/blocksV.txt"), "0.5");
			linear16.performClick(); 
		}
	}
	
	@Override
	public void onBackPressed() {
		if (linear3.getVisibility() == View.GONE) {
			d.setTitle("exit");
			d.setMessage("are you sure?");
			d.setPositiveButton("exit", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					finishAffinity();
				}
			});
			d.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			d.create().show();
		}
		else {
			linear3.setVisibility(View.GONE);
			try {
				txtv.setText("manager");
			} catch (Exception e) {
				 
			}
		}
	}
	public void _refresh() {
		path_files.clear();
		listmap.clear();
		FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/.WebStar/projects/"), path_files);
		listview1.setVisibility(View.GONE);
		for(String i:path_files){
			if (FileUtil.isDirectory(i)) {
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("path", i);
					listmap.add(_item);
				}
				
			}
			if (listview1.getVisibility() == View.GONE) {
				listview1.setVisibility(View.VISIBLE);
			}
		}
		listview1.setAdapter(new Listview1Adapter(listmap));
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
	
	
	public void _putKeysSplitBy(final String _split, final HashMap<String, Object> _map, final String _str) {
		boolean b = false;
		String set="";
		for(String s:_str.split(_split)){
				if (b) {
						_map.put(set, s);
				}
				else {
						set=s; 
				}
				b = !b;
		}
	}
	
	
	public void _tapTarget(final View _view, final String _title, final String _subtitle, final String _color) {
		TapTargetView.showFor(MenuActivity.this,
		
		TapTarget.forView(_view,_title,_subtitle).outerCircleColorInt(Color.parseColor("#" + _color.replace("#", ""))).outerCircleAlpha(0.99f).targetCircleColor(android.R.color.white).titleTextSize(24).titleTextColor(android.R.color.white).descriptionTextSize(18).descriptionTextColor(android.R.color.white).textColor(android.R.color.white)
		.dimColor(android.R.color.black).drawShadow(true).cancelable(true).tintTarget(true).transparentTarget(true).targetRadius(60),new TapTargetView.Listener() {
			@Override public void onTargetClick(TapTargetView view) { super.onTargetClick(view);
				
			}
		});
		//TapTarget
		
	}static class UiUtil {
		    UiUtil() {
			    }
		    static int dp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_DIP, val, context.getResources().getDisplayMetrics());
			    }
		    static int sp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_SP, val, context.getResources().getDisplayMetrics());
			    }
		    static int themeIntAttr(Context context, String attr) {
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        if (theme == null) {
				            return -1;
				        }
			        final TypedValue value = new TypedValue();
			        final int id = context.getResources().getIdentifier(attr, "attr", context.getPackageName());
			
			        if (id == 0) {
				            // Not found
				            return -1;
				        }
			        theme.resolveAttribute(id, value, true);
			        return value.data;
			    }
		    static int setAlpha(int argb, float alpha) {
			        if (alpha > 1.0f) {
				            alpha = 1.0f;
				        } else if (alpha <= 0.0f) {
				            alpha = 0.0f;
				        }
			        return ((int) ((argb >>> 24) * alpha) << 24) | (argb & 0x00FFFFFF);
			    }
	}
			static class FloatValueAnimatorBuilder {
		
		    private final ValueAnimator animator;
		
		    private EndListener endListener;
		
		    interface UpdateListener {
			        void onUpdate(float lerpTime);
			    }
		    interface EndListener {
			        void onEnd();
			    }
		    protected FloatValueAnimatorBuilder() {
			        this(false);
			    }
		    FloatValueAnimatorBuilder(boolean reverse) {
			        if (reverse) {
				            this.animator = ValueAnimator.ofFloat(1.0f, 0.0f);
				        } else {
				            this.animator = ValueAnimator.ofFloat(0.0f, 1.0f);
				        }
			    }
		    public FloatValueAnimatorBuilder delayBy(long millis) {
			        animator.setStartDelay(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder duration(long millis) {
			        animator.setDuration(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder interpolator(TimeInterpolator lerper) {
			        animator.setInterpolator(lerper);
			        return this;
			    }
		    public FloatValueAnimatorBuilder repeat(int times) {
			        animator.setRepeatCount(times);
			        return this;
			    }
		    public FloatValueAnimatorBuilder onUpdate(final UpdateListener listener) {
			        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                listener.onUpdate((float) animation.getAnimatedValue());
					            }
				        });
			        return this;
			    }
		    public FloatValueAnimatorBuilder onEnd(final EndListener listener) {
			        this.endListener = listener;
			        return this;
			    }
		    public ValueAnimator build() {
			        if (endListener != null) {
				            animator.addListener(new AnimatorListenerAdapter() {
					                @Override
					                public void onAnimationEnd(Animator animation) {
						                    endListener.onEnd();
						                }
					            });
				        }
			        return animator;
			    }
	}
			static class ReflectUtil {
		    ReflectUtil() {
			    }
		    static Object getPrivateField(Object source, String fieldName)
		            throws NoSuchFieldException, IllegalAccessException {
			        final java.lang.reflect.Field objectField = source.getClass().getDeclaredField(fieldName);
			        objectField.setAccessible(true);
			        return objectField.get(source);
			    }
	}
			static class TapTarget extends Activity {
		    final CharSequence title;
		    final CharSequence description;
		    float outerCircleAlpha = 0.96f;
		    int targetRadius = 44;
		    Rect bounds;
		    android.graphics.drawable.Drawable icon;
		    Typeface titleTypeface;
		    Typeface descriptionTypeface;
		
		
		    private int outerCircleColorRes = -1;
		    private int targetCircleColorRes = -1;
		    private int dimColorRes = -1;
		    private int titleTextColorRes = -1;
		    private int descriptionTextColorRes = -1;
		
		    private Integer outerCircleColor = null;
		    private Integer targetCircleColor = null;
		    private Integer dimColor = null;
		    private Integer titleTextColor = null;
		    private Integer descriptionTextColor = null;
		
		    private int titleTextDimen = -1;
		    private int descriptionTextDimen = -1;
		    private int titleTextSize = 20;
		    private int descriptionTextSize = 18;
		    int id = -1;
		    boolean drawShadow = false;
		    boolean cancelable = true;
		    boolean tintTarget = true;
		    boolean transparentTarget = false;
		    float descriptionTextAlpha = 0.54f;
		
		    public static TapTarget forView(View view, CharSequence title) {
			        return forView(view, title, null);
			    }
		    public static TapTarget forView(View view, CharSequence title, CharSequence description) {
			        return new ViewTapTarget(view, title, description);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title) {
			        return forBounds(bounds, title, null);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title,  CharSequence description) {
			        return new TapTarget(bounds, title, description);
			    }
		    protected TapTarget(Rect bounds, CharSequence title,  CharSequence description) {
			        this(title, description);
			        if (bounds == null) {
				            throw new IllegalArgumentException("Cannot pass null bounds or title");
				        }
			        this.bounds = bounds;
			    }
		    protected TapTarget(CharSequence title,  CharSequence description) {
			        if (title == null) {
				            throw new IllegalArgumentException("Cannot pass null title");
				        }
			        this.title = title;
			        this.description = description;
			    }
		    public TapTarget transparentTarget(boolean transparent) {
			        this.transparentTarget = transparent;
			        return this;
			    }
		    public TapTarget outerCircleColor( int color) {
			        this.outerCircleColorRes = color;
			        return this;
			    }
		    public TapTarget outerCircleColorInt( int color) {
			        this.outerCircleColor = color;
			        return this;
			    }
		    public TapTarget outerCircleAlpha(float alpha) {
			        if (alpha < 0.0f || alpha > 1.0f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + alpha);
				        }
			        this.outerCircleAlpha = alpha;
			        return this;
			    }
		    public TapTarget targetCircleColor( int color) {
			        this.targetCircleColorRes = color;
			        return this;
			    }
		    public TapTarget targetCircleColorInt( int color) {
			        this.targetCircleColor = color;
			        return this;
			    }
		    public TapTarget textColor( int color) {
			        this.titleTextColorRes = color;
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget textColorInt( int color) {
			        this.titleTextColor = color;
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget titleTextColor( int color) {
			        this.titleTextColorRes = color;
			        return this;
			    }
		    public TapTarget titleTextColorInt( int color) {
			        this.titleTextColor = color;
			        return this;
			    }
		    public TapTarget descriptionTextColor( int color) {
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget descriptionTextColorInt( int color) {
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget textTypeface(Typeface typeface) {
			        if (typeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        titleTypeface = typeface;
			        descriptionTypeface = typeface;
			        return this;
			    }
		    public TapTarget titleTypeface(Typeface titleTypeface) {
			        if (titleTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.titleTypeface = titleTypeface;
			        return this;
			    }
		    public TapTarget descriptionTypeface(Typeface descriptionTypeface) {
			        if (descriptionTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.descriptionTypeface = descriptionTypeface;
			        return this;
			    }
		    public TapTarget titleTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.titleTextSize = sp;
			        return this;
			    }
		    public TapTarget descriptionTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.descriptionTextSize = sp;
			        return this;
			    }
		    public TapTarget titleTextDimen( int dimen) {
			        this.titleTextDimen = dimen;
			        return this;
			    }
		    public TapTarget descriptionTextAlpha(float descriptionTextAlpha) {
			        if (descriptionTextAlpha < 0 || descriptionTextAlpha > 1f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + descriptionTextAlpha);
				        }
			        this.descriptionTextAlpha = descriptionTextAlpha;
			        return this;
			    }
		    public TapTarget descriptionTextDimen( int dimen) {
			        this.descriptionTextDimen = dimen;
			        return this;
			    }
		    public TapTarget dimColor( int color) {
			        this.dimColorRes = color;
			        return this;
			    }
		    public TapTarget dimColorInt( int color) {
			        this.dimColor = color;
			        return this;
			    }
		    public TapTarget drawShadow(boolean draw) {
			        this.drawShadow = draw;
			        return this;
			    }
		    public TapTarget cancelable(boolean status) {
			        this.cancelable = status;
			        return this;
			    }
		    public TapTarget tintTarget(boolean tint) {
			        this.tintTarget = tint;
			        return this;
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon) {
			        return icon(icon, false);
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon, boolean hasSetBounds) {
			        if (icon == null) throw new IllegalArgumentException("Cannot use null drawable");
			        this.icon = icon;
			        if (!hasSetBounds) {
				            this.icon.setBounds(new Rect(0, 0, this.icon.getIntrinsicWidth(), this.icon.getIntrinsicHeight()));
				        }
			        return this;
			    }
		    public TapTarget id(int id) {
			        this.id = id;
			        return this;
			    }
		    public TapTarget targetRadius(int targetRadius) {
			        this.targetRadius = targetRadius;
			        return this;
			    }
		    public int id() {
			        return id;
			    }
		    public void onReady(Runnable runnable) {
			        runnable.run();
			    }
		    public Rect bounds() {
			        if (bounds == null) {
				            throw new IllegalStateException("Requesting bounds that are not set! Make sure your target is ready");
				        }
			        return bounds;
			    }
		    Integer outerCircleColorInt(Context context) {
			        return colorResOrInt(context, outerCircleColor, outerCircleColorRes);
			    }
		    Integer targetCircleColorInt(Context context) {
			        return colorResOrInt(context, targetCircleColor, targetCircleColorRes);
			    }
		    Integer dimColorInt(Context context) {
			        return colorResOrInt(context, dimColor, dimColorRes);
			    }
		    Integer titleTextColorInt(Context context) {
			        return colorResOrInt(context, titleTextColor, titleTextColorRes);
			    }
		
		    Integer descriptionTextColorInt(Context context) {
			        return colorResOrInt(context, descriptionTextColor, descriptionTextColorRes);
			    }
		    int titleTextSizePx(Context context) {
			        return dimenOrSize(context, titleTextSize, titleTextDimen);
			    }
		    int descriptionTextSizePx(Context context) {
			        return dimenOrSize(context, descriptionTextSize, descriptionTextDimen);
			    }
		
		    private Integer colorResOrInt(Context context, Integer value,  int resource) {
			        if (resource != -1) {
				            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					                return context.getColor(resource);
					            }
				        }
			        return value;
			    }
		    private int dimenOrSize(Context context, int size,  int dimen) {
			        if (dimen != -1) {
				            return context.getResources().getDimensionPixelSize(dimen);
				        }
			        return UiUtil.sp(context, size);
			    }
	}
			static class TapTargetView extends View {
		    private boolean isDismissed = false;
		    private boolean isDismissing = false;
		    private boolean isInteractable = true;
		
		    final int TARGET_PADDING;
		    final int TARGET_RADIUS;
		    final int TARGET_PULSE_RADIUS;
		    final int TEXT_PADDING;
		    final int TEXT_SPACING;
		    final int TEXT_MAX_WIDTH;
		    final int TEXT_POSITIONING_BIAS;
		    final int CIRCLE_PADDING;
		    final int GUTTER_DIM;
		    final int SHADOW_DIM;
		    final int SHADOW_JITTER_DIM;
		
		
		    final ViewGroup boundingParent;
		    final ViewManager parent;
		    final TapTarget target;
		    final Rect targetBounds;
		
		    final TextPaint titlePaint;
		    final TextPaint descriptionPaint;
		    final Paint outerCirclePaint;
		    final Paint outerCircleShadowPaint;
		    final Paint targetCirclePaint;
		    final Paint targetCirclePulsePaint;
		
		    CharSequence title;
		
		    StaticLayout titleLayout;
		
		    CharSequence description;
		
		    StaticLayout descriptionLayout;
		    boolean isDark;
		    boolean debug;
		    boolean shouldTintTarget;
		    boolean shouldDrawShadow;
		    boolean cancelable;
		    boolean visible;
		
		    // Debug related variables
		
		    SpannableStringBuilder debugStringBuilder;
		
		    DynamicLayout debugLayout;
		
		    TextPaint debugTextPaint;
		
		    Paint debugPaint;
		
		    // Drawing properties
		    Rect drawingBounds;
		    Rect textBounds;
		
		    Path outerCirclePath;
		    float outerCircleRadius;
		    int calculatedOuterCircleRadius;
		    int[] outerCircleCenter;
		    int outerCircleAlpha;
		
		    float targetCirclePulseRadius;
		    int targetCirclePulseAlpha;
		
		    float targetCircleRadius;
		    int targetCircleAlpha;
		
		    int textAlpha;
		    int dimColor;
		
		    float lastTouchX;
		    float lastTouchY;
		
		    int topBoundary;
		    int bottomBoundary;
		
		    Bitmap tintedTarget;
		
		    Listener listener;
		
		
		    ViewOutlineProvider outlineProvider;
		
		    public static TapTargetView showFor(Activity activity, TapTarget target) {
			        return showFor(activity, target, null);
			    }
		
		    public static TapTargetView showFor(Activity activity, TapTarget target, Listener listener) {
			        if (activity == null) throw new IllegalArgumentException("Activity is null");
			
			        final ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
			        final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
			                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			        final ViewGroup content = (ViewGroup) decor.findViewById(android.R.id.content);
			        final TapTargetView tapTargetView = new TapTargetView(activity, decor, content, target, listener);
			        decor.addView(tapTargetView, layoutParams);
			
			        return tapTargetView;
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target) {
			        return showFor(dialog, target, null);
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target, Listener listener) {
			        if (dialog == null) throw new IllegalArgumentException("Dialog is null");
			
			        final Context context = dialog.getContext();
			        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
			        params.format = PixelFormat.RGBA_8888;
			        params.flags = 0;
			        params.gravity = Gravity.START | Gravity.TOP;
			        params.x = 0;
			        params.y = 0;
			        params.width = WindowManager.LayoutParams.MATCH_PARENT;
			        params.height = WindowManager.LayoutParams.MATCH_PARENT;
			
			        final TapTargetView tapTargetView = new TapTargetView(context, windowManager, null, target, listener);
			        windowManager.addView(tapTargetView, params);
			
			        return tapTargetView;
			    }
		
		    public static class Listener {
			        /** Signals that the user has clicked inside of the target **/
			        public void onTargetClick(TapTargetView view) {
				            view.dismiss(true);
				        }
			
			        /** Signals that the user has long clicked inside of the target **/
			        public void onTargetLongClick(TapTargetView view) {
				            onTargetClick(view);
				        }
			
			        /** If cancelable, signals that the user has clicked outside of the outer circle **/
			        public void onTargetCancel(TapTargetView view) {
				            view.dismiss(false);
				        }
			
			        /** Signals that the user clicked on the outer circle portion of the tap target **/
			        public void onOuterCircleClick(TapTargetView view) {
				            // no-op as default
				        }
			
			        /**
         * Signals that the tap target has been dismissed
         * @param userInitiated Whether the user caused this action
         *
         *
         */
			        public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
				        }
			    }
		
		    final FloatValueAnimatorBuilder.UpdateListener expandContractUpdateListener = new FloatValueAnimatorBuilder.UpdateListener() {
			        @Override
			        public void onUpdate(float lerpTime) {
				            final float newOuterCircleRadius = calculatedOuterCircleRadius * lerpTime;
				            final boolean expanding = newOuterCircleRadius > outerCircleRadius;
				            if (!expanding) {
					                // When contracting we need to invalidate the old drawing bounds. Otherwise
					                // you will see artifacts as the circle gets smaller
					                calculateDrawingBounds();
					            }
				
				            final float targetAlpha = target.outerCircleAlpha * 255;
				            outerCircleRadius = newOuterCircleRadius;
				            outerCircleAlpha = (int) Math.min(targetAlpha, (lerpTime * 1.5f * targetAlpha));
				            outerCirclePath.reset();
				            outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				
				            targetCircleAlpha = (int) Math.min(255.0f, (lerpTime * 1.5f * 255.0f));
				
				            if (expanding) {
					                targetCircleRadius = TARGET_RADIUS * Math.min(1.0f, lerpTime * 1.5f);
					            } else {
					                targetCircleRadius = TARGET_RADIUS * lerpTime;
					                targetCirclePulseRadius *= lerpTime;
					            }
				
				            textAlpha = (int) (delayedLerp(lerpTime, 0.7f) * 255);
				
				            if (expanding) {
					                calculateDrawingBounds();
					            }
				
				            invalidateViewAndOutline(drawingBounds);
				        }
			    };
		
		    final ValueAnimator expandAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .delayBy(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    pulseAnimation.start();
				                    isInteractable = true;
				                }
			            })
		            .build();
		
		    final ValueAnimator pulseAnimation = new FloatValueAnimatorBuilder()
		            .duration(1000)
		            .repeat(ValueAnimator.INFINITE)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float pulseLerp = delayedLerp(lerpTime, 0.5f);
				                    targetCirclePulseRadius = (1.0f + pulseLerp) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - pulseLerp) * 255);
				                    targetCircleRadius = TARGET_RADIUS + halfwayLerp(lerpTime) * TARGET_PULSE_RADIUS;
				
				                    if (outerCircleRadius != calculatedOuterCircleRadius) {
					                        outerCircleRadius = calculatedOuterCircleRadius;
					                    }
				
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .build();
		
		    final ValueAnimator dismissAnimation = new FloatValueAnimatorBuilder(true)
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private final ValueAnimator dismissConfirmAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float spedUpLerp = Math.min(1.0f, lerpTime * 2.0f);
				                    outerCircleRadius = calculatedOuterCircleRadius * (1.0f + (spedUpLerp * 0.2f));
				                    outerCircleAlpha = (int) ((1.0f - spedUpLerp) * target.outerCircleAlpha * 255.0f);
				                    outerCirclePath.reset();
				                    outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				                    targetCircleRadius = (1.0f - lerpTime) * TARGET_RADIUS;
				                    targetCircleAlpha = (int) ((1.0f - lerpTime) * 255.0f);
				                    targetCirclePulseRadius = (1.0f + lerpTime) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - lerpTime) * targetCirclePulseAlpha);
				                    textAlpha = (int) ((1.0f - spedUpLerp) * 255.0f);
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private ValueAnimator[] animators = new ValueAnimator[]
		            {expandAnimation, pulseAnimation, dismissConfirmAnimation, dismissAnimation};
		
		    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
		    public TapTargetView(final Context context,
		                         final ViewManager parent,
		                          final ViewGroup boundingParent,
		                         final TapTarget target,
		                          final Listener userListener) {
			        super(context);
			        if (target == null) throw new IllegalArgumentException("Target cannot be null");
			
			        this.target = target;
			        this.parent = parent;
			        this.boundingParent = boundingParent;
			        this.listener = userListener != null ? userListener : new Listener();
			        this.title = target.title;
			        this.description = target.description;
			
			        TARGET_PADDING = UiUtil.dp(context, 20);
			        CIRCLE_PADDING = UiUtil.dp(context, 40);
			        TARGET_RADIUS = UiUtil.dp(context, target.targetRadius);
			        TEXT_PADDING = UiUtil.dp(context, 40);
			        TEXT_SPACING = UiUtil.dp(context, 8);
			        TEXT_MAX_WIDTH = UiUtil.dp(context, 360);
			        TEXT_POSITIONING_BIAS = UiUtil.dp(context, 20);
			        GUTTER_DIM = UiUtil.dp(context, 88);
			        SHADOW_DIM = UiUtil.dp(context, 8);
			        SHADOW_JITTER_DIM = UiUtil.dp(context, 1);
			        TARGET_PULSE_RADIUS = (int) (0.1f * TARGET_RADIUS);
			
			        outerCirclePath = new Path();
			        targetBounds = new Rect();
			        drawingBounds = new Rect();
			
			        titlePaint = new TextPaint();
			        titlePaint.setTextSize(target.titleTextSizePx(context));
			        titlePaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
			        titlePaint.setAntiAlias(true);
			
			        descriptionPaint = new TextPaint();
			        descriptionPaint.setTextSize(target.descriptionTextSizePx(context));
			        descriptionPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
			        descriptionPaint.setAntiAlias(true);
			        descriptionPaint.setAlpha((int) (0.54f * 255.0f));
			
			        outerCirclePaint = new Paint();
			        outerCirclePaint.setAntiAlias(true);
			        outerCirclePaint.setAlpha((int) (target.outerCircleAlpha * 255.0f));
			
			        outerCircleShadowPaint = new Paint();
			        outerCircleShadowPaint.setAntiAlias(true);
			        outerCircleShadowPaint.setAlpha(50);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        outerCircleShadowPaint.setStrokeWidth(SHADOW_JITTER_DIM);
			        outerCircleShadowPaint.setColor(Color.BLACK);
			
			        targetCirclePaint = new Paint();
			        targetCirclePaint.setAntiAlias(true);
			
			        targetCirclePulsePaint = new Paint();
			        targetCirclePulsePaint.setAntiAlias(true);
			
			        applyTargetOptions(context);
			
			        globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                if (isDismissing) {
						                    return;
						                }
					                updateTextLayouts();
					                target.onReady(new Runnable() {
						                    @Override
						                    public void run() {
							                        final int[] offset = new int[2];
							
							                        targetBounds.set(target.bounds());
							
							                        getLocationOnScreen(offset);
							                        targetBounds.offset(-offset[0], -offset[1]);
							
							                        if (boundingParent != null) {
								                            final WindowManager windowManager
								                                    = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
								                            final DisplayMetrics displayMetrics = new DisplayMetrics();
								                            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
								
								                            final Rect rect = new Rect();
								                            boundingParent.getWindowVisibleDisplayFrame(rect);
								
								                            // We bound the boundaries to be within the screen's coordinates to
								                            // handle the case where the layout bounds do not match
								                            // (like when FLAG_LAYOUT_NO_LIMITS is specified)
								                            topBoundary = Math.max(0, rect.top);
								                            bottomBoundary = Math.min(rect.bottom, displayMetrics.heightPixels);
								                        }
							
							                        drawTintedTarget();
							                        requestFocus();
							                        calculateDimensions();
							
							                        startExpandAnimation();
							                    }
						                });
					            }
				        };
			
			        getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
			
			        setFocusableInTouchMode(true);
			        setClickable(true);
			        setOnClickListener(new OnClickListener() {
				            @Override
				            public void onClick(View v) {
					                if (listener == null || outerCircleCenter == null || !isInteractable) return;
					
					                final boolean clickedInTarget =
					                        distance(targetBounds.centerX(), targetBounds.centerY(), (int) lastTouchX, (int) lastTouchY) <= targetCircleRadius;
					                final double distanceToOuterCircleCenter = distance(outerCircleCenter[0], outerCircleCenter[1],
					                        (int) lastTouchX, (int) lastTouchY);
					                final boolean clickedInsideOfOuterCircle = distanceToOuterCircleCenter <= outerCircleRadius;
					
					                if (clickedInTarget) {
						                    isInteractable = false;
						                    listener.onTargetClick(TapTargetView.this);
						                } else if (clickedInsideOfOuterCircle) {
						                    listener.onOuterCircleClick(TapTargetView.this);
						                } else if (cancelable) {
						                    isInteractable = false;
						                    listener.onTargetCancel(TapTargetView.this);
						                }
					            }
				        });
			
			        setOnLongClickListener(new OnLongClickListener() {
				            @Override
				            public boolean onLongClick(View v) {
					                if (listener == null) return false;
					
					                if (targetBounds.contains((int) lastTouchX, (int) lastTouchY)) {
						                    listener.onTargetLongClick(TapTargetView.this);
						                    return true;
						                }
					
					                return false;
					            }
				        });
			    }
		
		    private void startExpandAnimation() {
			        if (!visible) {
				            isInteractable = false;
				            expandAnimation.start();
				            visible = true;
				        }
			    }
		
		    protected void applyTargetOptions(Context context) {
			        shouldTintTarget = target.tintTarget;
			        shouldDrawShadow = target.drawShadow;
			        cancelable = target.cancelable;
			
			        // We can't clip out portions of a view outline, so if the user specified a transparent
			        // target, we need to fallback to drawing a jittered shadow approximation
			        if (shouldDrawShadow && Build.VERSION.SDK_INT >= 21 && !target.transparentTarget) {
				            outlineProvider = new ViewOutlineProvider() {
					                @Override
					                public void getOutline(View view, Outline outline) {
						                    if (outerCircleCenter == null) return;
						                    outline.setOval(
						                            (int) (outerCircleCenter[0] - outerCircleRadius), (int) (outerCircleCenter[1] - outerCircleRadius),
						                            (int) (outerCircleCenter[0] + outerCircleRadius), (int) (outerCircleCenter[1] + outerCircleRadius));
						                    outline.setAlpha(outerCircleAlpha / 255.0f);
						                    if (Build.VERSION.SDK_INT >= 22) {
							                        outline.offset(0, SHADOW_DIM);
							                    }
						                }
					            };
				
				            setOutlineProvider(outlineProvider);
				            setElevation(SHADOW_DIM);
				        }
			
			        if (shouldDrawShadow && outlineProvider == null && Build.VERSION.SDK_INT < 18) {
				            setLayerType(LAYER_TYPE_SOFTWARE, null);
				        } else {
				            setLayerType(LAYER_TYPE_HARDWARE, null);
				        }
			
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        isDark = UiUtil.themeIntAttr(context, "isLightTheme") == 0;
			
			        final Integer outerCircleColor = target.outerCircleColorInt(context);
			        if (outerCircleColor != null) {
				            outerCirclePaint.setColor(outerCircleColor);
				        } else if (theme != null) {
				            outerCirclePaint.setColor(UiUtil.themeIntAttr(context, "colorPrimary"));
				        } else {
				            outerCirclePaint.setColor(Color.WHITE);
				        }
			
			        final Integer targetCircleColor = target.targetCircleColorInt(context);
			        if (targetCircleColor != null) {
				            targetCirclePaint.setColor(targetCircleColor);
				        } else {
				            targetCirclePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        if (target.transparentTarget) {
				            targetCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
				        }
			
			        targetCirclePulsePaint.setColor(targetCirclePaint.getColor());
			
			        final Integer targetDimColor = target.dimColorInt(context);
			        if (targetDimColor != null) {
				            dimColor = UiUtil.setAlpha(targetDimColor, 0.3f);
				        } else {
				            dimColor = -1;
				        }
			
			        final Integer titleTextColor = target.titleTextColorInt(context);
			        if (titleTextColor != null) {
				            titlePaint.setColor(titleTextColor);
				        } else {
				            titlePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        final Integer descriptionTextColor = target.descriptionTextColorInt(context);
			        if (descriptionTextColor != null) {
				            descriptionPaint.setColor(descriptionTextColor);
				        } else {
				            descriptionPaint.setColor(titlePaint.getColor());
				        }
			
			        if (target.titleTypeface != null) {
				            titlePaint.setTypeface(target.titleTypeface);
				        }
			
			        if (target.descriptionTypeface != null) {
				            descriptionPaint.setTypeface(target.descriptionTypeface);
				        }
			    }
		
		    @Override
		    protected void onDetachedFromWindow() {
			        super.onDetachedFromWindow();
			        onDismiss(false);
			    }
		
		    void onDismiss(boolean userInitiated) {
			        if (isDismissed) return;
			
			        isDismissing = false;
			        isDismissed = true;
			
			        for (final ValueAnimator animator : animators) {
				            animator.cancel();
				            animator.removeAllUpdateListeners();
				        }
			        ViewUtil.removeOnGlobalLayoutListener(getViewTreeObserver(), globalLayoutListener);
			        visible = false;
			
			        if (listener != null) {
				            listener.onTargetDismissed(this, userInitiated);
				        }
			    }
		
		    @Override
		    protected void onDraw(Canvas c) {
			        if (isDismissed || outerCircleCenter == null) return;
			
			        if (topBoundary > 0 && bottomBoundary > 0) {
				            c.clipRect(0, topBoundary, getWidth(), bottomBoundary);
				        }
			
			        if (dimColor != -1) {
				            c.drawColor(dimColor);
				        }
			
			        int saveCount;
			        outerCirclePaint.setAlpha(outerCircleAlpha);
			        if (shouldDrawShadow && outlineProvider == null) {
				            saveCount = c.save();
				            {
					                c.clipPath(outerCirclePath, Region.Op.DIFFERENCE);
					                drawJitteredShadow(c);
					            }
				            c.restoreToCount(saveCount);
				        }
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, outerCirclePaint);
			
			        targetCirclePaint.setAlpha(targetCircleAlpha);
			        if (targetCirclePulseAlpha > 0) {
				            targetCirclePulsePaint.setAlpha(targetCirclePulseAlpha);
				            c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
				                    targetCirclePulseRadius, targetCirclePulsePaint);
				        }
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
			                targetCircleRadius, targetCirclePaint);
			
			        saveCount = c.save();
			        {
				            c.translate(textBounds.left, textBounds.top);
				            titlePaint.setAlpha(textAlpha);
				            if (titleLayout != null) {
					                titleLayout.draw(c);
					            }
				
				            if (descriptionLayout != null && titleLayout != null) {
					                c.translate(0, titleLayout.getHeight() + TEXT_SPACING);
					                descriptionPaint.setAlpha((int) (target.descriptionTextAlpha * textAlpha));
					                descriptionLayout.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        saveCount = c.save();
			        {
				            if (tintedTarget != null) {
					                c.translate(targetBounds.centerX() - tintedTarget.getWidth() / 2,
					                        targetBounds.centerY() - tintedTarget.getHeight() / 2);
					                c.drawBitmap(tintedTarget, 0, 0, targetCirclePaint);
					            } else if (target.icon != null) {
					                c.translate(targetBounds.centerX() - target.icon.getBounds().width() / 2,
					                        targetBounds.centerY() - target.icon.getBounds().height() / 2);
					                target.icon.setAlpha(targetCirclePaint.getAlpha());
					                target.icon.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        if (debug) {
				            drawDebugInformation(c);
				        }
			    }
		
		    @Override
		    public boolean onTouchEvent(MotionEvent e) {
			        lastTouchX = e.getX();
			        lastTouchY = e.getY();
			        return super.onTouchEvent(e);
			    }
		
		    @Override
		    public boolean onKeyDown(int keyCode, KeyEvent event) {
			        if (isVisible() && cancelable && keyCode == KeyEvent.KEYCODE_BACK) {
				            event.startTracking();
				            return true;
				        }
			
			        return false;
			    }
		
		    @Override
		    public boolean onKeyUp(int keyCode, KeyEvent event) {
			        if (isVisible() && isInteractable && cancelable
			                && keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && !event.isCanceled()) {
				            isInteractable = false;
				
				            if (listener != null) {
					                listener.onTargetCancel(this);
					            } else {
					                new Listener().onTargetCancel(this);
					            }
				
				            return true;
				        }
			
			        return false;
			    }
		
		    /**
     * Dismiss this view
     * @param tappedTarget If the user tapped the target or not
     *                     (results in different dismiss animations)
     */
		    public void dismiss(boolean tappedTarget) {
			        isDismissing = true;
			        pulseAnimation.cancel();
			        expandAnimation.cancel();
			        if (tappedTarget) {
				            dismissConfirmAnimation.start();
				        } else {
				            dismissAnimation.start();
				        }
			    }
		
		    /** Specify whether to draw a wireframe around the view, useful for debugging **/
		    public void setDrawDebug(boolean status) {
			        if (debug != status) {
				            debug = status;
				            postInvalidate();
				        }
			    }
		
		    /** Returns whether this view is visible or not **/
		    public boolean isVisible() {
			        return !isDismissed && visible;
			    }
		
		    void drawJitteredShadow(Canvas c) {
			        final float baseAlpha = 0.20f * outerCircleAlpha;
			        outerCircleShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			        outerCircleShadowPaint.setAlpha((int) baseAlpha);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM, outerCircleRadius, outerCircleShadowPaint);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        final int numJitters = 7;
			        for (int i = numJitters - 1; i > 0; --i) {
				            outerCircleShadowPaint.setAlpha((int) ((i / (float) numJitters) * baseAlpha));
				            c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM ,
				                    outerCircleRadius + (numJitters - i) * SHADOW_JITTER_DIM , outerCircleShadowPaint);
				        }
			    }
		
		    void drawDebugInformation(Canvas c) {
			        if (debugPaint == null) {
				            debugPaint = new Paint();
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugPaint.setStyle(Paint.Style.STROKE);
				            debugPaint.setStrokeWidth(UiUtil.dp(getContext(), 1));
				        }
			
			        if (debugTextPaint == null) {
				            debugTextPaint = new TextPaint();
				            debugTextPaint.setColor(0xFFFF0000);
				            debugTextPaint.setTextSize(UiUtil.sp(getContext(), 16));
				        }
			
			        // Draw wireframe
			        debugPaint.setStyle(Paint.Style.STROKE);
			        c.drawRect(textBounds, debugPaint);
			        c.drawRect(targetBounds, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], 10, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], calculatedOuterCircleRadius - CIRCLE_PADDING, debugPaint);
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(), TARGET_RADIUS + TARGET_PADDING, debugPaint);
			
			        // Draw positions and dimensions
			        debugPaint.setStyle(Paint.Style.FILL);
			        final String debugText =
			                "Text bounds: " + textBounds.toShortString() + "n" +
			                        "Target bounds: " + targetBounds.toShortString() + "n" +
			                        "Center: " + outerCircleCenter[0] + " " + outerCircleCenter[1] + "n" +
			                        "View size: " + getWidth() + " " + getHeight() + "n" +
			                        "Target bounds: " + targetBounds.toShortString();
			
			        if (debugStringBuilder == null) {
				            debugStringBuilder = new SpannableStringBuilder(debugText);
				        } else {
				            debugStringBuilder.clear();
				            debugStringBuilder.append(debugText);
				        }
			
			        if (debugLayout == null) {
				            debugLayout = new DynamicLayout(debugText, debugTextPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        }
			
			        final int saveCount = c.save();
			        {
				            debugPaint.setARGB(220, 0, 0, 0);
				            c.translate(0.0f, topBoundary);
				            c.drawRect(0.0f, 0.0f, debugLayout.getWidth(), debugLayout.getHeight(), debugPaint);
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugLayout.draw(c);
				        }
			        c.restoreToCount(saveCount);
			    }
		
		    void drawTintedTarget() {
			        final android.graphics.drawable.Drawable icon = target.icon;
			        if (!shouldTintTarget || icon == null) {
				            tintedTarget = null;
				            return;
				        }
			
			        if (tintedTarget != null) return;
			
			        tintedTarget = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(),
			                Bitmap.Config.ARGB_8888);
			        final Canvas canvas = new Canvas(tintedTarget);
			        icon.setColorFilter(new PorterDuffColorFilter(
			                outerCirclePaint.getColor(), PorterDuff.Mode.SRC_ATOP));
			        icon.draw(canvas);
			        icon.setColorFilter(null);
			    }
		
		    void updateTextLayouts() {
			        final int textWidth = Math.min(getWidth(), TEXT_MAX_WIDTH) - TEXT_PADDING * 2;
			        if (textWidth <= 0) {
				            return;
				        }
			
			        titleLayout = new StaticLayout(title, titlePaint, textWidth,
			                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
			
			        if (description != null) {
				            descriptionLayout = new StaticLayout(description, descriptionPaint, textWidth,
				                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        } else {
				            descriptionLayout = null;
				        }
			    }
		
		    float halfwayLerp(float lerp) {
			        if (lerp < 0.5f) {
				            return lerp / 0.5f;
				        }
			
			        return (1.0f - lerp) / 0.5f;
			    }
		
		    float delayedLerp(float lerp, float threshold) {
			        if (lerp < threshold) {
				            return 0.0f;
				        }
			
			        return (lerp - threshold) / (1.0f - threshold);
			    }
		
		    void calculateDimensions() {
			        textBounds = getTextBounds();
			        outerCircleCenter = getOuterCircleCenterPoint();
			        calculatedOuterCircleRadius = getOuterCircleRadius(outerCircleCenter[0], outerCircleCenter[1], textBounds, targetBounds);
			    }
		
		    void calculateDrawingBounds() {
			        if (outerCircleCenter == null) {
				            // Called dismiss before we got a chance to display the tap target
				            // So we have no center -> cant determine the drawing bounds
				            return;
				        }
			        drawingBounds.left = (int) Math.max(0, outerCircleCenter[0] - outerCircleRadius);
			        drawingBounds.top = (int) Math.min(0, outerCircleCenter[1] - outerCircleRadius);
			        drawingBounds.right = (int) Math.min(getWidth(),
			                outerCircleCenter[0] + outerCircleRadius + CIRCLE_PADDING);
			        drawingBounds.bottom = (int) Math.min(getHeight(),
			                outerCircleCenter[1] + outerCircleRadius + CIRCLE_PADDING);
			    }
		
		    int getOuterCircleRadius(int centerX, int centerY, Rect textBounds, Rect targetBounds) {
			        final int targetCenterX = targetBounds.centerX();
			        final int targetCenterY = targetBounds.centerY();
			        final int expandedRadius = (int) (1.1f * TARGET_RADIUS);
			        final Rect expandedBounds = new Rect(targetCenterX, targetCenterY, targetCenterX, targetCenterY);
			        expandedBounds.inset(-expandedRadius, -expandedRadius);
			
			        final int textRadius = maxDistanceToPoints(centerX, centerY, textBounds);
			        final int targetRadius = maxDistanceToPoints(centerX, centerY, expandedBounds);
			        return Math.max(textRadius, targetRadius) + CIRCLE_PADDING;
			    }
		
		    Rect getTextBounds() {
			        final int totalTextHeight = getTotalTextHeight();
			        final int totalTextWidth = getTotalTextWidth();
			
			        final int possibleTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight;
			        final int top;
			        if (possibleTop > topBoundary) {
				            top = possibleTop;
				        } else {
				            top = targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING;
				        }
			
			        final int relativeCenterDistance = (getWidth() / 2) - targetBounds.centerX();
			        final int bias = relativeCenterDistance < 0 ? -TEXT_POSITIONING_BIAS : TEXT_POSITIONING_BIAS;
			        final int left = Math.max(TEXT_PADDING, targetBounds.centerX() - bias - totalTextWidth);
			        final int right = Math.min(getWidth() - TEXT_PADDING, left + totalTextWidth);
			        return new Rect(left, top, right, top + totalTextHeight);
			    }
		
		    int[] getOuterCircleCenterPoint() {
			        if (inGutter(targetBounds.centerY())) {
				            return new int[]{targetBounds.centerX(), targetBounds.centerY()};
				        }
			
			        final int targetRadius = Math.max(targetBounds.width(), targetBounds.height()) / 2 + TARGET_PADDING;
			        final int totalTextHeight = getTotalTextHeight();
			
			        final boolean onTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight > 0;
			
			        final int left = Math.min(textBounds.left, targetBounds.left - targetRadius);
			        final int right = Math.max(textBounds.right, targetBounds.right + targetRadius);
			        final int titleHeight = titleLayout == null ? 0 : titleLayout.getHeight();
			        final int centerY = onTop ?
			                targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight + titleHeight
			                :
			                targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING + titleHeight;
			
			        return new int[] { (left + right) / 2, centerY };
			    }
		
		    int getTotalTextHeight() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getHeight() + TEXT_SPACING;
				        }
			
			        return titleLayout.getHeight() + descriptionLayout.getHeight() + TEXT_SPACING;
			    }
		
		    int getTotalTextWidth() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getWidth();
				        }
			
			        return Math.max(titleLayout.getWidth(), descriptionLayout.getWidth());
			    }
		    boolean inGutter(int y) {
			        if (bottomBoundary > 0) {
				            return y < GUTTER_DIM || y > bottomBoundary - GUTTER_DIM;
				        } else {
				            return y < GUTTER_DIM || y > getHeight() - GUTTER_DIM;
				        }
			    }
		    int maxDistanceToPoints(int x1, int y1, Rect bounds) {
			        final double tl = distance(x1, y1, bounds.left, bounds.top);
			        final double tr = distance(x1, y1, bounds.right, bounds.top);
			        final double bl = distance(x1, y1, bounds.left, bounds.bottom);
			        final double br = distance(x1, y1, bounds.right, bounds.bottom);
			        return (int) Math.max(tl, Math.max(tr, Math.max(bl, br)));
			    }
		    double distance(int x1, int y1, int x2, int y2) {
			        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
			    }
		    void invalidateViewAndOutline(Rect bounds) {
			        invalidate(bounds);
			        if (outlineProvider != null && Build.VERSION.SDK_INT >= 21) {
				            invalidateOutline();
				        }
			    }
	}
			static class ViewUtil {
		
		    ViewUtil() {}
		
		    private static boolean isLaidOut(View view) {
			        return true;
			    }
		    static void onLaidOut(final View view, final Runnable runnable) {
			        if (isLaidOut(view)) {
				            runnable.run();
				            return;
				        }
			        final ViewTreeObserver observer = view.getViewTreeObserver();
			        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                final ViewTreeObserver trueObserver;
					                if (observer.isAlive()) {
						                    trueObserver = observer;
						                } else {
						                    trueObserver = view.getViewTreeObserver();
						                }
					                removeOnGlobalLayoutListener(trueObserver, this);
					                runnable.run();
					            }
				        });
			    }
		    @SuppressWarnings("deprecation")
		    static void removeOnGlobalLayoutListener(ViewTreeObserver observer,
		                                             ViewTreeObserver.OnGlobalLayoutListener listener) {
			        if (Build.VERSION.SDK_INT >= 16) {
				            observer.removeOnGlobalLayoutListener(listener);
				        } else {
				            observer.removeGlobalOnLayoutListener(listener);
				        }
			    }
		    static void removeView(ViewManager parent, View child) {
			        if (parent == null || child == null) {
				            return;
				        }
			        try {
				            parent.removeView(child);
				        } catch (Exception ignored) {
				        }
			    }
	}
			static class ViewTapTarget extends TapTarget {
		    final View view;
		
		    ViewTapTarget(View view, CharSequence title,  CharSequence description) {
			        super(title, description);
			        if (view == null) {
				            throw new IllegalArgumentException("Given null view to target");
				        }
			        this.view = view;
			    }
		
		    @Override
		    public void onReady(final Runnable runnable) {
			        ViewUtil.onLaidOut(view, new Runnable() {
				            @Override
				            public void run() {
					                // Cache bounds
					                final int[] location = new int[2];
					                view.getLocationOnScreen(location);
					                bounds = new Rect(location[0], location[1],
					                        location[0] + view.getWidth(), location[1] + view.getHeight());
					
					                if (icon == null && view.getWidth() > 0 && view.getHeight() > 0) {
						                    final Bitmap viewBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
						                    final Canvas canvas = new Canvas(viewBitmap);
						                    view.draw(canvas);
						                    icon = new android.graphics.drawable.BitmapDrawable(view.getContext().getResources(), viewBitmap);
						                    icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
						                }
					
					                runnable.run();
					            }
				        });
			    }
	}
			static class TapTargetSequence {
		    private final Activity activity;
		    private final Dialog dialog;
		    private final Queue<TapTarget> targets;
		    private boolean active;
		    private TapTargetView currentView;
		    Listener listener;
		    boolean considerOuterCircleCanceled;
		    boolean continueOnCancel;
		    public interface Listener {
			        void onSequenceFinish();
			        void onSequenceStep(TapTarget lastTarget, boolean targetClicked);
			        void onSequenceCanceled(TapTarget lastTarget);
			    }
		    public TapTargetSequence(Activity activity) {
			        if (activity == null) throw new IllegalArgumentException("Activity is null");
			        this.activity = activity;
			        this.dialog = null;
			        this.targets = new LinkedList<>();
			    }
		    public TapTargetSequence(Dialog dialog) {
			        if (dialog == null) throw new IllegalArgumentException("Given null Dialog");
			        this.dialog = dialog;
			        this.activity = null;
			        this.targets = new LinkedList<>();
			    }
		    public TapTargetSequence targets(List<TapTarget> targets) {
			        this.targets.addAll(targets);
			        return this;
			    }
		    public TapTargetSequence targets(TapTarget... targets) {
			        Collections.addAll(this.targets, targets);
			        return this;
			    }
		    public TapTargetSequence target(TapTarget target) {
			        this.targets.add(target);
			        return this;
			    }
		    public TapTargetSequence continueOnCancel(boolean status) {
			        this.continueOnCancel = status;
			        return this;
			    }
		    public TapTargetSequence considerOuterCircleCanceled(boolean status) {
			        this.considerOuterCircleCanceled = status;
			        return this;
			    }
		    public TapTargetSequence listener(Listener listener) {
			        this.listener = listener;
			        return this;
			    }
		    public void start() {
			        if (targets.isEmpty() || active) {
				            return;
				        }
			        active = true;
			        showNext();
			    }
		    public void startWith(int targetId) {
			        if (active) {
				            return;
				        }
			        while (targets.peek() != null && targets.peek().id() != targetId) {
				            targets.poll();
				        }
			        TapTarget peekedTarget = targets.peek();
			        if (peekedTarget == null || peekedTarget.id() != targetId) {
				            throw new IllegalStateException("Given target " + targetId + " not in sequence");
				        }
			        start();
			    }
		    public void startAt(int index) {
			        if (active) {
				            return;
				        }
			        if (index < 0 || index >= targets.size()) {
				            throw new IllegalArgumentException("Given invalid index " + index);
				        }
			        final int expectedSize = targets.size() - index;
			        while (targets.peek() != null && targets.size() != expectedSize) {
				            targets.poll();
				        }
			        if (targets.size() != expectedSize) {
				            throw new IllegalStateException("Given index " + index + " not in sequence");
				        }
			        start();
			    }
		    public boolean cancel() {
			        if (targets.isEmpty() || !active) {
				            return false;
				        }
			        if (currentView == null || !currentView.cancelable) {
				            return false;
				        }
			        currentView.dismiss(false);
			        active = false;
			        targets.clear();
			        if (listener != null) {
				            listener.onSequenceCanceled(currentView.target);
				        }
			        return true;
			    }
		    void showNext() {
			        try {
				            TapTarget tapTarget = targets.remove();
				            if (activity != null) {
					                currentView = TapTargetView.showFor(activity, tapTarget, tapTargetListener);
					            } else {
					                currentView = TapTargetView.showFor(dialog, tapTarget, tapTargetListener);
					            }
				        } catch (NoSuchElementException e) {
				            // No more targets
				            if (listener != null) {
					                listener.onSequenceFinish();
					            }
				        }
			    }
		    private final TapTargetView.Listener tapTargetListener = new TapTargetView.Listener() {
			        @Override
			        public void onTargetClick(TapTargetView view) {
				            super.onTargetClick(view);
				            if (listener != null) {
					                listener.onSequenceStep(view.target, true);
					            }
				            showNext();
				        }
			        @Override
			        public void onOuterCircleClick(TapTargetView view) {
				            if (considerOuterCircleCanceled) {
					                onTargetCancel(view);
					            }
				        }
			        @Override
			        public void onTargetCancel(TapTargetView view) {
				            super.onTargetCancel(view);
				            if (continueOnCancel) {
					                if (listener != null) {
						                    listener.onSequenceStep(view.target, false);
						                }
					                showNext();
					            } else {
					                if (listener != null) {
						                    listener.onSequenceCanceled(view.target);
						                }
					            }
				        }
			    };
		
		//Made by XenonDry
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
	
	
	public void _showCustom(final boolean _show) {
		_fab.clearAnimation();
		linFab1.clearAnimation();
		linFab2.clearAnimation();
		linFab3.clearAnimation();
		if (_show) {
			_fab.animate().setDuration(100).rotation(45);
			linFab1.setVisibility(View.VISIBLE);
			linFab2.setVisibility(View.VISIBLE);
			linFab3.setVisibility(View.VISIBLE);
			linFab1.animate().setDuration(100).alpha(1f).translationY(0).withEndAction(new Runnable() {
				@Override public void run() {
					
					linFab2.animate().setDuration(100).alpha(1f).translationY(0).withEndAction(new Runnable() {
						@Override public void run() {
							
							linFab3.animate().setDuration(100).alpha(1f).translationY(0);
							
						}
					});
					
				}
			});
		}
		else {
			_fab.animate().setDuration(100).rotation(0);
			linFab3.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
				@Override public void run() {
					
					linFab2.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
						@Override public void run() {
							
							linFab1.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
								@Override public void run() {
									
									linFab1.setVisibility(View.GONE);
									linFab2.setVisibility(View.GONE);
									linFab3.setVisibility(View.GONE);
									
								}
							});
							
						}
					});
					
				}
			});
		}
	}
	
	
	public void _removeView(final View _view) {
		if (_view.getParent() != null) ((ViewGroup)_view.getParent()).removeView(_view);
	}
	
	
	public void _init() {
	}
	
	private LinearLayout linFab1, linFab2, linFab3;
	
	private TextView textFab1, textFab2, textFab3;
	
	private ImageView imgFab1, imgFab2, imgFab3;
	
	{
	}
	
	
	public void _setRipple(final View _a, final String _b, final double _c, final String _d) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_b));
		gd.setCornerRadius((float)_c);
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_d)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
		_a.setClickable(true);
		_a.setClipToOutline(true);
		_a.setBackground(ripdrb);
	}
	
	
	public void _setup(final View _a, final String _b) {
		_setRipple(_a, _b, SketchwareUtil.getDip(getApplicationContext(), (int)(18)), "#FFFFFF");
		_a.setElevation(4f);
	}
	
	
	public void _create_project() {
		final AlertDialog dialog = new AlertDialog.Builder(MenuActivity.this).create();
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
		create.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/.WebStar/projects/".concat(name.getText().toString())))) {
					SketchwareUtil.showMessage(getApplicationContext(), "there is file with the same name");
				}
				else {
					if (name.getText().toString().equals("")) {
						SketchwareUtil.showMessage(getApplicationContext(), "write something!! ");
					}
					else {
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/projects/".concat(name.getText().toString().concat("/index.html"))), "<html><head><title>main</title></head><body></body></html>");
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/projects/".concat(name.getText().toString().concat("/index.html.editor.html"))), "<html><head><title>main</title></head><body onselectstart=\"return false\">\n<script id=\"scr\">\n//script here19373783\n</script>\n<select id=\"widgets\" onchange=\"WI.change(document.getElementById('widgets').value);\" style=\"width:100%;\">\n<option value=\"main\">main</option>\n</select><div id=\"main\" style=\"height: 100%; min-height:550px;\">\n<script src=\"drag.js\"></script>\n</div></body></html>");
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/projects/".concat(name.getText().toString().concat("/drag.js"))), "");
						try{
							int count;
							java.io.InputStream input= MenuActivity.this.getAssets().open("drag.js");
							java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getExternalStorageDir().concat("/.WebStar/projects/".concat(name.getText().toString().concat("/drag.js")))+"");
							byte data[] = new byte[1024];
							while ((count = input.read(data))>0) {
								output.write(data, 0, count);
							}
							output.flush();
							output.close();
							input.close();
							 
							}catch(Exception xe){
									SketchwareUtil.showMessage(getApplicationContext(), "Eror happen while extracting drag js file");
							}
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.WebStar/projects/".concat(name.getText().toString().concat("/long-press-event.js"))), "");
						_refresh();
						dialog.dismiss();
					}
				}
			}
		});
		image.setImageResource(R.drawable.icon);
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
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.project_csv, null);
			}
			
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout item = _view.findViewById(R.id.item);
			final LinearLayout btm = _view.findViewById(R.id.btm);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView name = _view.findViewById(R.id.name);
			final ImageView drop = _view.findViewById(R.id.drop);
			final Button delete = _view.findViewById(R.id.delete);
			final Button backup = _view.findViewById(R.id.backup);
			
			name.setText(Uri.parse(_data.get((int)_position).get("path").toString()).getLastPathSegment());
			btm.setVisibility(View.GONE);
			drop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (btm.getVisibility() == View.VISIBLE) {
						btm.setVisibility(View.GONE);
					}
					else {
						btm.setVisibility(View.VISIBLE);
					}
				}
			});
			linear5.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getApplicationContext(), LayoutEditorActivity.class);
					i.putExtra("path", _data.get((int)_position).get("path").toString());
					i.putExtra("tags", FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/tags.json")));
					i.putExtra("properties", FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/properties.json")));
					i.putExtra("events", FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.WebStar/save/events.json")));
					startActivity(i);
				}
			});
			delete.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					FileUtil.deleteFile(_data.get((int)_position).get("path").toString());
					_data.remove((int)(_position));
					if (0 == _data.size()) {
						listview1.setVisibility(View.GONE);
					}
					_refresh();
				}
			});
			delete.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)2, 0xFF000000, 0xFF29354B));
			backup.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)2, 0xFF000000, 0xFF29354B));
			backup.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					SketchwareUtil.showMessage(getApplicationContext(), "not ready! ");
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