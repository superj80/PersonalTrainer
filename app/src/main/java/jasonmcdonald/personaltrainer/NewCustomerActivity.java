package jasonmcdonald.personaltrainer;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static jasonmcdonald.personaltrainer.R.id.lastInputText;


public class NewCustomerActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
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

    public void onPicClick()
    {
        //TODO take picture code
    }

    public void onAddClick(View view)
    {
        //get inputs

        String last = findViewById(R.id.lastInputText).toString();
        String first = findViewById(R.id.firstInputText).toString();
        String phone = findViewById(R.id.phoneInputText).toString();
        String email = findViewById(R.id.emailInputText).toString();

        //TODO: ADD CUSTOMER TO CUSTOMERS TABLE
        //CustomerDB db = new CustomerDB();

        //refresh screen
        Intent intent = new Intent(this,NewCustomerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
