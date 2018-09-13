package tarun.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDataStruct extends BaseAdapter {

    LayoutInflater myLayout;
    Map<String, Integer> myList;
    List<String> itemNames;
    List<Integer> itemQuantities;

    public ItemDataStruct(Context c, Map m){

        myLayout = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myList = m;
        itemNames = new ArrayList<String>(myList.keySet());
        itemQuantities = new ArrayList<Integer>(myList.values());
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemNames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = myLayout.inflate(R.layout.list_layout, null);
        TextView itemName = (TextView) v.findViewById(R.id.itemName);
        TextView itemCount = (TextView) v.findViewById(R.id.itemCount);
        itemName.setText(itemNames.get(i));
        itemCount.setText("" + itemQuantities.get(i).toString());

        return v;
    }
}
