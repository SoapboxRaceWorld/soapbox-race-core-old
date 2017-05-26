package com.soapboxrace.jaxb.xmpp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMPP_ResponseTypeFriendResult", propOrder = { "friendResult" })
@XmlRootElement(name = "response")
public class XMPP_ResponseTypeFriendResult {
	@XmlElement(name = "FriendResult", required = true)
	protected XMPP_FriendResultType friendResult;
	@XmlAttribute(name = "status")
	protected int status = 1;
	@XmlAttribute(name = "ticket")
	protected int ticket = 0;

	public XMPP_FriendResultType getFriendResult() {
		return friendResult;
	}

	public void setFriendResult(XMPP_FriendResultType friendResult) {
		this.friendResult = friendResult;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
}