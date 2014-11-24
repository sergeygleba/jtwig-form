package com.customweb.jtwig.form.model;

import java.io.IOException;

import com.customweb.jtwig.form.Utils;
import com.customweb.jtwig.lib.model.AttributeCollection;
import com.customweb.jtwig.lib.model.AttributeDefinitionCollection;
import com.customweb.jtwig.lib.model.EmptyAttributeDefinition;
import com.customweb.jtwig.lib.model.NamedAttributeDefinition;
import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.content.api.Renderable;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.render.RenderContext;

public class FormRadio extends AbstractFormCheckedElement<FormRadio> {

	@Override
	public AttributeDefinitionCollection getAttributeDefinitions() {
		AttributeDefinitionCollection attributeDefinitions = super.getAttributeDefinitions();
		attributeDefinitions.add(new NamedAttributeDefinition("value", false));
		attributeDefinitions.add(new NamedAttributeDefinition("label", false));
		attributeDefinitions.add(new EmptyAttributeDefinition("disabled"));
		return attributeDefinitions;
	}

	@Override
	public Renderable compile(CompileContext context) throws CompileException {
		return new Compiled(this.getAttributeCollection());
	}

	private class Compiled extends AbstractFormCheckedElementCompiled {
		protected Compiled(AttributeCollection attributeCollection) {
			super(null, attributeCollection);
		}

		public String getValue() {
			return this.getAttributeValue("value");
		}

		public String getLabel() {
			return this.getAttributeValue("label");
		}

		public boolean hasLabel() {
			return this.getAttributeCollection().hasAttribute("label");
		}

		public boolean isDisabled() {
			return this.getAttributeCollection().hasAttribute("disabled");
		}

		@Override
		public void render(RenderContext context) throws RenderException {
			try {
				if (this.hasLabel()) {
					context.write(("<label>").getBytes());
				}
				context.write(("<input type=\"radio\" name=\"" + this.getName(context) + "\" id=\"" + this.getId(context) + "\" value=\""
						+ this.escapeHtml(this.getValue()) + "\" " + (this.isOptionSelected(context, this.getValue()) ? "checked=\"checked\" " : "")
						+ (this.isDisabled() ? "disabled=\"disabled\" " : "") + Utils.concatAttributes(this.getDynamicAttributes()) + " />")
						.getBytes());
				if (this.hasLabel()) {
					context.write((" " + this.escapeHtml(this.getLabel()) + "</label>").getBytes());
				}
			} catch (IOException e) {
			}
		}
	}
}
