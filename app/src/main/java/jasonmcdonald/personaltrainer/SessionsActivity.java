package jasonmcdonald.personaltrainer;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;


import java.util.ArrayList;


import java.util.List;

public class SessionsActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener  {
    private List<Session> mSessionList= new ArrayList<>();




    private class SessionHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Session mSession;
        private TextView mDateTextView;
        private TextView mCompTextView;

        private SessionHolder(View itemView)
        {
            super(itemView);

            mDateTextView=(TextView) itemView.findViewById(R.id.date);
            mCompTextView=(TextView) itemView.findViewById(R.id.completed);


            itemView.setOnClickListener(this);
        }
        private void bindSession(Session session)
        {
            mSession=session;
            mDateTextView.setText(mSession.getDate());
            mCompTextView.setText(mSession.getCompleted());
            if(mCompTextView.getText()=="SCHEDULED"){
                mCompTextView.setTextColor(Color.BLUE);
            }


        }
        @Override
        public void onClick(View v) {

            if(mCompTextView.getText()=="SCHEDULED"){
                String autoDisplayName= getIntent().getStringExtra("Name");
                Intent intent = new Intent(SessionsActivity.this, CompleteActivity.class );
                intent.putExtra("Name",autoDisplayName);
                intent.putExtra("Date",mDateTextView.getText());
                startActivity(intent);
            }
            else{
                //If session is already completed then do nothing.
            }
         }


    }
    private class RVAdapter extends RecyclerView.Adapter<SessionsActivity.SessionHolder>
    {
        private List<Session> mSessionList;
        private RVAdapter(List<Session> sessions)
        {
            mSessionList=sessions;
        }
        @Override
        public SessionsActivity.SessionHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater= LayoutInflater.from(SessionsActivity.this);
            View view=layoutInflater.inflate(R.layout.session_item,parent,false);
            return new SessionsActivity.SessionHolder(view);
        }
        @Override
        public void onBindViewHolder(SessionsActivity.SessionHolder holder, int position)
        {
            Session session = mSessionList.get(position);
            holder.bindSession(session);
        }
        @Override
        public int getItemCount()
        {
            return mSessionList.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
        RecyclerView sessionRecyclerView =  (RecyclerView) findViewById(R.id.sessions_recycler_view);
        TextView name = (TextView)findViewById(R.id.custNameText);
        String autoDisplayName= getIntent().getStringExtra("Name");
        name.setText(autoDisplayName);
        //TODO: GET/CREATE AND DISPLAY SESSIONS TABLE FROM DB


        //CODE TO CREATE DUMMY TESTING DATA
        for(int j=0;j<10;j++)
        {
            Session session = new Session();
            session.setDate(j+"/"+(j+1)+"/2017");;
            if (j%2==0) {
                session.setCompleted("Completed");

            }
            mSessionList.add(session);
        }


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        sessionRecyclerView.setLayoutManager(mLayoutManager);
        SessionsActivity.RVAdapter adapter = new SessionsActivity.RVAdapter(mSessionList);
        sessionRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.menu_item_home:
                Intent intent = new Intent(this, HomeActivity.class );
                startActivity(intent);
                return true;
            case R.id.menu_item_logoff:
                FragmentManager fm = getSupportFragmentManager();
                LogoutFragment dialog = new LogoutFragment();
                dialog.show(fm, "Logout");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onGoAddClick (View view)
    {
        Intent intent = new Intent(SessionsActivity.this, AddSessionActivity.class );
        String autoDisplayName= getIntent().getStringExtra("Name");
        intent.putExtra("Name",autoDisplayName);
        startActivity(intent);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
