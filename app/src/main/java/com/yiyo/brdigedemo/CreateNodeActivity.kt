package com.yiyo.brdigedemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CreateNodeActivity : AppCompatActivity() {
    lateinit var submitBtn: Button
    lateinit var addFieldBtn: Button
    companion object {
        var fields = mutableListOf<Pair<EditText, EditText>>()
    }
    lateinit var fieldsRecyclerView: RecyclerView
    lateinit var keysList: List<EditText>
    lateinit var valsList: List<EditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_node)
        submitBtn = findViewById(R.id.submitCreateNode)
        addFieldBtn = findViewById(R.id.addFieldBtn)
        fieldsRecyclerView = findViewById(R.id.fieldRowsRecyclerView)
    }

    fun addField(v: View) {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.createNodeLayout)
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = FieldRowAdapter(this, mutableListOf<Pair<String, String>>())
        fieldsRecyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }

    fun submit(v: View) {
    }

}

public class FieldRowAdapter(
        val context: Context,
        val dataSet: MutableList<Pair<String, String>>,
        val listenerPair: Pair<FieldRowListener, FieldRowListener>
    ):
    RecyclerView.Adapter<FieldRowViewHolder>() {
    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: FieldRowViewHolder, position: Int) {
        holder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldRowViewHolder {
        val viewShida = LayoutInflater.from(parent.context).inflate(R.layout.field_row, parent, false)
        FieldRowViewHolder(viewShida)
        return FieldRowViewHolder(viewShida)
    }

}

class FieldRowListener: TextWatcher {
    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

}

public class FieldRowViewHolder(val view: View, val listenerPair: Pair<FieldRowListener, FieldRowListener>): RecyclerView.ViewHolder(view) {
    lateinit var fieldPair: Pair<String, String>
    init {
        val fieldName = view.findViewById<EditText>(R.id.field)
        val fieldValue = view.findViewById<EditText>(R.id.value)
        fieldName.addTextChangedListener(listenerPair.first)
        fieldValue.addTextChangedListener(listenerPair.second)
    }
}

