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
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {   
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView t2 = (TextView) findViewById(R.id.site);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    }   

    public void getRecommendation(View view){
    	RestaurantDBHelper dbHelper = new RestaurantDBHelper(getApplicationContext());
   	 
   	 //Gets the data repository in write mode
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    db = dbHelper.getReadableDatabase();
	    
	    EditText tagFilter = (EditText)findViewById(R.id.tagFilter);
	    String filter = tagFilter.getText().toString();
	    
    	Cursor c = db.query(RestaurantEntry.TABLE_NAME,
    			new String[] { RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME,
    				RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS },
    				"("+ RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED+"='no'"+ ") AND (" + RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS+" LIKE '%"+ filter  + "%' ) ORDER BY RANDOM() LIMIT 1" , null, null, null, null);
    	    	// 
    	
    	if (c.getCount()==0){
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setMessage("Nakainan mo na ata lahat o walang resto sa listahan na may ganyan!Reset mo status o mag add ka ng resto.")
        	       .setTitle("Saan tayo kakain?");
        	AlertDialog dialog = builder.create();
        	dialog.show();
        	return;
    	}
    		
    	
    	c.moveToFirst();
    	String suggestion=c.getString(c.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME));
    	String tags= c.getString(c.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS));
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Eh di sa " +suggestion +"!"+"\n"+"Tags: "+tags)
    	       .setTitle("Saan tayo kakain?");
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	
    	db = dbHelper.getWritableDatabase();
   	    ContentValues values = new ContentValues();
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED, "yes");
   	    db.update(RestaurantEntry.TABLE_NAME, values,RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME+"='"+suggestion+"'", null);
    	
    }
    
    public void addRestaurant(View view){
    	    	  	
    	Intent intent = new Intent(this, AddRestaurantActivity.class);
        startActivity(intent);
    	
    }
    
    public void viewAllRestaurants(View view){
    	Intent intent = new Intent(this, ViewRestaurantsActivity.class);
        startActivity(intent);    	
    	/*
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("View all restaurants.")
    	       .setTitle("Saan tayo kakain?");
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	*/
    }
    
    public void resetStatus(View view)
    {
    	RestaurantDBHelper dbHelper = new RestaurantDBHelper(getApplicationContext());
   	    SQLiteDatabase db = dbHelper.getWritableDatabase();
   	    ContentValues values = new ContentValues();
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED, "no");
   	    db.update(RestaurantEntry.TABLE_NAME, values, null, null);
    	
    }
    
    public void clearSourceList(View view){
    	RestaurantDBHelper dbHelper = new RestaurantDBHelper(getApplicationContext());
   	 	 
    	// Gets the data repository in write mode
    	 SQLiteDatabase db = dbHelper.getWritableDatabase();
    	db.delete("restaurant", null, null);
    }
    
    public void loadLBRestos(View view){
    	RestaurantDBHelper dbHelper = new RestaurantDBHelper(getApplicationContext());
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
     	db.execSQL(RestaurantEntry.SQL_DELETE_ENTRIES);
     	db.execSQL(RestaurantEntry.SQL_CREATE_ENTRIES);
     	
     	ContentValues values = new ContentValues();
   	    
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "McDo");
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
   	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
   	    
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Chowking");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "KFC");
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
   	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
   	    
   	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Jollibee (Centro)");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Jollibee (Junction)");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Jollibee (Olivarez)");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Greenwich");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Pizza Hut");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Bonitos");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local,pasta");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Faustinas");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local,pasta");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Mio Cusina");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local,pasta");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Danielas");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local,lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Bugong");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local,chicken");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Cels");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local,lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Kens");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local,lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Aling Glo");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Ellens Crossing");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Cadapans");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, chicken, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "SEARCA");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "SU");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Coop");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Melville");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, silog, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Eatsumo");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, japanese");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "BigBelly");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "TessAndYlloy");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Papus");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, siomai");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Sizzlers");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Parduch");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Mang Inasal");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "fastfood");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Tita Cora");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Sulyaw");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, lutong bahay");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Petrinos");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, silog");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "OddBalls");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, silog");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "Ludys");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, chicken");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
	    
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME, "BlackAndBrew");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS, "local, pasta");
	    values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");
	    db.insert(RestaurantEntry.TABLE_NAME,null,values);
    }
}