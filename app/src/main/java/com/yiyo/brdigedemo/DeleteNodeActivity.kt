package com.yiyo.brdigedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DeleteNodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_node)
    }

    fun submitDeleteNode(v: View) {
        (v as Button).isEnabled = false
        val deleteNodeIdText = findViewById<EditText>(R.id.deleteNodeIdEditText)
        val nodeId = deleteNodeIdText.text.toString().toLong()
        val dbBlocking = MainActivity.dbBlocking
        val deleteNodeResult = dbBlocking.deleteNode(nodeId)
        Toast.makeText(this, "Node Deleted", Toast.LENGTH_SHORT).show()
        finish()
    }
}
