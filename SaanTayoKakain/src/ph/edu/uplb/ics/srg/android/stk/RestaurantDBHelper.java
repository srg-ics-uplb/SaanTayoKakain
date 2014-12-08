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
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDBHelper extends SQLiteOpenHelper {
	
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Restaurant.db";

    public RestaurantDBHelper (Context context) {
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
