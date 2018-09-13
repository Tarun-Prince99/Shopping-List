package tarun.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTitle = (TextView) findViewById(R.id.myTitle);
        Button currItems = (Button) findViewById(R.id.currItems);
     //   Button starredItems = (Button) findViewById(R.id.starredItems);
     //   Button savedLists = (Button) findViewById(R.id.savedLists);

        currItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });
    }

    public void startNewActivity(){

        Intent myIntent = new Intent(this, FirstButtonActivity.class);
        startActivity(myIntent);

    }

}
