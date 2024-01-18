import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

class Node {

	Node parent;
	Node children[];

	/**
	 * The test feature for internal node
	 */
	int testFts;

	int numOfFts;

	List<Instance> instances;

	int predictedLabel = -1;

	Node(Node parent, List<Instance> instances) {
		this.parent = parent;
		children = new Node[Instance.FTSVALUERANGE];

		this.instances = instances;
		numOfFts = instances.get(0).fts.length;
		testFts = -1;

		int count[] = { 0, 0 };
		for (Instance t : this.instances)
			count[t.label]++;
		predictedLabel = count[0] > count[1] ? 0 : 1;
	}

	public int classify(Instance t) {
		// System.out.println("test\t" + testFts);
		if (testFts == -1) {
			return predictedLabel;
		} else {
			if (children[t.fts[testFts]] != null) {
				return children[t.fts[testFts]].classify(t);
			} else {
				return -1;// cannot decide; return parent label
			}
		}
	}

	/**
	 * Exports the decision tree rooted from this node to a TreeML file.
	 * 
	 * @param filename
	 *            The name of the target file
	 */
	public void writeTreeXML(String filename) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(filename));
			writer.println("<?xml version=\"1.0\" ?>");
			writer.println("<tree>");
			writer.println("<declarations>");
			writer.println("<attributeDecl name=\"name\" type=\"String\" />");
			writer.println("<attributeDecl name=\"weight\" type=\"Real\" />");
			writer.println("</declarations>");
			writeTreeML(writer);
			writer.println("</tree>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeTreeML(PrintWriter writer) {
		if (testFts != -1)
			writer.println("<branch>");
		else
			writer.println("<leaf>");

		writer.print("<attribute name=\"name\" value=\"");
		if (parent == null)
			writer.print("root");
		else {
			for (int i = 0; i < Instance.FTSVALUERANGE; i++) {
				if (this == parent.children[i]) {
					writer.print("fts" + parent.testFts + " = " + i);
				}
			}
		}
		writer.println("\" />");

		if (testFts != -1) {
			for (int i = 0; i < Instance.FTSVALUERANGE; i++) {
				if (children[i] != null)
					children[i].writeTreeML(writer);
			}
			writer.println("</branch>");
		} else {
			writer.println("<attribute name=\"weight\" value=\""
					+ instances.size() + "\" />");
			writer.println("</leaf>");
		}
	}

}
