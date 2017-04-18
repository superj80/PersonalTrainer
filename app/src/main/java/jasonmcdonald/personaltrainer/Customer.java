package jasonmcdonald.personaltrainer;


import java.util.UUID;

/**
 * Created by Administrator on 4/4/2017.
 */

public class Customer {
    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private String mPhone;
    private String mEmail;


    public Customer()
    {
        mId=UUID.randomUUID();
        mFirstName=" ";
        mLastName=" ";
        mPhone=" ";
        mEmail=" ";

    }

    public Customer(String last,String first,String phone,String email){
        mId=UUID.randomUUID();
        mFirstName=first;
        mLastName=last;
        mPhone=phone;
        mEmail=email;

    }

    public UUID getId() {return mId;}
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
    public void setPhone(String phone)
    {
        mPhone=phone;
    }
    public String getEmail()
    {
        return mEmail;
    }
    public void setEmail(String email)
    {
        mEmail=email;
    }


}
