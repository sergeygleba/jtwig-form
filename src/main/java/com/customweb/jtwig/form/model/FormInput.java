package com.customweb.jtwig.form.model;

import java.io.IOException;

import com.customweb.jtwig.form.Utils;
import com.customweb.jtwig.lib.model.AttributeCollection;
import com.customweb.jtwig.lib.model.AttributeDefinitionCollection;
import com.customweb.jtwig.lib.model.EmptyAttributeDefinition;
import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.content.api.Renderable;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.render.RenderContext;

public class FormInput extends AbstractDataBoundFormElement<FormInput> {

	@Override
	public AttributeDefinitionCollection getAttributeDefinitions() {
		AttributeDefinitionCollection attributeDefinitions = super.getAttributeDefinitions();
		attributeDefinitions.add(new EmptyAttributeDefinition("disabled"));
		return attributeDefinitions;
	}

	@Override
	public Renderable compile(CompileContext context) throws CompileException {
		return new Compiled(this.getAttributeCollection());
	}

	private class Compiled extends AbstractDataBoundFormElementCompiled {
		protected Compiled(AttributeCollection attributeCollection) {
			super(null, attributeCollection);
		}

		public boolean isDisabled() {
			return this.getAttributeCollection().hasAttribute("disabled");
		}

		@Override
		public void render(RenderContext context) throws RenderException {
			try {
				context.write(("<input type=\"text\" name=\"" + this.getName(context) + "\" id=\"" + this.getId(context) + "\" value=\""
						+ this.escapeHtml(this.getDataValue(context, this.getPath()).toString()) + "\" "
						+ (this.isDisabled() ? "disabled=\"disabled\" " : "") + Utils.concatAttributes(this.getDynamicAttributes()) + " />")
						.getBytes());
			} catch (IOException e) {
			}
		}
	}
}