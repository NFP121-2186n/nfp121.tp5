package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class JPanelListe extends JPanel implements ActionListener, ItemListener {

	private JPanel cmd = new JPanel();
	private JLabel afficheur = new JLabel();
	private JTextField saisie = new JTextField();

	private JPanel panelBoutons = new JPanel();
	private JButton boutonRechercher = new JButton("rechercher");
	private JButton boutonRetirer = new JButton("retirer");

	private CheckboxGroup mode = new CheckboxGroup();
	private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
	private Checkbox ordreDecroissant = new Checkbox("d�croissant", mode, false);

	private JButton boutonOccurrences = new JButton("occurrence");

	private TextArea texte = new TextArea();

	private List<String> liste;
	private Map<String, Integer> occurrences;

	public JPanelListe(List<String> liste, Map<String, Integer> occurrences) {
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
		cmd.add(panelBoutons);

		// add event listener for sorting the list
		ordreCroissant.addItemListener(this);
		ordreDecroissant.addItemListener(this);
		
		// add event listener to occurences button
		boutonOccurrences.addActionListener(this);
		// add event listener(click) to retirer button
		boutonRetirer.addActionListener(this);
		
		
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

			} else if (ae.getSource() == boutonOccurrences) {
				Integer occur = occurrences.get(saisie.getText());
				if (occur != null)
					afficheur.setText(" -->  " + occur + " occurrence(s)");
				else
					afficheur.setText(" -->  ??? ");
			}
			texte.setText(liste.toString());

		} catch (Exception e) {
			afficheur.setText(e.toString());
		}
	}

	public void itemStateChanged(ItemEvent ie) {
		if (ie.getSource() == ordreCroissant) {
			Collections.sort(liste);
			texte.setText(liste.toString());
		} else if (ie.getSource() == ordreDecroissant) {
			Collections.sort(liste, new descendingList());
			texte.setText(liste.toString());
		}

		texte.setText(liste.toString());
	}

	private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
		boolean resultat = false;
		for (String s : liste) {
			if (s.startsWith(prefixe))
				resultat = true;
		}
		liste.removeIf(s -> (s.startsWith(prefixe)));

		texte.setText(liste.toString());
		afficheur.setText(resultat + " ");
		return resultat;
	}

	public class descendingList implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			return ((String) o2).compareTo((String) o1);

		}
	}
}