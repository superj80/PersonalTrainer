package jasonmcdonald.personaltrainer;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

import static jasonmcdonald.personaltrainer.R.string.date;

/**
 * Created by Administrator on 4/6/2017.
 */

public class Session {
    private UUID mId;
    private String mDate;
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
