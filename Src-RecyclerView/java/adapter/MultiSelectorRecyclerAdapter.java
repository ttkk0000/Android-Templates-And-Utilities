package com.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import com.example.ExampleApplication;
import com.example.R;
import com.example.entity.ProductEntity;


public class MultiSelectorRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private MultiSelector mMultiSelector = new MultiSelector(); // TODO: use MultiSelector or SingleSelector


	public static final class ProductViewHolder extends SwappingHolder implements View.OnClickListener, View.OnLongClickListener
	{
		private TextView nameTextView;
		private OnItemClickListener mListener;
		private MultiSelector mMultiSelector;


		public interface OnItemClickListener
		{
			public void onItemClick(View view, int position, long id, int viewType);
			public void onItemLongClick(View view, int position, long id, int viewType);
		}


		public ProductViewHolder(View itemView, OnItemClickListener listener, MultiSelector multiSelector)
		{
			super(itemView, multiSelector);
			mListener = listener;
			mMultiSelector = multiSelector;

			// set background
			setSelectionModeBackgroundDrawable(ExampleApplication.getContext().getResources().getDrawable(R.drawable.selector_selectable_item_bg));

			// set listener
			itemView.setOnClickListener(this);
			itemView.setOnLongClickListener(this);

			// find views
			nameTextView = (TextView) itemView.findViewById(R.id.fragment_recycler_item_name);
		}


		@Override
		public void onClick(View view)
		{
			if(!mMultiSelector.tapSelection(this))
			{
				mListener.onItemClick(view, getPosition(), getItemId(), getItemViewType());
			}
		}


		@Override
		public boolean onLongClick(View view)
		{
			if(!mMultiSelector.isSelectable())
			{
				mMultiSelector.setSelectable(true);
				mMultiSelector.setSelected(this, true);
			}
			else
			{
				mListener.onItemLongClick(view, getPosition(), getItemId(), getItemViewType());
			}
			return true;
		}


		public void bindData(ProductEntity product)
		{
			nameTextView.setText(product.getName());
		}
	}
}
