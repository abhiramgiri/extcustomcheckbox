package org.asi.ui.extcustomcheckbox;

import com.vaadin.data.Property;
import com.vaadin.shared.EventId;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import java.io.Serializable;
import java.lang.reflect.Method;
import org.asi.ui.extcustomcheckbox.client.CustomCheckBoxServerRpc;

// This is the server-side UI component that provides public API 
// for ExtCustomCheckBox
public class ExtCustomCheckBox extends CheckBox {
    private CustomCheckBoxServerRpc rpc1 = new CustomCheckBoxServerRpc() {
        
        @Override
        public void clicked() {
            fireEvent(new ClickEvent(ExtCustomCheckBox.this));
        }
    };
   
    /**
     * Creates a new checkbox.
     */
    public ExtCustomCheckBox() {
        super();
        registerRpc(rpc1);
    }
    
    /**
     * Creates a new checkbox with a set caption.
     * 
     * @param caption
     *            the Checkbox caption.
     */
    public ExtCustomCheckBox(String caption) {
        this();
        setCaption(caption);
    }

    /**
     * Creates a new checkbox with a caption and a set initial state.
     * 
     * @param caption
     *            the caption of the checkbox
     * @param initialState
     *            the initial state of the checkbox
     */
    public ExtCustomCheckBox(String caption, boolean initialState) {
        this(caption);
        setValue(initialState);
    }

    /**
     * Creates a new checkbox that is connected to a boolean property.
     * 
     * @param state
     *            the Initial state of the switch-button.
     * @param dataSource
     */
    public ExtCustomCheckBox(String caption, Property<?> dataSource) {
        this(caption);
        setPropertyDataSource(dataSource);
    }

    public static class ClickEvent extends Component.Event {

        public static final Method CLICK_METHOD;

        static {
            try {
                CLICK_METHOD = ClickListener.class
                        .getDeclaredMethod(EventId.CLICK_EVENT_IDENTIFIER,
                                new Class[]{ClickEvent.class});
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                throw new java.lang.RuntimeException(e);
            }
        }

        public ClickEvent(Component source) {
            super(source);
        }
    }

    public interface ClickListener extends Serializable {

        public void click(ClickEvent event);
    }

    /**
     * Adds a click listener to the TextField.
     *
     * @param listener The listener to attach to the TextField
     */
    public void addClickListener(ClickListener listener) {
        addListener(EventId.CLICK_EVENT_IDENTIFIER,
                ClickEvent.class, listener,
                ClickEvent.CLICK_METHOD);
    }

    /**
     * Removes a click listener from the TextField.
     *
     * @param listener The listener to remove
     */
    public void removeClickListener(ClickListener listener) {
        removeListener(EventId.CLICK_EVENT_IDENTIFIER,
                ClickEvent.class, listener);
    }

    /**
     * @deprecated As of 7.0, replaced by
     * {@link #addClickListener(ClickListener)}
     */
    @Deprecated
    public void addListener(ClickListener listener) {
        addClickListener(listener);
    }

    /**
     * @deprecated As of 7.0, replaced by
     * {@link #removeClickListener(ClickListener)}
     */
    @Deprecated
    public void removeListener(ClickListener listener) {
        removeClickListener(listener);
    }
}
