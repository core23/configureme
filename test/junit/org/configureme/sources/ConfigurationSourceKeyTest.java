package org.configureme.sources;

import org.configureme.sources.ConfigurationSourceKey.Format;
import org.configureme.sources.ConfigurationSourceKey.Type;
import org.junit.Test;
import static junit.framework.Assert.*;

public class ConfigurationSourceKeyTest {
	@Test public void testConstructors(){
		ConfigurationSourceKey key1 = new ConfigurationSourceKey();
		key1.setFormat(Format.JSON);
		key1.setType(Type.FILE);
		key1.setName("fixture");
		
		ConfigurationSourceKey key1equal = new ConfigurationSourceKey(Type.FILE, Format.JSON, "fixture");

		assertEquals(key1, key1);
		assertEquals(key1, key1equal);

		ConfigurationSourceKey key2 = new ConfigurationSourceKey(Type.FILE, Format.JSON, "foo");
		ConfigurationSourceKey key3 = new ConfigurationSourceKey(Type.FILE, Format.PROPERTIES, "fixture");
		ConfigurationSourceKey key4 = new ConfigurationSourceKey(Type.FIXTURE, Format.JSON, "fixture");

		assertFalse(key1.equals(key2));
		assertFalse(key1.equals(key3));
		assertFalse(key1.equals(key4));
		
		assertEquals(key1.toString(), key1equal.toString());
		//check for null
		assertFalse(key1.equals(null));
		assertFalse(key1.equals(key1.toString()));
		
		//check for hashcode
		assertEquals(key1.hashCode(), key1equal.hashCode());
		assertFalse(key1.hashCode()==key2.hashCode());
		assertFalse(key1.hashCode()==key3.hashCode());
		assertFalse(key1.hashCode()==key4.hashCode());		
	}
}