package jasonmcdonald.personaltrainer;


import android.graphics.Bitmap;

import java.util.UUID;

/**
 * Created by Administrator on 4/4/2017.
 */

public class Customer {
    private UUID mId;
    private String mFirstName;
    private String mLastName;
    //TODO: PHONE NUMBER FORMATTING
    private String mPhone;
    //TODO: EMAIL FORMATTING
    private String mEmail;
    private Bitmap mImageBitmap;



    public Customer()
    {
        mId=UUID.randomUUID();
        mFirstName=" ";
        mLastName=" ";
        mPhone=" ";
        mEmail=" ";
        mImageBitmap=null;


    }

    public Customer(String last,String first,String phone,String email){
        mId=UUID.randomUUID();
        mFirstName=first;
        mLastName=last;
        mPhone=phone;
        mEmail=email;
        mImageBitmap=null;


    }

    public UUID getId() {return mId;}
    public void setId(String id) {mId = UUID.fromString(id);}
    public String getFirstName()
    {
        return mFirstName;
    }
    public void setFirstname(String firstName)
    {
        mFirstName=firstName;
    }
    public String getLastName()
    {
        return mLastName;
    }
    public void setLastname(String lastName)
    {
        mLastName=lastName;
    }
    public String getPhone()
    {
        return mPhone;
    }
    public void setPhone(String phone) {mPhone=phone;}
    public String getEmail()
    {
        return mEmail;
    }
    public void setEmail(String email) {mEmail=email;}

    //CREATES NAME FOR CUSTOMER PHOTO FILE FROM UUID
    public String getPictureFilename(UUID id){
        return "IMG_"+id.toString()+".jpg";
    }






}
