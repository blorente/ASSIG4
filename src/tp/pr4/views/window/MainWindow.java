package tp.pr4.views.window;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tp.pr4.control.WindowController;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Observable;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

public class MainWindow extends JFrame implements GameObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WindowController controller;
	private Observable<GameObserver> game;
	private BoardPanel brdPanel;
	private CtrlPanel ctrlPanel;
	private JPanel quitPanel;
	
	public MainWindow(Observable<GameObserver> g, WindowController c) {	
		super("Assignment 4");
        this.setSize(700, 500);
		this.controller = c;
		this.game = g;
		initGUI();
		g.addObserver(this);
	}
	
	public void initGUI () {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		this.brdPanel = new BoardPanel(this.controller, this.game);//Nested Class
		this.ctrlPanel = new CtrlPanel(game, controller); // Nested Class
		this.quitPanel = createQuitPanel();
		
		mainPanel.add(this.brdPanel, BorderLayout.CENTER);
		mainPanel.add(this.ctrlPanel, BorderLayout.LINE_END);
		mainPanel.add(this.quitPanel, BorderLayout.PAGE_END);
		
		
		mainPanel.setVisible(true);
		this.setVisible(true);
        this.repaint();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {

	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {

	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttachToObserved(ReadOnlyBoard board, Counter turn) {
		
	}	
		
	
}
