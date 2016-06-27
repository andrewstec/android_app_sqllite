package com.example.a00584083.db_exercise_03_11_2016;

/**
 * Created by a00584083 on 3/11/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ContactCursorAdapter extends CursorAdapter {

    @SuppressWarnings("deprecation")
    public ContactCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // When the view will be created for first time,
        // we need to tell the adapters, how each item will look.
        // Uses an UI Fragment to inflate the View.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.contact_cell, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        /* Here we are setting our data by taking it from the cursor and
         * putting it in textViews defined in the fragment single_row_item.xml
         */
        TextView name = (TextView) view.findViewById(R.id.cell_name);
        name.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        TextView email = (TextView) view.findViewById(R.id.cell_email);
        email.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        TextView delete_id = (TextView) view.findViewById(R.id.cell_delete_button);
        delete_id.setTag(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
        TextView update_id = (TextView) view.findViewById(R.id.cell_update_button);
        update_id.setTag(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));

    }
}
