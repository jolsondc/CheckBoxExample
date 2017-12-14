# CheckBoxExample
CheckBoxGroup This class is used to create a multiple-exclusion scope for a set of checkbox buttons. Checking one checkbox button that belongs to a checkbox  group unchecks any previously checked checkbox button within the same group.

<h1> initializing in layout xml code like this :</h1>

```
<com.jolly.checkboxgroup.CheckboxGroup 
        android:id="@+id/checkbox"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:multi_select="false"
        app:checkbox_array="@array/rating"
        app:orientation="vertical" />
```
        
_in String intialise your array and pass it to checkbox group._

example:-
```
  <string-array name="rating">
        <item>AVERAGE</item>
        <item>GOOD</item>
        <item>BAD</item>
    </string-array>

```


**Listening to events**
```

 CheckboxGroup checkboxGroup = (CheckboxGroup) findViewById(R.id.checkbox);

        checkboxGroup.onCheckBoxListener(new CheckboxGroup.onSelected() {
            @Override
            public void itemSelected(CheckboxGroup group, int pos, int[] checkedArray) {
                Log.i("TAG", "View clicked id :" + pos+" Checkbox state :"+ Arrays.toString(checkedArray));
            }
        });

```

*Attributes :*
orientation - 1-vertical
              2-horizontal
text_color - checkbx textcolor
checkbox_array - string array of checkbox text string
multi_select- true :multi select is enabled
              false:single check is enabled 

