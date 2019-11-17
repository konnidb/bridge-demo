package com.yiyo.brdigedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class CreateNodeActivity : AppCompatActivity() {

    lateinit var keysList: List<EditText>
    lateinit var valsList: List<EditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_node)
        val addFieldBtn = findViewById<Button>(R.id.addField)
        addFieldBtn.setOnClickListener { view -> addField(view) }

    }

    fun addField(v: View) {
        val keyField = EditText(this)
        val valField = EditText(this)
        keyField.hint = "Field name"
        valField.hint = "Value"
        val layout = findViewById<ConstraintLayout>(R.id.createNodeLayout)
        keyField.layoutParams = ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.MATCH_PARENT)
        layout.addView(keyField)
        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)
        constraintSet.connect(keyField.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 15)
        constraintSet.applyTo(layout)
    }

}
