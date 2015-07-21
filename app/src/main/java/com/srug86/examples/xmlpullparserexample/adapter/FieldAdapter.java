package com.srug86.examples.xmlpullparserexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.srug86.examples.xmlpullparserexample.R;
import com.srug86.examples.xmlpullparserexample.domain.Field;
import com.srug86.examples.xmlpullparserexample.domain.FieldType;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ulabsrg on 21/07/2015.
 */
public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private final String TAG = FieldAdapter.class.getSimpleName();

    private final HashMap<FieldType, Integer> FieldTypeInput = new HashMap<FieldType, Integer>() {
        {
            put(FieldType.STRING, InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            put(FieldType.DATE, InputType.TYPE_DATETIME_VARIATION_DATE);
            put(FieldType.EMAIL, InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
            put(FieldType.NATURAL, InputType.TYPE_CLASS_NUMBER);
            put(FieldType.DECIMAL, InputType.TYPE_NUMBER_FLAG_DECIMAL);
            put(FieldType.DNI, InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            put(FieldType.PHONE, InputType.TYPE_CLASS_PHONE);
            put(FieldType.PICTURE, InputType.TYPE_TEXT_VARIATION_URI);
        }
    };

    private Context mContext;
    private List<Field> mFieldList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFieldName;
        private EditText etFieldValue;

        public ViewHolder(View itemView) {
            super(itemView);

            tvFieldName = (TextView) itemView.findViewById(R.id.tvFieldName);
            etFieldValue = (EditText) itemView.findViewById(R.id.etFieldValue);
        }
    }

    public FieldAdapter(Context context, List<Field> fieldList) {
        Log.d(TAG, "FieldAdapter(" + context + ", " + fieldList + ")");

        mContext = context;
        mFieldList = fieldList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int index) {
        Log.d(TAG, "onCreateViewHolder(" + parent.getId() + ", " + index + ")");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.field_standard_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int index) {
        Log.d(TAG, "onBindViewHolder(" + holder.getPosition() + ", " + index + ")");

        Field field = mFieldList.get(index);
        holder.tvFieldName.setText(field.getName());
        holder.etFieldValue.setText(field.getValue());
        holder.etFieldValue.setInputType(FieldTypeInput.get(field.getType()));
    }

    @Override
    public int getItemCount() {
        return mFieldList.size();
    }
}
