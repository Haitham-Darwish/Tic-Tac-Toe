/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * author Haitham Essam
 *
 * version 1.1 beta
 */

package com.android.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/***
 * {@link XOAdapter}is an{@link ArrayAdapter}that can provide the layout for each list item
 * based on a data source,which is a list of{@link XO}objects.
 */

public class XOAdapter extends ArrayAdapter<XO> {
    ArrayList<XO> mXo;
    /**
     * Create a new {@link XOAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param xo      is the list of {@link XO}s to be displayed.
     */
    public XOAdapter(Context context, ArrayList<XO> xo) {
        super(context, 0, xo);
        mXo = xo;

    }


    public void updateImage(int position, int resourceId)
    {

//       XO currentXO = xo.get(position);;
        mXo.set(position, new XO(resourceId));
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }


        // Get the {@link Word} object located at this position in the list
        XO currentXO = getItem(position);


        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) gridItemView.findViewById(R.id.xo);

        // Check if an image is provided for this word or not
        // provided image based on the resource ID
        imageView.setImageResource(currentXO.getImageResourceId());


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return gridItemView;
    }
}
