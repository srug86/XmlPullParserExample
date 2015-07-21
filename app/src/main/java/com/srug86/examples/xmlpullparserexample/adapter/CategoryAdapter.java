package com.srug86.examples.xmlpullparserexample.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.srug86.examples.xmlpullparserexample.R;
import com.srug86.examples.xmlpullparserexample.domain.Category;

import java.util.List;

/**
 * Created by ulabsrg on 21/07/2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> mCategoryList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivCategory;
        private RecyclerView rvFieldsContent;
        private Context mContext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ivCategory = (ImageView) itemView.findViewById(R.id.ivCategory);
            rvFieldsContent = (RecyclerView) itemView.findViewById(R.id.rvFields);
            mContext = context;
        }
    }

    public CategoryAdapter(List<Category> categoryList) {
        mCategoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        ViewHolder vhItem = new ViewHolder(view, viewGroup.getContext());
        return vhItem;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int imageResource;
        Category category = mCategoryList.get(i);
        switch (category.getType()) {
            case ADDRESS:
                imageResource = R.drawable.ic_place_black_36dp;
                break;
            case EMAIL:
                imageResource = R.drawable.ic_local_post_office_black_36dp;
                break;
            case PHONE:
                imageResource = R.drawable.ic_local_phone_black_36dp;
                break;
            default:
                imageResource = R.drawable.ic_person_black_36dp;
                break;
        }

        viewHolder.ivCategory.setImageResource(imageResource);
        FieldAdapter fieldAdapter = new FieldAdapter(category.getFieldList());
        viewHolder.rvFieldsContent.setAdapter(fieldAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(viewHolder.mContext);
        viewHolder.rvFieldsContent.setLayoutManager(layoutManager);

        for (int index = 0; index < category.getFieldList().size(); index++) {
            FieldAdapter.ViewHolder fieldViewHolder = fieldAdapter.onCreateViewHolder(viewHolder.rvFieldsContent, index);
            fieldAdapter.onBindViewHolder(fieldViewHolder, index);
        }

        //viewHolder.rvFieldsContent.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
