/*******************************************************************************
 * Copyright (c) 2014 University of the Philippines Los Banos
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at 
 * https://www.apache.org/licenses/LICENSE-2.0.html
 *
 * Contributors:
 *     Joseph Anthony C. Hermocilla (jchermocilla@up.edu.ph)
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
	 public void onCreate(Bundle savedInstanceState)
	 {   
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.view_restaurants);
	     
	     RestaurantDBHelper dbHelper = new RestaurantDBHelper(getApplicationContext());
    	 
    	 // Gets the data repository in write mode
      SQLiteDatabase db = dbHelper.getReadableDatabase();

	     // Define a projection that specifies which columns from the database
	     // you will actually use after this query.
	     String[] projection = {
	    		RestaurantEntry._ID,
	    	    RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME,
	    	    RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS,
	    	    RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,
	    	    };
	  	
	     // How you want the results sorted in the resulting Cursor
	     String sortOrder = RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME + " ASC";

	     Cursor c = db.query(
	    	    RestaurantEntry.TABLE_NAME,  				// The table to query
	    	    projection,                               	// The columns to return
	    	    null,                                		// The columns for the WHERE clause
	    	    null,                            			// The values for the WHERE clause
	    	    null,                                     	// don't group the rows
	    	    null,                                     	// don't filter by row groups
	    	    sortOrder                                 	// The sort order
	    	    );      
	    		     
	     EditText restoNameText = (EditText)findViewById(R.id.restoList);
	     String restoList = "";
	     String restoName;
	     String visited;
	        
	     while (c.moveToNext()){
	    	 restoName = c.getString(c.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME));
	    	 visited = c.getString(c.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED));
	    	 restoList+=restoName+"("+visited+")"+",";
	     }
	     restoNameText.setText(restoList);
	 }   
}
