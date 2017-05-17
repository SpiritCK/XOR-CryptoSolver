import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class CryptSolver extends JFrame implements ActionListener {
	
	JTextArea input;
	JTextField key;
	JTextField fileName1;
	JButton crypt;
	JLabel cryptStatus;
	JTextField fileName2;
	JButton decrypt;
	JTextArea output;
	JLabel decryptStatus;
	
	final Object[][] keyword = {
		{"th", 1},
		{"er", 1},
		{"re", 1},
		{"ed", 1},
		{"nd", 1},
		{"ha", 1},
		{"en", 1},
		{"es", 1},
		{"nt", 1},
		{"ea", 1},
		{"ti", 1},
		{"st", 1},
		{"io", 1},
		{"le", 1},
		{"ou", 1},
		{"ar", 1},
		{"de", 1},
		{"rt", 1},
		{"ve", 1},
		{"on", 3},
		{"an", 3},
		{"of", 3},
		{"in", 3},
		{"at", 3},
		{"or", 3},
		{"it", 3},
		{"to", 3},
		{"is", 3},
		{"as", 3},
		{"he", 3},
		{"be", 3},
		{"so", 3},
		{"we", 3},
		{"by", 3},
		{"do", 3},
		{"if", 3},
		{"me", 3},
		{"my", 3},
		{"up", 3},
		{"go", 3},
		{"no", 3},
		{"us", 3},
		{"am", 3},
		{"the", 100},
		{"and", 100},
		{"for", 100},
		{"are", 100},
		{"but", 100},
		{"not", 100},
		{"you", 100},
		{"all", 100},
		{"any", 100},
		{"can", 100},
		{"had", 100},
		{"her", 100},
		{"was", 100},
		{"one", 100},
		{"our", 100},
		{"out", 100},
		{"day", 100},
		{"get", 100},
		{"has", 100},
		{"him", 100},
		{"his", 100},
		{"how", 100},
		{"man", 100},
		{"new", 100},
		{"now", 100},
		{"old", 100},
		{"see", 100},
		{"two", 100},
		{"way", 100},
		{"who", 100},
		{"boy", 100},
		{"did", 100},
		{"its", 100},
		{"let", 100},
		{"put", 100},
		{"say", 100},
		{"she", 100},
		{"too", 100},
		{"use", 100},
		{"when", 600},
		{"make", 600},
		{"like", 600},
		{"just", 600},
		{"what", 600},
		{"will", 600},
		{"your", 600},
		{"from", 600},
		{"they", 600},
		{"know", 600},
		{"want", 600},
		{"been", 600},
		{"good", 600},
		{"much", 600},
		{"some", 600},
		{"time", 600},
		{"that", 1000},
		{"with", 1000},
		{"have", 1000},
		{"this", 1000},
		{"would", 4000},
		{"there", 4000},
		{"their", 4000},
		{"about", 4000},
		{"which", 4000},
		{"could", 4000},
		{"other", 4000},
		{"think", 4000},
	};
	
	public CryptSolver() throws IOException {
		super("XOR Cryptography");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        
		//cryptor
        JLabel label1 = new JLabel("Input message:");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(label1, c);
        
		input = new JTextArea();
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(input);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        c.gridy = 1;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        add(scrollPane, c);
        
        JLabel label2 = new JLabel("Key (0-255):");
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.ipady = 5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(label2, c);
        
        JLabel label3 = new JLabel("Save as:");
        c.gridy = 4;
        add(label3, c);
        
        key = new  JTextField();
        c.gridy = 3;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(key, c);
        
        fileName1 = new  JTextField();
        c.gridy = 4;
        add(fileName1, c);
        
        crypt = new JButton("Crypt");
        crypt.addActionListener(this);
        crypt.setPreferredSize(new Dimension(100, 30));
        c.gridy = 5;
        c.gridx = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        add(crypt, c);
        
        cryptStatus = new JLabel("<html>Crypt your message<br>Follow the instructions</html>");
        c.gridy = 6;
        c.ipady = 25;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(cryptStatus, c);
        
        //decryptor
        JLabel label4 = new JLabel("Input File:");
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(label4, c);
        
        fileName2 = new  JTextField();
        c.gridy = 1;
        c.ipady = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(fileName2, c);
        
        decrypt = new JButton("Decrypt");
        decrypt.addActionListener(this);
        decrypt.setPreferredSize(new Dimension(100, 30));
        c.gridy = 2;
        c.ipady = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        add(decrypt, c);
        
        JLabel label5 = new JLabel("Secret Message:");
        c.gridy = 3;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(label5, c);
        
		output = new JTextArea();
		output.setEditable(false);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
        scrollPane = new JScrollPane(output);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        c.gridy = 4;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        add(scrollPane, c);
        
        decryptStatus = new JLabel("<html>Decrypt secret message<br>Enter the file name</html>");
        c.gridy = 6;
        c.ipady = 25;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(decryptStatus, c);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == crypt) {
	        if (input.getText().isEmpty()) {
				cryptStatus.setText("<html>Error<br>Please input secret message</html>");
	        }
	        else if (key.getText().isEmpty()) {
	        	cryptStatus.setText("<html>Error<br>Please input the key</html>");
	        }
	        else if (fileName1.getText().isEmpty()) {
	        	cryptStatus.setText("<html>Error<br>Please input file name</html>");
	        }
	        else {
	        	try {
	        		int temp = Integer.parseInt(key.getText());
	        		if (temp < 0 || temp > 255) {
	        			throw new NumberFormatException();
	        		}
	        		File f = new File(fileName1.getText());
	        		f.createNewFile();
	        		FileWriter file = new FileWriter(f);
	        		for (int i = 0; i < input.getText().length(); i++) {
	        			char c = (char) (input.getText().charAt(i) ^ temp);
	        			file.write(c);
	        		}
	        		file.close();
	        		
	        		cryptStatus.setText("<html>Crypt successful<br>Saved as "+fileName1.getText()+"</html>");
	        	}
	        	catch (NumberFormatException nfe) {
	        		cryptStatus.setText("<html>Error<br>Enter number between 0-255</html>");
	        	}
	        	catch (IOException e1) {}
	        }
		}
		else if (e.getSource() == decrypt) {
			if (fileName2.getText().isEmpty()) {
				decryptStatus.setText("<html>Error<br>Please input file name</html>");
	        }
	        else {
	        	try {
		    		File f = new File(fileName2.getText());
		    		FileReader file = new FileReader(f);
		    		char[] buff = new char[1000];
		    		file.read(buff);
		    		file.close();
		    		String[] result = solve(buff);
		    		output.setText(result[0]);
		    		
		    		decryptStatus.setText("<html>Decrypt Successful<br>Key used: "+result[1]+"</html>");
	        	}
	        	catch (FileNotFoundException fnfe) {
	        		decryptStatus.setText("<html>Error<br>File not found</html>");
		        } catch (IOException e1) {}
	        }
		}
	}
	
	public String[] solve(char[] buff) {
		int maxPoint = -1;
		int maxPenalty = -1;
		String result = new String();
		int keyUsed = -1;
		for (char key = 0; key < 256; key++) {
			int messageLength = buff.length - 1;
			while (buff[messageLength] == 0) {
				messageLength--;
			}
			String temp = new String();
			int penalty = 0;
			for (int i = 0; i <= messageLength; i++) {
				char c = (char) (buff[i] ^ key);
				temp += c;
				if (!(((c >= 'A') && (c <= 'Z'))
						|| ((c >= 'a') && (c <= 'z'))
						|| ((c >= '0') && (c <= '9'))
						|| (c == ',') || (c == '.') || (c == '!')
						|| (c == '=') || (c == '(') || (c == ')')
						|| (c == '&') || (c == '%') || (c == '$')
						|| (c == '?') || (c == '-') || (c == '@'))) {
					penalty++;
				}
			}
			int point = 0;
			for (Object[] o : keyword) {
				point += search(temp.toLowerCase(), (String) o[0]) * (int) o[1];
			}
			for (int i = 0; i <= messageLength; i++) {
				if (temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z' &&
						!(i == 0 || temp.charAt(i-1) == ' ')) {
					penalty++;
				}
			}
			if ((point > maxPoint) || (point == maxPoint && penalty < maxPenalty)) {
				result = temp;
				keyUsed = key;
				maxPoint = point;
				maxPenalty = penalty;
			}
		}
		String[] answer = new String[2];
		answer[0] = result;
		answer[1] = Integer.toString(keyUsed);
		
		return answer;
	}

    public int search(String txt, String pat) {
        int[] right;
        
        right = new int[256];
        for (int c = 0; c < 256; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    	
        int m = pat.length();
        int n = txt.length();
        int skip;
        int found = 0;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) {
            	found++;
            	skip = 1;
            }
        }
        return found;
    }
	
	public static void main(String[] args) {
		try {
			new CryptSolver();
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
	
}
