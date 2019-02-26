package deniscbrex.dev.listviewadaptertest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOwnAdapter extends BaseAdapter {

    List<Animal> myList = new ArrayList<Animal>();

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Animal getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
