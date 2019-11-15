package com.yiyo.brdigedemo.bridgedb

import bridgedb.NetworkEdge
import bridgedb.NetworkNode

data class GraphDto(
    val nodes: List<NetworkNode>,
    val edges: List<NetworkEdge>
)