package jlrf.itl.gsa.listviewexample.model;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by joseluisrf on 6/20/15.
 */
public class Country {

    private static final String NAME = "name";
    private static final String ALPHA2_CODE = "alpha2_code";
    private static final String ALPHA3_CODE = "alpha3_code";

    private String name;
    private String alpha2_code;
    private String alpha3_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2_code() {
        return alpha2_code;
    }

    public void setAlpha2_code(String alpha2_code) {
        this.alpha2_code = alpha2_code;
    }

    public String getAlpha3_code() {
        return alpha3_code;
    }

    public void setAlpha3_code(String alpha3_code) {
        this.alpha3_code = alpha3_code;
    }

    public static Country parse(JSONObject jsonObject) throws JSONException{
        Country c = new Country();
        c.setName( jsonObject.getString(NAME));
        c.setAlpha2_code( jsonObject.getString(ALPHA2_CODE) );
        c.setAlpha3_code( jsonObject.getString(ALPHA3_CODE) );
        return c;
    }
}
