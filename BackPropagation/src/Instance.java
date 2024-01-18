

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


class Instance {
	/** DO NOT CHANGE this class */
	static int FTSVALUERANGE = 4; // fts can only be 0,1,2,3
	int label; // label=1 ==> +; label=0 ==> -; label=-1 ==> cannot decide
	int[] fts; // mapping a t c g TO 0,1,2,3. For example fts[3]=2 means 3rd
				// feature is 'c'
	int uniqueId;// every instance will have an uniqe Id;

	public Instance(String line, int id) {
		this.uniqueId = id;
		String[] temp = line.split(" ");
		// set this.label
		if (temp[1].equals("+")) {
			label = 1;
		} else {
			label = 0;
		}
		// set this.fts
		char[] charfts = temp[0].toCharArray();
		this.fts = new int[charfts.length];
		for (int i = 0; i < charfts.length; i++) {
			if (charfts[i] == 'a') {
				fts[i] = 0;
			} else if (charfts[i] == 't') {
				fts[i] = 1;
			} else if (charfts[i] == 'c') {
				fts[i] = 2;
			} else {
				fts[i] = 3;
			}
		}
	}
}

