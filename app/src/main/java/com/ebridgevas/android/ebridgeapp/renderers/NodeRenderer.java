//package com.ebridgevas.android.ebridgeapp.renderers;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.PorterDuff;
//import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.ebridgevas.android.ebridgeapp.R;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.picasso.RoundedTransformation;
//import com.ebridgevas.android.ebridgeapp.widget.CircleImageView;
//import com.pedrogomez.renderers.Renderer;
//import com.squareup.picasso.Picasso;
//
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * TODO Class description
// */
//public class NodeRenderer extends Renderer<NodeData> {
//
//    private static final String TAG = "Render";
//
//    protected Context mContext;
//
//    @BindView(R.id.node_container)
//    RelativeLayout root;
//
//    @BindView(R.id.node_thumbnail)
//    CircleImageView nodeThumbnail;
//
//    @BindView(R.id.node_text_top_left)
//    TextView nodeTextTopLeft;
//
//    @BindView(R.id.node_text_bottom_left)
//    TextView nodeTextBottomLeft;
//
////    @BindView(R.id.node_text_top_right)
////    TextView nodeTextTopRight;
////
////    @BindView(R.id.node_text_bottom_right)
////    TextView nodeTextBottomRight;
//
//    protected OnNodeClicked mListener = new EmptyOnNodeClicked();
//
//    /**
//     * TODO Construct description
//     *
//     * @param context
//     * @param onNodeClicked
//     */
//    public NodeRenderer(Context context, OnNodeClicked onNodeClicked) {
//        mContext = context.getApplicationContext();
//        setListener(onNodeClicked);
//    }
//
//    // --- Node View Inflater ------------------------------------------------------------------------------------------
//
//    /**
//     * TODO Method description
//     */
//    @Override
//    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
//        return inflater.inflate(R.layout.node, parent, false);
//    }
//
//    /**
//     * TODO Method description
//     */
//    @Override
//    protected void setUpView(View rootView) {
//        ButterKnife.bind(this, rootView);
//    }
//
//    // --- Node Renderer -----------------------------------------------------------------------------------------------
//
//    /**
//     * TODO Method description
//     */
//    @Override
//    public void render() {
//        Log.i(TAG, "render()");
//        NodeData nodeData = getContent();
//        Map<String, String> params = nodeData.getParams();
//
//        // render node thumbnail
//        Log.i(TAG, "mContext : " + mContext);
//        Resources resources = mContext.getResources();
//        String packageName = mContext.getPackageName();
//        Log.i(TAG, "resources : " + resources);
//        Log.i(TAG, "packageName : " + packageName);
//        Log.i(TAG, "thumbnail : " + params.get("thumbnail"));
//
//        int resId = resources.getIdentifier(params.get("thumbnail"), "drawable", packageName);
//        Log.i(TAG, "resId : " + resId);
//        Picasso.with(mContext).load(resId).into(nodeThumbnail);
//
//        // render node text
//        Log.i(TAG, "top-left-text : " + params.get("node-text-top-left"));
//        Log.i(TAG, "node-text-bottom-left : " + params.get("node-text-bottom-left"));
//        nodeTextTopLeft.setText(params.get("node-text-top-left"));
//        nodeTextBottomLeft.setText(params.get("node-text-bottom-left"));
////        nodeTextTopRight.setText(params.get("node-text-top-right"));
////        nodeTextBottomRight.setText(params.get("node-text-bottom-right"));
//    }
//
//    // --- Event Listeners ---------------------------------------------------------------------------------------------
//
//    /**
//     * TODO Class description
//     */
//    public interface OnNodeClicked {
//        void onPictureClicked(NodeData nodeData);
//        void onBackgroundClicked(NodeData nodeData);
//    }
//
//    /**
//     * TODO Method description
//     */
//    public void setListener(OnNodeClicked listener) {
//        if (listener != null)
//            mListener = listener;
//    }
//
//    /**
//     * TODO Method description
//     */
//    @Override
//    protected void hookListeners(View rootView) {
//
//    }
//
//    // --- Event Handlers ----------------------------------------------------------------------------------------------
//
//    /**
//     * TODO Method description
//     */
//    @OnClick(R.id.node_container)
//    public void onClickRow(RelativeLayout root) {
//        mListener.onBackgroundClicked(getContent());
//    }
//
//    /**
//     * TODO Method description
//     */
//    @OnClick(R.id.node_thumbnail)
//    public void onClickThumbnail(ImageView imageView) {
//        mListener.onPictureClicked(getContent());
//    }
//
//    /**
//     * TODO Method description
//     */
//    private class EmptyOnNodeClicked implements OnNodeClicked {
//
//        @Override
//        public void onPictureClicked(NodeData nodeData) {
//        }
//
//        @Override
//        public void onBackgroundClicked(NodeData nodeData) {
//        }
//    }
//}
