package tarun.shoppinglist;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstButtonActivity extends AppCompatActivity {

    ItemDataStruct myDataStruct;
    Context myContext;
    ListView myList;
    Map<String, Integer> itemQuantityMap = new LinkedHashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_button);

        TextView currListTitle = (TextView) findViewById(R.id.currListTitle);

        Resources res = getResources();
        myList = (ListView) findViewById(R.id.myList);
        myContext = this;

        initateDataRetrieval();

    }

    private void initateDataRetrieval() {
        GetData retrieveData = new GetData();
    //    retrieveData.execute();
    }

    private class GetData extends AsyncTask<String,String,String>{

        String programStatus;
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String FINAL_URL = "jdbc:mysql://" + DB_Constant.DB_URL + "/" + DB_Constant.DB_NAME;

        @Override
        protected String doInBackground(String... strings) {

            Connection myConnection = null;
            Statement myStatement = null;

            try{

                Class.forName(JDBC_DRIVER);
                myConnection = DriverManager.getConnection(FINAL_URL, DB_Constant.DB_USERNAME, DB_Constant.DB_PASSWORD);

                myStatement = myConnection.createStatement();
                String input = "SELECT * FROM items";
                ResultSet mySet = myStatement.executeQuery(input);

                while(mySet.next()){
                    String name = mySet.getString("name");
                    int quantity = mySet.getInt("quantity");
                    itemQuantityMap.put(name, quantity);
                }

                programStatus = "Successsfully completed.";
                mySet.close();
                myStatement.close();
                myConnection.close();

            }
            catch(Exception e){
                programStatus = "An error was thrown in JDBC Library. Check doInBackground()";
            }
            return null;
        }
        
        @Override
        protected void onPostExecute(String msg){

            if(itemQuantityMap.size()>0){
                myDataStruct = new ItemDataStruct(myContext, itemQuantityMap);
                myList.setAdapter(myDataStruct);
            }
        }
    }
}
