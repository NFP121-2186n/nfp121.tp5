package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import question2.JPanelListe.descendingList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {
	Originator originator = new Originator();
	Caretaker caretaker = new Caretaker();
    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);
    
    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;

    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);
   
    	// add event listener for sorting the list
		ordreCroissant.addItemListener(this);
		ordreDecroissant.addItemListener(this);
		// create memento pattern

		// add event listener to occurences button
		boutonOccurrences.addActionListener(this);
		// add event listener(click) to retirer button
		boutonRetirer.addActionListener(this);
	    boutonAnnuler.addActionListener(this);
		boutonAnnuler.setEnabled(caretaker.hasMemento());
		if (liste != null && occurrences != null) {
			afficheur.setText(liste.getClass().getName() + " et " + occurrences.getClass().getName());
			texte.setText(liste.toString());
		} else {
			texte.setText("la classe Chapitre2CoreJava semble incomplète");
		}

		setLayout(new BorderLayout());

		add(cmd, "North");
		add(texte, "Center");

		boutonRechercher.addActionListener(this);

		// press enter event
		InputMap im = boutonRechercher.getInputMap();
		im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		im.put(KeyStroke.getKeyStroke("released ENTER"), "released");
		// à compléter;

	}

	/** ne pas modifier les affichages, les classes de tests en ont besoin ... */
	public void actionPerformed(ActionEvent ae) {
		try {
			boolean res = false;
			if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
				res = liste.contains(saisie.getText());
				Integer occur = occurrences.get(saisie.getText());
				afficheur.setText("résultat de la recherche de : " + saisie.getText() + " -->  " + res);
			} else if (ae.getSource() == boutonRetirer) {
				res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
				afficheur.setText("résultat du retrait de tous les éléments commençant par -->  " + saisie.getText()
						+ " : " + res);

			} else if (ae.getSource() == boutonAnnuler) {
				annulerAction();
				} else if (ae.getSource() == boutonOccurrences) {
				Integer occur = occurrences.get(saisie.getText());
				if (occur != null)
					afficheur.setText(" -->  " + occur + " occurrence(s)");
				else
					afficheur.setText(" -->  ??? ");
			}
			texte.setText(liste.toString());
//			
		} catch (Exception e) {
			afficheur.setText(e.toString());
		}
	}

	private void annulerAction() {
//		 TODO Auto-generated method stub
		Memento mem = caretaker.getMemento();
		liste = originator.restoreListFromMemento(mem);
		Map<String, Integer> prevOccurences = originator.restoreMapFromMemento(mem);
		this.occurrences = prevOccurences;
		boutonAnnuler.setEnabled(caretaker.hasMemento());
		texte.setText(liste.toString());

	}

	public void itemStateChanged(ItemEvent ie) {
		if (ie.getSource() == ordreCroissant) {
			originator.set(liste, occurrences);
			Memento memo = originator.storeInMemento();
			caretaker.addMemento(memo);
			boutonAnnuler.setEnabled(true);
			Collections.sort(this.liste);
			texte.setText(liste.toString());
		} else if (ie.getSource() == ordreDecroissant) {
			originator.set(liste, occurrences);
			caretaker.addMemento( originator.storeInMemento() );
			boutonAnnuler.setEnabled(caretaker.hasMemento());
			Collections.sort(liste, new descendingList());
			texte.setText(liste.toString());
		}

		texte.setText(liste.toString());
	}

	private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
		boolean resultat = false;
		for(String s : liste) {
			if (s.startsWith(prefixe)) {
				resultat = true;
				originator.set(liste, occurrences);
				caretaker.addMemento( originator.storeInMemento());
				boutonAnnuler.setEnabled(caretaker.hasMemento());
			}
		}
		liste.removeIf(str -> (str.startsWith(prefixe)));
		texte.setText(liste.toString());
		afficheur.setText(resultat + " ");
		return resultat;
	}

	private class descendingList implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			return ((String) o2).compareTo((String) o1);

		}
	}
	
}