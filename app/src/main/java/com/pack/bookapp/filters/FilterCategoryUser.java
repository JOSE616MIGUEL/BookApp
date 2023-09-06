package com.pack.bookapp.filters;

import android.widget.Filter;

import com.pack.bookapp.adapters.AdapterCategory;
import com.pack.bookapp.adapters.AdapterCategoryUser;
import com.pack.bookapp.models.ModelCategory;

import java.util.ArrayList;

public class FilterCategoryUser extends Filter {
    ArrayList<ModelCategory> filterList;

    AdapterCategoryUser adapterCategoryuser;

    public FilterCategoryUser(ArrayList<ModelCategory> filterList, AdapterCategoryUser adapterCategoryuser) {
        this.filterList = filterList;
        this.adapterCategoryuser = adapterCategoryuser;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelCategory> filteredModel = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++){
                if (filterList.get(i).getCategory().toUpperCase().contains(constraint)){
                    filteredModel.add(filterList.get(i));
                }
            }

            results.count = filteredModel.size();
            results.values = filteredModel;

        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterCategoryuser.categoryArrayList = (ArrayList<ModelCategory>) results.values;
        adapterCategoryuser.notifyDataSetChanged();
    }
}
