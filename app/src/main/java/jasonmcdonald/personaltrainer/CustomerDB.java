package jasonmcdonald.personaltrainer;

/**
 * Created by Administrator on 4/7/2017.
 */



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class CustomerDB {
    /**
     * Listing 8-1: Skeleton code for contract class constants
     */
    //The index (key) column name for use in where clauses.
    public static final String KEY_CUSTOMER_ID = "customer_id";
    public static final String KEY_SESSION_ID = "session_id";

    //The name and column index of each column in your database.
    //These should be descriptive.
    //CUSTOMERS TABLE
    public static final String KEY_LAST_NAME_COLUMN =
            "LAST_NAME_COLUMN";
    public static final String KEY_FIRST_NAME_COLUMN =
            "FIRST_NAME_COLUMN";
    public static final String KEY_PHONE_COLUMN =
            "PHONE_COLUMN";
    public static final String KEY_EMAIL_COLUMN =
            "EMAIL_COLUMN";


    //SESSIONS TABLE
    public static final String KEY_DATE_COLUMN =
            "DATE_COLUMN";
    public static final String KEY_COMPLETED_COLUMN =
            "COMPLETED_COLUMN";




    // Database open/upgrade helper
    private CustomerDBOpenHelper CustomerDBOpenHelper;

    public CustomerDB(Context context) {
        CustomerDBOpenHelper = new CustomerDBOpenHelper(context, CustomerDBOpenHelper.DATABASE_NAME, null,
                CustomerDBOpenHelper.DATABASE_VERSION);
    }

    // Called when you no longer need access to the database.
    public void closeDatabase() {
        CustomerDBOpenHelper.close();
    }

    private Cursor getAccessibleCustomer() {
        /**
         * Listing 8-3: Querying a database
         */
        // Specify the result column projection. Return the minimum set
        // of columns required to satisfy your requirements.
        String[] result_columns = new String[] {
                KEY_CUSTOMER_ID, KEY_LAST_NAME_COLUMN, KEY_FIRST_NAME_COLUMN,KEY_PHONE_COLUMN,KEY_EMAIL_COLUMN};

        // Specify the where clause that will limit our results.
        String where = null;//KEY_LAST_NAME_COLUMN + "=" + 1;

        // Replace these with valid SQL statements as necessary.
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = CustomerDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(CustomerDBOpenHelper.CUSTOMERS_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        //
        return cursor;
    }
    public ArrayList<Customer> getCustomers() {
        Cursor cursor = getAccessibleCustomer();

        /**
         * Listing 8-4: Extracting values from a Cursor
         */


        // Find the index to the column(s) being used.
        int LAST_COLUMN_INDEX = cursor.getColumnIndexOrThrow(KEY_LAST_NAME_COLUMN);
        int FIRST_COLUMN_INDEX = cursor.getColumnIndexOrThrow(KEY_FIRST_NAME_COLUMN);
        int PHONE_COLUMN_INDEX = cursor.getColumnIndexOrThrow(KEY_PHONE_COLUMN);
        int EMAIL_COLUMN_INDEX = cursor.getColumnIndexOrThrow(KEY_EMAIL_COLUMN);
        // Iterate over the cursors rows.
        // The Cursor is initialized at before first, so we can
        // check only if there is a "next" row available. If the
        // result Cursor is empty this will return false.
        ArrayList<Customer> customerList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String last = cursor.getString(LAST_COLUMN_INDEX);
            String first = cursor.getString(FIRST_COLUMN_INDEX);
            String phone = cursor.getString(PHONE_COLUMN_INDEX);
            String email = cursor.getString(EMAIL_COLUMN_INDEX);
            Customer customer = new Customer(last,first,phone,email);
            customerList.add(customer);
        }



        // Close the Cursor when you've finished with it.
        cursor.close();

        return customerList;
    }



    public void addNewCustomer(Customer customer) {
        /**
         * Listing 8-5: Inserting new rows into a database
         */
        // Create a new row of values to insert.
        ContentValues newValues = new ContentValues();

        // Assign values for each row.
        newValues.put(KEY_LAST_NAME_COLUMN, customer.getLastName());
        newValues.put(KEY_FIRST_NAME_COLUMN, customer.getFirstName());
        newValues.put(KEY_PHONE_COLUMN, customer.getPhone());
        newValues.put(KEY_EMAIL_COLUMN, customer.getEmail());


        // [ ... Repeat for each column / value pair ... ]

        // Insert the row into your table
        SQLiteDatabase db = CustomerDBOpenHelper.getWritableDatabase();
        db.insert(CustomerDBOpenHelper.CUSTOMERS_TABLE, null, newValues);
    }
    //TODO: Fix update method
    public void updateCustomerValue(int customerId, float newCustomerValue) {
        /**
         * Listing 8-6: Updating a database row
         */
        // Create the updated row Content Values.
        ContentValues updatedValues = new ContentValues();

        // Assign values for each row.

        updatedValues.put(KEY_LAST_NAME_COLUMN, newCustomerValue);
        updatedValues.put(KEY_FIRST_NAME_COLUMN, newCustomerValue);
        updatedValues.put(KEY_PHONE_COLUMN, newCustomerValue);
        updatedValues.put(KEY_EMAIL_COLUMN, newCustomerValue);
        // [ ... Repeat for each column to update ... ]

        // Specify a where clause the defines which rows should be
        // updated. Specify where arguments as necessary.
        String where = KEY_CUSTOMER_ID + "=" + customerId;
        String whereArgs[] = null;

        // Update the row with the specified index with the new values.
        SQLiteDatabase db = CustomerDBOpenHelper.getWritableDatabase();
        db.update(CustomerDBOpenHelper.CUSTOMERS_TABLE, updatedValues,
                where, whereArgs);
    }
    //TODO: Fix delete method
    public void deleteCustomers() {
        /**
         * Listing 8-7: Deleting a database row
         */
        // Specify a where clause that determines which row(s) to delete.
        // Specify where arguments as necessary.
        String where = KEY_LAST_NAME_COLUMN + "=" + 0;
        String whereArgs[] = null;

        // Delete the rows that match the where clause.
        SQLiteDatabase db = CustomerDBOpenHelper.getWritableDatabase();
        db.delete(CustomerDBOpenHelper.CUSTOMERS_TABLE, where, whereArgs);
    }

    /**
     * Listing 8-2: Implementing an SQLite Open Helper
     */
    public static class CustomerDBOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "personalTrainer.db";
        private static final String CUSTOMERS_TABLE = "Customers";
        private static final String SESSIONS_TABLE = "Sessions";
        private static final int DATABASE_VERSION = 1;

        // SQL Statement to create a new table.
        private static final String CUSTOMERS_TABLE_CREATE = "create table " +
                CUSTOMERS_TABLE + " (" + KEY_CUSTOMER_ID +
                " integer primary key autoincrement, " +
                KEY_LAST_NAME_COLUMN + " text not null, " +
                KEY_FIRST_NAME_COLUMN +" text not null, "+
                KEY_PHONE_COLUMN +" text not null, "+
                KEY_EMAIL_COLUMN +" text not null ); ";
        private static final String SESSIONS_TABLE_CREATE = "create table " +
                SESSIONS_TABLE + " (" + KEY_SESSION_ID +
                " integer primary key autoincrement, " +
                KEY_CUSTOMER_ID + " INTEGER, " +
                KEY_DATE_COLUMN + " text not null, " +
                KEY_COMPLETED_COLUMN+" text not null ); ";


        public CustomerDBOpenHelper(Context context, String name,
                                    CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CUSTOMERS_TABLE_CREATE);
            db.execSQL(SESSIONS_TABLE_CREATE);
        }

        // Called when there is a database version mismatch meaning that
        // the version of the database on disk needs to be upgraded to
        // the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            // Log the version upgrade.
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            // Upgrade the existing database to conform to the new
            // version. Multiple previous versions can be handled by
            // comparing oldVersion and newVersion values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF IT EXISTS " + CUSTOMERS_TABLE);
            db.execSQL("DROP TABLE IF IT EXISTS " + SESSIONS_TABLE);
            // Create a new one.
            onCreate(db);
        }
    }
}
