/*******************************************************************************
 *    Copyright 2014 University of the Philippines Los Banos
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Contributors:
 *    			Joseph Anthony C. Hermocilla,  jchermocilla@up.edu.ph
 *******************************************************************************/
package ph.edu.uplb.ics.srg.android.stk;

import ph.edu.uplb.ics.srg.android.stk.RestaurantDatabaseContract.RestaurantEntry;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddRestaurantActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_restaurant);
	}

	public void addRestaurant(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		EditText restoNameText = (EditText) findViewById(R.id.restoName);
		EditText restoTagsText = (EditText) findViewById(R.id.restoTags);

		String restoName = restoNameText.getText().toString();

		if (restoName.trim().length() == 0) {
			finish();
			return;
		}

		RestaurantDBHelper dbHelper = new RestaurantDBHelper(
				getApplicationContext());

		// Gets the data repository in write mode
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, restoNameText
				.getText().toString());
		values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, restoTagsText
				.getText().toString());
		values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED, "no");

		// Insert the new row, returning the primary key value of the new row
		db.insert(RestaurantEntry.TABLE_NAME, null, values);

		db.close();
		finish();
	}
}
