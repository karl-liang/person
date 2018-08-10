package com.sciov.lh.mybatis;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.Context;


public class ServiceInterfaceGenerator extends AbstractJavaGenerator {

	public static final String RESPONSE_DATA_CLASS_KEY = "reponseDataClass";
	
	
	Properties properties;
	
	public ServiceInterfaceGenerator(Properties properties) {
		super();
		this.properties = properties;
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {

		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		progressCallback.startTask(String.format("Generating Service Interface for table %s", table.getAlias())); 
		CommentGenerator commentGenerator = context.getCommentGenerator();

		
		String fullQualif = PluginUtil.getTargetPackage(properties) + ".I" + table.getDomainObjectName() + "Service";
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(fullQualif);

		Interface serviceInterface = new Interface(type);
		serviceInterface.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(serviceInterface);


		getMethodWithId(serviceInterface, context, introspectedTable);
		addMethod(serviceInterface, context, introspectedTable);
		deleteMethodWithId(serviceInterface, context, introspectedTable);
		updateMethod(serviceInterface, context, introspectedTable);
		addListQuery(serviceInterface, context, introspectedTable);
		
		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(serviceInterface);
		return answer;
	}

	private void getMethodWithId(Interface serviceInterface, Context context, IntrospectedTable introspectedTable) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);
		serviceInterface.addImportedType(voType);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(voType);
		method.setName("get" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "id");
		method.addParameter(idParameter);

		serviceInterface.addMethod(method);
	}
	
	private void addMethod(Interface serviceInterface, Context context, IntrospectedTable introspectedTable) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);
		serviceInterface.addImportedType(voType);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("add" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(voType, "vo");
		method.addParameter(idParameter);

		serviceInterface.addMethod(method);
	}
	
	private void deleteMethodWithId(Interface serviceInterface, Context context, IntrospectedTable introspectedTable) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);
		serviceInterface.addImportedType(voType);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("delete" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "id");
		method.addParameter(idParameter);

		serviceInterface.addMethod(method);
	}
	
	private void updateMethod(Interface serviceInterface, Context context, IntrospectedTable introspectedTable) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);
		serviceInterface.addImportedType(voType);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("update" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(voType, "vo");
		method.addParameter(idParameter);

		serviceInterface.addMethod(method);
	}
	
	private String getResponseDataClass() {
		return properties.getProperty(RESPONSE_DATA_CLASS_KEY);
	}
	
	
	private void addListQuery(Interface serviceInterface, Context context, IntrospectedTable introspectedTable) {
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);
		
		FullyQualifiedJavaType responseDataClassType = new  FullyQualifiedJavaType(getResponseDataClass());
		FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
		listType.addTypeArgument(voType);
		responseDataClassType.addTypeArgument(listType);
		serviceInterface.addImportedType(responseDataClassType);
		
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
	
		serviceInterface.addImportedType(voType);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(responseDataClassType);
		method.setName("find" + table.getDomainObjectName() + "List");
				
		Parameter exampleParameter = new Parameter(voType, "example");
		FullyQualifiedJavaType dateType = new FullyQualifiedJavaType("java.util.Date");
		serviceInterface.addImportedType(dateType);
		Parameter startDateParameter = new Parameter(dateType, "startDate");
		Parameter endDateParameter = new Parameter(dateType, "endDate");
		
		Parameter pageNumParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "pageNum");
		Parameter pageSizeParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "pageSize");
		
		method.addParameter(exampleParameter);
		method.addParameter(startDateParameter);
		method.addParameter(endDateParameter);
		method.addParameter(pageNumParameter);
		method.addParameter(pageSizeParameter);

		serviceInterface.addMethod(method);
	}
	
}
