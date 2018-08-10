package com.sciov.lh.mybatis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

public class ControllerGenerator extends AbstractJavaGenerator {

	public static final String RESPONSE_DATA_CLASS_KEY = "reponseDataClass";

	Properties properties;

	public ControllerGenerator(Properties properties) {
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
		String controllerPath = PluginUtil.getControllerTargetPackage(properties) + "." + table.getDomainObjectName() +"Controller";
		

		FullyQualifiedJavaType serviceInterfaceType = new FullyQualifiedJavaType(interfaceQualify);
		FullyQualifiedJavaType serviceImplType = new FullyQualifiedJavaType(implQualify);
		String voParam = PluginUtil.getVofullQualif(properties, introspectedTable);
		FullyQualifiedJavaType voType = new FullyQualifiedJavaType(voParam);
		

		TopLevelClass serviceContorller = new TopLevelClass(controllerPath);
		serviceContorller.setVisibility(JavaVisibility.PUBLIC);
		serviceContorller.addImportedType(interfaceQualify);
		serviceContorller.addImportedType(voType);
		addCommonImport(serviceContorller);
		serviceContorller.addAnnotation("@RestController");
		serviceContorller.addAnnotation("@RequestMapping(\"/v1/"+ table.getDomainObjectName().toLowerCase() +"\")");
		serviceContorller.addAnnotation("@Api(tags = \""+ introspectedTable.getColumn("id").getRemarks() +"管理\")");
		addDateFormatField(serviceContorller);
		addLogField(serviceContorller);
		commentGenerator.addJavaFileComment(serviceContorller);
		
		addServiceField(serviceContorller, serviceInterfaceType, serviceImplType);

		getMethodWithId(serviceContorller,introspectedTable,serviceImplType,voType);
		addMethod(serviceContorller, introspectedTable,serviceImplType,voType);
		deleteMethodWithId(serviceContorller, introspectedTable,serviceImplType,voType);
		updateMethod(serviceContorller, introspectedTable,serviceImplType,voType);
		listQueryImpl(serviceContorller, introspectedTable,serviceImplType,voType);
		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(serviceContorller);
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
	
	private void addDateFormatField(TopLevelClass serviceContoller) {
		//public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		FullyQualifiedJavaType dateFormatType = new FullyQualifiedJavaType("java.text.SimpleDateFormat");
		serviceContoller.addImportedType(dateFormatType);

		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		field.setType(dateFormatType);
		field.setName("format");
		field.setStatic(true);
		field.setFinal(true);
		field.setInitializationString("new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
		
		serviceContoller.addField(field);
	}

	private void addCommonImport(TopLevelClass serviceImpl) {
		serviceImpl.addImportedType("org.springframework.web.bind.annotation.PathVariable");
		serviceImpl.addImportedType("org.springframework.web.bind.annotation.RequestBody");
		serviceImpl.addImportedType("org.springframework.web.bind.annotation.RequestMethod");
		serviceImpl.addImportedType("org.springframework.web.bind.annotation.RequestParam");
		serviceImpl.addImportedType("org.springframework.web.bind.annotation.RestController");
		serviceImpl.addImportedType("io.swagger.annotations.Api");
		serviceImpl.addImportedType("io.swagger.annotations.ApiImplicitParam");
		serviceImpl.addImportedType("io.swagger.annotations.ApiImplicitParams");
		serviceImpl.addImportedType("io.swagger.annotations.ApiOperation");
		serviceImpl.addImportedType("org.springframework.web.bind.annotation.RequestMapping");
		serviceImpl.addImportedType("org.springframework.http.MediaType");
		serviceImpl.addImportedType("javax.validation.Valid");
		serviceImpl.addImportedType("com.github.pagehelper.util.StringUtil");		
		serviceImpl.addImportedType("java.util.Date");
		serviceImpl.addImportedType("java.text.ParseException");
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
	
	private Field addServiceField(TopLevelClass serviceController, FullyQualifiedJavaType serviceType,FullyQualifiedJavaType serviceImpl) {

		serviceController.addImportedType("org.springframework.beans.factory.annotation.Autowired");

		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		field.setType(serviceType);
		field.setName(getTypeFieldName(serviceImpl));
		field.addAnnotation("@Autowired");
		serviceController.addField(field);
		
		return field;
	}

	private void getMethodWithId(TopLevelClass controller, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType serviceImpl,FullyQualifiedJavaType voType ) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		
		FullyQualifiedJavaType responseDataClassType = new FullyQualifiedJavaType(getResponseDataClass());
		responseDataClassType.addTypeArgument(voType);
		controller.addImportedType(responseDataClassType);
		
		String methodName = "get" + table.getDomainObjectName();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(responseDataClassType);
		method.setName(methodName);
		Parameter idParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "id");
		idParameter.addAnnotation("@PathVariable");
		
		method.addParameter(idParameter);
		method.addAnnotation("@ApiOperation(value = \"查询"+ introspectedTable.getColumn("id").getRemarks() +"\")");
		method.addAnnotation("@RequestMapping(value = \"/{id}\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)");
		
		String serviceFieldName = getTypeFieldName(serviceImpl);
		method.addBodyLine( " return new ResponseData<>("+ serviceFieldName + "." + methodName+"(id));");

		controller.addMethod(method);
	}

	private void addMethod(TopLevelClass controller, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType serviceType,
			FullyQualifiedJavaType voType) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();

		FullyQualifiedJavaType responseDataClassType = new FullyQualifiedJavaType(getResponseDataClass());
		responseDataClassType.addTypeArgument(new FullyQualifiedJavaType("java.lang.Void"));
		controller.addImportedType(responseDataClassType);
		
		String methodName = "add" + table.getDomainObjectName();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(responseDataClassType);
		method.setName(methodName);
		Parameter voParameter = new Parameter(voType, "vo");
		voParameter.addAnnotation("@Valid");
		voParameter.addAnnotation("@RequestBody");
		method.addParameter(voParameter);
		method.addAnnotation("@ApiOperation(value = \"新增"+ introspectedTable.getColumn("id").getRemarks() +"\")");
		method.addAnnotation("@RequestMapping(value = \"\", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)");
		
		String serviceFieldName = getTypeFieldName(serviceType);
		method.addBodyLine(  serviceFieldName + "." + methodName+"(vo);");		
		method.addBodyLine("return new ResponseData(200, \"添加成功\");");
		
		controller.addMethod(method);
	}

