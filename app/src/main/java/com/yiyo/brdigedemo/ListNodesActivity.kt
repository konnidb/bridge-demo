package com.yiyo.brdigedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import bridgedb.NetworkNode

class ListNodesActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    companion object {
        lateinit var currentNode: NetworkNode
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nodes)
        /*val authCredentials = AuthCredentials("root", "qwe", "other", "other")
        dbBlocking = BridgeDbBlocking(authCredentials, "10.0.2.2", 5000)//"169.254.239.4", 5000)
        dbBlocking.connect()
        dbBlocking.createSession()
        println(dbBlocking.token)
        var txtview = findViewById<TextView>(R.id.text)
        txtview.text = dbBlocking.token
        this.graphDto = dbBlocking.getGraph()
        for (n in graphDto.nodes) {
            println(n.id.toString() + ": NODE")
        }*/
        listView = findViewById(R.id.recipe_list_view)
        val listItems = arrayOfNulls<String>(20)
        for (i: Int in 0 until 20) {
            listItems[i] = "Node: "+i
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            //SET currentNode
            val intent = Intent(this, NodeDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
