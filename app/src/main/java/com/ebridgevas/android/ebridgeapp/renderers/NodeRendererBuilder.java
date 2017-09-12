//package com.ebridgevas.android.ebridgeapp.renderers;
//
//import android.content.Context;
//
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.pedrogomez.renderers.Renderer;
//import com.pedrogomez.renderers.RendererBuilder;
//
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * TODO Class description
// */
//public class NodeRendererBuilder extends RendererBuilder<NodeData> {
//
//    /**
//     * TODO Construct description
//     * @param context
//     * @param onNodeClicked
//     */
//    public NodeRendererBuilder(Context context, NodeRenderer.OnNodeClicked onNodeClicked) {
//        List<Renderer<NodeData>> prototypes = getPrototypes(context, onNodeClicked);
//        setPrototypes(prototypes);
//    }
//
//    /**
//     * TODO Method description
//     *
//     * @param content used to map object to Renderers.
//     * @return
//     */
//    protected Class getPrototypeClass(NodeData content) {
//        return NodeRenderer.class;
//    }
//
//    private List<Renderer<NodeData>> getPrototypes(Context context, NodeRenderer.OnNodeClicked onNodeClicked) {
//        List<Renderer<NodeData>> prototypes = new LinkedList<Renderer<NodeData>>();
//
//        NodeRenderer nodeRenderer = new NodeRenderer(context, onNodeClicked);
//        prototypes.add(nodeRenderer);
//
//        return prototypes;
//    }
//}
