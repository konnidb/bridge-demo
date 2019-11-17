package com.yiyo.brdigedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import bridgedb.NetworkEdge
import bridgedb.NetworkEdgeOrBuilder
import bridgedb.NetworkNode
import com.yiyo.brdigedemo.bridgedb.BridgeDbBlocking
import kotlinx.android.synthetic.main.activity_create_rel.*

class CreateRelActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_rel)
    }

    fun onSubmit(v: View) {
        val destEditText = findViewById<EditText>(R.id.destEditText)
        val originEditText = findViewById<EditText>(R.id.originEditText)
        if (destEditText.text.isBlank() || destEditText.text.isBlank()) {
            return
        }
        val destinationId = destEditText.text.toString()
        val originId = originEditText.text.toString()
        val fields = mutableMapOf<String, String>()
        fields["weight"] = weightEditText.text.toString()
        val db = MainActivity.dbBlocking
        val edge = NetworkEdge.newBuilder()
            .putAllFields(fields)
            .setDestination(NetworkNode.newBuilder().setId(destinationId.toLong()).build())
            .setOrigin(NetworkNode.newBuilder().setId(originId.toLong()).build())
            .build()
        db.createEdge(edge)
    }

}
