package jlrf.itl.gsa.listviewexample.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import jlrf.itl.gsa.listviewexample.R;
import jlrf.itl.gsa.listviewexample.model.Country;

/**
 * Created by joseluisrf on 6/20/15.
 */
public class CountriesAdapter extends ArrayAdapter<Country>{


    public CountriesAdapter(Context context) {
        super(context, R.layout.countries_activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.country_item,parent,false);
        TextView tvCountryName = (TextView)rowView.findViewById(R.id.tvCountryName);
        TextView tvAlpha2 = (TextView)rowView.findViewById(R.id.tvAlpha2);
        TextView tvAlpha3 = (TextView)rowView.findViewById(R.id.tvAlpha3);
        Country c =  getItem(position);
        tvCountryName.setText(c.getName());
        tvAlpha2.setText(c.getAlpha2_code());
        tvAlpha3.setText(c.getAlpha3_code());
        return rowView;
    }
}
