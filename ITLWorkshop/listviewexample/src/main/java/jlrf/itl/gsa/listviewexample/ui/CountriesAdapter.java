package jlrf.itl.gsa.listviewexample.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import jlrf.itl.gsa.listviewexample.model.Country;

/**
 * Created by joseluisrf on 6/20/15.
 */
public class CountriesAdapter extends ArrayAdapter<Country>{

    public CountriesAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
