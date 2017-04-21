package jasonmcdonald.personaltrainer;


import java.util.UUID;



/**
 * Created by Administrator on 4/6/2017.
 */

public class Session {
    private UUID mId;
    //TODO: CHANGE TO DATE
    private String mDate;
    //TODO: CHANGE TO TIME
    private String mTime;
    private String mCompleted;



    public Session()
    {
        mId=UUID.randomUUID();
        mDate="  /  /  ";
        mTime="";
        mCompleted="SCHEDULED";

    }

    public UUID getId() {return mId;}
    public String getDate()
    {
        return mDate;
    }
    public void setDate(String date)
    {
        mDate=date;
    }
    //TODO: ADD TIME TO SESSIONS TABLE AND SESSION_ITEM.XML
    public String getTime()
    {
        return mTime;
    }
    public void setTime(String time)
    {
        mTime=time;
    }
    public String getCompleted()
    {
        return mCompleted;
    }
    public void setCompleted(String completed)
    {
        mCompleted=completed;
    }

}
