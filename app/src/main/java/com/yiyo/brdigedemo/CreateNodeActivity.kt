package com.yiyo.brdigedemo

import android.content.ContentValues
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
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bridgedb.NetworkEdge
import bridgedb.NetworkNode
import com.google.gson.Gson

class CreateNodeActivity : AppCompatActivity() {
    lateinit var submitBtn: Button
    lateinit var addFieldBtn: Button
    lateinit var viewManager: LinearLayoutManager
    lateinit var viewAdapter: FieldRowAdapter
    companion object {
        var values = mutableListOf<EditText?>()
        var keys = mutableListOf<EditText?>()
    }
    lateinit var fieldsRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_node)
        submitBtn = findViewById(R.id.submitCreateNode)
        addFieldBtn = findViewById(R.id.addFieldBtn)
        fieldsRecyclerView = findViewById(R.id.fieldRowsRecyclerView)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.createNodeLayout)
        viewManager = LinearLayoutManager(this)
        viewAdapter = FieldRowAdapter(this, keys)
        fieldsRecyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun addField(v: View) {
        values.add(null)
        keys.add(null)
        viewAdapter.notifyDataSetChanged()

    }

    fun submit(v: View) {
        submitBtn.isEnabled = false
        val list = listOf<ContentValues>()
        val networkNodeBuilder = NetworkNode.newBuilder()
        val properties = networkNodeBuilder.fieldsMap
        for (i in 0 until keys.size) {
            val view = fieldsRecyclerView.getChildAt(i)
            val keyEditText = view.findViewById<EditText>(R.id.field)
            val valueEditText = view.findViewById<EditText>(R.id.value)
            properties[keyEditText.text.toString()] = valueEditText.text.toString()
        }
        val nodeResult = MainActivity.dbBlocking.createNode(networkNodeBuilder.build())
        Toast.makeText(this, "Node ID: " + nodeResult.id.toString(), Toast.LENGTH_SHORT).show()

    }

}

public class FieldRowAdapter(
        val context: Context,
        val dataSet: MutableList<EditText?>
    ):
    RecyclerView.Adapter<FieldRowViewHolder>() {
    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: FieldRowViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldRowViewHolder {
        val viewShida = LayoutInflater.from(parent.context).inflate(R.layout.field_row, parent, false)
        return FieldRowViewHolder(viewShida, dataSet.size-1)
    }

}




public class FieldRowViewHolder(
    val view: View,
    val index: Int
): RecyclerView.ViewHolder(view) {

    init {
        val fieldName = view.findViewById<EditText>(R.id.field)
        val fieldValue = view.findViewById<EditText>(R.id.value)
    }
}

