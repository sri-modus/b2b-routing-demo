package com.cisco.gslo.transformer;


import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.module.xml.transformer.XsltTransformer;
import org.mule.transformer.AbstractMessageTransformer;

public class DynamicXSLTTransformer extends AbstractMessageTransformer {
	
	private String variableName;
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		System.out.println("before");
		String xslt = message.getInvocationProperty(variableName, null);
		System.out.println("after");
		if (xslt == null) {
			throw new TransformerException(MessageFactory.createStaticMessage("Transformer not configured, properly, missing xslt variableName"));
		}
		
		XsltTransformer delegate = new XsltTransformer();
		delegate.setXslt(xslt);
		
		
				
		return delegate.transform(message, outputEncoding);
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	

}
