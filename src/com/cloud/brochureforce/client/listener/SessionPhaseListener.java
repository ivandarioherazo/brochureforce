package com.cloud.brochureforce.client.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * 
 * PhaseListener to ensure the HttpSession data is written to the datastore.
 * <p>
 * If properly configured, an application deployed on the
 * <a href="http://developers.google.com/appengine">Google App Engine<a>
 * platform can leverage HttpSessions to store data between requests. Session
 * data is stored in the datastore and memcache is also used for speed. Session
 * data is persisted at the <strong>end</strong> of the request.
 * </p>
 * <p>
 * The App Engine session implementation will not recognize if properties of
 * objects stored in the session are changed which is why we have this
 * <code>PhaseListener</code> which will modify a session attribute with the
 * current date and time (in milliseconds) at the end of every phase.
 * </p>
 * 
 * @author LIA Solutions SAS.
 * @version 2016-01-06, 1.0
 * @see <a href=
 *      "https://developers.google.com/appengine/docs/java/config/appconfig#Java_appengine_web_xml_Enabling_sessions">
 *      appengine-web.xml Reference</a>
 * @see <a href=
 *      "http://stackoverflow.com/questions/19259457/session-lost-in-google-app-engine-using-jsf">
 *      Session lost in Google App Engine using JSF</a>
 */
public class SessionPhaseListener implements PhaseListener {

	/** Used in object serialization */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CURRENT_TIME",
				System.currentTimeMillis());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}