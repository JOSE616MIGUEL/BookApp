package com.pack.bookapp.filters;

import android.widget.Filter;

import com.pack.bookapp.adapters.AdapterCategory;
import com.pack.bookapp.adapters.AdapterPdfAdmin;
import com.pack.bookapp.models.ModelCategory;
import com.pack.bookapp.models.ModelPdf;

import java.util.ArrayList;

public class FilterPdfAdmin extends Filter {
    ArrayList<ModelPdf> filterList;

    AdapterPdfAdmin adapterPdfAdmin;

    public FilterPdfAdmin(ArrayList<ModelPdf> filterList, AdapterPdfAdmin adapterPdfAdmin) {
        this.filterList = filterList;
        this.adapterPdfAdmin = adapterPdfAdmin;
    }

    protected FilterResults performFiltering(CharSequence constraint){
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelPdf> filteredModel = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++){
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint)){
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

    protected void publishResults(CharSequence constraint, FilterResults results){
        adapterPdfAdmin.pdfArrayList = (ArrayList<ModelPdf>)results.values;
        adapterPdfAdmin.notifyDataSetChanged();


    }
}
