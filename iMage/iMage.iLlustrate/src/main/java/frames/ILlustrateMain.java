package frames;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

// TODO create Action listener over ILlustrateMain

/**
 * The Main window for the iLlustrate GUI.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class ILlustrateMain extends JFrame {

	GroupLayout layout;
	JButton loadButton;
	JButton runButton;
	JPanel originPicture;
	JPanel resultPicture;
	JSlider iterations;
	JSlider samples;
	JTextField iterationsText;
	JTextField samplesText;

	public ILlustrateMain(ActionListener listener) {
		layout = new GroupLayout(this);
		loadButton = new JButton("load");
		loadButton.setActionCommand("load");
		loadButton.addActionListener(listener);
		runButton = new JButton("run");
		runButton.setActionCommand("run");
		runButton.addActionListener(listener);
		originPicture = new JPanel();
		resultPicture.setSize(150, 150);
		iterations = new JSlider(0, 2000);
		samples = new JSlider(0, 200);
		iterationsText = new JTextField("iterations (" + iterations.getValue() + ")");
		samplesText = new JTextField("samples (" + samples.getValue() + ")");
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(originPicture)
						.addComponent(iterationsText).addComponent(samplesText).addComponent(loadButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(resultPicture)
						.addComponent(iterations).addComponent(samples).addComponent(runButton)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(originPicture)
						.addComponent(resultPicture))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(iterationsText)
						.addComponent(iterations))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(samplesText)
						.addComponent(samples))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(loadButton)
						.addComponent(runButton)));
	}
}
