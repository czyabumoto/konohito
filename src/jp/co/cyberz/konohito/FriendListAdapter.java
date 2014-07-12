package jp.co.cyberz.konohito;

import java.util.List;

import jp.co.cyberz.konohito.model.Friend;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendListAdapter extends BaseAdapter {
    private Context context;
	private List<Friend> items;

	public FriendListAdapter(Context context, List<Friend> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View friendView = null; 
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            friendView = inflater.inflate(R.layout.friend_list_item, parent, false);
            Log.d(this.getClass().getName(), "created");
        } else {
            friendView = convertView;
            Log.d(this.getClass().getName(), "recycled");
        }

        TextView titleTextView = (TextView)friendView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = (TextView)friendView.findViewById(R.id.descriptionTextView);

		Friend item = items.get(position);
        titleTextView.setText(item.name);
        descriptionTextView.setText(item.birthday);

        return friendView;
    }

}
