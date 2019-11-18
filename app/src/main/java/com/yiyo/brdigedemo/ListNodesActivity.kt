package com.yiyo.brdigedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import bridgedb.NetworkNode
import com.google.gson.Gson

class ListNodesActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    companion object {
        lateinit var currentNode: NetworkNode
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_list_nodes)

        val dbBlocking = MainActivity.dbBlocking
        val nodes = dbBlocking.getGraph().nodes
        listView = findViewById(R.id.recipe_list_view)
        val listItems = mutableListOf<String>()
        nodes.forEachIndexed { index, node ->
            val jsonProps = Gson().toJson(node.fieldsMap)
            listItems.add( "Node: ${node.id}, $jsonProps")
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            //SET currentNode
            NodeDetailsActivity.currentNode = nodes[position]
            val intent = Intent(this, NodeDetailsActivity::class.java)
            startActivity(intent)
        }
    }

}
