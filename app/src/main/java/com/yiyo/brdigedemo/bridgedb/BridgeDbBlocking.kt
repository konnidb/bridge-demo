package com.yiyo.brdigedemo.bridgedb

import bridgedb.*
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class BridgeDbBlocking(
    val credentials: AuthCredentials,
    val address: String,
    val port: Int
) {
    lateinit var token: String
    lateinit var channel: ManagedChannel
    lateinit var stub: QueryServiceGrpc.QueryServiceBlockingStub
    fun connect() {
        channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build()
        stub = QueryServiceGrpc.newBlockingStub(channel)
    }

    fun createSession() {
        val createSessionRequest = SessionRequest.newBuilder()
            .setUsername(credentials.username)
            .setDatabase(credentials.database)
            .setPassword(credentials.password)
            .setGraph(credentials.graph)
            .build()
        val session = stub.createSession(createSessionRequest)
        token = session.token
    }

    fun createNode(node: NetworkNode): NetworkNode {
        val createNodeReq = CreateNodeReq.newBuilder().setToken(token)
            .setNode(node)
            .build()

        val response = stub.createNode(createNodeReq)
        return response.node
    }

    fun searchNode(nodeId: Long): NetworkNode {
        val networkNode = NetworkNode.newBuilder().setId(nodeId).build()
        val searchNodeReq = SearchNodeReq.newBuilder().setToken(token).setNode(networkNode).build()

        val response = stub.searchNode(searchNodeReq)
        return response.getNodes(0);
    }

    fun searchNodes(node: NetworkNode): List<NetworkNode> {
        val searchNodeReq = SearchNodeReq.newBuilder().setToken(token).setNode(node).build()

        val response = stub.searchNode(searchNodeReq)
        return response.nodesList
    }

    fun deleteNode(nodeId: Long): NetworkNode {
        val deleteNodeReq = DeleteNodeReq.newBuilder().setToken(token)
            .setNodeId(nodeId).build()
        val response = stub.deleteNode(deleteNodeReq)
        return response.node
    }

    fun createEdge(edge: NetworkEdge): NetworkEdge {
        val createEdgeReq = CreateEdgeReq.newBuilder()
            .setToken(token)
            .setEdge(edge)
            .build()
        val response = stub.createEdge(createEdgeReq)
        return response.edge
    }

    fun getGraph(): GraphDto {
        val request = NetworkGraphRequest.newBuilder().setToken(token).build()
        val response = stub.getGraph(request)
        return GraphDto(response.nodesList, response.edgesList)
    }
}