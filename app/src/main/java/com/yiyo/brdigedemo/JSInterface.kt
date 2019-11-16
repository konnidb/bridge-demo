package com.yiyo.brdigedemo

import android.webkit.JavascriptInterface
import bridgedb.NetworkEdge
import bridgedb.NetworkNode
import com.google.gson.Gson
import com.yiyo.brdigedemo.bridgedb.GraphDto

class JSInterface(
    var graphDto:GraphDto
){

    @JavascriptInterface
    fun getGraph(): GraphDto {

        for (node in graphDto.nodes) {
            val mutableMap = node.fieldsMap
            mutableMap.put("id", node.id.toString())
            println("FROM JS INTERFACE: " + node.id)
        }
        return graphDto
    }

    @JavascriptInterface
    fun getGraphEdges(): Array<String> {
        val stringArray = arrayOf<String>()
        graphDto.edges.forEachIndexed { index, networkEdge ->
            val gson = Gson()
            val fieldsMap = networkEdge.fieldsMap
            fieldsMap["origin"] = parseNetworkNode(networkEdge.origin)
            fieldsMap["destination"] = parseNetworkNode(networkEdge.destination)
            stringArray[index] = gson.toJson(fieldsMap)
        }
        return stringArray
    }

    fun parseNetworkNode(n: NetworkNode): String {
        val fieldsMap = n.fieldsMap
        fieldsMap["id"] = n.id.toString()
        val gson = Gson()
        return gson.toJson(fieldsMap)
    }


}