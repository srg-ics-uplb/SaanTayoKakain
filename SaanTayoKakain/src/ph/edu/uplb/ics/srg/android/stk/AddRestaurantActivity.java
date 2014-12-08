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
    public void onCreate(Bundle savedInstanceState)
    {   
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_restaurant);
    }   

    public void addRestaurant(View view) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
    	EditText restoNameText = (EditText)findViewById(R.id.restoName);
    	EditText restoTagsText = (EditText)findViewById(R.id.restoTags);
    	
    	
    	RestaurantDBHelper dbHelper = new RestaurantDBHelper(getApplicationContext());
   	 
   	 
    	// Gets the data repository in write mode
    	 SQLiteDatabase db = dbHelper.getWritableDatabase();

    	 // Create a new map of values, where column names are the keys
    	 ContentValues values = new ContentValues();
    	 values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_NAME,restoNameText.getText().toString());
    	 values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_TAGS,restoTagsText.getText().toString());
    	 values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_VISITED,"no");

    	 // Insert the new row, returning the primary key value of the new row
    	 long newRowId;
    	 newRowId = db.insert(
    	          RestaurantEntry.TABLE_NAME,
    	          null,
    	          values);
    	
    	finish();
    	/*
    	builder.setMessage(restoNameText.getText().toString()+ " added")
    	       .setTitle("Saan tayo kakain?");
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	*/
    }
}
