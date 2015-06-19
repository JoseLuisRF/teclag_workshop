package jlrf.itl.gsa.itlworkshop.model;

/**
 * Created by joseluisrf on 5/23/15.
 */
public class MyContact {

    public static final  String NAME = "contact_name";
    public static  final String BRIEF_MESSAGE = "brief_message";
    public static final String PHONE_NUMBER= "phone_number";
    public static  final String  THUMBNAIL_PHOTO = "thumbnail_photo";
    public static  final String  PHOTO = "photo";
    public static  final  String TABLE_NAME = "mycontacts";


    private String name;
    private String brief_message;
    private String phone_number;
    private String photo;
    private String thumbnail_photo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief_message() {
        return brief_message;
    }

    public void setBrief_message(String brief_message) {
        this.brief_message = brief_message;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getThumbnail_photo() {
        return thumbnail_photo;
    }

    public void setThumbnail_photo(String thumbnail_photo) {
        this.thumbnail_photo = thumbnail_photo;
    }
}
