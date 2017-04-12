package jasonmcdonald.personaltrainer;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        FragmentManager fm = getSupportFragmentManager();
        LogoutFragment dialog = new LogoutFragment();
        dialog.show(fm, "Logout");
        return true;

        //THIS CODE IS FOR AN ALTERNATIVE LOG-OUT TOAST INSTEAD OF THE USER-CONFIRMATION DIALOG
        /*switch(item.getItemId())
        {
            case R.id.menu_item_logoff:
                Toast.makeText(CustomersActivity.this,R.string.logoff_toast,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CustomersActivity.this, LoginActivity.class );
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
    }

    public void onPrevClick()
    {
        //TODO submit payment code
        //Create Session
        Session session= new Session();
        EditText date = (EditText)findViewById(R.id.sessionDateText);
        EditText time = (EditText)findViewById(R.id.sessionTimeText);
        session.setDate(date.getText().toString());
        session.setTime(time.getText().toString());

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
