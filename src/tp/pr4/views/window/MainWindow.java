package tp.pr4.views.window;


import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.control.WindowController;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Observable;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

public class MainWindow extends JFrame implements GameObserver {
	
	private WindowController controller;
	private Observable<GameObserver> game;
	
	public MainWindow(Observable<GameObserver> g, WindowController c) {	
		super("Assignment 4");
		this.controller = c;
		this.game = g;
		initGUI();
		g.addObserver(this);
	}
	
	public void initGUI () {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel boardPanel = new BoardPanel(this.controller, this.game);
		JPanel ctrlPanel = new CtrlPanel(game, controller); 
		JPanel quitPanel = createQuitPanel();
		
		mainPanel.add(boardPanel, BorderLayout.LINE_START);
		mainPanel.add(ctrlPanel, BorderLayout.LINE_END);
		mainPanel.add(quitPanel, BorderLayout.PAGE_END);

	}
	
	private JPanel createQuitPanel() {
		JPanel quitPanel = new JPanel();
		JButton quit = new JButton("quit");
		quit.setIcon(new ImageIcon("src/tp/pr4/icons/exit.png"));
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showOptionDialog(new JFrame(), 
						"Are sure tou want to quit?", "Quit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null, null, null);
				if (n== 0) {
					System.exit(0);
				}
			}		
		});
		quitPanel.add(quit);
		return quitPanel;
	}
	
	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		// TODO Auto-generated method stub
		
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
