package com.jolly.checkboxgroupexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jolly.checkboxgroup.CheckboxGroup
import java.util.*

class MainActivity : AppCompatActivity() {
    var checkboxGroup: CheckboxGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkboxGroup = findViewById(R.id.checkbox)
        checkboxGroup!!.onCheckBoxListener(object : CheckboxGroup.OnSelected {
            override fun itemSelected(group: CheckboxGroup?, pos: Int, checkedArray: IntArray?) {
                Log.i(
                    "TAG",
                    "View clicked id :" + pos + " Checkbox state :" + Arrays.toString(checkedArray)
                )
            }
        })
    }
}