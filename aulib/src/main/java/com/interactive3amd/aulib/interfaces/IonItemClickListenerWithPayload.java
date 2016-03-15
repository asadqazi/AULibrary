package com.interactive3amd.aulib.interfaces;

import android.view.View;

/**
 * Created by Asad on 2/2/2016.
 */
public interface IonItemClickListenerWithPayload {
    public void onRecyclerItemClick(View view, int position, Object payLoad);
}
