package er.r2d2w.components.relationships;

import com.webobjects.appserver.WOContext;

import er.directtoweb.components.relationships.ERD2WEditToManyRelationship;

public class R2D2WEditToManyRelationship extends ERD2WEditToManyRelationship {
    public R2D2WEditToManyRelationship(WOContext context) {
        super(context);
    }

	private String labelID;

	public void reset() {
		labelID = null;
		super.reset();
	}
	
	/**
	 * @return the labelID
	 */
	public String labelID() {
		if(labelID == null) {
			labelID = "id" + context().elementID();
		}
		return labelID;
	}

}