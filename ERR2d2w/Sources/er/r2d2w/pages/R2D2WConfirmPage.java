package er.r2d2w.pages;

import com.webobjects.appserver.WOContext;

/** 
 * Unless you need the special shouldProvideConfirmMessage, one should use R2D2WMessagePage instead 
 */
public class R2D2WConfirmPage extends R2D2WMessagePage {
	/**
	 * Do I need to update serialVersionUID?
	 * See section 5.6 <cite>Type Changes Affecting Serialization</cite> on page 51 of the 
	 * <a href="http://java.sun.com/j2se/1.4/pdf/serial-spec.pdf">Java Object Serialization Spec</a>
	 */
	private static final long serialVersionUID = 1L;

    public R2D2WConfirmPage(WOContext context) {
        super(context);
    }
}