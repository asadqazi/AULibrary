package com.interactive3amd.aulib.interfaces;

import android.view.View;

/**
 * Created by UmerKiani on 2/1/2016.
 */
public interface IOnItemClickListner<T> {

    public void onRecyclerItemClick(T model, View view, int position);


}
