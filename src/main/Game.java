package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JLabel;

public class Game {

	//private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JFrame frame = new JFrame();

	    CheckBoxNode flatshapeOptions[] = {
	        new CheckBoxNode("Square", true),
	        new CheckBoxNode("Triangle", true),
	        new CheckBoxNode("Circle", true),
	        new CheckBoxNode("Rhombus", true),
	        };
	    
	    CheckBoxNode solidshapeOptions[] = {
	        new CheckBoxNode("Cube", false),
	        new CheckBoxNode("Cylinder", false),
	        new CheckBoxNode("Cone", false),
	        new CheckBoxNode("Square Pyramid", false) ,
	        new CheckBoxNode("Triangular Prism", false) 
	        };
	    
	    
	    Vector flatVector = new NamedVector("2D shapes", flatshapeOptions);
	    Vector solidVector = new NamedVector("3D Shapes", solidshapeOptions);
	    Object rootNodes[] = { flatVector, solidVector };
	    Vector rootVector = new NamedVector("Shapes to Practice", rootNodes);
	    JTree tree = new JTree(rootVector);
	    

	    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
	    tree.setCellRenderer(renderer);

	    tree.setCellEditor(new CheckBoxNodeEditor(tree));
	    tree.setEditable(true);

	    
	    JScrollPane scrollPane = new JScrollPane(tree);
	    JPanel panel = new JPanel();
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, panel);
	    
	    JLabel lblNewLabel = new JLabel("Shapes to Practice:");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	    scrollPane.setColumnHeaderView(lblNewLabel);
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setDividerLocation(250);
	   
	    frame.getContentPane().add(splitPane, BorderLayout.CENTER);
	    
	  //  frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	    //frame.setSize(300, 150);
	    frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	}

}


/***
 *  Credit for Checkbox Tree 
 *  http://www.java2s.com/Code/Java/Swing-JFC/CheckBoxNodeTreeSample.htm
 *
 */
class CheckBoxNodeRenderer implements TreeCellRenderer {
  private JCheckBox leafRenderer = new JCheckBox();

  private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();

  Color selectionBorderColor, selectionForeground, selectionBackground,
      textForeground, textBackground;

  protected JCheckBox getLeafRenderer() {
    return leafRenderer;
  }

  public CheckBoxNodeRenderer() {
    Font fontValue;
    fontValue = UIManager.getFont("Tree.font");
    if (fontValue != null) {
      leafRenderer.setFont(fontValue);
    }
    Boolean booleanValue = (Boolean) UIManager
        .get("Tree.drawsFocusBorderAroundIcon");
    leafRenderer.setFocusPainted((booleanValue != null)
        && (booleanValue.booleanValue()));

    selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
    selectionForeground = UIManager.getColor("Tree.selectionForeground");
    selectionBackground = UIManager.getColor("Tree.selectionBackground");
    textForeground = UIManager.getColor("Tree.textForeground");
    textBackground = UIManager.getColor("Tree.textBackground");
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row,
      boolean hasFocus) {

    Component returnValue;
    if (leaf) {

      String stringValue = tree.convertValueToText(value, selected,
          expanded, leaf, row, false);
      leafRenderer.setText(stringValue);
      leafRenderer.setSelected(false);

      leafRenderer.setEnabled(tree.isEnabled());

      if (selected) {
        leafRenderer.setForeground(selectionForeground);
        leafRenderer.setBackground(selectionBackground);
      } else {
        leafRenderer.setForeground(textForeground);
        leafRenderer.setBackground(textBackground);
      }

      if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
        Object userObject = ((DefaultMutableTreeNode) value)
            .getUserObject();
        if (userObject instanceof CheckBoxNode) {
          CheckBoxNode node = (CheckBoxNode) userObject;
          leafRenderer.setText(node.getText());
          leafRenderer.setSelected(node.isSelected());
        }
      }
      returnValue = leafRenderer;
    } else {
      returnValue = nonLeafRenderer.getTreeCellRendererComponent(tree,
          value, selected, expanded, leaf, row, hasFocus);
    }
    return returnValue;
  }
}

class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

  CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

  ChangeEvent changeEvent = null;

  JTree tree;

  public CheckBoxNodeEditor(JTree tree) {
    this.tree = tree;
  }

  public Object getCellEditorValue() {
    JCheckBox checkbox = renderer.getLeafRenderer();
    CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(),
        checkbox.isSelected());
    return checkBoxNode;
  }

  public boolean isCellEditable(EventObject event) {
    boolean returnValue = false;
    if (event instanceof MouseEvent) {
      MouseEvent mouseEvent = (MouseEvent) event;
      TreePath path = tree.getPathForLocation(mouseEvent.getX(),
          mouseEvent.getY());
      if (path != null) {
        Object node = path.getLastPathComponent();
        if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
          DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
          Object userObject = treeNode.getUserObject();
          returnValue = ((treeNode.isLeaf()) && (userObject instanceof CheckBoxNode));
        }
      }
    }
    return returnValue;
  }

  public Component getTreeCellEditorComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row) {

    Component editor = renderer.getTreeCellRendererComponent(tree, value,
        true, expanded, leaf, row, true);

    // editor always selected / focused
    ItemListener itemListener = new ItemListener() {
      public void itemStateChanged(ItemEvent itemEvent) {
        if (stopCellEditing()) {
          fireEditingStopped();
        }
      }
    };
    if (editor instanceof JCheckBox) {
      ((JCheckBox) editor).addItemListener(itemListener);
    }

    return editor;
  }
}

class CheckBoxNode {
  String text;

  boolean selected;

  public CheckBoxNode(String text, boolean selected) {
    this.text = text;
    this.selected = selected;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean newValue) {
    selected = newValue;
  }

  public String getText() {
    return text;
  }

  public void setText(String newValue) {
    text = newValue;
  }

  public String toString() {
    return getClass().getName() + "[" + text + "/" + selected + "]";
  }
}

class NamedVector extends Vector {
  String name;

  public NamedVector(String name) {
    this.name = name;
  }

  public NamedVector(String name, Object elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

  public String toString() {
    return "[" + name + "]";
  }
}
