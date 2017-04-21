package jasonmcdonald.personaltrainer;


import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;


public class NewCustomerActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener {
    private Customer newCustomer= new Customer();
    private static final int REQUEST_PHOTO=0;
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
    public File getPictureFile(Customer customer){
        File externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(externalFilesDir==null){
            return null;
        }
        return new File(externalFilesDir, customer.getPictureFilename(newCustomer.getId()));
    }

    public void onPicClick(View view)
    {

        File mPictureFile=getPictureFile(newCustomer);
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(mPictureFile);
        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, REQUEST_PHOTO);
        }


    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            newCustomer.setImageBitmap(imageBitmap);
        }
    }*/

    public void onAddClick(View view)
    {
        //get inputs

        EditText lastInput = (EditText)findViewById(R.id.lastInputText);
        EditText firstInput = (EditText)findViewById(R.id.firstInputText);
        EditText phoneInput = (EditText)findViewById(R.id.phoneInputText);
        EditText emailInput = (EditText)findViewById(R.id.emailInputText);

        newCustomer.setLastname(lastInput.getText().toString());
        newCustomer.setFirstname(firstInput.getText().toString());
        newCustomer.setPhone(phoneInput.getText().toString());
        newCustomer.setEmail(emailInput.getText().toString());

        //add customer to db

        CustomerDB db = new CustomerDB(this);

        db.addNewCustomer(newCustomer);

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
