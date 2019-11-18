package com.yiyo.brdigedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import bridgedb.NetworkNode

class NodeDetailsActivity : AppCompatActivity() {
    private lateinit var propListView: ListView
    private lateinit var edgeListView: ListView
    private lateinit var nodeIdView: TextView
    companion object {
        lateinit var currentNode: NetworkNode
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_node_details)

        nodeIdView = findViewById(R.id.NodeIdtxt)
        nodeIdView.text = "${currentNode.id}"

        propListView = findViewById(R.id.PropertiesListView)
        val propList = mutableListOf<String>()
        currentNode.fieldsMap.forEach { (key, value) ->
            propList.add("$key: $value")
        }
        val propAd = ArrayAdapter(this, android.R.layout.simple_list_item_1, propList)
        propListView.adapter = propAd

        edgeListView = findViewById(R.id.EdgesListView)
        val edgeList = arrayOfNulls<String>(20)
        for (i: Int in 0 until 20) {
            edgeList[i] = "Edge: "+i
        }
        val edgAd = ArrayAdapter(this, android.R.layout.simple_list_item_1, edgeList)
        edgeListView.adapter = edgAd
    }
}
