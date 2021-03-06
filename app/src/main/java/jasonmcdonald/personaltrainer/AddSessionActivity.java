package jasonmcdonald.personaltrainer;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddSessionActivity extends AppCompatActivity  implements UserFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        TextView name = (TextView)findViewById(R.id.custNameDisplay);
        String autoDisplayName= getIntent().getStringExtra("Name");
        name.setText(autoDisplayName);


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



    public void onSubmitClick(View view)
    {
        //TODO  update Sessions table in db insert new session, do receipt stuff, do transaction stuff
        Toast.makeText(this,R.string.session_added,Toast.LENGTH_SHORT).show();
        String autoDisplayName= getIntent().getStringExtra("Name");
        Intent intent = new Intent(this, SessionsActivity.class );
        intent.putExtra("Name",autoDisplayName);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
