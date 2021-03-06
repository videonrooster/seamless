/*
 * Copyright (C) 2011 4th Line GmbH, Switzerland
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.seamless.gwt.component.client.binding;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBoxBase;
import org.seamless.gwt.theme.shared.client.ThemeStyle;
import org.seamless.gwt.validation.shared.ValidationError;

/**
 * @author Christian Bauer
 */
public class TextBoxIntegerViewProperty extends FormViewProperty<Integer> {

    protected TextBoxBase textBox;
    protected String errorStyleName;

    public TextBoxIntegerViewProperty(TextBoxBase textBox) {
        this(null, textBox, ThemeStyle.FormErrorField());
    }

    public TextBoxIntegerViewProperty(HasWidgets errorPanel, TextBoxBase textBox) {
        this(errorPanel, textBox, ThemeStyle.FormErrorField());
    }

    public TextBoxIntegerViewProperty(TextBoxBase textBox, String errorStyleName) {
        this(null, textBox, errorStyleName);
    }

    public TextBoxIntegerViewProperty(HasWidgets errorPanel, TextBoxBase textBox, String errorStyleName) {
        super(errorPanel);
        this.textBox = textBox;
        this.errorStyleName = errorStyleName;
    }

    @Override
    public void reset() {
        set(null);
    }

    @Override
    public void set(Integer value) {
        clearValidationError();
        textBox.setValue(value != null ? value.toString() : null);
    }

    @Override
    public Integer get() {
        try {
            return Integer.parseInt(textBox.getValue());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Override
    public void showValidationError(ValidationError error) {
        super.showValidationError(error);
        textBox.addStyleName(errorStyleName);
    }

    @Override
    public void clearValidationError() {
        super.clearValidationError();
        textBox.removeStyleName(errorStyleName);
    }

}
