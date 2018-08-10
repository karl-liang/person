package com.sciov.lh.mybatis;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getSetterMethodName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.internal.util.JavaBeansUtil;


public class ServiceImplGenerator extends AbstractJavaGenerator {

	public static final String RESPONSE_DATA_CLASS_KEY = "reponseDataClass";

	Properties properties;

	public ServiceImplGenerator(Properties properties) {
		super();
		this.properties = properties;
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {

		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		progressCallback.startTask(String.format("Generating Service Interface for table %s", table.getAlias()));
		CommentGenerator commentGenerator = context.getCommentGenerator();

		String interfaceQualify = PluginUtil.getTargetPackage(properties) + ".I" + table.getDomainObjectName()
				+ "Service";
		String implQualify = PluginUtil.getTargetPackage(properties) + ".impl." + table.getDomainObjectName()
				+ "ServiceImpl";
		String beanPath = PluginUtil.getBeanTargetPackage(properties) + "." + table.getDomainObjectName();
		String mapperPath = PluginUtil.getMapperTargetPacakge(properties) + "." + table.getDomainObjectName()
				+ "Mapper";

		FullyQualifiedJavaType superInterface = new FullyQualifiedJavaType(interfaceQualify);
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(implQualify);
		FullyQualifiedJavaType beanType = new FullyQualifiedJavaType(beanPath);
		FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType(mapperPath);
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);

		TopLevelClass serviceImpl = new TopLevelClass(type);
		serviceImpl.setVisibility(JavaVisibility.PUBLIC);
		serviceImpl.addImportedType(interfaceQualify);
		serviceImpl.addImportedType(beanType);
		serviceImpl.addImportedType(mapperType);
		serviceImpl.addImportedType(voType);
		serviceImpl.addSuperInterface(superInterface);
		serviceImpl.addImportedType("org.springframework.stereotype.Service");
		
		
		serviceImpl.addAnnotation("@Service");
		commentGenerator.addJavaFileComment(serviceImpl);
		
		addMapperField(serviceImpl, mapperType);
		addLogField(serviceImpl);

		getMethodWithIdImpl(serviceImpl, introspectedTable,voType,mapperType);
		addMethodImplImpl(serviceImpl, introspectedTable,beanType,voType,mapperType);
		
		deleteMethodWithIdImpl(serviceImpl, introspectedTable,voType,mapperType);
		updateMethodImpl(serviceImpl, introspectedTable,beanType,voType,mapperType);
		listQueryImpl(serviceImpl, introspectedTable, beanType, voType,mapperType);

		
		addToVoImpl(serviceImpl, beanType);
		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(serviceImpl);
		return answer;
	}

	private void addLogField(TopLevelClass serviceImpl) {
		FullyQualifiedJavaType loggerType = new FullyQualifiedJavaType("org.slf4j.Logger");
		FullyQualifiedJavaType loggerFactoryType = new FullyQualifiedJavaType("org.slf4j.LoggerFactory");
		serviceImpl.addImportedType(loggerType);
		serviceImpl.addImportedType(loggerFactoryType);

		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		field.setType(loggerType);
		field.setName("logger");
		field.setFinal(true);
		field.setStatic(true);
		field.setInitializationString("LoggerFactory.getLogger("+serviceImpl.getType().getShortName()+".class);");
		
		serviceImpl.addField(field);
		
	}

	private String getTypeFieldName(FullyQualifiedJavaType type) {
		String inputString = type.getShortName();
		return formatFieldName(inputString);
	}
	
	private String formatFieldName(String inputString ) {
		String answer = null;
		if (Character.isUpperCase(inputString.charAt(0)) && !Character.isUpperCase(inputString.charAt(1))) {
			answer = inputString.substring(0, 1).toLowerCase(Locale.US) + inputString.substring(1);
		} else {
			answer = inputString;
		}
		return answer;
	}
	
	public Field addMapperField(TopLevelClass serviceImpl, FullyQualifiedJavaType mapperType) {

		serviceImpl.addImportedType("org.springframework.beans.factory.annotation.Autowired");

		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		field.setType(mapperType);
		field.setName(getTypeFieldName(mapperType));
		field.addAnnotation("@Autowired");
		serviceImpl.addField(field);
		
		return field;
	}


	private void getMethodWithIdImpl(TopLevelClass serviceImpl, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType voType,FullyQualifiedJavaType mapperType ) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(voType);
		method.setName("get" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "id");
		method.addParameter(idParameter);
		
		String mapperTypeFieldName = this.getTypeFieldName(mapperType);
		method.addBodyLine( "return toVo(" +mapperTypeFieldName + ".selectByPrimaryKey(id));");

