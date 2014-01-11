package com.jbirdvegas.mgerrit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jbirdvegas.mgerrit.R;
import com.jbirdvegas.mgerrit.objects.GooFileObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * Copyright (C) 2013 Android Open Kang Project (AOKP)
 *  Author: Jon Stanford (JBirdVegas), 2013
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
public class GooFileArrayAdapter extends BaseAdapter {
    private final Context mContext;
    private final int mLayoutResourceId;
    private final List<GooFileObject> mGooFilesList;

    public GooFileArrayAdapter(Context context, int layoutResourceId, List<GooFileObject> objects) {
        this.mContext = context;
        this.mLayoutResourceId = layoutResourceId;
        this.mGooFilesList = objects;
    }

    @Override
    public int getCount() {
        return mGooFilesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGooFilesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = convertView;
        if (root == null) {
            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = inflater.inflate(mLayoutResourceId, null);
            TextView fileName = (TextView) root.findViewById(R.id.goo_file_name);
            TextView fileUpdate = (TextView) root.findViewById(R.id.goo_file_date);
            final GooFileObject file = mGooFilesList.get(position);
            fileName.setText(file.getFileName());
            long unixDate = file.getModified();
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            fileUpdate.setText(df.format(new Date(unixDate)));
        }
        return root;
    }

    public List<GooFileObject> getGooFilesList() {
        return mGooFilesList;
    }

    public GooFileObject getObjectAtPostition(int position) {
        return (GooFileObject) getItem(position);
    }
}