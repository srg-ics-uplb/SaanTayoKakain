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
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class ViewRestaurantsActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_restaurants);

		RestaurantDBHelper dbHelper = new RestaurantDBHelper(
				getApplicationContext());

		// Gets the data repository in write mode
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { RestaurantEntry._ID,
				RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME,
				RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS,
				RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED, };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME + " ASC";

		Cursor c = db.query(RestaurantEntry.TABLE_NAME, // The table to query
				projection, // The columns to return
				null, // The columns for the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				sortOrder // The sort order
				);

		EditText restoNameText = (EditText) findViewById(R.id.restoList);
		String restoList = "";
		String restoName;
		String visited;

		while (c.moveToNext()) {
			restoName = c
					.getString(c
							.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME));
			visited = c
					.getString(c
							.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED));
			restoList += restoName + "(" + visited + ")" + ",";
		}
		restoNameText.setText(restoList);
		db.close();
	}
}
