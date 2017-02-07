package encaixeme.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EnumType;

import org.apache.commons.lang3.StringUtils;

/**
 * Conversor genérico de tipos aleatórios para enumerator<br>
 * O contrário também acontece
 * 
 * @author Marcos Olavo S. Buganeme
 */

@FacesConverter(forClass = Enum.class)
public class EnumConverter implements Converter {

	@Override public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ( StringUtils.isNotBlank(value) ) {
			return EnumType.valueOf(value);
		}
		return null;
	}

	@Override public String getAsString(FacesContext context, UIComponent component, Object value) {
		if ( value != null && value instanceof EnumType) {
			return ((EnumType) value).name();
		}
		return null;
	}
}