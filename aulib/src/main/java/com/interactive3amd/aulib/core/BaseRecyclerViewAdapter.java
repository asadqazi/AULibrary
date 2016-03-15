package com.interactive3amd.aulib.core;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interactive3amd.aulib.interfaces.IOnItemClickListner;
import com.interactive3amd.aulib.interfaces.IonItemClickListenerWithPayload;

import java.util.List;


public abstract class BaseRecyclerViewAdapter<T, B> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    protected final Context context;
    private final List<T> data ;
    protected IOnItemClickListner<T> onItemClickListner;
    protected IonItemClickListenerWithPayload onItemClickWithLoad;

    public BaseRecyclerViewAdapter(List<T> data, Context context) {
        this.data = data;
        this.context = context;
    }


    public List<T> getData() {
        return this.data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater v = LayoutInflater.from(parent.getContext());

        return new ViewHolder(onCreateViewHolderDynamic(parent.getContext(), v, parent, viewType));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
        onBindViewHolderDynamic(getItem(position), holder, position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setOnItemClickListner(IOnItemClickListner<T> itemClickRef) {
        this.onItemClickListner = itemClickRef;
    }


    public void setOnItemClickListner(IonItemClickListenerWithPayload itemClickRef) {
        this.onItemClickWithLoad = itemClickRef;
    }

    public T getItem(int index) {
        return ((data != null && index < data.size()) ? data.get(index) : null);
    }

    protected abstract View onCreateViewHolderDynamic(Context context, LayoutInflater inflater, ViewGroup viewGroup, int viewType);

    protected abstract void onBindViewHolderDynamic(T item, ViewHolder viewHolder, int position);

    protected abstract void onViewHolderCreation(View itemView);

    protected abstract void onViewHolderCreated(ViewHolder viewHolder);


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public B binding;


        public ViewHolder(View itemView) {
            super(itemView);
            binding = (B) DataBindingUtil.bind(itemView);
            onViewHolderCreation(itemView);
            itemView.setOnClickListener(this);
            onViewHolderCreated(this);


        }

        public B getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListner != null) {
                onItemClickListner.onRecyclerItemClick(data.get(getAdapterPosition()), v, getAdapterPosition());
            }

            if (onItemClickWithLoad != null) {
                onItemClickWithLoad.onRecyclerItemClick(v, getAdapterPosition(), data);
            }
        }
    }
}
