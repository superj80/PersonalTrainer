package jasonmcdonald.personaltrainer;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AddSessionActivity extends AppCompatActivity  implements UserFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
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

    public void onGoPayClick(View view)
    {
        Intent intent = new Intent(AddSessionActivity.this, PaymentActivity.class );
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
