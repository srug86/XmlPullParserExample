package com.srug86.examples.xmlpullparserexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.srug86.examples.xmlpullparserexample.R;
import com.srug86.examples.xmlpullparserexample.domain.Field;
import com.srug86.examples.xmlpullparserexample.domain.FieldType;

import java.util.List;

/**
 * Created by ulabsrg on 21/07/2015.
 */
public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private List<Field> mFieldList;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        /*for (int index = 0; index < mFieldList.size(); index++) {
            FieldAdapter.ViewHolder fieldViewHolder = onCreateViewHolder(recyclerView, index);
            onBindViewHolder(fieldViewHolder, index);
        }*/
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFieldName;
        private EditText etFieldValue;

        public ViewHolder(View itemView, FieldType fieldType) {
            super(itemView);

            int tvFieldId;
            int etFieldId;
            switch (fieldType) {
                case DATE:
                    tvFieldId = R.id.tvDateFieldName;
                    etFieldId = R.id.etDateFieldValue;
                    break;
                case NATURAL:
                    tvFieldId = R.id.tvNaturalFieldName;
                    etFieldId = R.id.etNaturalFieldValue;
                    break;
                case EMAIL:
                    tvFieldId = R.id.tvEmailFieldName;
                    etFieldId = R.id.etEmailFieldValue;
                    break;
                default:
                    tvFieldId = R.id.tvStringFieldName;
                    etFieldId = R.id.etStringFieldValue;
                    break;
            }

            tvFieldName = (TextView) itemView.findViewById(tvFieldId);
            etFieldValue = (EditText) itemView.findViewById(etFieldId);
        }
    }

    public FieldAdapter(List<Field> fieldList) {
        mFieldList = fieldList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int fieldLayoutId;
        FieldType fieldType = mFieldList.get(i).getType();
        switch (fieldType) {
            case DATE:
                fieldLayoutId = R.layout.field_date_item;
                break;
            case NATURAL:
                fieldLayoutId = R.layout.field_natural_item;
                break;
            case EMAIL:
                fieldLayoutId = R.layout.field_email_item;
                break;
            default:
                fieldLayoutId = R.layout.field_string_item;
                break;
        }

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(fieldLayoutId, viewGroup, false);
        ViewHolder vhItem = new ViewHolder(view, fieldType);
        return vhItem;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Field field = mFieldList.get(i);
        viewHolder.tvFieldName.setText(field.getName());
        viewHolder.etFieldValue.setText(field.getValue());
    }

    @Override
    public int getItemCount() {
        return mFieldList.size();
    }
}
