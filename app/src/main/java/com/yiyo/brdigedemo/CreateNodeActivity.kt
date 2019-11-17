package com.yiyo.brdigedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class CreateNodeActivity : AppCompatActivity() {
    var fields = mutableListOf<Pair<EditText, EditText>>()
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
        val p = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        keyField.layoutParams = p
        valField.layoutParams = p
        fields.add(Pair(keyField, valField))


    }

}
