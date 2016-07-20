package com.example.datebase;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MyOpenHelper mMyOpenHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMyOpenHelper = new MyOpenHelper(getApplicationContext());
		// mSqLiteDatabase = mMyOpenHelper.getWritableDatabase();

	}

	// add
	public void click1(View v) {
		SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", "王五");
		values.put("phone", "120");
		long insert = db.insert("info", null, values);
		db.close();
		if (insert > 0) {
			Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

		}
	}

	// delete
	public void click2(View v) {
		SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
		int delete = db.delete("info", "name=?", new String[] { "王五" });
		Toast.makeText(this, "删除了" + delete + "行", Toast.LENGTH_SHORT).show();
		db.close();
	}

	// update
	public void click3(View v) {
		SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("phone", "189");
		int update = db.update("info", values, "name=?", new String[] { "王五" });
		Toast.makeText(this, "更新了" + update + "行", Toast.LENGTH_SHORT).show();
		db.close();

	}

	// find
	public void click4(View v) {
		SQLiteDatabase db = mMyOpenHelper.getReadableDatabase();
		Cursor cursor = db.query("info", new String[] { "phone" }, "name=?", new String[] { "王五" }, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				String phone = cursor.getString(0);
				Toast.makeText(this, "phone is " + phone, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
