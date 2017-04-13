package jasonmcdonald.personaltrainer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

    public void onPicClick()
    {
        //TODO take picture code
    }

    public void onAddClick(View view)
    {
        //get inputs

        EditText lastInput = (EditText)findViewById(R.id.lastInputText);
        EditText firstInput = (EditText)findViewById(R.id.firstInputText);
        EditText phoneInput = (EditText)findViewById(R.id.phoneInputText);
        EditText emailInput = (EditText)findViewById(R.id.emailInputText);

        String  last=lastInput.getText().toString();
        String  first=firstInput.getText().toString();
        String  phone=phoneInput.getText().toString();
        String  email=emailInput.getText().toString();
        //add customer to db

        CustomerDB db = new CustomerDB(this);

        db.addNewCustomer(last,first,phone,email);

        //refresh screen

        lastInput.setText("");
        firstInput.setText("");
        phoneInput.setText("");
        emailInput.setText("");
        //confirm new customer
        Toast.makeText(this,R.string.customer_added,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
