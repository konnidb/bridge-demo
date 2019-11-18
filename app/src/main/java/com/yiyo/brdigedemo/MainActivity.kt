package com.yiyo.brdigedemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import bridgedb.NetworkEdge
import bridgedb.NetworkNode
import com.yiyo.brdigedemo.bridgedb.AuthCredentials
import com.yiyo.brdigedemo.bridgedb.BridgeDbBlocking
import com.yiyo.brdigedemo.bridgedb.GraphDto
import de.blox.graphview.Graph
import de.blox.graphview.Node
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dbBlocking: BridgeDbBlocking
    }
    lateinit var graphDto: GraphDto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val wifiManager = this.getSystemService(Context.WIFI_SERVICE) as Wif
    }

    override fun onResume() {
        super.onResume()
        val authCredentials = AuthCredentials("root", "qwe", "test", "test")
        dbBlocking = BridgeDbBlocking(authCredentials, "10.0.2.2", 5000)//"169.254.239.4", 5000)
        dbBlocking.connect()
        dbBlocking.createSession()
        println(dbBlocking.token)
        var txtview = findViewById<TextView>(R.id.text)
        txtview.text = dbBlocking.token
        this.graphDto = dbBlocking.getGraph()
        for (n in graphDto.nodes) {
            println(n.id.toString() + ": NODE")
        }
    }

    fun getNodesFromEdges() {
        val layoutNodes = mutableMapOf<Long, Node>()
        var graph: Graph = Graph()

        for (edge in this.graphDto.edges) {
            val originNode = edge.origin
            val destinationNode = edge.destination
            var layOrNode: Node
            var layDestNode: Node

            if (layoutNodes.containsKey(originNode.id)) {
                layOrNode = layoutNodes[originNode.id]!!
            } else {
                layOrNode = Node(buildNodeText(originNode))
                layoutNodes[originNode.id] = layOrNode
            }
            if (layoutNodes.containsKey(destinationNode.id)) {
                layDestNode = layoutNodes[destinationNode.id]!!
            } else {
                layDestNode = Node(buildNodeText(destinationNode))
                layoutNodes[destinationNode.id] = layDestNode
            }
            graph.addEdge(layOrNode, layDestNode)
        }
    }

    fun buildNodeText(node: NetworkNode): String {
        var appendableString = ""
        for (key in node.fieldsMap.keys) {
            appendableString = appendableString + key + ":" + node.fieldsMap[key] + "\n"
        }
        return appendableString
    }

    fun onPressCreateNode(v: View) {
        val intent =  Intent(this, CreateNodeActivity::class.java)
        startActivity(intent)
    }

    fun onPressCreateRel(v: View) {
        val intent = Intent(this, CreateRelActivity::class.java)
        startActivity(intent)
    }
}
