package view;
import java.awt.CardLayout;
import java.awt.Container;

public class CardSwitcher {
    CardLayout layout;
    Container container;

    public CardSwitcher(Container container, CardLayout layout) {
        this.layout = layout;
        this.container = container;
    }

    public void switchTo(String card) { layout.show(container, card); }
    
    public void reset() { layout.first(container); }
}