package com.yiyo.brdigedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

import bridgedb.NetworkEdge;
import bridgedb.NetworkNode;
import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.ViewHolder;
import de.blox.graphview.energy.FruchtermanReingoldAlgorithm;
public class GraphActivity extends AppCompatActivity {
    private int nodeCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }

    public Graph getNodesFromEdges() {
        HashMap<Long, Node> layoutNodes = new HashMap<>();
        Graph graph = new Graph();
        System.out.println("IM HERE");
        for (NetworkNode node: MainActivity.Companion.getGraphDto().getNodes()) {
            Gson gson = new Gson();
            String value = "ID: " + node.getId() + ", " + gson.toJson(node.getFieldsMap());
            layoutNodes.put(node.getId(), new Node(value));
        }

        for(NetworkEdge edge: MainActivity.Companion.getGraphDto().getEdges()) {
            NetworkNode origin = edge.getOrigin();
            NetworkNode destination = edge.getDestination();
            Node layoutOrigin = null;
            Node layoutDestination = null;
            System.out.println("Iterating edges in GraphActivity");
            System.out.println(origin.getId());
            System.out.println(destination.getId());
            if (layoutNodes.get(origin.getId()) == null) {
                Gson gson = new Gson();
                gson.toJson(origin.getFieldsMap());
                layoutOrigin = new Node("ID: " + origin.getId());
                layoutNodes.put(origin.getId(), layoutOrigin);
            } else {
                layoutOrigin = layoutNodes.get(origin.getId());
            }
            if (layoutNodes.get(destination.getId()) == null) {
                Gson gson = new Gson();
                gson.toJson(destination.getFieldsMap());
                layoutDestination = new Node("ID: " + destination.getId());
                layoutNodes.put(destination.getId(), layoutDestination);
            } else {
                layoutDestination = layoutNodes.get(destination.getId());
            }

            graph.addEdge(layoutOrigin, layoutDestination);
        }
        return graph;
    }


    @Override
    protected void onResume() {

        // example tree
        super.onResume();
        final Graph graph = getNodesFromEdges();
        GraphView graphView = findViewById(R.id.graph);

        // you can set the graph via the constructor or use the adapter.setGraph(Graph) method
        final BaseGraphAdapter<ViewHolder> adapter = new BaseGraphAdapter<ViewHolder>(graph) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                ((SimpleViewHolder) viewHolder).textView.setText(data.toString());
            }
        };
        graphView.setAdapter(adapter);


        // set the algorithm here
        FruchtermanReingoldAlgorithm alg = new FruchtermanReingoldAlgorithm();
        adapter.setAlgorithm(alg);
    }

    private String getNodeText() {
        return "Node " + nodeCount++;
    }
}

class SimpleViewHolder extends ViewHolder {
    TextView textView;

    SimpleViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }
}