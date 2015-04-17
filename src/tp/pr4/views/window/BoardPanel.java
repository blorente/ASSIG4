package tp.pr4.views.window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr4.control.WindowController;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.Observable;
import tp.pr4.logic.ReadOnlyBoard;


@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements GameObserver{
	
	private WindowController ctrl;
	private JButton[][] buttons;
	private GridBagConstraints c;
	private boolean active;
	
	public BoardPanel(WindowController ctrl, Observable<GameObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
	}

	private JButton createButton (final int row, final int col, final Counter player, Counter colour) {
		JButton button = new JButton();
		switch(colour) {
		case BLACK:
			button.setIcon(new ImageIcon("src/tp/pr4/icons/black.png"));
			break;
		case WHITE:
			button.setIcon(new ImageIcon("src/tp/pr4/icons/white.png"));
			break;
		default:
			break;		
		}
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (active) {
					ctrl.makeMove(row, col, player);
				}	
			}	
		});
		return button;		
	}

	public void UpdateView(Counter player, ReadOnlyBoard board) {
		this.buttons = new JButton[board.getWidth()][board.getHeight()];
		this.removeAll();
		
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				//this.buttons[i][j] = createButton(i, j,  player, board.getPosition(i + 1, j + 1));
				//setButtonDisabled(i,j, board.getPosition(i, j));
				this.c.gridy = j;
				this.add(createButton(i, j,  player, board.getPosition(i + 1, j + 1)), c);
			}
            this.c.gridx = i;
		}
        this.revalidate();
        this.active = true;
	}
	
	public void setButtonDisabled (int i, int j, Counter colour) {
		if (colour != Counter.EMPTY) {
			this.buttons[i][j].setEnabled(false);
		}
	}
	
	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		this.active = false;
		this.UpdateView(winner, board);
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		this.UpdateView(nextPlayer, board);	
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		this.UpdateView(nextPlayer, board);
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAttachToObserved(ReadOnlyBoard board, Counter turn) {
		this.UpdateView(turn, board);	
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		this.UpdateView(player, board);		
	}
}


