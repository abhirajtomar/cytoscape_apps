

import java.awt.event.ActionEvent;
import java.util.List;

import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;


public class MenuAction extends AbstractCyAction {
    private final CyAppAdapter adapter;

    public MenuAction(CyAppAdapter adapter) {
        super("Create Membership Edges",
            adapter.getCyApplicationManager(),
            "network",
            adapter.getCyNetworkViewManager());
        this.adapter = adapter;
        setPreferredMenu("Select");
    }
     public void actionPerformed(ActionEvent e) {
    	
    	 
        final CyApplicationManager manager = adapter.getCyApplicationManager();
        final CyNetworkView networkView = manager.getCurrentNetworkView();
        final CyNetwork network = manager.getCurrentNetwork();
      
        CyNode centroid = network.addNode();
                
        network.getRow(centroid).set(CyNetwork.NAME, "Centroid");
        for (CyNode node : CyTableUtil.getNodesInState(network,"selected",true)/* network.getNodeList()*/) {
        	network.addEdge(centroid, node, true);
        	
        }
        networkView.updateView();
        View<CyNode> nodeView = networkView.getNodeView(centroid);
        double x = 0;
        double y = 0;
        double count = 0; 
        for (CyNode node : CyTableUtil.getNodesInState(network,"selected",true)){
        	nodeView = networkView.getNodeView(node);
        	x += nodeView.getVisualProperty(BasicVisualLexicon.NODE_X_LOCATION);
        	y += nodeView.getVisualProperty(BasicVisualLexicon.NODE_Y_LOCATION);
        	System.out.println(x);
        	count = count + 1;
        }
        x = x/count;
        y = y/count;
        View<CyNode> centroidView = networkView.getNodeView(centroid);
        
        centroidView.setVisualProperty(BasicVisualLexicon.NODE_X_LOCATION, x);
        centroidView.setVisualProperty(BasicVisualLexicon.NODE_Y_LOCATION, y);
                       
        networkView.updateView();
    }
}