	private void deleteMethodWithId(TopLevelClass controller, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType serviceType,FullyQualifiedJavaType voType) {
		
		FullyQualifiedJavaType responseDataClassType = new FullyQualifiedJavaType(getResponseDataClass());
		responseDataClassType.addTypeArgument(new FullyQualifiedJavaType("java.lang.Void"));
		controller.addImportedType(responseDataClassType);
		
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String methodName = "delete" + table.getDomainObjectName();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(responseDataClassType);
		method.setName(methodName);
		Parameter idParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "id");
		idParameter.addAnnotation("@PathVariable");
		method.addParameter(idParameter);
		
		method.addAnnotation("@ApiOperation(value = \"删除"+ introspectedTable.getColumn("id").getRemarks() +"\")");
		method.addAnnotation("@RequestMapping(value = \"/{id}\", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)");
		String serviceFieldName = getTypeFieldName(serviceType);
		method.addBodyLine(  serviceFieldName + "." + methodName+"(id);");		
		method.addBodyLine("return new ResponseData(200, \"删除成功\");");
		
		controller.addMethod(method);
	}
	
	private void updateMethod(TopLevelClass controller, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType serviceType,
			FullyQualifiedJavaType voType) {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();

		FullyQualifiedJavaType responseDataClassType = new FullyQualifiedJavaType(getResponseDataClass());
		responseDataClassType.addTypeArgument(new FullyQualifiedJavaType("java.lang.Void"));
		controller.addImportedType(responseDataClassType);
		
		String methodName = "update" + table.getDomainObjectName();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(responseDataClassType);
		method.setName(methodName);
		Parameter voParameter = new Parameter(voType, "vo");
		voParameter.addAnnotation("@Valid");
		voParameter.addAnnotation("@RequestBody");
		method.addParameter(voParameter);
		
		method.addAnnotation("@ApiOperation(value = \"更新"+ introspectedTable.getColumn("id").getRemarks() +"\")");
		method.addAnnotation("@RequestMapping(value = \"\", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)");
		String serviceFieldName = getTypeFieldName(serviceType);
		method.addBodyLine(  serviceFieldName + "." + methodName+"(vo);");		
		method.addBodyLine("return new ResponseData(200, \"更新成功\");");
		
		controller.addMethod(method);
	}

	private String getResponseDataClass() {
		return properties.getProperty(RESPONSE_DATA_CLASS_KEY);
	}

	private void listQueryImpl(TopLevelClass controller, IntrospectedTable introspectedTable,
			FullyQualifiedJavaType serviceType,
			FullyQualifiedJavaType voType) {

		FullyQualifiedJavaType responseDataClassType = new FullyQualifiedJavaType(getResponseDataClass());
		FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
		listType.addTypeArgument(voType);
		responseDataClassType.addTypeArgument(listType);
		controller.addImportedType(responseDataClassType);

		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();

		controller.addImportedType(voType);
		String methodName = "find" + table.getDomainObjectName() + "List";
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(responseDataClassType);
		method.setName(methodName);

		String annotation = "@ApiOperation(\""+ introspectedTable.getColumn("id").getRemarks() +"列表\")\r\n" + 
				"	@ApiImplicitParams({\r\n" + 
				"	    @ApiImplicitParam(value = \"id型查询条件\", name = \"condtionId\", dataType = \"int\", paramType = \"query\", required = true),\r\n" + 
				"	    @ApiImplicitParam(value = \"字符串型查询条件\", name = \"conditionStr\", dataType = \"String\", paramType = \"query\", required = false),\r\n" + 
				"	    @ApiImplicitParam(value = \"页码\", name = \"pageNum\", dataType = \"int\", paramType = \"query\", defaultValue = \"1\"),\r\n" + 
				"	    @ApiImplicitParam(value = \"显示数量\", name = \"pageSize\", dataType = \"int\", paramType = \"query\", defaultValue = \"100000\")\r\n" + 
				"	})";
		method.addAnnotation(annotation);
		method.addAnnotation("@RequestMapping(value = \"/list\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)");
		
		Parameter conditionParameter =  new Parameter(FullyQualifiedJavaType.getStringInstance(),"conditionStr");
		conditionParameter.addAnnotation("@RequestParam(defaultValue = \"\")");
		Parameter startDateParameter = new Parameter(FullyQualifiedJavaType.getStringInstance(), "startDateStr");
		startDateParameter.addAnnotation("@RequestParam(required = false)");
		Parameter endDateParameter = new Parameter(FullyQualifiedJavaType.getStringInstance(), "endDateStr");
		endDateParameter.addAnnotation("@RequestParam(required = false)");
		Parameter conditionIdParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "condtionId");
		conditionIdParameter.addAnnotation("@RequestParam(defaultValue = \"0\")");
		Parameter pageNumParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "pageNum");
		Parameter pageSizeParameter = new Parameter(FullyQualifiedJavaType.getIntInstance(), "pageSize");

		method.addParameter(conditionParameter);
		method.addParameter(startDateParameter);
		method.addParameter(endDateParameter);
		method.addParameter(conditionIdParameter);
		method.addParameter(pageNumParameter);
		method.addParameter(pageSizeParameter);
		method.addBodyLine("Date startDate = null;");
		method.addBodyLine("if(!StringUtil.isEmpty(startDateStr)) {\r\n" + 
				"			startDate = format.parse(startDateStr);\r\n" + 
				"    	}");
		method.addBodyLine("Date endDate = null;");
		method.addBodyLine("if(!StringUtil.isEmpty(endDateStr)) {\r\n" + 
				"		endDate = format.parse(endDateStr);\r\n" + 
				"    	}");
		
		
		String voVariableName = this.getTypeFieldName(voType);
		method.addBodyLine(voType.getShortName() + " " + voVariableName + "= new " + voType.getShortName() + "();");
		String serviceFieldName = getTypeFieldName(serviceType);
		method.addBodyLine("return " + serviceFieldName + "." + methodName+"("+voVariableName+",startDate,endDate,pageNum,pageSize);");
		
		FullyQualifiedJavaType exception = new FullyQualifiedJavaType("java.text.ParseException");
		method.addException(exception );
		controller.addMethod(method);
	}

	
}
