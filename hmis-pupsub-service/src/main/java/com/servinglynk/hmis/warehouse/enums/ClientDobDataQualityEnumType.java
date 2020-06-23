package com.servinglynk.hmis.warehouse.enums;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;



public class ClientDobDataQualityEnumType extends GenericEnumType<String, ClientDobDataQualityEnum> {
	public ClientDobDataQualityEnumType() throws NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		super(ClientDobDataQualityEnum.class, ClientDobDataQualityEnum.values(), "getValue", Types.OTHER);
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		return nullSafeGet(rs, names, owner);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		nullSafeSet(st, value, index);
		
	}
}
