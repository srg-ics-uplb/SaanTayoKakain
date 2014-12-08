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

import android.provider.BaseColumns;

public final class RestaurantDatabaseContract {
	public RestaurantDatabaseContract() {
	}

	/* Inner class that defines the table contents */
	public static abstract class RestaurantEntry implements BaseColumns {
		public static final String TABLE_NAME = "restaurant";
		public static final String COLUMN_NAME_RESTAURANT_ID = "restaurant_id";
		public static final String COLUMN_NAME_RESTAURANT_NAME = "name";
		public static final String COLUMN_NAME_RESTAURANT_TAGS = "tags";
		public static final String COLUMN_NAME_RESTAURANT_VISITED = "visited";

		public static final String TEXT_TYPE = " TEXT";
		public static final String COMMA_SEP = ",";
		public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
				+ RestaurantEntry.TABLE_NAME + " (" + RestaurantEntry._ID
				+ " INTEGER PRIMARY KEY,"
				+ RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME + TEXT_TYPE
				+ COMMA_SEP + RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS
				+ TEXT_TYPE + COMMA_SEP
				+ RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED + TEXT_TYPE
				+ " )";

		public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
				+ RestaurantEntry.TABLE_NAME;
	}
}
