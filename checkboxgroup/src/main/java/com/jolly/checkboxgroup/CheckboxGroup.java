package com.jolly.checkboxgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;


/**
 * Created by CS251685 on 22-11-2017.
 */

public class CheckboxGroup extends LinearLayout implements View.OnClickListener {
    private onSelected onClick;
    private CheckBox[] checkBox = new CheckBox[1];
    private TypedArray title;//= new String[3];
    private int mCheckTextColor;
    private Context mContext;
    private boolean arrayPassed = false,multiSelect=true;
    private int[] checkedArray ;
    private int orientation=0;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init(getContext());
    }

    public CheckboxGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.check_box_group, 0, 0);
        final int arrayResourceId = attr.getResourceId(
                R.styleable.check_box_group_checkbox_array, 0);
        if (arrayResourceId != 0) {
            arrayPassed = true;
            title = context.getResources().obtainTypedArray(arrayResourceId);

            checkBox = new CheckBox[title.length()];

        }
        orientation =attr.getInt(R.styleable.check_box_group_orientation,0);
        multiSelect =attr.getBoolean(R.styleable.check_box_group_text_color, true);
          mCheckTextColor = attr.getColor(R.styleable.check_box_group_text_color, 0);
        checkedArray = new int[checkBox.length];
        Log.i("TAG","checkbox :"+orientation);
        attr.recycle();

    }

    private void init(Context context) {

        setOrientation(orientation==1?VERTICAL:HORIZONTAL);
        initializeCheckbox(context);


    }

    private void initializeCheckbox(Context context) {

        if (arrayPassed) {
            for (int i = 0; i < title.length(); i++) {
                checkBox[i] = new CheckBox(context);
                checkBox[i].setText(title.getString(i));
                checkBox[i].setId(i + 1);
                if (mCheckTextColor > 0) {
                    checkBox[i].setTextColor(mCheckTextColor);
                } else {
                    checkBox[i].setTextColor(getColor(R.color.grey_text));
                }

                checkBox[i].setOnClickListener(this);
                addView(checkBox[i]);
            }
        } else {
            checkBox[0] = new CheckBox(context);
            checkBox[0].setText("Checkbox");
            if (mCheckTextColor > 0) {
                checkBox[0].setTextColor(mCheckTextColor);
            } else {
                checkBox[0].setTextColor(getColor(R.color.grey_text));
            }
            checkBox[0].setOnClickListener(this);
            addView(checkBox[0]);
        }

    }

    private int getColor(int mCheckTextColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mContext.getColor(mCheckTextColor);
        } else {
            return mContext.getResources().getColor(mCheckTextColor);

        }
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < checkBox.length; i++) {
            checkedArray[i]=checkBox[i].isChecked()?1:0;
        }
        handleClick(view.getId());
        if (onClick != null) {
            onClick.itemSelected(this, view.getId(),checkedArray);
        }
    }

    private void handleClick(int id) {
        if(checkBox.length>1 && !multiSelect) {
            for (int i = 0; i < checkBox.length; i++) {
                int index = id - 1;
                checkBox[i].setChecked(false);
                if (i == index) {
                    checkBox[i].setChecked(true);
                }
            }

        }
    }


    public void onCheckBoxListener(onSelected onClick) {
        this.onClick = onClick;
    }


    public interface onSelected {
        void itemSelected(CheckboxGroup group, int pos, int[] checkedArray);
    }


}
