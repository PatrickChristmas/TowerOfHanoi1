/**
 * 
 */
package org.bishopireton.hanoi;

import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 * @author Mrs. Kelly
 */
public class Hanoi {
	
    private HanoiGraphics hanoiGraphics;
	/**
	 * Instantiates the game
	 */
	public Hanoi() {
	
		hanoiGraphics = new HanoiGraphics(this);
	}
	
	
	// TODO Fill in these java docs
	
	/**
	 * creates a swing worker to allow the graphics to display while the  
	 * hanoi method runs in the background
	 * @param from  the index we are moving the disk from
	 * @param to the index we are moving the disk to
	 * @param spare   index of the spare (non-from or to)
	 * @param numDisks the number of disks that need to be moved
	 * @param button makes sure that the user cannot press the button when Hanoi is solving itself
	 * 
	 * 
	 */
	

	public void  createSwingWorker(int from, int to, int spare, int numDisks, JButton button) {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO UPDATE this call to include only the parameters that you need
				hanoi(numDisks, from, to, spare);
				return null;
			}

			@Override
			protected void done() {
				hanoiGraphics.setInAction(false);
				button.setEnabled(true);
			}
		};
		button.setEnabled(false);
		worker.execute();
	}

	// TODO Fill in these java docs
	
	/**
	 * a recursive method that moves a tower of disks to the spare spindle
	 * then moves the remaining disk to the to spindle, imitating the
	 * towers of hanoi challenge.Through recursion, the topDisks (numdisks -1) are moved from the from 
	 * to the spare and then the biggest disk (bottom disk) is moved from the from 
	 * to the to and then those topDisks are moved from the spare to the to recursively
	 *@param numDisks the number of disks that need to be moved
	 *@param from the index we are moving the disk from
	 *@param to the index we are moving the disk to
	 *@param spare index of the spare (non-from or to)
	 * @throws IllegalDiskMoveException if you cannot do a move 
	 */
	private void hanoi(int numDisks, int from, int to, int spare) throws IllegalDiskMoveException {
		// TODO Create this method, including choosing what parameters you need

		// if theres zero disks then it does not move. - base case 
		if (numDisks == 0) {
	        return; 
	    }
		// if theres only 1 disk then it moves the top to the other side. - base case 
	    else if (numDisks == 1) {
	    	// moves the biggest (only one) disk from the from to the to
	        hanoiGraphics.moveDisk(from, to);
	        return; 
	    }
		// if there are two or more disks. RECURSIVE CASE
	    else {	        
	        // all  smaller disks that needs to be moved before the larger disk
	        int topDisks = numDisks - 1;
	        
	        // Moves the topsdisks  from the from to the spare 
	        hanoi(topDisks, from, spare, to);
	        
	        // moves the biggest disk from the from to the to 
	        hanoiGraphics.moveDisk(from, to);
	        
	        // moves the top disk from the spare to the to
	        hanoi(topDisks, spare, to, from);

	    }
	}
	
	/**
	 * The main program for the Towers of Hanoi animation
	 * @param args unused
	 */
	public static void main(String[] args) {

		new Hanoi();
	}

}
