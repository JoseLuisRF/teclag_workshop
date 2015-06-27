package com.apps.fourplex.tabletop.obj;

/**
 * Created by jose.ramos.fernandez on 17/03/2015.
 */
public class Response {
    public String msg;
    public boolean error;
    public Object data;

    public static Response newResponse(String m, boolean e, Object d){
        Response r = new Response();
        r.msg = m;
        r.error = e;
        r.data = d;
        return r;
    }
}
