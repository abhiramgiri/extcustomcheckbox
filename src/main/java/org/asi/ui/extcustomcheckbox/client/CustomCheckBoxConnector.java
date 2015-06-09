package org.asi.ui.extcustomcheckbox.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.ui.checkbox.CheckBoxConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.checkbox.CheckBoxServerRpc;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(ExtCustomCheckBox.class)
public class CustomCheckBoxConnector extends CheckBoxConnector{
    @Override
    public void onClick(ClickEvent event) {        
       if (!isEnabled()) {
            return;
        }

        // We get click events also from the label text, which do not alter the
        // actual value. The server-side is only interested in real changes to
        // the state.
        if (getState().checked != getWidget().getValue()) {
            getState().checked = getWidget().getValue();

            // Add mouse details
            MouseEventDetails details = MouseEventDetailsBuilder
                    .buildMouseEventDetails(event.getNativeEvent(), getWidget()
                            .getElement());
            getRpcProxy(CheckBoxServerRpc.class).setChecked(getState().checked,
                    details);
            getRpcProxy(CustomCheckBoxServerRpc.class).clicked();
        }
    }
}
