package com.srug86.examples.xmlpullparserexample.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.srug86.examples.xmlpullparserexample.R;
import com.srug86.examples.xmlpullparserexample.domain.Category;
import com.srug86.examples.xmlpullparserexample.domain.CategoryType;
import com.srug86.examples.xmlpullparserexample.domain.FieldType;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ulabsrg on 21/07/2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final String TAG = CategoryAdapter.class.getName();

    private final HashMap<CategoryType, Integer> CategoryImages = new HashMap<CategoryType, Integer>() {
        {
            put(CategoryType.ADDRESS, R.drawable.ic_place_black_36dp);
            put(CategoryType.EMAIL, R.drawable.ic_local_post_office_black_36dp);
            put(CategoryType.PHONE, R.drawable.ic_local_phone_black_36dp);
            put(CategoryType.PROFILE, R.drawable.ic_person_black_36dp);
            put(CategoryType.OTHER, R.drawable.ic_person_black_36dp);
        }
    };

    private Context mContext;
    private List<Category> mCategoryList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivCategory;
        private final RecyclerView rvFieldsContent;

        public ViewHolder(View view) {
            super(view);

            ivCategory = (ImageView) view.findViewById(R.id.ivCategory);
            rvFieldsContent = (RecyclerView) view.findViewById(R.id.rvFields);
        }
    }

    public CategoryAdapter(Context context, List<Category> categoryList) {
        mContext = context;
        mCategoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int index) {
        Log.d(TAG, "onCreateViewHolder(" + parent.getId() + ", " + index + ")");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.rvFieldsContent.setHasFixedSize(false);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rvFieldsContent.setLayoutManager(mLayoutManager);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int index) {
        Log.d(TAG, "onBindViewHolder(" + holder.getPosition() + ", " + index + ")");

        Category category = mCategoryList.get(index);
        holder.ivCategory.setImageResource(CategoryImages.get(category.getType()));
        FieldAdapter fieldAdapter = new FieldAdapter(mContext, category.getFieldList());
        holder.rvFieldsContent.setAdapter(fieldAdapter);
        int fieldLayoutStandardHeightInPixels = (int) holder.itemView.getResources().getDimension(R.dimen.field_layout_standard_height);
        int standardMarginInPixels = (int) holder.itemView.getResources().getDimension(R.dimen.activity_horizontal_margin);
        holder.itemView.setMinimumHeight(fieldAdapter.getItemCount() * fieldLayoutStandardHeightInPixels + standardMarginInPixels * 2);
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
