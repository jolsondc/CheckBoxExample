package com.jolly.checkboxgroupexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jolly.checkboxgroup.CheckboxGroup;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    CheckboxGroup checkboxGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkboxGroup = (CheckboxGroup) findViewById(R.id.checkbox);

        checkboxGroup.onCheckBoxListener(new CheckboxGroup.onSelected() {
            @Override
            public void itemSelected(CheckboxGroup group, int pos, int[] checkedArray) {
                Log.i("TAG", "View clicked id :" + pos+" Checkbox state :"+ Arrays.toString(checkedArray));
            }
        });
    }
}
