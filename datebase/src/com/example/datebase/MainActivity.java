package com.example.datebase;

import android.app.Activity;
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
		db.execSQL("insert into info(name, phone) values(?, ?);", new Object[] { "James", "13881384138" });
		db.close();
	}

	// delete
	public void click2(View v) {
		SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
		db.execSQL("delete from info where name=?", new Object[] { "James" });
		db.close();
	}

	// update
	public void click3(View v) {
		SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();
		db.execSQL("update info  set phone=? where name=?", new Object[] { "13888888888", "James" });
		db.close();

	}

	// find
	public void click4(View v) {
		SQLiteDatabase db = mMyOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from info", null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				String name = cursor.getString(1);
				String phone = cursor.getString(2);
				String buf = String.format("name: %s phone: %s", name, phone);
				Toast.makeText(this, buf, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
