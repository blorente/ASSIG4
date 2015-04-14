package tp.pr4.views.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import tp.pr4.control.Instruction;
import tp.pr4.control.Player;
import tp.pr4.control.WindowController;
import tp.pr4.logic.ComplicaRules;
import tp.pr4.logic.Connect4Rules;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.GravityRules;
import tp.pr4.logic.Observable;
import tp.pr4.logic.ReadOnlyBoard;

public class CtrlPanel extends JPanel implements GameObserver {
	
	private WindowController controller;
	private Instruction inst;
	private int col, row;
	
	public CtrlPanel (Observable<GameObserver> g, WindowController c) {
		this.controller = c;
		inst = Instruction.PLAY_C4;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		g.addObserver(this);
	}

	private void initGUI(final Counter player) {
		JPanel firstPanel = new JPanel(new BorderLayout());
		
		JButton undo = new JButton("Undo");
		undo.setIcon(new ImageIcon("src/tp/pr4/icons/undo.png"));
		undo.setPreferredSize(new Dimension (30, 30));
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				controller.undo();
				}
				catch (Exception ex) {
					onUndoNotPossible();
				}
			}		
		});
		
		JButton reset = new JButton("Reset");
		undo.setIcon(new ImageIcon("src/tp/pr4/icons/reset.png"));
		undo.setPreferredSize(new Dimension (30, 30));
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameRules rules;
				switch(inst) {
				case PLAY_C4:
					rules = new Connect4Rules();
					break;
				case PLAY_CO:
					rules = new ComplicaRules();
					break;
				case PLAY_G:
					rules = new GravityRules(col, row);
					break;
				default:
					rules = new Connect4Rules();
					break;
				}
				controller.reset(rules);
			}		
		});
		
		JButton random = new JButton("Random Move");
		undo.setIcon(new ImageIcon("src/tp/pr4/icons/random.png"));
		undo.setPreferredSize(new Dimension (30, 30));
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.randomMove(player);
			}		
		});
		
		firstPanel.add(undo, BorderLayout.LINE_START);
		firstPanel.add(reset, BorderLayout.CENTER);
		firstPanel.add(random, BorderLayout.LINE_END);
		
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
		String[] instructions = {inst.toString(Instruction.PLAY_C4), inst.toString(Instruction.PLAY_CO), inst.toString(Instruction.PLAY_G)};
		
		final JTextArea height = new JTextArea(5, 5);
		height.setText("Height");
		height.setEditable(true);
		height.setEnabled(false);
		height.setVisible(false);
		
		final JTextArea width = new JTextArea(5, 5);
		width.setText("Width");
		width.setEditable(true);
		width.setEnabled(false);
		width.setVisible(false);
		
		JComboBox list = new JComboBox(instructions);
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb= (JComboBox<String>)e.getSource();
				String instruction = (String)cb.getSelectedItem();
				inst = inst.StringToInstruction(instruction);
				if (inst == Instruction.PLAY_G){
					width.setVisible(true);
					width.setEnabled(true);
					height.setVisible(true);
					height.setEnabled(true);
					try {
						col = Integer.parseInt(width.getText());
						row = Integer.parseInt(height.getText());
					}
					catch (NumberFormatException e2) {
						//TODO show dialog
					}
				}
			}
		});
		
		JButton change = new JButton("Change");
		undo.setIcon(new ImageIcon("src/tp/pr4/icons/change.png"));
		undo.setPreferredSize(new Dimension (30, 30));
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (inst == Instruction.PLAY_G) {
					controller.changeGame(inst, col, row);
				}
				else {
					controller.changeGame(inst, 0, 0);
				}
			}		
		});
		
		secondPanel.add(list);
		secondPanel.add(width);
		secondPanel.add(height);
		secondPanel.add(change);
		
		this.add(firstPanel);
		this.add(secondPanel);
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		initGUI(player);
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		this.setEnabled(false);	
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		initGUI(player);
		
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		initGUI(nextPlayer);	
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttachToObserved(ReadOnlyBoard board, Counter turn) {
		// TODO Auto-generated method stub
		
	}
}
