package presentacion.view;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;

public class CardSwitcher {
    CardLayout layout;
    Container container;

    public CardSwitcher(Container container, CardLayout layout) {
        this.layout = layout;
        this.container = container;
        this.container.add(new JPanel(), "reset");
    }

    public void switchTo(String card) { layout.show(container, card); }
    
    public void reset() { layout.show(container, "reset"); }
}