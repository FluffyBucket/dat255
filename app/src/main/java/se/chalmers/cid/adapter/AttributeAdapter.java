package se.chalmers.cid.adapter;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import se.chalmers.cid.models.Attribute;
import se.chalmers.cid.models.User;

abstract class AttributeAdapter extends BaseAdapter {

	Context mContext;
	ArrayMap<String, Attribute> mAttributes;

	AttributeAdapter(Context context, User user) {
		mContext = context;
		mAttributes = attributesForUser(user);
	}

	abstract ArrayMap<String, Attribute> attributesForUser(User user);

	@Override
	public int getCount() {
		return mAttributes.size();
	}

	@Override
	public String getItem(int position) {
		return mAttributes.keyAt(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

}
