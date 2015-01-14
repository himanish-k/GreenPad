package com.himanishkaushal.greenpad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CursorTreeAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableEntryAdapter extends CursorTreeAdapter {
	
	private LayoutInflater inflater;
	private EntrySource source;

	public ExpandableEntryAdapter(Cursor cursor, Context context) {
		super(cursor, context, true);
		source = new EntrySource(context);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	protected View newGroupView(Context context, Cursor cursor,
			boolean isExpanded, ViewGroup parent) {
		return inflater.inflate(R.layout.list_group_item, parent, false);
	}

	@Override
	protected void bindGroupView(View view, Context context, Cursor cursor,
			boolean isExpanded) {
		
		TextView date, payee, payment;
		
		int id = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
		int yearValue = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(1)));
        int monthValue = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(2)));
        int dayValue = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(3)));
		
		date = (TextView) view.findViewById(R.id.group_item_date);
		date.setText(AppAssistant.getDateForListDisplay(yearValue, monthValue, dayValue));
		
		
	}
	
	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		
		int yearValue = groupCursor.getInt(groupCursor.getColumnIndex(groupCursor.getColumnName(1)));
        int monthValue = groupCursor.getInt(groupCursor.getColumnIndex(groupCursor.getColumnName(2)));
        int dayValue = groupCursor.getInt(groupCursor.getColumnIndex(groupCursor.getColumnName(3)));
        
        source.open();
        Cursor c = source.fetchEntriesForDate(yearValue, monthValue, dayValue);
        source.close();
        return c;
	}

	@Override
	protected View newChildView(Context context, Cursor cursor,
			boolean isLastChild, ViewGroup parent) {
		return inflater.inflate(R.layout.list_subgroup_item, parent, false);
	}

	@Override
	protected void bindChildView(View view, Context context, Cursor cursor,
			boolean isLastChild) {
		
		int id = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
		
        TextView payee = (TextView) view.findViewById(R.id.group_expanded_list_item_payee);  
        payee.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
        
        TextView payment = (TextView) view.findViewById(R.id.group_expanded_list_item_payment);
        double paymentValue = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(5)));
        payment.setText(new String("$" + paymentValue));

        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.group_expanded_button_delete);
        //deleteButton.setOnClickListener(new DeleteButtonListener(context, id));
		
	}
	
	private class DeleteButtonListener implements OnClickListener {
		
		EntrySource source;
		int id;
		Context context;
		
		public DeleteButtonListener(Context context, int id) {
			this.context = context;
			source = new EntrySource(context);
			this.id = id;
		}
		@Override
		public void onClick(View v) {
			
			showDialog();
			
			// ((MainActivity) context).refreshList();
		}
		
		void showDialog() {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
			 
	        alertDialog.setTitle("Confirm Delete");
	        alertDialog.setMessage("Are you sure you want delete this entry?");
	        alertDialog.setIcon(R.drawable.ic_action_remove);
	 
	        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int which) {
	            	
	            	source.open();
	    			source.deleteEntry(id);
	    			source.close();
	    			
	    			// ((MainActivity) context).refreshList();
	 
		            Toast.makeText(context, "The entry is deleted.", Toast.LENGTH_SHORT).show();
	            }
	        });
	 
	        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            
	            	dialog.cancel();
	            }
	        });
	 
	        alertDialog.show();
	    }

	}

}
