package com.jolly.checkboxgroup

import android.content.Context
import android.widget.LinearLayout
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox

/**
 * Created by Jol.
 */
class CheckboxGroup(private val mContext: Context, attrs: AttributeSet?) : LinearLayout(
    mContext, attrs
), View.OnClickListener {
    private var onClick: OnSelected? = null
    private var checkBox = arrayOfNulls<CheckBox>(1)
    private var title : TypedArray? = null
    private val mCheckTextColor: Int
    private var arrayPassed = false
    private var multiSelect = true
    private val checkedArray: IntArray
    private lateinit var name:String

    override fun onFinishInflate() {
        super.onFinishInflate()
        init(context)
    }

    private fun init(context: Context) {
        orientation = orientation
        initializeCheckbox(context)
    }

    init {
        val attr = mContext.obtainStyledAttributes(attrs, R.styleable.check_box_group, 0, 0)
        val arrayResourceId = attr.getResourceId(
            R.styleable.check_box_group_checkbox_array, 0
        )
        if (arrayResourceId != 0) {
            arrayPassed = true
            title = mContext.resources.obtainTypedArray(arrayResourceId)
            checkBox = arrayOfNulls(title!!.length())
        }
        orientation = attr.getInt(R.styleable.check_box_group_orientation, 0)
        multiSelect = attr.getBoolean(R.styleable.check_box_group_multi_select, true)
        mCheckTextColor = attr.getColor(R.styleable.check_box_group_text_color, 0)
        checkedArray = IntArray(checkBox.size)
        name = attr.getString(R.styleable.check_box_group_name).toString()
        attr.recycle()
    }

    private fun initializeCheckbox(context: Context) {
        if (arrayPassed) {
            for (i in 0 until title!!.length()) {
                checkBox[i] = CheckBox(context)
                checkBox[i]!!.apply {
                    this.text = title!!.getString(i)
                    id = i + 1
                    setTextColor(if(mCheckTextColor > 0) mCheckTextColor else getColor(R.color.grey_text))
                    setOnClickListener(this@CheckboxGroup)
                }
                addView(checkBox[i])
            }
        } else {
            checkBox[0] = CheckBox(context)
            checkBox[0]!!.apply {
                this.text = name
                id = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    View.generateViewId()
                }else{
                    rootView.id
                }
                setTextColor(if(mCheckTextColor > 0) mCheckTextColor else getColor(R.color.grey_text))
                setOnClickListener(this@CheckboxGroup)

            }
            addView(checkBox[0])
        }
    }

    private fun getColor(mCheckTextColor: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mContext.getColor(mCheckTextColor)
        } else {
            mContext.resources.getColor(mCheckTextColor)
        }
    }

    override fun onClick(view: View) {
        handleClick(view.id)
        for (i in checkBox.indices) {
            checkedArray[i] = if (checkBox[i]!!.isChecked) 1 else 0
        }
        if (onClick != null) {
            onClick!!.itemSelected(this, view.id, checkedArray)
        }
    }

    private fun handleClick(id: Int) {
        if (checkBox.size > 1 && !multiSelect) {
            for (i in checkBox.indices) {
                val index = id - 1
                checkBox[i]!!.isChecked = false
                if (i == index) {
                    checkBox[i]!!.isChecked = true
                }
            }
        }
    }

    fun onCheckBoxListener(onClick: OnSelected?) {
        this.onClick = onClick
    }

    interface OnSelected {
        fun itemSelected(group: CheckboxGroup?, pos: Int, checkedArray: IntArray?)
    }

}