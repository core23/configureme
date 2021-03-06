package org.configureme.repository;

import org.configureme.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Value of a composite attribute. Maps names of the child attributes to their values.
 * Each child attribute can have arbitrary type.
 *
 * @author another
 * @version $Id: $Id
 */
public class CompositeValue implements Value {
	/**
	 * Configuration of the composite attribute value.
	 */
	private final ConfigurationImpl config;

	/**
	 * Constructs new composite attribute value.
	 *
	 * @param name name of the attribute
	 * @param value map of name/value pairs of child attributes.
	 */
	public CompositeValue(final String name, final Map<String, Value> value) {
		config = new ConfigurationImpl(name);
		for (final Map.Entry<String, Value> attr : value.entrySet())
			config.setAttribute(attr.getKey(), attr.getValue());
	}

	/**
	 * Gets configuration of the composite attribute.
	 *
	 * @return configuration of the composite attribute.
	 */
	public Configuration get() {
		return config;
	}

	@Override
	public Object getRaw() {
		final Map<String, Object> raw = new HashMap<>(config.getEntries().size());
		for (final Map.Entry<String, Value> entry : config.getEntries())
			raw.put(entry.getKey(), entry.getValue().getRaw());

		return raw;
	}

	@Override
	public String toString() {
		return String.valueOf(config);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((config == null) ? 0 : config.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CompositeValue)) {
			return false;
		}
		CompositeValue other = (CompositeValue) obj;
		if (config == null) {
			if (other.config != null) {
				return false;
			}
		} else if (!config.equals(other.config)) {
			return false;
		}
		return true;
	}

}
