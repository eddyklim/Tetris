/*
 * TCSS 305 – Fall 2017
 * Assignment 6 - Tetris
 */
package view;

import colorthemes.ColdTheme;
import colorthemes.MinimalTheme;
import colorthemes.SpringTheme;
import colorthemes.StandardTheme;
import colorthemes.WarmTheme;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 * The theme menu which allows the user to select a set of colors for the blocks.
 * 
 * @author eduardk
 * @version 7 Dec, 2017
 *
 */
public class ThemeMenu extends JMenu {
    /** The serialVersionUID. */
    private static final long serialVersionUID = 4422941366683149175L;
    /** The next piece preview panel. */
    private final PreviewBoard myPPanel;
    /** The tetris panel. */
    private final TetrisBoard myTPanel;

    /**
     * Default constructor that initializes the theme menu.
     * 
     * @param thePreview The preview panel.
     * @param theBoard The tetris panel.
     */
    public ThemeMenu(final PreviewBoard thePreview, final TetrisBoard theBoard) {
        super("Themes");
        myPPanel = thePreview;
        myTPanel = theBoard;
        setUpMenu();
    }

    /** Sets up the menu with different themes. */
    private void setUpMenu() {
        setMnemonic(KeyEvent.VK_T);
        
        final JMenuItem standardThemeItem = new JMenuItem("Default Theme");
        standardThemeItem.setMnemonic(KeyEvent.VK_D);
        standardThemeItem.addActionListener((theEvent) -> {
            myPPanel.updateTheme(new StandardTheme());
            myTPanel.updateTheme(new StandardTheme());
        });
        add(standardThemeItem);
        
        final JMenuItem warmThemeItem = new JMenuItem("Warm Theme");
        warmThemeItem.setMnemonic(KeyEvent.VK_W);
        warmThemeItem.addActionListener((theEvent) -> {
            myPPanel.updateTheme(new WarmTheme());
            myTPanel.updateTheme(new WarmTheme());
        });
        
        final JMenuItem coldThemeItem = new JMenuItem("Cold Theme");
        coldThemeItem.setMnemonic(KeyEvent.VK_C);
        coldThemeItem.addActionListener((theEvent) -> {
            myPPanel.updateTheme(new ColdTheme());
            myTPanel.updateTheme(new ColdTheme());
        });
        
        final JMenuItem springThemeItem = new JMenuItem("Spring Theme");
        springThemeItem.setMnemonic(KeyEvent.VK_S);
        springThemeItem.addActionListener((theEvent) -> {
            myPPanel.updateTheme(new SpringTheme());
            myTPanel.updateTheme(new SpringTheme());
        });
        
        final JMenuItem minimalThemeItem = new JMenuItem("Minimal Theme");
        minimalThemeItem.setMnemonic(KeyEvent.VK_M);
        minimalThemeItem.addActionListener((theEvent) -> {
            myPPanel.updateTheme(new MinimalTheme());
            myTPanel.updateTheme(new MinimalTheme());
        });
        
        add(standardThemeItem);
        add(warmThemeItem);
        add(coldThemeItem);
        add(springThemeItem);
        add(minimalThemeItem);
    }
}
