package org.androidpn.server.xmpp.handler;

import org.androidpn.server.service.NotificationService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.xmpp.session.Session;
import org.androidpn.server.xmpp.session.SessionManager;
import org.androidpn.server.xmpp.UnauthorizedException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.dom4j.Element;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;

public class IQSetAliasHandle extends IQHandler {
	 private static final String NAMESPACE = "androidpn:iq:setalias";
	 SessionManager sessionManager;
	 public IQSetAliasHandle(){
		 sessionManager=SessionManager.getInstance();
	 }
	@Override
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		 IQ reply = null;
	        ClientSession session = sessionManager.getSession(packet.getFrom());
	        if (session == null) {
	            log.error("Session not found for key " + packet.getFrom());
	            reply = IQ.createResultIQ(packet);
	            reply.setChildElement(packet.getChildElement().createCopy());
	            reply.setError(PacketError.Condition.internal_server_error);
	            return reply;
	        }
	        
	        ///
	        if(session.getStatus()==Session.STATUS_AUTHENTICATED){
	        	if(IQ.Type.set.equals(packet.getType())){
	        		Element element=packet.getChildElement();
	        		String username=element.elementText("username");
	        		String alias=element.elementText("alias");
//	        		notificationService.deleteNotification(uuid);
	        		System.out.println("alias:"+alias+"-username:"+username);
	        		if(username!=null&&!username.equals("")&&alias!=null&&!alias.equals("")){
	        			System.out.println("set username alias successfully!!!");
	        		sessionManager.setUserAlias(alias, username);
	        		}
	        	}
	        }
		
		
		return null;
	}

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

}
