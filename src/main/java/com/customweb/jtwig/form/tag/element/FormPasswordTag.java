package com.customweb.jtwig.form.tag.element;

import com.customweb.jtwig.form.tag.AbstractFormInputElementTag;
import com.customweb.jtwig.lib.attribute.model.AttributeCollection;
import com.customweb.jtwig.lib.attribute.model.definition.AttributeDefinitionCollection;
import com.customweb.jtwig.lib.attribute.model.definition.EmptyAttributeDefinition;
import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.content.api.Renderable;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.ParseException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.exception.ResourceException;
import com.lyncode.jtwig.render.RenderContext;
import com.lyncode.jtwig.resource.JtwigResource;

public class FormPasswordTag extends AbstractFormInputElementTag<FormPasswordTag> {

	@Override
	public AttributeDefinitionCollection getAttributeDefinitions() {
		AttributeDefinitionCollection attributeDefinitions = super.getAttributeDefinitions();
		attributeDefinitions.add(new EmptyAttributeDefinition("showpassword"));
		attributeDefinitions.getDynamicAttributeDefinition().addDisallowedKey("type");
		return attributeDefinitions;
	}
	
	@Override
	public Renderable compile(CompileContext context) throws CompileException {
		this.getAttributeCollection().compile(context);
		try {
			JtwigResource resource = this.retrieveResource(context, "form/password");
			return new Compiled(context.parse(resource).compile(context), this.getAttributeCollection());
		} catch (ParseException | ResourceException e) {
			throw new CompileException(e);
		}
	}

	private class Compiled extends AbstractFormInputElementTag<FormPasswordTag>.Compiled {
		protected Compiled(Renderable block, AttributeCollection attributeCollection) {
			super(block, null, attributeCollection);
		}
		
		@Override
		public void prepareContext(RenderContext context) throws RenderException {
			context.with("password", new Data(context, this.getAttributeCollection()));
		}
	}
	
	protected class Data extends AbstractFormInputElementTag<FormPasswordTag>.Data {
		protected Data(RenderContext context, AttributeCollection attributeCollection) {
			super(context, attributeCollection);
		}
		
		public boolean isShowPassword() {
			return this.getAttributeCollection().hasAttribute("showpassword");
		}
	}
}
