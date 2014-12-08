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
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDBHelper extends SQLiteOpenHelper {

	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "Restaurant.db";

	public RestaurantDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(RestaurantEntry.SQL_CREATE_ENTRIES);

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(RestaurantEntry.SQL_DELETE_ENTRIES);
		onCreate(db);
	}
}
