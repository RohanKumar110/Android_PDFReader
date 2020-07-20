package com.rk.pdfreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {

    Context context;
    List<FileModel> modelList, modelListFiltered;

    public CustomAdapter(Context context, List<FileModel> list) {
        this.context = context;
        this.modelList = list;
        this.modelListFiltered = new ArrayList<>(list);
    }

    @Override
    public int getCount() {
        return modelListFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return modelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_adapter,parent,false);
        }
        TextView textview = convertView.findViewById(R.id.txtFilename);
        ImageView imageView = convertView.findViewById(R.id.imgView);

        imageView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        textview.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        textview.setText(modelListFiltered.get(position).getFileName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();
            if(constraint == null || constraint.length() == 0){
                filterResults.count =  modelList.size();
                filterResults.values = modelList;
            }
            else
            {
                List<FileModel> resultsModel = new ArrayList<>();
                String searchStr = constraint.toString().toLowerCase().trim();

                for(FileModel model:modelList)
                    if(model.getFileName().toLowerCase().trim().contains(searchStr)){
                        resultsModel.add(model);
                    }
                filterResults.count = resultsModel.size();
                filterResults.values = resultsModel;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            modelListFiltered = (List<FileModel>) results.values;
            MainActivity.modelList = (List<FileModel>) results.values;
            notifyDataSetChanged();
        }
    };
}

