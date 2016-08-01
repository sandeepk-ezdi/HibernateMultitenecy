package com.ezdi.multitenency.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.ezdi.multitenency.bean.BaseTenantBean;
import com.ezdi.multitenency.bean.Patient;
import com.ezdi.multitenency.exception.TenantIdMisMatchException;
import com.ezdi.multitenency.exception.TenantIdMissingException;
import com.ezdi.multitenency.exception.TenantIdNotFoundException;
import com.ezdi.multitenency.util.HospitalConstants;

public class TenantInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	private static final String HOSPITAL_ID_PROPERTY="hospitalId";
	private static final String HOSPITAL_ID_COLUMN="HOSPITAL_ID";
	
	
	@Override
	public String onPrepareStatement(String sql) {
		if (sql.contains("insert into")){
			return sql;
		}
					
		if (!sql.contains("where")){
			throw new TenantIdMissingException("Oops!! Sorry Man!! You cannot event hit my DB without passing tenant_id in where clause");
		}
		if (sql.contains("where")) {
			// This block checks whether SQL where clause contains tenant_id or not.
			String test = sql.substring(sql.indexOf(" where "));
			if (!test.contains(HOSPITAL_ID_COLUMN)){
				throw new TenantIdMissingException("Oops!! You cannot access records without specifing tenent_id in where clause");

			}

		} 

		return super.onPrepareStatement(sql);
	}
	// Called when you delete an object, the object is not delete into database yet.
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof BaseTenantBean){
			for (int i = 0; i < propertyNames.length; i++) {
				if (HOSPITAL_ID_PROPERTY.equals(propertyNames[i])){

					if(state[i]==null){
						throw new TenantIdNotFoundException("Please set tenant_id. You Cannot Delete this Entity without specifing tenant_id");
					}

					if(!HospitalConstants.HCA.equals((String) state[i])){
						throw new TenantIdMisMatchException("Not a Valid Tenant_id. You do not have Permission to Delete for this Entity");
					}
				}
			}
		}
		super.onDelete(entity, id, state, propertyNames, types);
	}
	
	
	//Called when you save an object, the object is not save into database yet
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		if (entity instanceof BaseTenantBean){
			for (int i = 0; i < propertyNames.length; i++) {
				if (HOSPITAL_ID_PROPERTY.equals(propertyNames[i])){

					if(state[i]==null){
						throw new TenantIdNotFoundException("Please set tenant_id for this Object");
					}

					if(!HospitalConstants.HCA.equals((String) state[i])){
						throw new TenantIdMisMatchException("Not a Valid Tenant_id");
					}
				}
			}
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	
	/*
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof BaseTenantBean){
			for (int i = 0; i < propertyNames.length; i++) {
				if (HOSPITAL_ID_PROPERTY.equals(propertyNames[i])){

					if(state[i]==null){
						throw new TenantIdNotFoundException("Please set tenant_id for this Object");
					}

					if(!HospitalConstants.HCA.equals((String) state[i])){
						throw new TenantIdMisMatchException("Not a Valid Tenant_id. You do not Permission to get this Entity");
					}
				}
			}
		}
		return super.onLoad(entity, id, state, propertyNames, types);
	}
	*/
	
	//  Called when you update an object, the object is not update into database yet.
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		
		if (entity instanceof BaseTenantBean){
			for (int i = 0; i < propertyNames.length; i++) {
				if (HOSPITAL_ID_PROPERTY.equals(propertyNames[i])){

					if(currentState[i]==null){
						throw new TenantIdNotFoundException("Please set tenant_id. You Cannot Update any Entity without specifing tenant_id.");
					}

					if(!HospitalConstants.HCA.equals((String) currentState[i])){
						throw new TenantIdMisMatchException("Not a Valid Tenant_id. You do not have Permission to update for this tenant");
					}
				}
			}
		}
		
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}



}