		serviceImpl.addMethod(method);
	}

	private void addMethodImplImpl(TopLevelClass serviceImpl, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType beanType,
			FullyQualifiedJavaType voType,
			FullyQualifiedJavaType mapperType) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		serviceImpl.addImportedType(voType);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("add" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(voType, "vo");
		method.addParameter(idParameter);

		String mapperTypeFieldName = this.getTypeFieldName(mapperType);
		String beanVariableName = this.getTypeFieldName(beanType);
		method.addBodyLine(beanType.getShortName() + " " + beanVariableName + "= new " + beanType.getShortName() + "();");
		
		List<IntrospectedColumn> introspectedColumns;
		if (includePrimaryKeyColumns()) {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getAllColumns();
			} else {
				introspectedColumns = introspectedTable.getNonBLOBColumns();
			}
		} else {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getNonPrimaryKeyColumns();
			} else {
				introspectedColumns = introspectedTable.getBaseColumns();
			}
		}

		String rootClass = getRootClass();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			if (RootClassInfo.getInstance(rootClass, warnings).containsProperty(introspectedColumn)) {
				continue;
			}
			FullyQualifiedJavaType fqjt = introspectedColumn
	                .getFullyQualifiedJavaType();
			Method tmpMethod = getJavaBeansSetter(introspectedColumn);
			if((fqjt.equals(FullyQualifiedJavaType.getIntInstance()) || 
					fqjt.equals(PrimitiveTypeWrapper.getIntegerInstance())) && !introspectedColumn.getJavaProperty().equals("id")) {
				method.addBodyLine(beanVariableName+"."+tmpMethod.getName()+"(0);");
			}
			
			if(fqjt.equals(FullyQualifiedJavaType.getDateInstance()) && introspectedColumn.getJavaProperty().equals("gmtCreate")) {
				method.addBodyLine(beanVariableName+"."+tmpMethod.getName()+"(new Date());");
			}
			
		}
		
		method.addBodyLine("BeanUtils.copyProperties(vo, "+ beanVariableName +");");
		
		method.addBodyLine("return "+mapperTypeFieldName+".insert("+beanVariableName+");");
		
		method.addBodyLine("");
		
		serviceImpl.addMethod(method);
	}

	private void deleteMethodWithIdImpl(TopLevelClass serviceImpl, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType voType,FullyQualifiedJavaType mapperType) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("delete" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "id");
		method.addParameter(idParameter);
		String mapperTypeFieldName = this.getTypeFieldName(mapperType);
		method.addBodyLine( "return " +mapperTypeFieldName + ".deleteByPrimaryKey(id);");
		
		serviceImpl.addMethod(method);
	}

	
	private void updateMethodImpl(TopLevelClass serviceImpl, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType beanType,
			FullyQualifiedJavaType voType,
			FullyQualifiedJavaType mapperType) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("update" + table.getDomainObjectName());
		Parameter idParameter = new Parameter(voType, "vo");
		method.addParameter(idParameter);
		
		
	
		String mapperTypeFieldName = this.getTypeFieldName(mapperType);
		String beanVariableName = this.getTypeFieldName(beanType);
		method.addBodyLine(beanType.getShortName() + " " + beanVariableName + "= " + mapperTypeFieldName + ".selectByPrimaryKey(vo.getId());");
		method.addBodyLine( 
				"if(" + beanVariableName + " == null) {\r\n" + 
				"			return 0;\r\n" + 
				"		}");
		method.addBodyLine("BeanUtils.copyProperties(vo, "+ beanVariableName +");");
		
		List<IntrospectedColumn> introspectedColumns;
		if (includePrimaryKeyColumns()) {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getAllColumns();
			} else {
				introspectedColumns = introspectedTable.getNonBLOBColumns();
			}
		} else {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getNonPrimaryKeyColumns();
			} else {
				introspectedColumns = introspectedTable.getBaseColumns();
			}
		}

		String rootClass = getRootClass();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			if (RootClassInfo.getInstance(rootClass, warnings).containsProperty(introspectedColumn)) {
				continue;
			}
			FullyQualifiedJavaType fqjt = introspectedColumn
	                .getFullyQualifiedJavaType();
			Method tmpMethod = getJavaBeansSetter(introspectedColumn);
			if(fqjt.equals(FullyQualifiedJavaType.getDateInstance()) && introspectedColumn.getJavaProperty().equals("gmtModify")) {
				method.addBodyLine(beanVariableName+"."+tmpMethod.getName()+"(new Date());");
			}
			
		}
		
		
		method.addBodyLine("return "+mapperTypeFieldName+".updateByPrimaryKey("+beanVariableName+");");
		
		serviceImpl.addMethod(method);
	}

	private String getResponseDataClass() {
		return properties.getProperty(RESPONSE_DATA_CLASS_KEY);
	}

	private void listQueryImpl(TopLevelClass serviceImpl, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType beanType,
			FullyQualifiedJavaType voType,
			FullyQualifiedJavaType mapperType) {

		FullyQualifiedJavaType responseDataClassType = new FullyQualifiedJavaType(getResponseDataClass());
		FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
		listType.addTypeArgument(voType);
		responseDataClassType.addTypeArgument(listType);
		serviceImpl.addImportedType(responseDataClassType);

		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		serviceImpl.addImportedType("org.springframework.util.StringUtils");
		serviceImpl.addImportedType("com.github.pagehelper.Page");
		serviceImpl.addImportedType("com.github.pagehelper.PageHelper");
		serviceImpl.addImportedType("org.springframework.util.CollectionUtils");
		serviceImpl.addImportedType("java.util.ArrayList");
		
		serviceImpl.addImportedType(voType);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(responseDataClassType);
		method.setName("find" + table.getDomainObjectName() + "List");

		Parameter exampleParameter = new Parameter(voType, "vo");
		FullyQualifiedJavaType dateType = new FullyQualifiedJavaType("java.util.Date");
		serviceImpl.addImportedType(dateType);
		Parameter startDateParameter = new Parameter(dateType, "startDate");
		Parameter endDateParameter = new Parameter(dateType, "endDate");

		Parameter pageNumParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "pageNum");
		Parameter pageSizeParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "pageSize");

		method.addParameter(exampleParameter);
		method.addParameter(startDateParameter);
		method.addParameter(endDateParameter);
		method.addParameter(pageNumParameter);
		method.addParameter(pageSizeParameter);
		
		FullyQualifiedJavaType beanExampleType = new FullyQualifiedJavaType(beanType.getFullyQualifiedName()+"Example");
		serviceImpl.addImportedType(beanExampleType);
		method.addBodyLine(beanExampleType.getShortName() + " example = new "+beanExampleType.getShortName()+"();");
		method.addBodyLine(beanExampleType.getShortName() + ".Criteria criteria = example.createCriteria();");
		
	
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		for(IntrospectedColumn column:columns) {
			FullyQualifiedJavaType fieldType = column.getFullyQualifiedJavaType();
			String property = column.getJavaProperty();
			StringBuilder sb = new StringBuilder();
	        sb.append(property);
	        if (Character.isLowerCase(sb.charAt(0))) {
	            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
	                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
	            }
	        }
	        String upProperty = sb.toString();
			String getterName = JavaBeansUtil.getGetterMethodName(property, fieldType);
			if (fieldType.equals(FullyQualifiedJavaType.getStringInstance())) {
				method.addBodyLine(fieldType.getShortName() + " " + property + " = vo." +  getterName + "();\r\n"
						+ "		if (!StringUtils.isEmpty("+ property +")) {\r\n"
						+ "			criteria.and" + upProperty + "Like(\"%" + property+"%\");\r\n"
						+ "		}");
			}else if(fieldType.equals(FullyQualifiedJavaType.getIntInstance()) || 
					fieldType.equals(PrimitiveTypeWrapper.getIntegerInstance())) {
				method.addBodyLine(fieldType.getShortName() + " " + property + " = vo." +  getterName + "();\r\n"
						+ "		if (0 !="+ property +") {\r\n"
						+ "			criteria.and" + upProperty + "EqualTo(" + property+");\r\n"
						+ "		}");
			}else if(fieldType.equals(FullyQualifiedJavaType.getDateInstance()) && property.equals("gmtCreate")) { 
				method.addBodyLine("if(startDate != null) {\r\n" + 
						"			criteria.andGmtCreateGreaterThanOrEqualTo(startDate);\r\n" + 
						"		}\r\n" + 
						"		if(endDate != null) {\r\n" + 
						"			criteria.andGmtCreateLessThanOrEqualTo(endDate);\r\n" + 
						"		}");
				
			}
			
		}
		
		method.addBodyLine("Page page = PageHelper.startPage(pageNum, pageSize);\r\n" + 
				"		List<"+beanType.getShortName()+"> entityList = " + getTypeFieldName(mapperType) + ".selectByExample(example);");
		method.addBodyLine("List<"+ voType.getShortName() +"> voList = new ArrayList<>();\r\n" + 
				"		if (!CollectionUtils.isEmpty(entityList)) {\r\n" + 
				"			for ("+ beanType.getShortName() +" entity : entityList) {\r\n" + 
				"				voList.add(toVo(entity));\r\n"+
				"			}\r\n" + 
				"		}"
				);
		method.addBodyLine("//根据需要设置分页信息");
		method.addBodyLine("//PageInfo pageInfo = new PageInfo(pageNum, page.getPageSize(), page.getTotal());\r\n" + 
				"		return new ResponseData<>(voList);");
		
		serviceImpl.addMethod(method);
	}

	
	private void addToVoImpl(TopLevelClass serviceImpl, FullyQualifiedJavaType beanType) {
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);
		serviceImpl.addImportedType(voType);
		serviceImpl.addImportedType("org.springframework.beans.BeanUtils");
		

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(voType);
		method.setName("toVo");
		Parameter idParameter = new Parameter(beanType, "entity");
		method.addParameter(idParameter);
		
		String voVariableName = this.getTypeFieldName(voType);
		method.addBodyLine(voType.getShortName() + " " + voVariableName + "= new " + voType.getShortName() + "();");
		method.addBodyLine("BeanUtils.copyProperties(entity, "+ voVariableName +");");
		method.addBodyLine("return "+voVariableName+";");
		serviceImpl.addMethod(method);
	}
	
	private boolean includePrimaryKeyColumns() {
		return !introspectedTable.getRules().generatePrimaryKeyClass() && introspectedTable.hasPrimaryKeyColumns();
	}

	private boolean includeBLOBColumns() {
		return !introspectedTable.getRules().generateRecordWithBLOBsClass() && introspectedTable.hasBLOBColumns();
	}
}
