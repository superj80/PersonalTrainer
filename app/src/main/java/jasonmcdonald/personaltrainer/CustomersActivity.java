package jasonmcdonald.personaltrainer;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomersActivity extends AppCompatActivity  implements UserFragment.OnFragmentInteractionListener {

    private List<Customer> mCustList= new ArrayList<>();

    SQLiteDatabase customerDb = new CustomerDB.CustomerDBOpenHelper(this,null,null,1).getWritableDatabase() ;



    private class CustomerHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Customer mCustomer;
        private TextView mFirstTextView;
        private TextView mLastTextView;
        private TextView mPhoneTextView;
        private TextView mEmailTextView;
        private CustomerHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            mFirstTextView=(TextView) itemView.findViewById(R.id.firstname);
            mLastTextView=(TextView) itemView.findViewById(R.id.lastname);
            mPhoneTextView=(TextView) itemView.findViewById(R.id.phone);
            mEmailTextView=(TextView) itemView.findViewById(R.id.email);
        }
        private void bindCustomer(Customer customer)
        {
            mCustomer=customer;
            mFirstTextView.setText(mCustomer.getFirstName());
            mLastTextView.setText(mCustomer.getLastName());
            mPhoneTextView.setText(mCustomer.getPhone());
            mEmailTextView.setText(mCustomer.getEmail());


        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CustomersActivity.this, SessionsActivity.class );
            startActivity(intent);
        }
    }
    private class RVAdapter extends RecyclerView.Adapter<CustomerHolder>
    {
        private List<Customer> mCustomerList;
        private RVAdapter(List<Customer> customers)
        {
            mCustomerList=customers;
        }
        @Override
        public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater= LayoutInflater.from(CustomersActivity.this);
            View view=layoutInflater.inflate(R.layout.customer_item,parent,false);
            return new CustomerHolder(view);
        }
        @Override
        public void onBindViewHolder(CustomerHolder holder, int position)
        {
            Customer customer = mCustomerList.get(position);
            holder.bindCustomer(customer);
        }
        @Override
        public int getItemCount()
        {
            return mCustomerList.size();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        RecyclerView customerRecyclerView =  (RecyclerView) findViewById(R.id.customer_recycler_view);

        //CODE TO CREATE DUMMY TESTING DATA
        for(int i=0;i<100;i++) {
            Customer customer = new Customer();
            customer.setFirstname("George");
            customer.setLastname("Customer " + (i + 1));
            customer.setPhone("555-272-4567");
            customer.setEmail("george1234@gmail.com");
            mCustList.add(customer);
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        customerRecyclerView.setLayoutManager(mLayoutManager);
        RVAdapter adapter = new RVAdapter(mCustList);
        customerRecyclerView.setAdapter(adapter);



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



    @Override
    public void onFragmentInteraction(Uri uri) {
        //Just a logged-in display
    }
}
